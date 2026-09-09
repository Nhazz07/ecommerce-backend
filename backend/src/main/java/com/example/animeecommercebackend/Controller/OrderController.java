package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.OrderRequestDto;
import com.example.animeecommercebackend.Dto.Response.OrderResponseDto;
import com.example.animeecommercebackend.Entity.Enums.OrderStatus;
import com.example.animeecommercebackend.Service.Impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Validated
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;


        @PostMapping
        @PreAuthorize("hasRole('CUSTOMER')")
        @SecurityRequirement(name = "bearerAuth")
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

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<List<OrderResponseDto>>> getMyOrder(){
        List<OrderResponseDto> orders = orderServiceImpl.getMyOrders();

        ApiResponseDto<List<OrderResponseDto>> response = new ApiResponseDto<>(
                true,
                "Order Retrieved Successfully",
                orders
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @SecurityRequirement(name = "bearerAuth")
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

    @PutMapping("{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponseDto<OrderResponseDto>> updatedOrderStatus(@PathVariable @Positive Long id, @RequestParam OrderStatus orderStatus){
            OrderResponseDto updated = orderServiceImpl.updateOrderStatus(id, orderStatus);

            ApiResponseDto<OrderResponseDto> response = new ApiResponseDto<>(
                    true,
                    "Order Status updated successfully!",
                    updated
            );
            return ResponseEntity.ok(response);
    }
}
