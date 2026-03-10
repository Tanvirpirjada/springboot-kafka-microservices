package com.order.orderapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderTopic(){
        return new NewTopic("order-topic", 3, (short) 1);
    }

    //order topic name
    // 3-> partisions
    //1            → replication factor (local)
}
