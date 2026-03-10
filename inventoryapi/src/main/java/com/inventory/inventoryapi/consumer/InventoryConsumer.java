package com.inventory.inventoryapi.consumer;

import com.inventory.inventoryapi.event.OrderCreatedEvent;
import com.inventory.inventoryapi.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {


    private final InventoryService inventoryService;


    public InventoryConsumer(InventoryService inventoryService){

        this.inventoryService = inventoryService;
    }
    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    public void consume(OrderCreatedEvent event) {

        System.out.println("hii i am inside the inventory");
    inventoryService.processOrder(event);

    }
}
