package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ReturnRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReturnResponseDto;
import com.example.animeecommercebackend.Entity.Return;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ReturnMapper;
import com.example.animeecommercebackend.Repository.ReturnRepository;
import com.example.animeecommercebackend.Service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    private final ReturnRepository returnRepository;
    @Override
    public ReturnResponseDto createReturn(ReturnRequestDto dto) {
        Return Areturn = ReturnMapper.toEntity(dto);

        Return saved = returnRepository.save(Areturn);
        return ReturnMapper.toResponse(saved);
    }

    @Override
    public ReturnResponseDto getReturnById(Long id) {
        Return Areturn = returnRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Return Not Found"));

        return ReturnMapper.toResponse(Areturn);
    }

    @Override
    public List<ReturnResponseDto> getAllReturn() {
        return returnRepository.findAll().stream().map(ReturnMapper::toResponse).toList();
    }

    @Override
    public List<ReturnResponseDto> getReturnByOrderId(Long orderId) {
        List<Return> returns = returnRepository.findByOrderId(orderId);
        if(returns.isEmpty()){
            throw new ResourceNotFoundException("Return Not Found");
        }
        return returns.stream().map(ReturnMapper::toResponse).toList();
    }

    @Override
    public ReturnResponseDto updateReturn(Long id, ReturnRequestDto dto) {
        Return Areturn = returnRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Return Not Found!"));

        Areturn.setReason(dto.getReason());

        Return updated = returnRepository.save(Areturn);
        return ReturnMapper.toResponse(updated);
    }

    @Override
    public void deleteReturn(Long id) {
        Return Areturn = returnRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Return Not Found"));

        returnRepository.delete(Areturn);
    }
}
