package com.mehmet.order_event_platform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    
    @NotBlank(message = "Customer email is required")
    @Email(message = "Customer email must be a valid email address") 
    private String customerEmail;

    @NotNull (message = "Total amount cannot be null")
    @Positive (message = "Total amount must be a positive value")
    private BigDecimal totalAmount;

}
