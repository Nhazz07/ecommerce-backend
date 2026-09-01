package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.RefundRequestDto;
import com.example.animeecommercebackend.Dto.Response.RefundResponseDto;
import com.example.animeecommercebackend.Mapper.RefundMapper;
import com.example.animeecommercebackend.Service.Impl.RefundServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refund")
public class RefundController {
    private final RefundServiceImpl refundServiceImpl;

    public RefundController(RefundServiceImpl refundServiceImpl) {
        this.refundServiceImpl = refundServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<RefundResponseDto>> createRefund(@RequestBody @Valid RefundRequestDto dto){
        RefundResponseDto refund = refundServiceImpl.createRefund(dto);

        ApiResponseDto<RefundResponseDto> response = new ApiResponseDto<>(
                true,
                "Refund Created Successfully",
                refund
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<RefundResponseDto>> getRefundId(@PathVariable @Positive Long id){
        RefundResponseDto refund = refundServiceImpl.getRefundById(id);

        ApiResponseDto<RefundResponseDto> response = new ApiResponseDto<>(
                true,
                "Refund Retrieved Successfully",
                refund
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{returnId}")
    public ResponseEntity<ApiResponseDto<RefundResponseDto>> getRefundByOrderId(@PathVariable @Positive Long returnId){
        RefundResponseDto refund = refundServiceImpl.getRefundByReturnId(returnId);

        ApiResponseDto<RefundResponseDto> response = new ApiResponseDto<>(
                true,
                "Refund Retrieved Successfully",
                refund
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<RefundResponseDto>>> getAllRefund(){
        List<RefundResponseDto> refunds = refundServiceImpl.getAllRefund();

        ApiResponseDto<List<RefundResponseDto>> response = new ApiResponseDto<>(
                true,
                "Refund Retrieved Successfully",
                refunds
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<RefundResponseDto>> updateRefund(@PathVariable @Positive Long id, @RequestBody @Positive RefundRequestDto dto){
        RefundResponseDto refund = refundServiceImpl.updateRefund(id,dto);

        ApiResponseDto<RefundResponseDto> response = new ApiResponseDto<>(
                true,
                "Refund Updated Successfully",
                refund
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteRefund(@PathVariable @Positive Long id){
       refundServiceImpl.deleteRefund(id);

       ApiResponseDto<Void> response = new ApiResponseDto<>(
               true,
               "Refund Deleted Successfully",
               null
       );
       return ResponseEntity.ok(response);
    }
}
