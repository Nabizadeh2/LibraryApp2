package com.nj.libraryapp2.service.auth.admin;

import com.nj.libraryapp2.dao.repository.UserRepository;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;
import com.nj.libraryapp2.service.jwt.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService{
    UserRepository userRepository;

    JwtService jwtService;

    AuthenticationManager authManager;
    @Override
    public JwtAuthenticationResponseDto login(JwtAuthenticationRequestDto request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwtToken);
    }
}
