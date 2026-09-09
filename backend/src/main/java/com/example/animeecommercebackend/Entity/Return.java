package com.example.animeecommercebackend.Entity;

import com.example.animeecommercebackend.Entity.Enums.ReturnStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "returns")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Return number is required")
    @Column(nullable = false, unique = true)
    private String returnNumber;

    @NotBlank(message = "Return reason is required")
    private String reason;

    @NotNull(message = "Return status is required")
    @Enumerated(EnumType.STRING)
    private ReturnStatus status;

    @NotNull(message = "Requested date is required")
    private LocalDateTime requestedAt;

    private LocalDateTime processedAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "refund_id")
    private Refund refund;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
