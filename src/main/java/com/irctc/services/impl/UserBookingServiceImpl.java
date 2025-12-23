package com.irctc.services.impl;

import com.irctc.entities.User;
import com.irctc.services.UserBookingService;
import com.irctc.dao.IUserDAO;
import com.irctc.dao.InMemoryUserDAO;

public class UserBookingServiceImpl implements UserBookingService {

    private User user; // Stores the currently logged-in user
    private IUserDAO userDAO; // Used to talk to the Database

    // Constructor 1: If we already have a user (e.g. passed from Main)
    public UserBookingServiceImpl(User user) {
        this.user = user;
        this.userDAO = new InMemoryUserDAO();
    }

    // Constructor 2: Default constructor (Normal startup)
    public UserBookingServiceImpl() {
        this.userDAO = new InMemoryUserDAO();
    }

    @Override
    public Boolean loginUser(String name, String password) {
        // Fetch user from Database instead of a local List
        User userFromDb = userDAO.getUserByName(name);
        
        if (userFromDb != null) {
            // Compare passwords
            if (userFromDb.getPassword().equals(password)) {
                this.user = userFromDb; // Login success: Set the session
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean signUpUser(User user) {
        try {
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