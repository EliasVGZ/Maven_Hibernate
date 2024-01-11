package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        // Configuración de Hibernate y creación de la sesión
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Creación del DAO para empleados
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(sessionFactory);

        // Obtención de todos los empleados
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();

        // Imprimir los empleados
        if (empleados != null) {
            for (Empleado empleado : empleados) {
                System.out.println("NIF: " + empleado.getNif());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Apellidos: " + empleado.getApellidos());
                System.out.println("Salario: " + empleado.getSalario());
                System.out.println("------------------------");
            }
        }

        // Cierre de la sesión de Hibernate
        sessionFactory.close();
    }
}
