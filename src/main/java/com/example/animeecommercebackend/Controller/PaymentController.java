package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.PaymentRequestDto;
import com.example.animeecommercebackend.Dto.Response.PaymentResponseDto;
import com.example.animeecommercebackend.Service.Impl.PaymentServiceImpl;
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
@RequestMapping("/api/payment")
@Validated
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> createPayment(
            @RequestBody @Valid PaymentRequestDto dto) {
        PaymentResponseDto payment = paymentServiceImpl.creatPayment(dto);

        ApiResponseDto<PaymentResponseDto> response = new ApiResponseDto<>(
                true,
                "Payment Created Successfully",
                payment
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> getPaymentById(
            @PathVariable @Positive Long id) {
        PaymentResponseDto payment = paymentServiceImpl.getPaymentById(id);

        ApiResponseDto<PaymentResponseDto> response = new ApiResponseDto<>(
                true,
                "Payment Retrieved Successfully",
                payment
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> getPaymentByOrderId(
            @PathVariable @Positive Long orderId) {
        PaymentResponseDto payment = paymentServiceImpl.getPaymentByOrderId(orderId);

        ApiResponseDto<PaymentResponseDto> response = new ApiResponseDto<>(
                true,
                "Payment Retrieved Successfully",
                payment
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<List<PaymentResponseDto>>> getAllPayment() {
        List<PaymentResponseDto> payments = paymentServiceImpl.getAllPayment();

        ApiResponseDto<List<PaymentResponseDto>> response = new ApiResponseDto<>(
                true,
                "Payment Retrieved Successfully!",
                payments
        );
        return ResponseEntity.ok(response);
    }
}
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> updatePayment(
//            @PathVariable @Positive Long id,
//            @RequestBody @Valid PaymentRequestDto dto){
//        PaymentResponseDto payment = paymentServiceImpl.updatePayment(id,dto);
//
//        ApiResponseDto<PaymentResponseDto> response = new ApiResponseDto<>(
//                true,
//                "Payment Updated Successfully!",
//                payment
//        );
//        return ResponseEntity.ok(response);
//    }
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ApiResponseDto<Void>> deletePayment(
//            @PathVariable @Positive Long id){
//        paymentServiceImpl.deletePayment(id);
//        ApiResponseDto<Void> response = new ApiResponseDto<>(
//                true,
//                "Payment Deleted Successfully",
//                null
//        );
//        return ResponseEntity.ok(response);
//    }

