package com.example.proyecto_intermedio.SampleClasses;

public class Account {

    private String ownerName;
    private double balance;

    public Account(String ownerName, double balance){
        this.ownerName = ownerName;
        this.balance = balance;
    }


    // -- Getters y Setters -- //
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "Nombre del propietario:'" + ownerName + '\'' +
                ", Saldo:" + balance +
                '}';
    }
}
