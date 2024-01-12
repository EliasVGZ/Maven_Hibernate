package org.example;

import com.mysql.cj.jdbc.DatabaseMetaData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestHibernate {

    public static void main(String[] args) {
        // Configura Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Abre una sesión de Hibernate
        //Session session = sessionFactory.openSession();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Conexión exitosa a la base de datos 'Sakila'");
        } catch (Exception e) {
            System.out.println("Excepcion: "+e.getStackTrace());
        } finally {
            // Cierra la sesión de Hibernate
            sessionFactory.close();
        }

//        try {
//            // Iniciar transacción
//            Transaction transaction = session.beginTransaction();
//
//            // Ejemplo de consulta para obtener todos los actores sin la necesidad de una clase Actor
//            String queryString = "SELECT actor_id, first_name, last_name FROM actor";
//            List<Object[]> actorsData = session.createNativeQuery(queryString).list();
//
//            // Mostrar los actores
//            System.out.println("Lista de actores:");
//            for (Object[] actorData : actorsData) {
//                // Utilizar Short en lugar de Integer para el actor_id
//                Short actorId = (Short) actorData[0];
//                String firstName = (String) actorData[1];
//                String lastName = (String) actorData[2];
//
//                System.out.println("ID: " + actorId + ", Nombre: " + firstName + ", Apellido: " + lastName);
//            }
//
//            // Commit de la transacción
//            transaction.commit();
//        } catch (Exception e) {
//            // Manejar cualquier excepción
//            e.printStackTrace();
//        } finally {
//            // Cerrar la sesión de Hibernate
//            session.close();
//            sessionFactory.close();
//        }




            //TODO OBTENER NOMBRE DE LA BASE DE DATOS



        // Cierra el SessionFactory al final de la aplicación
        sessionFactory.close();
    }
}


