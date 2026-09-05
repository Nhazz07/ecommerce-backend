package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.LoginRequestDto;
import com.example.animeecommercebackend.Dto.Request.RegisterRequestDto;
import com.example.animeecommercebackend.Dto.Response.LoginResponseDto;
import com.example.animeecommercebackend.Dto.Response.RegisterResponseDto;
import com.example.animeecommercebackend.Service.Impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterResponseDto>> register(
            @RequestBody @Valid RegisterRequestDto dto){
        RegisterResponseDto register = authServiceImpl.register(dto);

        ApiResponseDto<RegisterResponseDto> response = new ApiResponseDto<>(
                true,
                "Registered Successfully",
                register
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @RequestBody @Valid LoginRequestDto dto){
        LoginResponseDto login = authServiceImpl.login(dto);

        ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>(
                true,
                "Login Successfully",
                login
        );
        return ResponseEntity.ok(response);
    }
}
