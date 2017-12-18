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
    private static Statement statement;

    public static void main(String[] args) {
        showMenu();
    }

    private static void generateRandomInsQuery(Object object) throws SQLException {
        statement = ConnectDB.getInstance().createStatement();
        int queryResult;

        if (object instanceof Departamento) {
            queryResult = statement.executeUpdate("INSERT INTO departamentos (`dept_name`)" + "VALUES('" + ((Departamento) object).getDeptName() + "')");
            System.out.println(queryResult + " row/s updated");
        } else {
            queryResult = statement.executeUpdate("INSERT INTO empleados (`dept_id`, `empl_first_name`, `empl_second_name`, `empl_last_name`)" + "VALUES('1', '" + ((Empleado) object).getFirstName() + "', '" + ((Empleado) object).getSecondName() + "', '" + ((Empleado) object).getLastName() + "')");
            System.out.println(queryResult + " row/s updated");
        }
    }

    private static void showMenu() {
        int option;
        boolean exit = false;

        do {
            System.out.println("----------- MENÚ -----------");
            System.out.println("1. Add an employee");
            System.out.println("2. Remove an employee");
            System.out.println("3. Show employee with max. salary");
            System.out.println("4. Show employees of a specified dept.");
            System.out.println("5. Insert random data");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();

            try {
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
                        generateRandomInsQuery(new Departamento());
                        generateRandomInsQuery(new Empleado());
                        break;
                    case 6:
                        System.out.println("Closing program...");
                        exit = true;
                        break;
                }
            } catch (SQLException e) {
                System.err.println("Error in SQL sentence" + e.getSQLState());
            } finally {
                try {
                    ConnectDB.closeConnection();
                    statement.close();
                } catch (SQLException e2) {
                    System.err.println("Error closing connection" + e2.getSQLState());
                }
            }
        } while (exit != true);

    }
}
