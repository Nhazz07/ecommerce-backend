package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ReturnRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReturnResponseDto;
import com.example.animeecommercebackend.Entity.Enums.ReturnStatus;

import java.util.List;

public interface ReturnService {
    ReturnResponseDto createReturn(ReturnRequestDto dto);
    ReturnResponseDto getReturnById(Long id);
    List<ReturnResponseDto> getAllReturn();
    List<ReturnResponseDto> getReturnByOrderId(Long orderId);
    ReturnResponseDto updateReturn(Long id, ReturnRequestDto dto);
    void deleteReturn(Long id);
    ReturnResponseDto updateReturnStatus(Long id, ReturnStatus status);
}
