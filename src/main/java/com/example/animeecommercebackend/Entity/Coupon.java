package com.example.animeecommercebackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Coupon Code is Required")
    private String code;

    @NotBlank(message = "Discount type is Required")
    private String discountType;

    private String description;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", inclusive = false,
    message = "Discount value must be greater than 0"
    )
    private BigDecimal discountValue;

    @NotNull(message = "Discount minimum is required")
    @DecimalMin(value = "0.0", inclusive = false,
    message = "Minimum order amount cannot be negative"
    )
    private BigDecimal minimumOrderAmount;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    private LocalDateTime endDate;

    @Min(value = 1, message = "Usage Limit must be at lease 1")
    private Integer usageLimit;


    private Integer usedCount;

    @NotNull(message = "Active status is required")
    private Boolean active;

//    @ManyToMany(mappedBy = "coupons")
//    private Set<Order> orders = new HashSet<>();
@OneToMany(mappedBy = "coupon")
private Set<Order> orders;

@OneToMany(mappedBy = "coupon")
    private List<User> users;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;


}
