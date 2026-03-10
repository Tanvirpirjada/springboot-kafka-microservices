package com.inventory.inventoryapi.producers;

import com.inventory.inventoryapi.event.InventoryEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class InventoryConfirmedProducer {

    private final KafkaTemplate<String, InventoryEvent> template;

    public InventoryConfirmedProducer(KafkaTemplate<String, InventoryEvent> template){
        this.template = template;
    }

    public void  sendInventoryConfirmedEvent(InventoryEvent event){
            template.send("inventory-confirmed",event);
    }

    public void  sendInventoryFailedEvent(InventoryEvent event){
        template.send("inventory-failed",event);
    }
}
