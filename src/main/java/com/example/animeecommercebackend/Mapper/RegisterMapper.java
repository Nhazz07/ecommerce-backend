package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.RegisterRequestDto;
import com.example.animeecommercebackend.Dto.Response.RegisterResponseDto;
import com.example.animeecommercebackend.Entity.User;

public class RegisterMapper {

    public static User toEntity(RegisterRequestDto dto) {

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setStatus(dto.getStatus());

        return user;
    }

    public static RegisterResponseDto toResponse(User user) {

        RegisterResponseDto dto = new RegisterResponseDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}