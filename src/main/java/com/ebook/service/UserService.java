package com.ebook.service;

import com.ebook.dao.UserDAO;
import com.ebook.entity.User;
import com.ebook.utils.EncryptUtil;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
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
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        String hashPassword = EncryptUtil.encryptPassword(user.getPassword());
        user.setPassword(hashPassword);

        userDAO.create(user);
    }

    public void updateUser(User user) {
        User existingUser = userDAO.find(user.getUserId());

        if (existingUser == null) {
            throw new IllegalArgumentException("User with id " + user.getUserId() + " does not exist");
        }

        if (existingUser.getUserId() != user.getUserId()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        String hashPassword = EncryptUtil.encryptPassword(user.getPassword());
        user.setPassword(hashPassword);

        userDAO.update(user);
    }

    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    public boolean authenticateUser(String email, String password) {
        String hashPassword = EncryptUtil.encryptPassword(password);

        User user = userDAO.findByEmailAndPasswordWithHQL(email, hashPassword);
        return user != null;
    }

}
