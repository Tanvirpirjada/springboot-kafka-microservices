package com.inventory.inventoryapi.repo;


import com.inventory.inventoryapi.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByProductName(String productName);
}
