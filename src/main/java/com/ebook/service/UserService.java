package com.ebook.service;

import com.ebook.dao.UserDAO;
import com.ebook.entity.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers(String searchString) {
        return userDAO.findAll(searchString);
    }

    public User getUserById(int id) {
        return userDAO.find(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public void insertUser(User user) {
        userDAO.create(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(int id) {
        userDAO.delete(id);
    }

}
