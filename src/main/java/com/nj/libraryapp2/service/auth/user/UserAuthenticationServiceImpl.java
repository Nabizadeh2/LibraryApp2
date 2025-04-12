package com.nj.libraryapp2.service.auth.user;

import com.nj.libraryapp2.dao.repository.UserRepository;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;
import com.nj.libraryapp2.dto.user.UserRequestDto;
import com.nj.libraryapp2.mapper.UserMapper;
import com.nj.libraryapp2.service.jwt.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserAuthenticationServiceImpl implements UserAuthenticationService{
    UserMapper userMapper;

    UserRepository userRepository;

    PasswordEncoder encoder;

    JwtService jwtService;

    AuthenticationManager authManager;

    @Override
    public JwtAuthenticationResponseDto register(UserRequestDto request) {
        //Register the user to repository and generate a token
        var user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwtToken);
    }

    @Override
    public JwtAuthenticationResponseDto login(JwtAuthenticationRequestDto request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwtToken);
    }
}
