package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.PaymentRequestDto;
import com.example.animeecommercebackend.Dto.Response.PaymentResponseDto;

import java.util.List;

public interface PaymentService {
    PaymentResponseDto creatPayment(PaymentRequestDto dto);
    PaymentResponseDto getPaymentById(Long id);
    PaymentResponseDto getPaymentByOrderId(Long orderId);
    List<PaymentResponseDto> getAllPayment();
//    PaymentResponseDto updatePayment(Long id, PaymentRequestDto dto);
//    void deletePayment(Long id);
}
