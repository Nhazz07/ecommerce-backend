package com.example.animeecommercebackend.Service;

import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto dto);
    List<OrderResponseDto> getMyOrders();
    OrderResponseDto getOrderById(Long id);
    OrderResponseDto getOrderByUserId(Long userId);
    List<OrderResponseDto> getAllOrder();
    OrderResponseDto updateOrder(Long id, OrderRequestDto dto);
    void deleteOrder(Long id);
}
