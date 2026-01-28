package com.irctc.services;

import java.util.List;
import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.exception.BookingFailedException;

public interface BookingService {
    void bookTicket(User user, Train train, List<String> passengerNames) throws BookingFailedException;
}