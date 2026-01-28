package com.irctc.services.impl;

import com.irctc.entities.User;
import com.irctc.services.UserBookingService;
import com.irctc.dao.UserDAO;
import com.irctc.util.PasswordUtil;
import java.util.Scanner;

public class UserBookingServiceImpl implements UserBookingService {

    private User user; // Stores the currently logged-in user
    private UserDAO userDAO; // Used to talk to the Database

    // Constructor 1: If we already have a user (e.g. passed from Main)
    public UserBookingServiceImpl(User user) {
        this.user = user;
        this.userDAO = new UserDAO();
    }

    // Constructor 2: Default constructor (Normal startup)
    public UserBookingServiceImpl() {
        this.userDAO = new UserDAO();
    }

    @Override
    public Boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String name = scanner.next();

        System.out.println("Enter Password: ");
        String password = scanner.next(); // Plain text input

        // Fetch user from Database
        User userFromDb = userDAO.getUserByName(name);

        if (userFromDb != null) {
            // Validate using Hash
            if (PasswordUtil.checkPassword(password, userFromDb.getHashedPassword())) {
                this.user = userFromDb;
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean signUpUser(User user) {
        try {
            // Hash the password before saving
            // Note: We assume the controller initially put the plain password in the
            // 'password' field.
            String plainPassword = user.getPassword();
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);

            user.setHashedPassword(hashedPassword);
            user.setPassword("PROTECTED"); // Clear plain text from memory/object

            userDAO.saveUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void fetchBookings() {
        if (user != null) {
            user.printTickets();
        } else {
            System.out.println("No user logged in.");
        }
    }

    @Override
    public Boolean cancelTicket(String ticketId) {
        // Future implementation
        return false;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}