package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ReturnRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReturnResponseDto;
import com.example.animeecommercebackend.Entity.Enums.ReturnStatus;
import com.example.animeecommercebackend.Entity.Return;
import com.example.animeecommercebackend.Entity.User;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ReturnMapper;
import com.example.animeecommercebackend.Repository.ReturnRepository;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {

    private final ReturnRepository returnRepository;
    private final CurrentUserService currentUserService;

    @Override
    public ReturnResponseDto createReturn(ReturnRequestDto dto) {

        Return Areturn = ReturnMapper.toEntity(dto);

        Return saved = returnRepository.save(Areturn);

        return ReturnMapper.toResponse(saved);
    }

    @Override
    public ReturnResponseDto getReturnById(Long id) {

        Return Areturn = returnRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Return Not Found"));

        checkReturnOwnership(Areturn);

        return ReturnMapper.toResponse(Areturn);
    }

    @Override
    public List<ReturnResponseDto> getAllReturn() {

        return returnRepository.findAll()
                .stream()
                .map(ReturnMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReturnResponseDto> getReturnByOrderId(Long orderId) {

        List<Return> returns = returnRepository.findByOrderId(orderId);

        if (returns.isEmpty()) {
            throw new ResourceNotFoundException("Return Not Found");
        }

        for (Return Areturn : returns) {
            checkReturnOwnership(Areturn);
        }

        return returns
                .stream()
                .map(ReturnMapper::toResponse)
                .toList();
    }

    @Override
    public ReturnResponseDto updateReturn(Long id, ReturnRequestDto dto) {

        Return Areturn = returnRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Return Not Found!"));

        checkReturnOwnership(Areturn);

        Areturn.setReason(dto.getReason());

        Return updated = returnRepository.save(Areturn);

        return ReturnMapper.toResponse(updated);
    }

    @Override
    public void deleteReturn(Long id) {

        Return Areturn = returnRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Return Not Found"));

        checkReturnOwnership(Areturn);

        returnRepository.delete(Areturn);
    }

    @Override
    public ReturnResponseDto updateReturnStatus(
            Long id,
            ReturnStatus status) {

        Return AReturn = returnRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Return Not Found"));

        ReturnStatus currentStatus = AReturn.getStatus();

        if (!isValidStatusTransition(currentStatus, status)) {
            throw new RuntimeException("Invalid return status ");
        }

        AReturn.setStatus(status);

        if (status == ReturnStatus.APPROVED
                || status == ReturnStatus.REJECTED
                || status == ReturnStatus.COMPLETED) {

            AReturn.setProcessedAt(
                    java.time.LocalDateTime.now()
            );
        }

        Return updated = returnRepository.save(AReturn);

        return ReturnMapper.toResponse(updated);
    }

    private boolean isValidStatusTransition(
            ReturnStatus currentStatus,
            ReturnStatus status) {

        return switch (currentStatus) {

            case REQUESTED ->
                    status == ReturnStatus.APPROVED
                            || status == ReturnStatus.REJECTED
                            || status == ReturnStatus.CANCELLED;

            case APPROVED ->
                    status == ReturnStatus.PROCESSING
                            || status == ReturnStatus.CANCELLED;

            case PROCESSING ->
                    status == ReturnStatus.COMPLETED;

            case COMPLETED,
                 REJECTED,
                 CANCELLED ->
                    false;
        };
    }

    private void checkReturnOwnership(Return Areturn) {

        User currentUser = currentUserService.getCurrentUser();

        if (!Areturn.getOrder()
                .getUser()
                .getId()
                .equals(currentUser.getId())) {

            throw new AccessDeniedException(
                    "You cannot access another user's return"
            );
        }
    }
}