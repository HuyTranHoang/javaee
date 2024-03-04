package com.ebook.dao;

import com.ebook.config.HibernateSessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaDAO<T> {
    protected final SessionFactory sessionFactory;
    private final Class<T> type;

    public JpaDAO(Class<T> type) {
        this.type = type;
        this.sessionFactory = HibernateSessionFactoryConfig.getSessionFactory();
    }

    public T create(T t) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(t);
            session.flush();
            session.refresh(t);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return t;
    }

    public T update(T t) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.update(t);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return t;
    }

    public T find(Object id) {
        T t = null;
        try (Session session = sessionFactory.openSession()) {
            t = session.get(type, (int) id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public void delete(Object id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            T t = session.get(type, (int) id);

            if (t != null)
                session.delete(t);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    Hibernate Criteria Query

    public List<T> findAll() {
        List<T> list = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            criteria.from(type);
//            criteria.select(criteria.from(type));
            list = session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public long count() {
        long count = 0;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
            Root<T> root = criteria.from(type);
            criteria.select(builder.count(root));
            count = session.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // HQL

    public List<T> findAllWithHQL(String hql) {
        List<T> list = null;
        try (Session session = sessionFactory.openSession()) {
            list = session.createNamedQuery(hql, type).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public long countWithHQL(String hql) {
        long count = 0;
        try (Session session = sessionFactory.openSession()) {
            count = (long) session.createNamedQuery(hql).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public T findOneWithHQL(String hql, String paramName, Object paramValue) {
        T t = null;
        try (Session session = sessionFactory.openSession()) {
            t = session.createNamedQuery(hql, type)
                    .setParameter(paramName, paramValue)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
