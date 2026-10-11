package com.mehmet.order_event_platform.service;

import com.mehmet.order_event_platform.config.KafkaTopicConfig;
import com.mehmet.order_event_platform.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j 
@Service
public class OrderConsumer {

    @KafkaListener(
            topics = KafkaTopicConfig.ORDER_CREATED_TOPIC,
            groupId = "${spring.kafka.consumer.group-id:order-group}"
    )
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("==================================================");
        log.info("Kafka Consumer event yakaladi!");
        log.info("Islenen Siparis ID : {}", event.getOrderId());
        log.info("Musteri Email      : {}", event.getCustomerEmail());
        log.info("Toplam Tutar       : {} TL", event.getTotalAmount());
        log.info("Olusturulma Tarihi : {}", event.getCreatedAt());
        log.info("Arka planda e-posta bildirimi gonderiliyor...");
        log.info("==================================================");
    }
}