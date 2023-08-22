package loga.dev11.hibernate.utils;

import loga.dev11.hibernate.entities.Client;
import loga.dev11.hibernate.entities.Planet;
import loga.dev11.hibernate.entities.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final HibernateUtil INSTANCE = new HibernateUtil();

    private final SessionFactory sessionFactory;

    private HibernateUtil() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
