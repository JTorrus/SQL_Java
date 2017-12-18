package com.executable;

import com.model.Departamento;
import com.model.Empleado;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database_java";
        String username = "root";
        String password = "";
        
        Empleado empl = new Empleado();
        Departamento dept = new Departamento();
        
        String queryForRandomInsertEmpl = generateRandomInsQuery(empl);
        String queryForRandomInsertDept = generateRandomInsQuery(dept);           
    }
    
    public static String generateRandomInsQuery(Object object) {
        if (object instanceof Departamento) {
            return ""; //INSERT PARA DEPARTAMENTOS
        } else {
            return ""; //INSERT PARA EMPLEADOS
        }
    }
}
