package com.mehmet.order_event_platform.dto;

import com.mehmet.order_event_platform.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder 
public class OrderResponse {
    private Long id;
    private String customerEmail;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

}
