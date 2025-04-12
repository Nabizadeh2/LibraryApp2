package com.nj.libraryapp2.service.auth.admin;

import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;

public interface AdminAuthenticationService {
    JwtAuthenticationResponseDto login(JwtAuthenticationRequestDto request);
}
