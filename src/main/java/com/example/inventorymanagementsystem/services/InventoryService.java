package com.example.inventorymanagementsystem.services;

import com.example.inventorymanagementsystem.commons.DBHikariCP;
import com.example.inventorymanagementsystem.commons.InventorySql;
import com.example.inventorymanagementsystem.domain.Inventory;
import com.example.inventorymanagementsystem.domain.ItemCategory;
import com.example.inventorymanagementsystem.domain.ItemLocation;
import com.example.inventorymanagementsystem.resources.InventoryResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;


public class InventoryService {


   // private final CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
    //private final Cache<Integer, Inventory> inventoryCache = cacheManager.getCache("inventoryCache", Integer.class, Inventory.class);
    //private final Cache<String, List> inventoryCacheList = cacheManager.getCache("inventoryCacheList", String.class, List.class);
    public InventoryService(){

    }


    public List<Inventory> getInventory() {

        /*try{
            List<Inventory> cachedInventory = inventoryCacheList.get("allInventory");
            System.out.println("1");
            if(cachedInventory != null){
                System.out.println("2");
                return cachedInventory;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.selectInventory);
            ResultSet resultSet = ps.executeQuery()) {

            List<Inventory> resultInventory = new ArrayList<>();

            while (resultSet.next()) {

                Inventory inventory = new Inventory();
                ItemCategory ic = new ItemCategory();
                ItemLocation il = new ItemLocation();

                inventory.setId(resultSet.getInt("id"));
                inventory.setItem_name(resultSet.getString("item_name"));
                inventory.setItem_quantity(resultSet.getInt("item_quantity"));
                ic.setId(resultSet.getInt("category_id"));
                ic.setCategory_name(resultSet.getString("category_name"));
                inventory.setItem_category(ic);
                il.setId(resultSet.getInt("location_id"));
                il.setLocation_name(resultSet.getString("location_name"));
                inventory.setItem_location(il);

                resultInventory.add(inventory);

            }
            System.out.println("3");
            InventoryResource.count = resultInventory.size();
           // inventoryCacheList.put("allInventory", resultInventory);
            return resultInventory;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getInventoryById(int id){

        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.selectInventoryById)){

            ps.setInt(1, id);

            try( ResultSet resultSet = ps.executeQuery()){ if(resultSet.next()){

                Inventory inventoryId = new Inventory();
                ItemCategory ic = new ItemCategory();
                ItemLocation il = new ItemLocation();

                inventoryId.setId(resultSet.getInt("id"));
                inventoryId.setItem_name(resultSet.getString("item_name"));
                inventoryId.setItem_quantity(resultSet.getInt("item_quantity"));
                ic.setId(resultSet.getInt("category_id"));
                ic.setCategory_name(resultSet.getString("category_name"));
                inventoryId.setItem_category(ic);
                il.setId(resultSet.getInt("location_id"));
                il.setLocation_name(resultSet.getString("location_name"));
                inventoryId.setItem_location(il);

                return inventoryId;

            }
                return "No inventory exists with such ID";
            }

        }catch(Exception e){e.printStackTrace();}

        return "Error";
    }

    public boolean createInventory(Inventory inventory){

       try(Connection connection = DBHikariCP.getDataSource().getConnection();
           PreparedStatement ps = connection.prepareStatement(InventorySql.insertInventory)){

        ps.setInt(1, inventory.getId());
        ps.setString(2, inventory.getItem_name());
        ps.setInt(3, inventory.getItem_quantity());
        ps.setInt(4, inventory.getItem_category().getId());
        ps.setInt(5, inventory.getItem_location().getId());

        int result = ps.executeUpdate();

        if (result > 0) return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
        return false;
    }


    public List<Inventory> getListByCategoryId(int id) {

        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.selectByCategoryId)) {

            List<Inventory> resultInventory = new ArrayList<>();

            ps.setInt(1, id);

            try( ResultSet resultSet = ps.executeQuery()){

                while (resultSet.next()) {

                Inventory inventory = new Inventory();
                ItemCategory ic = new ItemCategory();
                ItemLocation il = new ItemLocation();
                inventory.setId(resultSet.getInt("id"));
                inventory.setItem_name(resultSet.getString("item_name"));
                inventory.setItem_quantity(resultSet.getInt("item_quantity"));
                ic.setId(resultSet.getInt("category_id"));
                ic.setCategory_name(resultSet.getString("category_name"));
                inventory.setItem_category(ic);
                il.setId(resultSet.getInt("location_id"));
                il.setLocation_name(resultSet.getString("location_name"));
                inventory.setItem_location(il);
                resultInventory.add(inventory);

            }

                return resultInventory;}


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Inventory> getListByLocationId(int id) {

        try (Connection connection = DBHikariCP.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(InventorySql.selectByLocationId)){

            List<Inventory> resultInventory = new ArrayList<>();

            ps.setInt(1, id);

            try(ResultSet resultSet = ps.executeQuery()){ while (resultSet.next()) {

                Inventory inventory = new Inventory();
                ItemCategory ic = new ItemCategory();
                ItemLocation il = new ItemLocation();
                inventory.setId(resultSet.getInt("id"));
                inventory.setItem_name(resultSet.getString("item_name"));
                inventory.setItem_quantity(resultSet.getInt("item_quantity"));
                ic.setId(resultSet.getInt("category_id"));
                ic.setCategory_name(resultSet.getString("category_name"));
                inventory.setItem_category(ic);
                il.setId(resultSet.getInt("location_id"));
                il.setLocation_name(resultSet.getString("location_name"));
                inventory.setItem_location(il);
                resultInventory.add(inventory);

            }
                return resultInventory;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Inventory> getListByCategoryAndLocationId(int lid, int cid) {

        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.selectByCategoryAndLocationId);) {

            List<Inventory> resultInventory = new ArrayList<>();
            ps.setInt(1, lid);
            ps.setInt(2,cid);

            try( ResultSet resultSet = ps.executeQuery()){

                while (resultSet.next()) {

                    Inventory inventory = new Inventory();
                    ItemCategory ic = new ItemCategory();
                    ItemLocation il = new ItemLocation();
                    inventory.setId(resultSet.getInt("id"));
                    inventory.setItem_name(resultSet.getString("item_name"));
                    inventory.setItem_quantity(resultSet.getInt("item_quantity"));
                    ic.setId(resultSet.getInt("category_id"));
                    ic.setCategory_name(resultSet.getString("category_name"));
                    inventory.setItem_category(ic);
                    il.setId(resultSet.getInt("location_id"));
                    il.setLocation_name(resultSet.getString("location_name"));
                    inventory.setItem_location(il);
                    resultInventory.add(inventory);

                }

                return resultInventory;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInventory(int id, Inventory inventory){
        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.updateInventory)){

            ps.setInt(1, id);
            ps.setString(2, inventory.getItem_name());
            ps.setInt(3, inventory.getItem_quantity());
            ps.setInt(4, inventory.getItem_category().getId());
            ps.setInt(5, inventory.getItem_location().getId());
            ps.setInt(6, id);

            int result = ps.executeUpdate();
            if (result > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteInventory(int id){
        try(Connection connection = DBHikariCP.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(InventorySql.deleteInventory)){

        ps.setInt(1, id);
        int resultSet = ps.executeUpdate();
        if(resultSet > 0){
            return true;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }
}
