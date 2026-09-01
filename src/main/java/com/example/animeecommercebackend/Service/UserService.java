package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.UserRequestDto;
import com.example.animeecommercebackend.Dto.Response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);
}