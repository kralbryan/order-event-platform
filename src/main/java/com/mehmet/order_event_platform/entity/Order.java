package com.mehmet.order_event_platform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;  

@Entity 
@Table(name = "orders")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder 
public class Order {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column (name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private OrderStatus status;

    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist 
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = OrderStatus.CREATED;
        }
    }   
}
