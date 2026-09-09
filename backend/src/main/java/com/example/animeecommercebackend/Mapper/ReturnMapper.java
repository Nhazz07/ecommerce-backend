package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.ReturnRequestDto;
import com.example.animeecommercebackend.Dto.Response.ReturnResponseDto;
import com.example.animeecommercebackend.Entity.Order;
import com.example.animeecommercebackend.Entity.Refund;
import com.example.animeecommercebackend.Entity.Return;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReturnMapper {

    public static Return toEntity(ReturnRequestDto dto
                           ){
        Return aReturn = new Return();

        aReturn.setReason(dto.getReason());

       return aReturn;
    }
    public static ReturnResponseDto toResponse(Return aReturn){
        ReturnResponseDto dto = new ReturnResponseDto();

        dto.setId(aReturn.getId());
        dto.setReturnNumber(aReturn.getReturnNumber());
        dto.setReason(aReturn.getReason());
        dto.setStatus(aReturn.getStatus());
        dto.setRequestedAt(aReturn.getRequestedAt());
        dto.setProcessedAt(aReturn.getProcessedAt());
        dto.setCreatedAt(aReturn.getCreatedAt());
        dto.setUpdatedAt(aReturn.getUpdatedAt());
        if(aReturn.getOrder() != null){
            dto.setOrderId(aReturn.getOrder().getId());
        }
        if(aReturn.getRefund() != null){
            dto.setRefundId(aReturn.getRefund().getId());
        }
        return dto;
    }
}


