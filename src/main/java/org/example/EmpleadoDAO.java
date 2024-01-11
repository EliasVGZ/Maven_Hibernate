package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public List<Empleado> obtenerEmpleadosConSalarioMayorDe3000() {
        try (Session session = sessionFactory.openSession()) {
            // Creamos la consulta HQL para obtener empleados con salario mayor de 3000
            String hql = "FROM Empleado WHERE salario > 3000";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);

            // Ejecutamos la consulta y devolvemos los resultados
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Empleado> obtenerEmpleadosConSalarioMayorA(double salarioMinimo) {
        try (Session session = sessionFactory.openSession()) {
            // Creamos la consulta HQL para obtener empleados con salario mayor al proporcionado
            String hql = "FROM Empleado WHERE salario > :salarioMinimo";
            Query<Empleado> query = session.createQuery(hql, Empleado.class);
            query.setParameter("salarioMinimo", salarioMinimo);

            // Ejecutamos la consulta y devolvemos los resultados
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarSalarioPorNIF(String nif, double nuevoSalario) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Obtener el empleado por NIF
                Empleado empleado = session.get(Empleado.class, nif);

                if (empleado != null) {
                    // Actualizar el salario
                    empleado.setSalario(nuevoSalario);

                    // Guardar los cambios
                    session.update(empleado);
                    transaction.commit();
                    System.out.println("Salario actualizado con éxito.");
                } else {
                    System.out.println("No se encontró un empleado con el NIF proporcionado.");
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}

