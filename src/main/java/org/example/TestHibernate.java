package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {

    public static void main(String[] args) {
        // Configura Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Abre una sesión de Hibernate
        Session session = sessionFactory.openSession();

        try {
            // Realiza operaciones de prueba aquí, por ejemplo:
            // AlgoEntity algo = session.get(AlgoEntity.class, id);
            // System.out.println(algo);

            System.out.println("Hibernate está funcionando correctamente.");
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }

        // Cierra el SessionFactory al final de la aplicación
        sessionFactory.close();
    }
}


