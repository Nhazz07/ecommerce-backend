package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.UserRequestDto;
import com.example.animeecommercebackend.Dto.Response.UserResponseDto;
import com.example.animeecommercebackend.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getUserById(
            @PathVariable @Positive Long id) {

        UserResponseDto user =
                userServiceImpl.getUserById(id);

        ApiResponseDto<UserResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "User Retrieved Successfully",
                        user
                );

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllUsers() {

        List<UserResponseDto> users =
                userServiceImpl.getAllUsers();

        ApiResponseDto<List<UserResponseDto>> response =
                new ApiResponseDto<>(
                        true,
                        "Users Retrieved Successfully",
                        users
                );

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(
            @PathVariable @Positive Long id,
            @RequestBody @Valid UserRequestDto dto) {

        UserResponseDto user =
                userServiceImpl.updateUser(id, dto);

        ApiResponseDto<UserResponseDto> response =
                new ApiResponseDto<>(
                        true,
                        "User Updated Successfully",
                        user
                );

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(
            @PathVariable @Positive Long id) {

        userServiceImpl.deleteUser(id);

        ApiResponseDto<Void> response =
                new ApiResponseDto<>(
                        true,
                        "User Deleted Successfully",
                        null
                );

        return ResponseEntity.ok(response);
    }
}