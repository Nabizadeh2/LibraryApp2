package com.nj.libraryapp2.controller;

import com.nj.libraryapp2.dto.jwt.JwtAuthenticationRequestDto;
import com.nj.libraryapp2.dto.jwt.JwtAuthenticationResponseDto;
import com.nj.libraryapp2.dto.user.UserRequestDto;
import com.nj.libraryapp2.service.auth.user.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class UserAuthController {
    private final UserAuthenticationService authService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public ResponseEntity<JwtAuthenticationResponseDto> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody JwtAuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
