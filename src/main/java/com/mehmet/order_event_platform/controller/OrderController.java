package com.mehmet.order_event_platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehmet.order_event_platform.dto.CreateOrderRequest;
import com.mehmet.order_event_platform.dto.OrderResponse;
import com.mehmet.order_event_platform.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Tag(name = "Order Management", description = "Sipariş oluşturma ve sorgulama işlemleri")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Yeni sipariş oluştur", description = "Müşteri e-postası ve sipariş tutarını alarak yeni bir sipariş kaydı başlatır.")
    @ApiResponse(responseCode = "201", description = "Sipariş başarıyla oluşturuldu")
    @ApiResponse(responseCode = "400", description = "Geçersiz istek parametreleri (Validasyon hatası)")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Tüm siparişleri listele")
    @ApiResponse(responseCode = "200", description = "Sipariş listesi başarıyla getirildi")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> responses = orderService.getAllOrders();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "ID ile sipariş detayını getir")
    @ApiResponse(responseCode = "200", description = "Sipariş bulundu")
    @ApiResponse(responseCode = "404", description = "Belirtilen ID ile sipariş bulunamadı")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

}