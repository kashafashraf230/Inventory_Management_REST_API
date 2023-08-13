package com.example.inventorymanagementsystem.model;

public class UserRoles {

    private int userId;
    private String userRole;

    private String username;

    private String password;

    public UserRoles(){}


    public UserRoles(int userId, String userRole, String username, String password) {
        this.userId = userId;
        this.userRole = userRole;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
