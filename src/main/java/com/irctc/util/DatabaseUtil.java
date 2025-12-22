package com.irctc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil 
{
    private static final String URL = "jdbc:mysql://localhost:3306/irctc";
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345678";

    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Loading the driver
        } catch (ClassNotFoundException e)
        {
            System.err.println("MySQL Driver not found! Check your pom.xml");
            e.printStackTrace();
        }
    }

    //  The Method to Get a Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
