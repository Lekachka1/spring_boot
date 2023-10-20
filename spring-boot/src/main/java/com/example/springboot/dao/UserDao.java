package com.example.springboot.dao;

import com.example.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User updateUser(User user);
    User saveUser(User user);
    User readUser(int id);
    User deleteUser(int id);
}
