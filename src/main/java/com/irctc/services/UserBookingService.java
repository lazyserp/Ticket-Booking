package com.irctc.services;
import com.irctc.entities.User;


public interface UserBookingService
{
    Boolean loginUser();
    Boolean signUpUser(User user);
    void fetchBookings();
    User getUser();

    
    Boolean cancelTicket(String ticketId);
}


