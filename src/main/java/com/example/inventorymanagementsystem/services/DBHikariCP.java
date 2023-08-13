package com.example.inventorymanagementsystem.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBHikariCP {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static final String Url ="jdbc:mysql://localhost:3306/inventorysystem";
    private static final String username = "root";
    private static final String password = "admin";

    public static DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Url);
        config.setUsername(username);
        config.setPassword(password);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new HikariDataSource(config);
    }

}
