package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.ShipmentRequestDto;
import com.example.animeecommercebackend.Dto.Response.ShipmentResponseDto;

import java.util.List;

public interface ShipmentService {
    ShipmentResponseDto createShipment(ShipmentRequestDto dto);
    ShipmentResponseDto getShipmentById(Long id);
    List<ShipmentResponseDto> getAllShipment();
    ShipmentResponseDto getShipmentByOrderId(Long orderId );
    ShipmentResponseDto updateShipment(Long id, ShipmentRequestDto dto);
    void deleteShipment(Long id);
}
