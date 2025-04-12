package com.nj.libraryapp2.service.user;

import com.nj.libraryapp2.dto.user.UserRequestDto;
import com.nj.libraryapp2.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUser();
    UserResponseDto getUserById(Long id);
    void updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
