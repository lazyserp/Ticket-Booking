package com.irctc;

import com.irctc.controller.MenuController;
import com.irctc.entities.Train;
import com.irctc.services.BookingService;
import com.irctc.services.TrainService;
import com.irctc.services.UserBookingService;
import com.irctc.services.impl.BookingServiceImpl;
import com.irctc.services.impl.TrainServiceImpl;
import com.irctc.services.impl.UserBookingServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Train Ticket Booking System...");

        // Dependency Injection
        UserBookingService userBookingService = new UserBookingServiceImpl();
        TrainService trainService = new TrainServiceImpl();
        BookingService bookingService = new BookingServiceImpl();

        // Load Dummy Data (Bootstrap)
        loadDummyData(trainService);

        // Start Controller
        MenuController controller = new MenuController(userBookingService, trainService, bookingService);
        controller.start();
    }

    private static void loadDummyData(TrainService trainService) {
        List<String> stops = List.of("Delhi", "Agra", "Bhopal", "Mumbai");
        Map<String, String> times = Map.of("Delhi", "10:00", "Agra", "12:00", "Bhopal", "14:00", "Mumbai", "20:00");
        Train t1 = new Train(UUID.randomUUID().toString(), "12345", new ArrayList<>(), times, stops);
        trainService.addTrain(t1);
    }
}