package com.irctc.entities;
import java.util.*;

public class User 
{
    //User Attributes
    private String userId;
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;


    public User(String userId,String name,String password,String hashedPassword,List<Ticket> ticketsBooked)
    {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        //if null is passed creating an empty to list to prevetn nullPointerExcep
        this.ticketsBooked = (ticketsBooked != null) ? ticketsBooked : new ArrayList<>();

    } 

    public String getUserId() {
    return userId;
    }
    public String getName() {
        return name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    // Getters and Setters
    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void printTickets() {
        for (int i = 0; i < ticketsBooked.size(); i++) {
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    //Overriding toString for debugging , bcz when we print the object it should print what values is the
    //object holding without overrdiing it will print the memoryAddress of the objct.
    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' +", userId='" + userId + '\'' +'}';
    }
};
