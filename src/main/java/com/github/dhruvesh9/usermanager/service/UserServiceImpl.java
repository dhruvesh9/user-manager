package com.github.dhruvesh9.usermanager.service;

import java.util.List;
import java.util.Optional;

import com.github.dhruvesh9.usermanager.dao.UserDao;
import com.github.dhruvesh9.usermanager.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String createUser(User user) {
        User savedUser = userDao.save(user);
        return savedUser.get_id();
    }

    @Override
    public User getUserById(String _id) {

        User user = null;

        Optional<User> userList = userDao.findById(_id);

        if (userList != null) {
            user = userList.get();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
