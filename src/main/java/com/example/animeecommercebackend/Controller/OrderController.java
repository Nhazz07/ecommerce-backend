package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;
import com.example.animeecommercebackend.Service.Impl.OrderServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> createOrder(
            @RequestBody @Valid OrderRequestDto dto){
        OrderResponseDto order = orderServiceImpl.createOrder(dto);

        ApiResponseDto<OrderResponseDto> response = new ApiResponseDto<>(
                true,
                "Order Created Successfully!",
                order
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> getOrderById(
            @PathVariable @Positive Long id){
        OrderResponseDto order = orderServiceImpl.getOrderById(id);

        ApiResponseDto<OrderResponseDto> response = new ApiResponseDto<>(
                true,
                "Order Retrieved Successfully!",
                order
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> getOrderByUserId(
            @PathVariable @Positive Long userId){
        OrderResponseDto order = orderServiceImpl.getOrderByUserId(userId);

        ApiResponseDto<OrderResponseDto> response = new ApiResponseDto<>(
                true,
                "Order Retrieved Successfully!",
                order
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<OrderResponseDto>>> getAllOrder(){
        List<OrderResponseDto> orders = orderServiceImpl.getAllOrder();

        ApiResponseDto<List<OrderResponseDto>> response = new ApiResponseDto<>(
                true,
                "Order Retrieved Successfully!",
                orders
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> updateOrder(
            @PathVariable @Positive Long id,
            @RequestBody @Valid OrderRequestDto dto){
        OrderResponseDto order = orderServiceImpl.updateOrder(id,dto);

        ApiResponseDto<OrderResponseDto> response = new ApiResponseDto<>(
                true,
                "Order Updated Successfully!",
                order
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteOrder(
            @PathVariable @Positive Long id){
        orderServiceImpl.deleteOrder(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Order Deleted Successfully!",
                null
        );
        return ResponseEntity.ok(response);
    }
}
