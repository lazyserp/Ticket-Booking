package com.irctc.entities;
import java.util.*;

public class Ticket 
{
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private Train train;

    public Ticket(String ticketId,String userId,String source,String destination,Date dateOfTravel,Train train)
    {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }


    //Overriding toString for debugging , bcz when we print the object it should print what values is the
    //object holding without overrdiing it will print the memoryAddress of the objct.
    public String getTicketInfo()
    {
        return String.format("Ticket ID: %s | User ID: %s | Source: %s | Destination: %s | Date: %s", ticketId, userId, source, destination, dateOfTravel.toString());
    }
    
}
