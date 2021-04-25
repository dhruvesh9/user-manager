package com.github.dhruvesh9.usermanager.service;

import java.util.List;

import com.github.dhruvesh9.usermanager.model.User;

public interface UserService {
    List<User> getAllUsers();

    String createUser(User user);

    User getUserById(String _id);
}
