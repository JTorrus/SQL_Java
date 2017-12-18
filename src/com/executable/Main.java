package com.executable;

import com.jdbc.utilities.ConnectDB;
import com.model.Departamento;
import com.model.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        generateRandomInsQuery(new Empleado());
        generateRandomInsQuery(new Departamento());
    }

    public static void generateRandomInsQuery(Object object) {
        try(Connection connection = ConnectDB.getInstance();
            Statement statement = connection.createStatement();) {
            int queryResult;
            
            if (object instanceof Departamento) {
                queryResult = statement.executeUpdate("INSERT INTO departamentos");
            } else {
                queryResult = statement.executeUpdate("INSERT INTO empleados");
            }
        } catch (SQLException e) {
            System.err.println("SQL sentence error in " + e.getSQLState());
        }
    }
    
    public static void showMenu() {
        int option;
        boolean exit = false;
        
        do {
            System.out.println("----------- MENÚ -----------");
            System.out.println("1. Add employee");
            System.out.println("2. Remove employee");
            System.out.println("3. Show employee with max. salary");
            System.out.println("4. Show employees of a specified dept.");
            System.out.println("5. Insert random data");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (exit != true);
        
        
    }
}
