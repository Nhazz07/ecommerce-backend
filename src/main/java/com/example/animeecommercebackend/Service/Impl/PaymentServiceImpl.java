package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.PaymentRequestDto;
import com.example.animeecommercebackend.Dto.Response.PaymentResponseDto;
import com.example.animeecommercebackend.Entity.Payment;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.PaymentMapper;
import com.example.animeecommercebackend.Repository.PaymentRepository;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final CurrentUserService currentUserService;

    @Override
    public PaymentResponseDto creatPayment(PaymentRequestDto dto) {
        Payment payment = PaymentMapper.toEntity(dto);

        Payment saved = paymentRepository.save(payment);
        return PaymentMapper.toResponse(saved);
    }

    @Override
    public PaymentResponseDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Payment Not Found!"));

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if (!payment.getOrder().getUser().getId().equals(currentUserId)) {
            throw new ResourceNotFoundException("Payment Not found");
        }
        return PaymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponseDto getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment == null) {
            throw new ResourceNotFoundException("Payment Not Found!");
        }
        Long currentUserId = currentUserService.getCurrentUser().getId();

        if (!payment.getOrder().getUser().getId().equals(currentUserId)) {
            throw new ResourceNotFoundException("Payment Not Found");
        }
        return PaymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponseDto> getAllPayment() {
        return paymentRepository.findAll().stream().map(PaymentMapper::toResponse).toList();
    }
}
//    @Override
//    public PaymentResponseDto updatePayment(Long id, PaymentRequestDto dto) {
//        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Payment Not Found"));
//
//        payment.setAmount(dto.getAmount());
//        payment.setPaymentMethod(dto.getPaymentMethod());
//
//        Payment updated = paymentRepository.save(payment);
//        return PaymentMapper.toResponse(updated);
//    }
//
//    @Override
//    public void deletePayment(Long id) {
//        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Payment Not Found!!"));
//
//        paymentRepository.delete(payment);
//    }

