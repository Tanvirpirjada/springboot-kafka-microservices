package com.order.orderapi.kafka;

import com.order.orderapi.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;


@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public  OrderProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event){
        kafkaTemplate.send("order-topic",event);
    }
}
