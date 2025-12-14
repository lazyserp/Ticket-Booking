package com.irctc.services.impl;

import com.irctc.entities.User;
import com.irctc.services.UserBookingService;
import java.util.*;
import java.util.function.*;

public class UserBookingServiceImpl implements UserBookingService
{
    //User object that denotes current user
    private User user;

    //temp database
    private List<User> userList;

    // paramterized constructor is used if a user is based when object is instantiated,
    // this initialises our temporary database; 
    public UserBookingServiceImpl(User user)
    {
        this.user = user;
        this.userList = new ArrayList<>();
    }

    //Default constructor is called even if a user is not passed, this is to initialise the db 
    public UserBookingServiceImpl()
    {
        this.userList = new ArrayList<>();
    }

    // Overriding the login function
    @Override
    public Boolean loginUser()
    {
        Scanner ss = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = ss.next();

        System.out.println("Enter Password: ");
        String password = ss.next();


        //using Streams ( Java 8 )
        Optional<User> foundUser = userList.stream().filter(user -> user.getName().equals(name) && user.getPassword().equals(password)).findFirst();
        if (foundUser.isPresent())
        {
            this.user = foundUser.get();
            return true;
        }

        //Same thing but with normal function
        // for(var i : userList)
        // {
        //     if (i.getName().equals(name) && i.getPassword().equals(password))
        //         {
        //             this.user = i;
        //             return true;

        //         }
        // }
        return false;
    }

    //Overriding the signup function
    @Override
    public Boolean signUpUser(User user)
    {
        try{
            userList.add(user);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override 
    public void fetchBookings()
    {
        user.printTickets();
    }

    @Override
    public Boolean cancelTicket(String ticketId)
    {
        return false;
    }

    //A function to print the users currently in db
    public List<User> getUsers()
    {
        return userList;
    }


}

