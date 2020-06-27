package com.example.proyecto_intermedio.SampleClasses;


/**
 * Clase Gastos: se encuentran todos los gastos agregados
 */
public class Expense {


    private String nameOfExpense;
    private double amount;
    private String description;
    private String date;

    public Expense(String nameOfExpense, double amount, String description, String date) {
        this.nameOfExpense = nameOfExpense;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }


    public String getNameOfExpense() {
        return nameOfExpense;
    }

    public void setNameOfExpense(String nameOfExpense) {
        this.nameOfExpense = nameOfExpense;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return nameOfExpense + '\n'+ "$"+amount;
    }



}
