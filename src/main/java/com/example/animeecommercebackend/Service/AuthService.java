package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.LoginRequestDto;
import com.example.animeecommercebackend.Dto.Request.RegisterRequestDto;
import com.example.animeecommercebackend.Dto.Response.LoginResponseDto;
import com.example.animeecommercebackend.Dto.Response.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto dto);

    LoginResponseDto login(LoginRequestDto dto);
}