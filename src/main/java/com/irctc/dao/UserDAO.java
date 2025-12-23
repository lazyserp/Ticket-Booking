package com.irctc.dao;

import com.irctc.entities.User;
import com.irctc.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {

    // Save a User to the Database
    @Override
    public void saveUser(User user) {
        // The SQL template with '?' placeholders
        String sql = "INSERT INTO users (user_id, name, password, hashed_password, tickets_booked) VALUES (?, ?, ?, ?, ?)";

        // Try-with-resources (Automatically closes the connection when done)
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Fill in the '?' blanks
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getHashedPassword());
            pstmt.setString(5, "[]"); // Empty JSON array for tickets initially

            // Execute the update (for INSERT, UPDATE, DELETE)
            int rowsAffected = pstmt.executeUpdate();
            
            System.out.println("User saved to DB! Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // We found a user! Map the DB row to our Java Object
                return new User(
                    rs.getString("user_id"),
                    rs.getString("name"),
                    rs.getString("password"), // In real app, check hashed
                    rs.getString("hashed_password"),
                    new ArrayList<>() // We will fix ticket fetching later
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if user not found
    }
}