package com.example.inventorymanagementsystem.services;

import com.example.inventorymanagementsystem.domain.Inventory;
import com.example.inventorymanagementsystem.domain.ItemCategory;
import com.example.inventorymanagementsystem.domain.ItemLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InventoryServiceTest {

    InventoryService inventoryService;
    ItemCategory ic = new ItemCategory(22, "Laptop");
    ItemLocation il = new ItemLocation(7, "Phoenix");
    Inventory inventory = new Inventory(1, "iPhone 13", 22, ic, il);

    @BeforeEach
    void setUp() {
       inventoryService = new InventoryService();
    }


    @Test
    void getInventoryById() {

        Inventory result = (Inventory) inventoryService.getInventoryById(1);

        assertEquals(inventory.getId(), result.getId());
        assertEquals(inventory.getItem_name(), result.getItem_name());
        assertEquals(inventory.getItem_quantity(), result.getItem_quantity());
        assertEquals(inventory.getItem_category().getId(), result.getItem_category().getId());
        assertEquals(inventory.getItem_category().getCategory_name(), result.getItem_category().getCategory_name());
        assertEquals(inventory.getItem_location().getId(),result.getItem_location().getId() );
        assertEquals(inventory.getItem_location().getLocation_name(), result.getItem_location().getLocation_name());
    }


}