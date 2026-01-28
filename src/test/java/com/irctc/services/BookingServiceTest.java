package com.irctc.services;

import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.exception.BookingFailedException;
import com.irctc.services.impl.BookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingServiceTest {

    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        bookingService = new BookingServiceImpl();
    }

    @Test
    public void testBookTicket_Success() {
        User user = new User("u1", "Aman", "password", "hashed", new ArrayList<>());
        Train train = new Train("t1", "123", new ArrayList<>(), Map.of("Source", "10:00"), List.of("Source", "Dest"));
        List<String> passengers = List.of("Passenger1");

        Assertions.assertDoesNotThrow(() -> {
            bookingService.bookTicket(user, train, passengers);
        });

        Assertions.assertEquals(1, user.getTicketsBooked().size());
    }

    @Test
    public void testBookTicket_Failure_NullUser() {
        Train train = new Train("t1", "123", new ArrayList<>(), Map.of(), List.of());
        List<String> passengers = List.of("Passenger1");

        BookingFailedException exception = Assertions.assertThrows(BookingFailedException.class, () -> {
            bookingService.bookTicket(null, train, passengers);
        });

        Assertions.assertTrue(exception.getMessage().contains("Invalid booking request"));
    }

    @Test
    public void testBookTicket_Failure_NoPassengers() {
        User user = new User("u1", "Aman", "password", "hashed", new ArrayList<>());
        Train train = new Train("t1", "123", new ArrayList<>(), Map.of(), List.of());
        List<String> passengers = new ArrayList<>(); // Empty

        BookingFailedException exception = Assertions.assertThrows(BookingFailedException.class, () -> {
            bookingService.bookTicket(user, train, passengers);
        });

        Assertions.assertTrue(exception.getMessage().contains("No passengers provided"));
    }
}
