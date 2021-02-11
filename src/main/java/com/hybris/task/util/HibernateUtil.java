package com.hybris.task.util;

import com.hybris.task.entity.Order;
import com.hybris.task.entity.OrderItem;
import com.hybris.task.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static EntityManager em;

    public static EntityManager getEm() {
        if (em == null) {
            init();
        }
        return em;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            init();
        }
        return sessionFactory;
    }

    private static SessionFactory init() {

        try{
            Configuration configuration = new Configuration();
            Properties properties = new AppProperties();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, properties.getProperty(Environment.DRIVER, "org.hsqldb.jdbcDriver"));
            settings.put(Environment.URL, properties.getProperty(Environment.URL, "jdbc:hsqldb:mem:hybris;create=true"));
            settings.put(Environment.USER, properties.getProperty(Environment.USER, "as"));
            settings.put(Environment.PASS, properties.getProperty(Environment.PASS, ""));
            settings.put(Environment.DIALECT, properties.getProperty(Environment.DIALECT, "org.hibernate.dialect.HSQLDialect"));
            settings.put(Environment.SHOW_SQL, properties.getProperty(Environment.SHOW_SQL, "false"));
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, properties.getProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread"));
            settings.put(Environment.HBM2DDL_AUTO, properties.getProperty(Environment.HBM2DDL_AUTO, "create-drop"));
            settings.put(Environment.AUTOCOMMIT, properties.getProperty(Environment.AUTOCOMMIT, "false"));

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(OrderItem.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            em = sessionFactory.createEntityManager();

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }

    public static void performTransaction(Runnable runnable) {
        EntityTransaction transaction = HibernateUtil.getEm().getTransaction();
        transaction.begin();
        try {
            runnable.run();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive())
                transaction.rollback();

            throw new RuntimeException(ex);
        }
    }
}