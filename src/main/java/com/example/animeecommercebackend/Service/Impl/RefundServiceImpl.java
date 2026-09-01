package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Entity.Refund;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.RefundMapper;
import com.example.animeecommercebackend.Repository.RefundRepository;
import com.example.animeecommercebackend.Service.RefundService;
import com.fasterxml.classmate.types.ResolvedObjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    @Override
    public RefundResponseDto createRefund(RefundRequestDto dto) {
        Refund refund = RefundMapper.toEntity(dto);

        Refund saved = refundRepository.save(refund);
        return RefundMapper.toResponse(saved);
    }

    @Override
    public RefundResponseDto getRefundById(Long id) {
        Refund refund = refundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Refund Not Found!"));
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

        return RefundMapper.toResponse(refund);
    }

    @Override
    public RefundResponseDto updateRefund(Long id, RefundRequestDto dto) {
        Refund refund = refundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Refund Not Found!"));

        refund.setReason(dto.getReason());
        refund.setAmount(dto.getAmount());

        Refund updated = refundRepository.save(refund);
        return RefundMapper.toResponse(updated);
    }

    @Override
    public void deleteRefund(Long id) {
        Refund refund = refundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Refund Not Found"));

        refundRepository.delete(refund);
    }
}
