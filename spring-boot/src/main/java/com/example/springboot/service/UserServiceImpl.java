package com.example.springboot.service;

import com.example.springboot.dao.UserDao;
import com.example.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User readUser(int id) {
        return userDao.readUser(id);
    }


    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = userDao.deleteUser(id);

    }
    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User existingUser = readUser(user.getId());
        if(existingUser != null) {
            return userDao.updateUser(user);
        } else {
            return null;
        }
    }
}
