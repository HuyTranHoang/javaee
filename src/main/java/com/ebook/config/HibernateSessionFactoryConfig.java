package com.ebook.config;

import com.ebook.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateSessionFactoryConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            // Create the SessionFactory from hibernate.cfg.xml
//            // sessionFactory = new Configuration().configure().buildSessionFactory();
//            try {
//                Properties prop = getProperties();
//
//                Configuration configuration = new Configuration();
//                configuration.setProperties(prop);
//
//                // Models..
//                configuration.addAnnotatedClass(User.class);
//                configuration.addAnnotatedClass(Category.class);
//                configuration.addAnnotatedClass(Product.class);
//                configuration.addAnnotatedClass(Customer.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties())
//                        .build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//                return sessionFactory;
//            } catch (Throwable ex) {
//                System.err.println("Initial SessionFactory creation failed." + ex);
//                throw new ExceptionInInitializerError(ex);
//            }
//        }
//
//        return sessionFactory;
//    }
//
//    private static Properties getProperties() {
//        Properties prop = new Properties();
//        prop.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false");
//        prop.setProperty(Environment.USER, "root");
//        prop.setProperty(Environment.PASS, "g9v$;6~CmQRCq#U");
//        prop.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//        prop.setProperty(Environment.SHOW_SQL, "true");
//        prop.setProperty(Environment.FORMAT_SQL, "true");
//        prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//        prop.setProperty(Environment.HBM2DDL_AUTO, "update");
//        return prop;
//    }
}
