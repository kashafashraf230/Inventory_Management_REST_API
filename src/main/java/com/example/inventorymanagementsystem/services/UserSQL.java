package com.example.inventorymanagementsystem.services;

public class UserSQL {

    public static final String selectUsers = "SELECT * FROM userroles WHERE username = ? AND PASSWORD = ?";
}
