package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.ShipmentRequestDto;
import com.example.animeecommercebackend.Dto.Response.ShipmentResponseDto;
import com.example.animeecommercebackend.Entity.Shipment;
import com.example.animeecommercebackend.Service.Impl.ShipmentServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {
    private final ShipmentServiceImpl shipmentServiceImpl;

    public ShipmentController(ShipmentServiceImpl shipmentServiceImpl) {
        this.shipmentServiceImpl = shipmentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<ShipmentResponseDto>> createShipment(@RequestBody @Valid ShipmentRequestDto dto){
        ShipmentResponseDto shipment = shipmentServiceImpl.createShipment(dto);

        ApiResponseDto<ShipmentResponseDto> response = new ApiResponseDto<>(
                true,
                "Shipment Created Successfully",
                shipment
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ShipmentResponseDto>> getShipmentById(@PathVariable @Positive Long id){
        ShipmentResponseDto shipment = shipmentServiceImpl.getShipmentById(id);
        ApiResponseDto<ShipmentResponseDto> response = new ApiResponseDto<>(
                true,
                "Shipment Retrieved Successfully",
                shipment
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orderId/{orderId}")
    public ResponseEntity<ApiResponseDto<ShipmentResponseDto>> getShipmentByOrderId(@PathVariable @Positive Long orderId){
        ShipmentResponseDto shipment = shipmentServiceImpl.getShipmentByOrderId(orderId);

        ApiResponseDto<ShipmentResponseDto> response = new ApiResponseDto<>(
                true,
                "Shipment Retrieved Successfully",
                shipment
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ShipmentResponseDto>>> getAllShipment(){
        List<ShipmentResponseDto> shipment = shipmentServiceImpl.getAllShipment();
        ApiResponseDto<List<ShipmentResponseDto>> response = new ApiResponseDto<>(
                true,
                "Shipment Retrieved Successfully",
                shipment
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<ShipmentResponseDto>> updateShipment(@PathVariable @Positive Long id, @RequestBody @Valid ShipmentRequestDto dto){
        ShipmentResponseDto shipment = shipmentServiceImpl.updateShipment(id,dto);

        ApiResponseDto<ShipmentResponseDto> response = new ApiResponseDto<>(
                true,
                "Shipment Updated Successfully",
                shipment
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteShipment(@PathVariable @Positive Long id){
        shipmentServiceImpl.deleteShipment(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Shipment Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
