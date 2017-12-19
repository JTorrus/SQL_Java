package com.executable;

import com.jdbc.utilities.ConnectDB;
import com.model.Departamento;
import com.model.Empleado;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        run();
    }

    private static void generateRandomInsQuery(Object object) throws SQLException {
        connection = ConnectDB.getInstance();
        statement = connection.createStatement();
        int queryResult;

        if (object instanceof Departamento) {
            queryResult = statement.executeUpdate("INSERT INTO departamentos (`dept_name`)" + "VALUES('" + ((Departamento) object).getDeptName() + "')");
            System.out.println(queryResult + " row/s updated");
        } else {
            queryResult = statement.executeUpdate("INSERT INTO empleados (`dept_id`, `empl_first_name`, `empl_second_name`, `empl_last_name`)" + "VALUES('1', '" + ((Empleado) object).getFirstName() + "', '" + ((Empleado) object).getSecondName() + "', '" + ((Empleado) object).getLastName() + "')");
            System.out.println(queryResult + " row/s updated");
        }
    }

    private static Empleado askForEmployeeAddition() {
        sc.useDelimiter("\n");
        int deptId, emplTelNumber, emplSalary;
        String firstName, secondName, lastName, emplAddress, emplMarried, emplBirthDate;
        Date birthdateParsed = null;
        Empleado empleado = null;

        try {
            System.out.println("Insert employee's associated ID from Dept");
            deptId = sc.nextInt();

            System.out.println("Insert employee's name");
            firstName = sc.next();

            System.out.println("Insert employee's second name");
            secondName = sc.next();

            System.out.println("Insert employee's last name");
            lastName = sc.next();

            System.out.println("Insert employee's address");
            emplAddress = sc.next();

            System.out.println("Insert employee's tel. number");
            emplTelNumber = sc.nextInt();

            System.out.println("Insert employee's birthdate (yyyy-MM-dd)");
            emplBirthDate = sc.next();
            birthdateParsed.valueOf(emplBirthDate);

            System.out.println("Is this employee married? (yes/no)");
            emplMarried = sc.next();

            System.out.println("Insert employee's salary");
            emplSalary = sc.nextInt();

            empleado = new Empleado(deptId, firstName, secondName, lastName, emplAddress, emplTelNumber, birthdateParsed, checkMarried(emplMarried), emplSalary);
        } catch (InputMismatchException e) {
            System.err.println("Error setting employee's info, non valid value entered");
            sc.next();
        }

        return empleado;
    }

    private static void askForEmployeeRemoval() throws SQLException {
        sc.useDelimiter("\n");
        int id = 0;

        try {
            System.out.println("Enter the employee's ID you want to remove");
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Enter a valid value for the name");
            sc.next();
        }

        removeEmplFromDatabase(id);
    }
    
    private static void askForId() throws SQLException {
        sc.useDelimiter("\n");
        int id = 0;
        
        try {
            System.out.println("Enter the dept ID to show");
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Enter a valid value for the dept ID");
            sc.next();
        }
        
        showEmplFromDept(id);
    }

    public static void addEmplToDatabase(Empleado empleado) throws SQLException {
        try {
            connection = ConnectDB.getInstance();
            preparedStatement = connection.prepareStatement("INSERT INTO empleados (`dept_id`, `empl_first_name`, "
                    + "`empl_second_name`, `empl_last_name`, `empl_address`, `empl_tel_number`, `empl_birthdate`, `empl_married`, `empl_salary`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, empleado.getDeptId());
            preparedStatement.setString(2, empleado.getFirstName());
            preparedStatement.setString(3, empleado.getSecondName());
            preparedStatement.setString(4, empleado.getLastName());
            preparedStatement.setString(5, empleado.getEmplAddress());
            preparedStatement.setInt(6, empleado.getEmplTelNumber());
            preparedStatement.setDate(7, empleado.getEmplBirthDate());
            preparedStatement.setBoolean(8, empleado.isEmplMarried());
            preparedStatement.setInt(9, empleado.getEmplSalary());

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " row/s updated");
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.err.println("Couldn't add the employee, the Dept's ID entered doesn't exist");
        }
    }

    public static void removeEmplFromDatabase(int emplId) throws SQLException {
        connection = ConnectDB.getInstance();
        preparedStatement = connection.prepareStatement("DELETE FROM empleados WHERE empleados.empl_id = ?");

        preparedStatement.setInt(1, emplId);

        int i = preparedStatement.executeUpdate();

        if (i > 0) {
            System.out.println(i + " row/s updated");
        } else {
            System.err.println("There's no ID matching the one you entered, no employee was deleted");
        }

    }

    private static boolean checkMarried(String married) {
        if (married.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

    private static void showMaxSalary() throws SQLException {
        connection = ConnectDB.getInstance();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT empl_first_name, departamentos.dept_name FROM empleados, departamentos "
                + "WHERE departamentos.dept_id = empleados.dept_id AND empl_salary = ( SELECT MAX(empl_salary) FROM empleados )");

        while (resultSet.next()) {
            System.out.println("Name: " + resultSet.getString("empl_first_name"));
            System.out.println("Dept associated: " + resultSet.getString("departamentos.dept_name"));
        }
    }

    private static void showEmplFromDept(int deptId) throws SQLException {
        connection = ConnectDB.getInstance();
        preparedStatement = connection.prepareStatement("SELECT empleados.* FROM empleados, departamentos "
                + "WHERE departamentos.dept_id = empleados.dept_id AND departamentos.dept_id = ?");
        
        preparedStatement.setInt(1, deptId);
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            System.out.println("Employee ID: " + resultSet.getInt("empl_id"));
            System.out.println("Dept ID: " + resultSet.getInt("dept_id"));
            System.out.println("First name: " + resultSet.getString("empl_first_name"));
            System.out.println("Second name: " + resultSet.getString("empl_second_name"));
            System.out.println("Last name: " + resultSet.getString("empl_last_name"));
            System.out.println("Address: " + resultSet.getString("empl_address"));
            System.out.println("Tel. number: " + resultSet.getInt("empl_tel_number"));
            System.out.println("Birthdate: " + resultSet.getDate("empl_birthdate"));
            System.out.println("Is married?: " + resultSet.getBoolean("empl_married"));
            System.out.println("Salary: " + resultSet.getInt("empl_salary"));
            System.out.println("----------------------------------------------");   
        }
    }

    private static void run() {
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

            try {
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        addEmplToDatabase(askForEmployeeAddition());
                        break;
                    case 2:
                        askForEmployeeRemoval();
                        break;
                    case 3:
                        showMaxSalary();
                        break;
                    case 4:
                        askForId();
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
            } catch (InputMismatchException e) {
                System.err.println("Enter a valid value");
                sc.next();
            } catch (NullPointerException e) {
                System.err.println("Program aborted due to execution error");
            } catch (SQLException e) {
                System.err.println("Error in SQL executation" + e.getSQLState());
                e.printStackTrace();
            } finally {
                try {
                    ConnectDB.closeConnection();

                    if (statement != null) {
                        statement.close();
                    }

                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }

                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e2) {
                    System.err.println("Error closing connection" + e2.getSQLState());
                }
            }
        } while (exit != true);

    }
}
