package persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by sasha on 08.05.2016.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // Create the SessionFactory from hibernate.cfg.xml
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        return new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
