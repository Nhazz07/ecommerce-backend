package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ShipmentRequestDto;
import com.example.animeecommercebackend.Dto.Response.ShipmentResponseDto;
import com.example.animeecommercebackend.Entity.Enums.ShipmentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface ShipmentService {
    ShipmentResponseDto createShipment(ShipmentRequestDto dto);
    ShipmentResponseDto getShipmentById(Long id);
    List<ShipmentResponseDto> getAllShipment();
    ShipmentResponseDto getShipmentByOrderId(Long orderId );
    ShipmentResponseDto updateShipment(Long id, ShipmentRequestDto dto);
    void deleteShipment(Long id);
    BigDecimal calculateShipmentFee(BigDecimal orderAmount);
    ShipmentResponseDto updateShipmentStatus(
            Long id,
            ShipmentStatus status
    );
}
