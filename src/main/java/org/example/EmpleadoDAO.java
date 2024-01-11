package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

public class EmpleadoDAO {

    private final SessionFactory sessionFactory;

    public EmpleadoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        try (Session session = sessionFactory.openSession()) {
            // Creamos la consulta HQL para obtener todos los empleados
            String hql = "FROM Empleado";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);

            // Ejecutamos la consulta y devolvemos los resultados
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

