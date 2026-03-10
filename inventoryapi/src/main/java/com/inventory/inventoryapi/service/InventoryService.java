package com.inventory.inventoryapi.service;

import com.inventory.inventoryapi.entity.Inventory;
import com.inventory.inventoryapi.event.InventoryEvent;
import com.inventory.inventoryapi.event.OrderCreatedEvent;
import com.inventory.inventoryapi.producers.InventoryConfirmedProducer;
import com.inventory.inventoryapi.repo.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepo  inventoryRepo;

    private final InventoryConfirmedProducer inventoryConfirmedProducer;

    public  InventoryService (InventoryRepo inventoryRepo, InventoryConfirmedProducer inventoryConfirmedProducer){
        this.inventoryRepo = inventoryRepo;
        this.inventoryConfirmedProducer = inventoryConfirmedProducer;
    }
    public void  processOrder(OrderCreatedEvent event){
        Optional<Inventory> optionalInventory=inventoryRepo.findByProductName(event.getProductName());

        if(optionalInventory.isPresent()) {
            Inventory inventory=optionalInventory.get();
            if(inventory.getQuantity()>=event.getQuantity()) {
                inventory.setQuantity(inventory.getQuantity()-event.getQuantity());
                inventoryRepo.save(inventory);
                System.out.println("hii i am inside the inventory and about to send the email");

                inventoryConfirmedProducer.sendInventoryConfirmedEvent(new InventoryEvent(event.getOrderId(),"tanvirpirjada03@gmail.com","CONFIRMED"));
            }else{

                System.out.println("hii i am inside the inventory sorry i am failed");

                inventoryConfirmedProducer.sendInventoryFailedEvent(new InventoryEvent(event.getOrderId(),"tanvirpirjada03@gmail.com","FAILED"));
            }
        }
    }
}
