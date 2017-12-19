package com.model;

import com.jdbc.utilities.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class Empleado {
    private int emplId;
    private int deptId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String emplAddress;
    private int emplTelNumber;
    private Date emplBirthDate;
    private int emplSalary;
    private boolean emplMarried;
    
    String[] firstNameProb = {"Javier", "Nil", "Bhupinder", "Dani", "Hayk", "Amer", "Albert"};
    String[] secondNameProb = {"Martinez", "Fernandez", "Gonzalez"};
    String[] lastNameProb = {"Galindo", "Kumar", "de la Lastra", "Perez"};

    public Empleado() {
        this.firstName = chooseOne()[0];
        this.secondName = chooseOne()[1];
        this.lastName = chooseOne()[2];
        this.deptId = new Departamento().getDeptId();
    }

    public Empleado(String firstName, String secondName, String lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public Empleado(int deptId, String firstName, String secondName, String lastName, String emplAddress, int emplTelNumber, Date emplBirthDate, boolean emplMarried, int emplSalary) {
        this.deptId = deptId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.emplAddress = emplAddress;
        this.emplTelNumber = emplTelNumber;
        this.emplBirthDate = emplBirthDate;
        this.emplMarried = emplMarried;
        this.emplSalary = emplSalary;
    }
    
    public String[] chooseOne() {
        int firstChoice = (int) Math.floor(Math.random() * (this.firstNameProb.length - 1));
        int secondChoice = (int) Math.floor(Math.random() * (this.secondNameProb.length - 1));
        int thirdChoice = (int) Math.floor(Math.random() * (this.lastNameProb.length - 1));
        
        String[] data = {this.firstNameProb[firstChoice], this.secondNameProb[secondChoice], this.lastNameProb[thirdChoice]};
        
        return data;
    }

    public int getDeptId() {
        return deptId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmplAddress() {
        return emplAddress;
    }

    public int getEmplTelNumber() {
        return emplTelNumber;
    }

    public Date getEmplBirthDate() {
        return emplBirthDate;
    }

    public int getEmplSalary() {
        return emplSalary;
    }

    public boolean isEmplMarried() {
        return emplMarried;
    }
}
