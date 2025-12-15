package com.irctc.entities;

import java.util.List;

public class Booking 
{
    private String bookingId;
    private String userId;
    private String trainId;
    private List<Ticket> tickets; //one booking can have multiple tikcets
    private String status; // confiremd or waiting
    public String getBookingId() {
        return bookingId;
    }
    public Booking(String bookingId, String userId, String trainId, List<Ticket> tickets) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.trainId = trainId;
        this.tickets = tickets;
        this.status = "CONFIRMED";
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTrainId() {
        return trainId;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
