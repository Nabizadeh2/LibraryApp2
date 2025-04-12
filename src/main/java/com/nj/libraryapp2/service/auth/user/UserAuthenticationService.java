package com.nj.libraryapp2.service.auth.user;

import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;
import com.nj.libraryapp2.dto.user.UserRequestDto;

public interface UserAuthenticationService {
    JwtAuthenticationResponseDto register(UserRequestDto request);

    JwtAuthenticationResponseDto login(JwtAuthenticationRequestDto request);

}
