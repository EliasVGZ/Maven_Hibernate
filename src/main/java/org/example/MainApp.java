package org.example;

import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // Configuración de Hibernate y creación de la sesión
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Creación del DAO para empleados
        EmpleadoDAO empleadoDAO = new EmpleadoDAO(sessionFactory);

        // Obtención de todos los empleados
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
        List<Empleado> empleadosSalarioSuperior3000 = empleadoDAO.obtenerEmpleadosConSalarioMayorDe3000();


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

        //TODO EMPLEADOS CON SALARIO SUPERIOR A 3000 EUROS

//        if(empleadosSalarioSuperior3000 != null){
//
//            System.out.println("Empleados con salario superior a 3000 euros");
//            for (Empleado empleado : empleadosSalarioSuperior3000) {
//                System.out.println("NIF: " + empleado.getNif());
//                System.out.println("Nombre: " + empleado.getNombre());
//                System.out.println("Apellidos: " + empleado.getApellidos());
//                System.out.println("Salario: " + empleado.getSalario());
//                System.out.println("------------------------");
//            }
//        }

//        System.out.print("Ingrese un salario mínimo: ");
//        double salarioMinimo = Double.parseDouble(br.readLine());
//
//        // Obtención de empleados con salario mayor al proporcionado por el usuario
//        List<Empleado> empleadosConSalarioMayorAlUsuario = empleadoDAO.obtenerEmpleadosConSalarioMayorA(salarioMinimo);
//
//        // Imprimir los resultados
//        if (empleadosConSalarioMayorAlUsuario != null) {
//            for (Empleado empleado : empleadosConSalarioMayorAlUsuario) {
//                System.out.println("NIF: " + empleado.getNif());
//                System.out.println("Nombre: " + empleado.getNombre());
//                System.out.println("Apellidos: " + empleado.getApellidos());
//                System.out.println("Salario: " + empleado.getSalario());
//                System.out.println("------------------------");
//            }
//        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Ingrese el NIF del empleado: ");
            String nif = reader.readLine();

            System.out.print("Ingrese el nuevo salario: ");
            double nuevoSalario = Double.parseDouble(reader.readLine());

            // Llamar al método para actualizar el salario por NIF
            empleadoDAO.actualizarSalarioPorNIF(nif, nuevoSalario);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cierre de la sesión de Hibernate
        sessionFactory.close();
    }
}
