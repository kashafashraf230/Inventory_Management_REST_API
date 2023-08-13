package com.example.inventorymanagementsystem.model;

public class ItemCategory {

    private int id;

    private String category_name;

    public ItemCategory(){}
    public ItemCategory(int id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
