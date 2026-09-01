package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Entity.Refund;
import com.example.animeecommercebackend.Entity.Return;
import org.springframework.stereotype.Component;



@Component
public class RefundMapper {

    public static Refund toEntity(RefundRequestDto dto
                             ){
        Refund refund = new Refund();

        refund.setAmount(dto.getAmount());
        refund.setReason(dto.getReason());


        return refund;
    }

    public static RefundResponseDto toResponse(Refund refund){
        RefundResponseDto dto = new RefundResponseDto();

        dto.setId(refund.getId());
        dto.setRefundNumber(refund.getRefundNumber());
        dto.setAmount(refund.getAmount());
        dto.setReason(refund.getReason());
        dto.setStatus(refund.getStatus());
        dto.setRefundAt(refund.getRefundAt());

        if(refund.getAReturn() != null){
            dto.setReturnId(refund.getAReturn().getId());
        }
        return dto;
    }
}
