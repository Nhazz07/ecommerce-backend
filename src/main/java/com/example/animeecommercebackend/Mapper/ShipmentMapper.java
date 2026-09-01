package com.example.animeecommercebackend.Mapper;

import com.example.animeecommercebackend.Dto.Request.ShipmentRequestDto;
import com.example.animeecommercebackend.Dto.Response.ShipmentResponseDto;
import com.example.animeecommercebackend.Entity.Order;
import com.example.animeecommercebackend.Entity.Shipment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShipmentMapper {
    public static Shipment toEntity(ShipmentRequestDto dto
                             ){
        Shipment shipment = new Shipment();

        shipment.setCarrier(dto.getCarrier());
        shipment.setShipmentMethod(dto.getShippingMethod());


        return shipment;
    }

    public static ShipmentResponseDto toResponse(Shipment shipment){

        ShipmentResponseDto dto = new ShipmentResponseDto();

        dto.setId(shipment.getId());
        dto.setTrackingNumber(shipment.getTrackingNumber());
        dto.setCarrier(shipment.getCarrier());
        dto.setShippingMethod(shipment.getShipmentMethod());
        dto.setStatus(shipment.getStatus());
        dto.setShippedAt(shipment.getShippedAt());
        dto.setEstimatedDeliveryDate(shipment.getEstimatedDeliveryDate());
        dto.setDeliveredAt(shipment.getDeliveryAt());

        if(shipment.getOrder() != null){
            dto.setOrderId(shipment.getOrder().getId());
        }
        return dto;
    }
}
