package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.LoginRequestDto;
import com.example.animeecommercebackend.Dto.Request.RegisterRequestDto;
import com.example.animeecommercebackend.Dto.Response.LoginResponseDto;
import com.example.animeecommercebackend.Dto.Response.RegisterResponseDto;
import com.example.animeecommercebackend.Entity.User;
import com.example.animeecommercebackend.Mapper.RegisterMapper;
import com.example.animeecommercebackend.Mapper.UserMapper;
import com.example.animeecommercebackend.Repository.UserRepository;
import com.example.animeecommercebackend.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDto register(RegisterRequestDto dto) {

        // Check if email already exists
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered!");
        }

        // Check password confirmation
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match!");
        }

        // Convert DTO → Entity
        User user = RegisterMapper.toEntity(dto);

        // Hash password before saving
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Convert Entity → Response DTO
        return RegisterMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email or password!")
                );

        // Check password
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password!");
        }

        // For now, return basic login response
        return new LoginResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}