package com.example.animeecommercebackend.Dto.Response;

import com.example.animeecommercebackend.Entity.Enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private AddressType addressType;
    private Boolean isDefault;
    private Long userId;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
