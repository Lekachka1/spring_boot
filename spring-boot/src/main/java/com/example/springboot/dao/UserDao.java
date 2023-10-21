package com.example.springboot.dao;

import com.example.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void editUser(User user);
    void addUser(User user);
    User getUser(int id);
    void deleteUser(int id);
}
