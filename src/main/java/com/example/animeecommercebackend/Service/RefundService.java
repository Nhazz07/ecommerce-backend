package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Entity.Enums.RefundStatus;

import java.util.List;

public interface RefundService {
    RefundResponseDto createRefund(RefundRequestDto dto);
    RefundResponseDto getRefundById(Long id);
    List<RefundResponseDto> getAllRefund();
    RefundResponseDto getRefundByReturnId(Long returnId);
    RefundResponseDto updateRefund(Long id, RefundRequestDto dto);
    void deleteRefund(Long id);
    RefundResponseDto updateRefundStatus(Long id, RefundStatus status);

}
