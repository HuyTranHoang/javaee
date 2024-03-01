package com.ebook.dao;

import com.ebook.entity.User;

import java.util.List;

public class UserDAO extends JpaDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public User find(Object id) {
        return super.find(id);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public void delete(Object id) {
        super.delete(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    public List<User> findAllWithHQL() {
        return super.findAllWithHQL("User.findAll");
    }

    public long countWithHQL() {
        return super.countWithHQL("User.count");
    }
}
