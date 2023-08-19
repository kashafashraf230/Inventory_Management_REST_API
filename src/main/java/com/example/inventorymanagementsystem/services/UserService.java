package com.example.inventorymanagementsystem.services;

import com.example.inventorymanagementsystem.commons.DBHikariCP;
import com.example.inventorymanagementsystem.commons.UserSQL;
import com.example.inventorymanagementsystem.domain.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public UserService(){

    }

    public UserRoles isUser(String username, String password){

        try (Connection userConnection = DBHikariCP.getDataSource().getConnection();
             PreparedStatement ps = userConnection.prepareStatement(UserSQL.selectUsers)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try( ResultSet resultSet = ps.executeQuery()){
                if(resultSet.next()) {
                   UserRoles user = new UserRoles();
                   user.setUserId(resultSet.getInt("user_id"));
                   user.setUserRole(resultSet.getString("user_role"));
                   user.setUsername(resultSet.getString("username"));
                   user.setPassword(resultSet.getString("password"));
                   return user;
                }
                return null;
            }

        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }

}
