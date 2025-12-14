package com.irctc;

import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.services.TrainService;
import com.irctc.services.UserBookingService;
import com.irctc.services.impl.TrainServiceImpl;
import com.irctc.services.impl.UserBookingServiceImpl;


public class Main {
    public static void main(String[] args) {
        System.out.println("Running Train Ticket Booking System !");

        Scanner ss = new Scanner(System.in);
        int option = 0;

        //Initialising the service
        UserBookingService userBookingService = new UserBookingServiceImpl() ;
        TrainService trainService = new TrainServiceImpl();

        //Testing dummy rains
        List<String> stops = List.of("Delhi", "Agra", "Bhopal", "Mumbai"); // Order matters!
        Map<String, String> times = Map.of("Delhi", "10:00", "Agra", "12:00", "Bhopal", "14:00", "Mumbai", "20:00");
        Train t1 = new Train(UUID.randomUUID().toString(), "12345", new ArrayList<>(), times, stops);
        trainService.addTrain(t1);
        
        while ( option != 4 )
        {
            System.out.println("Choose Option");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Exit");


            option = ss.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter username to sign up: ");
                    String nameToSignUp = ss.next();

                    System.out.println("Enter password: ");
                    String passwordToSignUp = ss.next();

                    //Generate a random ID
                    User userToSignUp = new User(UUID.randomUUID().toString(), nameToSignUp,passwordToSignUp,passwordToSignUp,new ArrayList<>());
                    userBookingService.signUpUser(userToSignUp);
                    break;

                case 2:
                    if ( userBookingService.loginUser())
                    {
                        System.out.println("Login Successfull!");
                    }
                    else System.out.println("Login Failed");
                    break;

                case 3:
                    System.out.println("Fetching your bookings !");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Enter Source Station:");
                    String source = ss.next();

                    System.out.println("Enter Destination Station");
                    String destination = ss.next();

                    List<Train> trains = trainService.searchTrain(source, destination);

                    if (trains.isEmpty()) System.out.println("No trains found betwee n" + source + " and " + destination);
                    else
                    {
                        System.out.println("Found " + trains.size() + "trains: ");
                        trains.stream().forEach(t -> System.out.println(t.getTrainInfo()));
                    }
                    break;

                case 5:
                    System.out.println("Exiting the App");
                    break;
                default:
                    System.out.println("Enter valid choice");
            }


        }
    }
}