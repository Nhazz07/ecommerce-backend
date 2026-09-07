package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Entity.Refund;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.RefundMapper;
import com.example.animeecommercebackend.Repository.RefundRepository;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    private final CurrentUserService currentUserService;
    @Override
    public RefundResponseDto createRefund(RefundRequestDto dto) {
        Refund refund = RefundMapper.toEntity(dto);

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if(!refund.getAReturn().getOrder().getUser().getId().equals(currentUserId)){
            throw new ResourceNotFoundException("Refund Not Found!");
        }
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
}
