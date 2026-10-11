package com.mehmet.order_event_platform.service;

import com.mehmet.order_event_platform.config.KafkaTopicConfig;
import com.mehmet.order_event_platform.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service 
@RequiredArgsConstructor 
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event){
        log.info("Sending OrderCreatedEvent to Kafka: {}", event);
        kafkaTemplate.send(KafkaTopicConfig.ORDER_CREATED_TOPIC, String.valueOf(event.getOrderId()), event);
    }

}
