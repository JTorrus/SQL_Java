package model;

public class Departamento {
    String deptName;
    String[] probabilities = {"Soporte", "Recursos Humanos", "Android", "iOs", "Finanzas", "Compras"};

    public Departamento(String deptName) {
        this.deptName = deptName;
    }
    
    public Departamento() {
        this.deptName = chooseOne();
    }
    
    public String chooseOne() {
        int choice = (int) Math.floor(Math.random() * this.probabilities.length - 1);
        return this.probabilities[choice];
    }
}
