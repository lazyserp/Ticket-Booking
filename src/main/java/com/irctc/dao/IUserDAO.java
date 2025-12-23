package com.irctc.dao;

import com.irctc.entities.User;

public interface IUserDAO {
    void saveUser(User user);
    User getUserByName(String name);
}
