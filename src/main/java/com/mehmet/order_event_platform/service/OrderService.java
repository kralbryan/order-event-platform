package com.mehmet.order_event_platform.service;

import com.mehmet.order_event_platform.dto.CreateOrderRequest;
import com.mehmet.order_event_platform.dto.OrderResponse;
import com.mehmet.order_event_platform.entity.Order;
import com.mehmet.order_event_platform.event.OrderCreatedEvent;
import com.mehmet.order_event_platform.exception.ResourceNotFoundException;
import com.mehmet.order_event_platform.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j 
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .customerEmail(request.getCustomerEmail())
                .totalAmount(request.getTotalAmount())
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(savedOrder.getId())
                .customerEmail(savedOrder.getCustomerEmail())
                .totalAmount(savedOrder.getTotalAmount())
                .createdAt(savedOrder.getCreatedAt())
                .build();
        
        log.info("OrderService: Order created with ID {}, sending event to Kafka", savedOrder.getId());
        
        orderProducer.sendOrderCreatedEvent(event);

        return mapToResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerEmail(order.getCustomerEmail())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}