package com.example.animeecommercebackend.Controller;

import com.example.animeecommercebackend.Dto.ApiResponseDto;
import com.example.animeecommercebackend.Dto.Request.AddressRequestDto;
import com.example.animeecommercebackend.Dto.Response.AddressResponseDto;
import com.example.animeecommercebackend.Service.Impl.AddressServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:5173")
public class AddressController {
    private final AddressServiceImpl addressServiceImpl;

    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> createAddress(
            @RequestBody @Valid
            AddressRequestDto dto){
        AddressResponseDto address = addressServiceImpl.createdAddress(dto);
        ApiResponseDto<AddressResponseDto> response = new ApiResponseDto<>(
                true,
                "Address Created Successfully",
                address
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> getAddressById(
            @PathVariable @Positive
            Long id){
        AddressResponseDto address = addressServiceImpl.getAddressById(id);

        ApiResponseDto<AddressResponseDto> response = new ApiResponseDto<>(
                true,
                "Address Retrieved Successfully",
                address
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<AddressResponseDto>>> getAllAddress(){
        List<AddressResponseDto> address = addressServiceImpl.getAllAddress();

        ApiResponseDto<List<AddressResponseDto>> response = new ApiResponseDto<>(
                true,
                "Address Retrieved Successfully!",
                address
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> updateAddress(
            @PathVariable @Positive Long id,
            @RequestBody @Valid AddressRequestDto dto){
        AddressResponseDto address = addressServiceImpl.updateAddress(id,dto);

        ApiResponseDto<AddressResponseDto> response = new ApiResponseDto<>(
                true,
                "Address Updated Successfully",
                address
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteAddress(@PathVariable @Positive Long id){
        addressServiceImpl.deleteAddress(id);

        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Address Deleted Successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{addressId}/default/{userId}")
    public ResponseEntity<ApiResponseDto<AddressResponseDto>> setDefaultAddress(
            @PathVariable @Positive Long addressId,
            @PathVariable  @Positive Long userId){
      AddressResponseDto address = addressServiceImpl.setDefaultAddress(addressId,userId);

      ApiResponseDto<AddressResponseDto> response = new ApiResponseDto<>(
              true,
              "Default Address Sat Successfully",
              address
      );
      return ResponseEntity.ok(response);
    }
}
