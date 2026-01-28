package com.irctc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/irctc";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // Volatile variable for Double-Checked Locking
    private static volatile DatabaseUtil instance;

    // Private constructor to prevent instantiation
    private DatabaseUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found! Check your pom.xml");
            e.printStackTrace();
        }
    }

    // Public method to get the single instance
    public static DatabaseUtil getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtil.class) {
                if (instance == null) {
                    instance = new DatabaseUtil();
                }
            }
        }
        return instance;
    }

    // Method to get connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
