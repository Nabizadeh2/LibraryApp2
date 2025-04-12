package com.nj.libraryapp2.controller;

import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;
import com.nj.libraryapp2.service.auth.admin.AdminAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
public class AdminAuthController {
    AdminAuthenticationService authService;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody JwtAuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
