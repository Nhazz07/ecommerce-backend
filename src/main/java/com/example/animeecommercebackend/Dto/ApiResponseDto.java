package com.example.animeecommercebackend.Dto;

import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private Boolean success;

    private String message;

    private T data;



}
