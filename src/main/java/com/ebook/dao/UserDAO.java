package com.ebook.dao;

import com.ebook.entity.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public User findByEmail(String email) {
        User user = null;
        try (Session session = super.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root)
                    .where(builder.equal(root.get("email"), email));
            user = session.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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

    public User findByEmailWithHQL(String email) {
        return super.findOneWithHQL("User.findByEmail", "email", email);
    }
}
