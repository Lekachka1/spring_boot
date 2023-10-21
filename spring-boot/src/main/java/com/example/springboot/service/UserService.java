package com.example.springboot.service;
import com.example.springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(int id);
    void deleteUser(int id);
    void addUser(User user);
    void editUser(User user);
}