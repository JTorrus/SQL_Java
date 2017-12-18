package com.model;

public class Departamento {
    private int deptId;
    private String deptName;
    private String[] probabilities = {"Soporte", "Recursos Humanos", "Android", "iOs", "Finanzas", "Compras"};

    public Departamento(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }
    
    public Departamento() {
        this.deptName = chooseOne();
    }
    
    public String chooseOne() {
        int choice = (int) Math.floor(Math.random() * (this.probabilities.length - 1));
        return this.probabilities[choice];
    }

    public int getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }
}
