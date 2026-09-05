package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.ShipmentRequestDto;
import com.example.animeecommercebackend.Dto.Response.ShipmentResponseDto;
import com.example.animeecommercebackend.Entity.Shipment;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.ShipmentMapper;
import com.example.animeecommercebackend.Repository.ShipmentRepository;
import com.example.animeecommercebackend.Service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForArraysOfShort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Override
    public ShipmentResponseDto createShipment(ShipmentRequestDto dto) {
        Shipment shipment = ShipmentMapper.toEntity(dto);

        Shipment saved = shipmentRepository.save(shipment);
        return ShipmentMapper.toResponse(saved);
    }

    @Override
    public ShipmentResponseDto getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Shipment Not Found"));
        return ShipmentMapper.toResponse(shipment);
    }

    @Override
    public List<ShipmentResponseDto> getAllShipment() {
        return shipmentRepository.findAll().stream().map(ShipmentMapper::toResponse).toList();
    }

    @Override
    public ShipmentResponseDto getShipmentByOrderId(Long orderId) {
        Shipment shipment = shipmentRepository.findByOrderId(orderId);
        if(shipment == null){
            throw new ResourceNotFoundException("Shipment Not Found");
        }
        return ShipmentMapper.toResponse(shipment);
    }

    @Override
    public ShipmentResponseDto updateShipment(Long id, ShipmentRequestDto dto) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Shipment Not Found"));
        shipment.setShipmentMethod(dto.getShippingMethod());
        shipment.setCarrier(dto.getCarrier());

        Shipment updated = shipmentRepository.save(shipment);
        return ShipmentMapper.toResponse(updated);
    }

    @Override
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Shipment Not Found"));

        shipmentRepository.delete(shipment);
    }
}
