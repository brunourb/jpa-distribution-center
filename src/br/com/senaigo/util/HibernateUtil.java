package br.com.senaigo.util;

import br.com.senaigo.entities.Category;
import br.com.senaigo.entities.Product;
import br.com.senaigo.entities.Suppliers;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by bruno on 22/04/16.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("resources/hibernate.cfg.xml");
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Suppliers.class);
            logger.info("Hibernate Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            logger.info("Hibernate serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
