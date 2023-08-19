package com.example.inventorymanagementsystem.commons;

public class InventorySql {

    public final static String selectInventory = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.category_id INNER JOIN item_location ON inventory.item_location_id = item_location.location_id";
    public final static String selectInventoryById = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.category_id INNER JOIN item_location ON inventory.item_location_id = item_location.location_id WHERE id = ?";
    public final static String insertInventory = "INSERT INTO inventory(id, item_name, item_quantity, item_category_id ,item_location_id) VALUES (?, ?,  ?, ?, ?)";
    /*public final static String updateInventory = "UPDATE inventory SET id=?, item_name=?, item_quantity=?, item_category_id=? ,item_location_id=? " +
                                                 "INNER JOIN item_category ON inventory.item_category_id = item_category.category_id" +
                                                 "SET category_id = ?, category_name =? " +
                                                 "INNER JOIN item_location ON inventory.item_location_id = item_location.location_id " +
                                                 "SET location_id =?, location_name = ?" +
                                                 "WHERE id = ? ";*/
    public final static String updateInventory = "UPDATE inventory SET id=?, item_name=?, item_quantity=?, item_category_id=? ,item_location_id=? WHERE id = ?";
    public final static String deleteInventory = "DELETE FROM inventory WHERE id = ?";
    public final static String selectByCategoryId = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.category_id INNER JOIN item_location ON inventory.item_location_id = item_location.location_id WHERE item_category_id = ?";
    public final static String selectByLocationId = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.category_id INNER JOIN item_location ON inventory.item_location_id = item_location.location_id WHERE item_location_id = ?";

    public final static String selectByCategoryAndLocationId = "SELECT * FROM inventory INNER JOIN item_category ON inventory.item_category_id = item_category.category_id INNER JOIN item_location ON inventory.item_location_id = item_location.location_id WHERE item_location_id = ? OR item_category_id = ?";
}
