package com.irctc.services.impl;

import com.irctc.entities.Booking;
import com.irctc.entities.Ticket;
import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.exception.BookingFailedException;
import com.irctc.services.BookingService;
import com.irctc.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class BookingServiceImpl implements BookingService {

    private final Logger logger = LoggerUtil.getLogger(BookingServiceImpl.class);

    @Override
    public void bookTicket(User user, Train train, List<String> passengerNames) throws BookingFailedException {
        if (user == null || train == null) {
            throw new BookingFailedException("Invalid booking request: User or Train is null.");
        }

        if (passengerNames == null || passengerNames.isEmpty()) {
            throw new BookingFailedException("No passengers provided.");
        }

        try {
            List<Ticket> tickets = new ArrayList<>();
            String bookingId = UUID.randomUUID().toString();

            for (String name : passengerNames) {
                // Ideally, Ticket should have passenger name, but reusing existing Entity
                // structure
                Ticket ticket = new Ticket(UUID.randomUUID().toString(), user.getUserId(),
                        train.getStations().get(0),
                        train.getStations().get(train.getStations().size() - 1),
                        new Date(), train);
                tickets.add(ticket);
            }

            Booking booking = new Booking(bookingId, user.getUserId(), train.getTrainId(), tickets);
            user.getTicketsBooked().addAll(tickets);

            logger.info("Booking successful for user: " + user.getName());

        } catch (Exception e) {
            logger.severe("Booking failed: " + e.getMessage());
            throw new BookingFailedException("Internal error during booking: " + e.getMessage());
        }
    }
}
