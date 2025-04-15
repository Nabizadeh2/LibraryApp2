package com.nj.libraryapp2.service.user;

import com.nj.libraryapp2.dao.entity.UserEntity;
import com.nj.libraryapp2.dao.repository.UserRepository;
import com.nj.libraryapp2.dto.user.UserRequestDto;
import com.nj.libraryapp2.dto.user.UserResponseDto;
import com.nj.libraryapp2.exception.UserNotFoundException;
import com.nj.libraryapp2.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Qualifier("userMapper")
    UserMapper mapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id " + id));
        return mapper.toDto(user);
    }

    @Override
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        UserEntity user = mapper.toEntity(userRequestDto);
        user.setId(id);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id " + id));
        userRepository.delete(user);
    }

}
