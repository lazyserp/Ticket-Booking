package com.irctc.services;

import java.util.List;
import com.irctc.entities.Train;
import com.irctc.entities.User;

public interface BookingService
{
    Boolean bookTicket(User user,Train train,List<String> passengerNames);
}