package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;
import com.example.animeecommercebackend.Entity.Order;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.OrderMapper;
import com.example.animeecommercebackend.Repository.OrderRepository;
import com.example.animeecommercebackend.Service.CurrentUserService;
import com.example.animeecommercebackend.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CurrentUserService currentUserService;


    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Order order = OrderMapper.toEntity(dto);

        order.setUser(currentUserService.getCurrentUser());
        Order saved = orderRepository.save(order);

        return OrderMapper.toResponse(saved);
    }
    @Override
    public List<OrderResponseDto> getMyOrders() {
        Long currentUserId = currentUserService.getCurrentUser().getId();

        return orderRepository.findByUserId(currentUserId)
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if(!order.getId().equals(currentUserId)){
            throw new ResourceNotFoundException("Order Not found");
        }
        return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDto getOrderByUserId(Long userId) {
        Order order = orderRepository.findOrderByUserId(userId);
        if(order == null){
            throw new ResourceNotFoundException("Order Not Found!");
        }
        return OrderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> getAllOrder() {
        return orderRepository.findAll().stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if(!order.getUser().getId().equals(currentUserId)){
            throw new ResourceNotFoundException("Order Not Found!!");
        }
        order.setShippingAddress(dto.getShippingAddress());

        Order saved = orderRepository.save(order);
        return OrderMapper.toResponse(saved);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId = currentUserService.getCurrentUser().getId();

        if(!order.getUser().getId().equals(currentUserId)){
            throw new ResourceNotFoundException("Order Not Found");
        }

        orderRepository.delete(order);
    }
}
