package com.irctc.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import com.irctc.entities.Booking;
import com.irctc.entities.Ticket;
import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.services.BookingService;


public class BookingServiceImpl implements BookingService 
{

    @Override
    public Boolean bookTicket(User user, Train train, List<String> passengerNames) {
        List<Ticket> tickets = new ArrayList<>();
        String bookingId = UUID.randomUUID().toString();

        for(String names:passengerNames)
        {
            Ticket ticket = new Ticket(UUID.randomUUID().toString(),user.getUserId(),train.getStations().get(0),train.getStations().get(train.getStations().size()-1),new Date(),train);
            tickets.add(ticket);
            
        }

        Booking booking  = new Booking(bookingId, user.getUserId(), train.getTrainId(), tickets);
        user.getTicketsBooked().addAll(tickets);
        


        return true;

    }
    

    
}
