package com.irctc.controller;

import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.services.BookingService;
import com.irctc.services.TrainService;
import com.irctc.services.UserBookingService;
import com.irctc.util.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuController {

    private final UserBookingService userBookingService;
    private final TrainService trainService;
    private final BookingService bookingService;
    private final Scanner scanner;

    public MenuController(UserBookingService userBookingService, TrainService trainService,
            BookingService bookingService) {
        this.userBookingService = userBookingService;
        this.trainService = trainService;
        this.bookingService = bookingService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                MenuOption option = MenuOption.fromId(choice);

                if (option == null) {
                    System.out.println("Invalid choice, please try again.");
                    continue;
                }

                switch (option) {
                    case SIGN_UP -> handleSignUp();
                    case LOGIN -> handleLogin();
                    case FETCH_BOOKINGS -> handleFetchBookings();
                    case SEARCH_TRAINS -> handleSearchTrains();
                    case BOOK_TICKET -> handleBookTicket();
                    case EXIT -> {
                        System.out.println("Exiting the App...");
                        exit = true;
                    }
                    default -> System.out.println("Feature not implemented yet.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Train Booking System ---");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getOptionId() + ". " + option.getLabel());
        }
        System.out.print("Choose Option: ");
    }

    private void handleSignUp() {
        System.out.println("Enter username to sign up: ");
        String name = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();

        User user = new User(UUID.randomUUID().toString(), name, password, password, new ArrayList<>());
        if (userBookingService.signUpUser(user)) {
            System.out.println("User signed up successfully!");
        } else {
            System.out.println("Sign up failed.");
        }
    }

    private void handleLogin() {
        if (userBookingService.loginUser()) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Login Failed.");
        }
    }

    private void handleFetchBookings() {
        userBookingService.fetchBookings();
    }

    private void handleSearchTrains() {
        System.out.println("Enter Source Station:");
        String source = scanner.next();
        System.out.println("Enter Destination Station");
        String destination = scanner.next();

        List<Train> trains = trainService.searchTrain(source, destination);
        if (trains.isEmpty()) {
            System.out.println("No trains found between " + source + " and " + destination);
        } else {
            System.out.println("Found " + trains.size() + " trains:");
            trains.forEach(t -> System.out.println(t.getTrainInfo()));
        }
    }

    private void handleBookTicket() {
        User currentUser = userBookingService.getUser();
        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.println("Enter Train Number to book:");
        String trainNum = scanner.next();
        Train train = trainService.getTrain(trainNum);

        if (train == null) {
            System.out.println("Train not found!");
            return;
        }

        System.out.println("Enter Passenger Name:");
        String passengerName = scanner.next();
        List<String> passengers = new ArrayList<>();
        passengers.add(passengerName);

        try {
            bookingService.bookTicket(currentUser, train, passengers);
            System.out.println("Booking Successful!");
        } catch (Exception e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}
