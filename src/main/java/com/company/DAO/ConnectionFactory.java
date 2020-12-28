package com.company.DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PASS = "root";
    private static Connection instance;

    static{
        try{
            Class.forName( "com.mysql.cj.jdbc.Driver").newInstance();
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (instance == null) {
            String url = "jdbc:mysql://localhost:3307/headphones?serverTimezone=UTC";
            instance = DriverManager.getConnection(url, DB_USER_NAME, DB_USER_PASS);
        }
        return instance;
    }
    public static synchronized MongoDatabase getConnectionMongo() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("headphone");
        return database;
    }
}
