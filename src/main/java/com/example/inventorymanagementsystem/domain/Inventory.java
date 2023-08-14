package com.example.inventorymanagementsystem.domain;

public class Inventory {

    private int id;

    private String item_name;

    private int item_quantity;

    private ItemCategory item_category;
    private ItemLocation item_location;

    public Inventory(){}

    public Inventory(int id, String item_name, int item_quantity, ItemCategory item_category, ItemLocation item_location) {
        this.id = id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_category = item_category;
        this.item_location = item_location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public ItemCategory getItem_category() {
        return item_category;
    }

    public void setItem_category(ItemCategory item_category) {
        this.item_category = item_category;
    }

    public ItemLocation getItem_location() {
        return item_location;
    }

    public void setItem_location(ItemLocation item_location) {
        this.item_location = item_location;
    }
}
