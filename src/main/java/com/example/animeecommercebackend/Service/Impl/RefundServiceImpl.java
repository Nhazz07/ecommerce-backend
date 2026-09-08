package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Entity.Enums.RefundStatus;
import com.example.animeecommercebackend.Entity.Enums.ReturnStatus;
import com.example.animeecommercebackend.Entity.Refund;
import com.example.animeecommercebackend.Entity.Return;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.RefundMapper;
import com.example.animeecommercebackend.Repository.RefundRepository;
import com.example.animeecommercebackend.Repository.ReturnRepository;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.RefundService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    private final CurrentUserService currentUserService;
    private final ReturnRepository returnRepository;
    private static final BigDecimal REFUND_PERCENTAGE =
            BigDecimal.valueOf(45);

    private static final BigDecimal ONE_HUNDRED =
            BigDecimal.valueOf(100);

    private BigDecimal calculateRefundAmount(BigDecimal amount) {
        return amount
                .multiply(REFUND_PERCENTAGE)
                .divide(ONE_HUNDRED);
    }

    @Override
    public RefundResponseDto createRefund(RefundRequestDto dto) {
        Return aReturn = returnRepository.findById(dto.getReturnId()).orElseThrow(() -> new ResourceNotFoundException("Return Not Found"));

        Long currentUser = currentUserService.getCurrentUser().getId();

        if(!aReturn.getOrder().getUser().getId().equals(currentUser)){
            throw new RuntimeException("Return not found!");
        }
        if(aReturn.getStatus() != ReturnStatus.COMPLETED){
            throw new RuntimeException("Refund can only be created for a completed return!");
        }
        if(aReturn.getRefund() != null){
            throw new RuntimeException("Refund already exist for this return!");
        }

        BigDecimal refundAmount = calculateRefundAmount(aReturn.getOrder().getTotalAmount());

        Refund refund = new Refund();
        refund.setRefundNumber(UUID.randomUUID().toString());
        refund.setAmount(refundAmount);
        refund.setReason(dto.getReason());
        refund.setStatus(RefundStatus.REQUESTED);
        refund.setAReturn(aReturn);

        Refund saved = refundRepository.save(refund);

        return RefundMapper.toResponse(saved);
    }

    @Override
    public RefundResponseDto getRefundById(Long id) {
        Refund refund = refundRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Refund Not Found!"));

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if(!refund.getAReturn().getOrder().getUser().getId().equals(currentUserId)){
           throw new ResourceNotFoundException("Refund Not Found!");
    }

        return RefundMapper.toResponse(refund);
    }

    @Override
    public List<RefundResponseDto> getAllRefund() {
        return refundRepository.findAll().stream().map(RefundMapper::toResponse).toList();
    }
    @Override
    public RefundResponseDto getRefundByReturnId(Long returnId) {
        Refund refund = refundRepository.findByAReturnId(returnId)
                .orElseThrow(() -> new ResourceNotFoundException("Refund Not Found!"));
        Long currentUserId = currentUserService.getCurrentUser().getId();
        if(!refund.getAReturn().getOrder().getUser().getId().equals(currentUserId)){
            throw new ResourceNotFoundException("Refund Not Found!");
        }

        return RefundMapper.toResponse(refund);
    }

    @Override
    public RefundResponseDto updateRefund(Long id, RefundRequestDto dto) {
        Refund refund = refundRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Refund Not Found!"));

        refund.setReason(dto.getReason());
        refund.setAmount(dto.getAmount());

        Refund updated = refundRepository.save(refund);
        return RefundMapper.toResponse(updated);
    }

    @Override
    public void deleteRefund(Long id) {
        Refund refund = refundRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Refund Not Found"));

        refundRepository.delete(refund);
    }

    @Override
    public RefundResponseDto updateRefundStatus(Long id, RefundStatus status) {
        Refund refund = refundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Refund Not Found"));

        if(isValidStatusTransition(refund.getStatus(), status)){
            throw new RuntimeException("Invalid refund status transition");
        }
        refund.setStatus(status);

        if(status == RefundStatus.COMPLETED){
            refund.setRefundAt(java.time.LocalDateTime.now());
        }

        Refund updated = refundRepository.save(refund);
        return RefundMapper.toResponse(updated);
    }

    private boolean isValidStatusTransition(RefundStatus currentStatus, RefundStatus status) {
        return switch (currentStatus){
            case REQUESTED ->
                status == RefundStatus.APPROVED||
                status == RefundStatus.REJECTED||
                status == RefundStatus.CANCELLED;
            case APPROVED ->
                status == RefundStatus.PROCESSING||
                status == RefundStatus.CANCELLED;
            case PROCESSING ->
                status == RefundStatus.COMPLETED;
            case COMPLETED,
                 REJECTED,
                 CANCELLED ->
                false;
        };
    }
}
