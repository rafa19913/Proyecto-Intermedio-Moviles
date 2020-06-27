package com.example.proyecto_intermedio.SampleClasses;



import java.util.ArrayList;

public class Account {

    public static Account myAccount;

    private String ownerName;
    private double balance;
    private String profilePhotoPath;
    public ArrayList<Expense> expenses;
    public ArrayList<Income> incomes;

    public Account(String ownerName, double balance){
        expenses  = new ArrayList<>();
        incomes  = new ArrayList<>();
        this.ownerName = ownerName;
        this.balance = balance;
    }


    public double getTotalOfIncomes(){
        double totalAux = 0.0;
        for (int i=0; i < incomes.size(); i++){
            totalAux+= incomes.get(i).getAmount();
        }
        return totalAux;
    }


    public double getTotalOfExpenses(){
        double totalAux = 0.0;
        for (int i=0; i < expenses.size(); i++){
            totalAux+= expenses.get(i).getAmount();
        }
        return totalAux;
    }

    public double getCurrentBalance(){
        double totalExpenses = getTotalOfExpenses();
        double totalIncomes = getTotalOfIncomes();
        return (totalIncomes-totalExpenses);
    }

    public double getTotalCurrentBalanceInAccount(){
        double totalExpenses = getTotalOfExpenses();
        double totalIncomes = getTotalOfIncomes();
        return ((balance+totalIncomes) - totalExpenses);
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

    public String getProfilePhoto() {
        return profilePhotoPath;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhotoPath = profilePhoto;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(ArrayList<Income> incomes) {
        this.incomes = incomes;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "Nombre del propietario:'" + ownerName + '\'' +
                ", Saldo:" + balance +
                '}';
    }
}
