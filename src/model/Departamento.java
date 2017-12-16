package model;

public class Departamento {
    int deptId;
    String deptName;
    String[] probabilities = {"Soporte", "Recursos Humanos", "Android", "iOs", "Finanzas", "Compras"};

    public Departamento(int deptId, String deptName) {
        this.deptId = deptId;
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
