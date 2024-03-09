package com.ebook.dao;

import com.ebook.entity.User;
import lombok.extern.java.Log;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;


@Log
public class UserDAO extends JpaDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String email) {
        User user = null;
        try (Session session = super.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("email"), email));
            user = session.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "Error finding user by email: " + email, e);
        }
        return user;
    }

    public List<User> findAll(String searchString) {
        List<User> users = null;
        try (Session session = super.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);

            if (searchString != null && !searchString.isEmpty()) {
                criteria.where(builder.or(
                        builder.like(root.get("email"), "%" + searchString + "%"),
                        builder.like(root.get("fullName"), "%" + searchString + "%")
                ));
            }
            users = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            log.log(Level.WARNING, "Error finding all users", e);
        }
        return users;
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
