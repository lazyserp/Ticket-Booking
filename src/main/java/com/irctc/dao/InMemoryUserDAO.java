package com.irctc.dao;

import com.irctc.entities.User;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDAO implements IUserDAO {

    private static Map<String, User> users = new HashMap<>();

    @Override
    public void saveUser(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User getUserByName(String name) {
        return users.get(name);
    }
}
