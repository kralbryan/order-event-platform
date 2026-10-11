package com.mehmet.order_event_platform.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class KafkaTopicConfig {
    
    public static final String ORDER_CREATED_TOPIC = "order-created-events";

    @Bean 
    public NewTopic orderCreatedTopic() {
        return TopicBuilder.name(ORDER_CREATED_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
