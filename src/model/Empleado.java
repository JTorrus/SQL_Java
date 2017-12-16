package model;
import java.util.Date;

public class Empleado {
    int emplId;
    int deptId;
    String firstName;
    String secondName;
    String lastName;
    String emplAddress;
    int emplTelNumber;
    Date emplBirthDate;
    int emplSalary;
    boolean emplMarried;
    
    String[] firstNameProb = {"Javier", "Nil", "Bhupinder", "Dani", "Hayk", "Amer", "Albert"};
    String[] secondNameProb = {"Martinez", "Fernandez", "Gonzalez"};
    String[] lastNameProb = {"Galindo", "Kumar", "de la Lastra", "Perez"};

    public Empleado() {
        this.firstName = chooseOne()[0];
        this.secondName = chooseOne()[1];
        this.lastName = chooseOne()[2];
    }

    public Empleado(String firstName, String secondName, String lastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public Empleado(int emplId, int deptId, String firstName, String secondName, String lastName, String emplAddress, int emplTelNumber, Date emplBirthDate, boolean emplMarried) {
        this.emplId = emplId;
        this.deptId = deptId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.emplAddress = emplAddress;
        this.emplTelNumber = emplTelNumber;
        this.emplBirthDate = emplBirthDate;
        this.emplMarried = emplMarried;
    }
    
    public String[] chooseOne() {
        int firstChoice = (int) Math.floor(Math.random() * this.firstNameProb.length - 1);
        int secondChoice = (int) Math.floor(Math.random() * this.secondNameProb.length - 1);
        int thirdChoice = (int) Math.floor(Math.random() * this.lastNameProb.length - 1);
        
        String[] data = {this.firstNameProb[firstChoice], this.firstNameProb[secondChoice], this.firstNameProb[thirdChoice]};
        
        return data;
    }
}
