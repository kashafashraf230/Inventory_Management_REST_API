package com.example.inventorymanagementsystem.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserCrud {

    Connection userConnection;

    public UserCrud(){
        try{
            userConnection = DBHikariCP.getDataSource().getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setUserConnection(Connection userConnection) {
        this.userConnection = userConnection;
    }

    public Connection getUserConnection(){
        return userConnection;
    }

    public boolean isUser(String username, String password){

        try {

            PreparedStatement ps = userConnection.prepareStatement(UserSQL.selectUsers);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            return false;
        }catch(Exception e ){
            e.printStackTrace();
        }
        return false;
    }

}
