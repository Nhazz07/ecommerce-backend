package com.example.animeecommercebackend.Service.Impl;

import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;
import com.example.animeecommercebackend.Entity.*;
import com.example.animeecommercebackend.Entity.Enums.OrderStatus;
import com.example.animeecommercebackend.Exception.ResourceNotFoundException;
import com.example.animeecommercebackend.Mapper.OrderMapper;
import com.example.animeecommercebackend.Repository.CartRepository;

import com.example.animeecommercebackend.Repository.OrderRepository;
import com.example.animeecommercebackend.Service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CurrentUserService currentUserService;
    private final InventoryService inventoryService;
    private final PromotionService promotionService;
    private final CouponService couponService;
    private final ShipmentService shipmentService;


    @Transactional
    @Override
    public OrderResponseDto createOrder(OrderRequestDto dto) {

        User user = currentUserService.getCurrentUser();


        List<Cart> carts = cartRepository.findByUserId(user.getId());

        if (carts.isEmpty()) {
            throw new ResourceNotFoundException("Cart Not Found!");
        }

        Cart cart = carts.get(0);

        if (cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart Is Empty!");
        }

        Order order = new Order();

        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        BigDecimal subTotal = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {

            ProductVariant variant = cartItem.getProductVariant();

            int quantity = cartItem.getQuantity();

            if (!inventoryService.hasEnoughStock(
                    variant.getId(),
                    quantity
            )) {
                throw new RuntimeException(
                        "Not Enough Stock for product variant: "
                                + variant.getId()
                );
            }
            BigDecimal unitPrice = variant.getPrice();

            BigDecimal discount = promotionService.calculateDiscount(
                    variant.getProduct().getId(),unitPrice
            );

            BigDecimal finalUnitPrice =
                    unitPrice.subtract(discount);

            BigDecimal itemSubtotal =
                    finalUnitPrice.multiply(BigDecimal.valueOf(quantity));

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProductVariant(variant);
            orderItem.setQuantity(quantity);
            orderItem.setUnitPrice(finalUnitPrice);
            orderItem.setSubTotal(itemSubtotal);

            orderItems.add(orderItem);

            subTotal = subTotal.add(itemSubtotal);
        }

        order.setOrderItems(orderItems);


        order.setSubTotal(subTotal);

        // Temporary business logic
        // Discount and shipping will be implemented later.
       BigDecimal couponDiscount = BigDecimal.ZERO;
       if(dto.getCouponId() != null){
           couponDiscount = couponService.calculateDiscount(dto.getCouponId(), subTotal);
       }
       order.setDiscountAmount(couponDiscount);

       BigDecimal shippingFee = shipmentService
               .calculateShipmentFee(subTotal);
       order.setShippingFee(shippingFee);
       BigDecimal total = subTotal
               .subtract(couponDiscount)
               .add(shippingFee);

        order.setTotalAmount(total);

        order.setShippingAddress(dto.getShippingAddress());

        Order savedOrder = orderRepository.save(order);

        for(CartItem cartItem : cart.getCartItems()){
            ProductVariant variant =  cartItem.getProductVariant();

            inventoryService.decreaseStock(
                    variant.getId(),
                    cartItem.getQuantity()
            );
        }
        cart.getCartItems().clear();

        cartRepository.save(cart);

        return OrderMapper.toResponse(savedOrder);
    }


    @Override
    public List<OrderResponseDto> getMyOrders() {

        Long currentUserId =
                currentUserService.getCurrentUser().getId();

        return orderRepository.findByUserId(currentUserId)
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }


    @Override
    public OrderResponseDto getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId =
                currentUserService.getCurrentUser().getId();

        if (!order.getUser().getId().equals(currentUserId)) {
            throw new ResourceNotFoundException("Order Not Found!");
        }

        return OrderMapper.toResponse(order);
    }


    @Override
    public OrderResponseDto getOrderByUserId(Long userId) {

        Order order = orderRepository.findOrderByUserId(userId);

        if (order == null) {
            throw new ResourceNotFoundException("Order Not Found!");
        }

        return OrderMapper.toResponse(order);
    }


    @Override
    public List<OrderResponseDto> getAllOrder() {

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }


    @Override
    public OrderResponseDto updateOrder(
            Long id,
            OrderRequestDto dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId =
                currentUserService.getCurrentUser().getId();

        if (!order.getUser().getId().equals(currentUserId)) {
            throw new ResourceNotFoundException("Order Not Found!");
        }

        order.setShippingAddress(dto.getShippingAddress());

        Order saved = orderRepository.save(order);

        return OrderMapper.toResponse(saved);
    }


    @Override
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order Not Found!"));

        Long currentUserId =
                currentUserService.getCurrentUser().getId();

        if (!order.getUser().getId().equals(currentUserId)) {
            throw new ResourceNotFoundException("Order Not Found!");
        }

        orderRepository.delete(order);
    }
}