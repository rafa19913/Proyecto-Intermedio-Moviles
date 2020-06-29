package com.example.proyecto_intermedio.SampleClasses;

import java.util.Calendar;

/**
 * Clase Incomes: Se agregan todos los ingresos del propietario
 */
public class Income {

    private String nameOfIncome;
    private double amount;
    private String description;
    private String date;


    public Income(String nameOfIncome, double amount, String description, String date){
        this.nameOfIncome = nameOfIncome;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }


    //Métodos get / set / tostring
    public String getNameOfIncome() {
        return nameOfIncome;
    }

    public void setNameOfIncome(String nameOfIncome) {
        this.nameOfIncome = nameOfIncome;
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
        return nameOfIncome + '\n'+ "$"+amount;
    }
}
