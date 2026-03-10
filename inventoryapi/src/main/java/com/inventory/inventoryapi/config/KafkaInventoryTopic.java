package com.inventory.inventoryapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaInventoryTopic {
    @Bean
    public NewTopic inventoryConfirmedTopic() {
        return TopicBuilder.name("inventory-confirmed")
                .build();
    }

    @Bean
    public NewTopic inventoryFailedTopic() {
        return TopicBuilder.name("inventory-failed").build();
    }
}