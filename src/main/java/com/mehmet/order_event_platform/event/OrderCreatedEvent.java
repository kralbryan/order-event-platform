package com.mehmet.order_event_platform.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 


public class OrderCreatedEvent {
    private Long orderId;
    private String customerEmail;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

}
