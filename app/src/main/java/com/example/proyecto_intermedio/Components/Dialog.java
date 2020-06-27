package com.example.proyecto_intermedio.Components;


import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_intermedio.Activities.MyAccountActivity;
import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;
import com.example.proyecto_intermedio.SampleClasses.Expense;
import com.example.proyecto_intermedio.SampleClasses.Income;

import es.dmoral.toasty.Toasty;

import static com.example.proyecto_intermedio.Activities.ExpensesActivity.adapterListOfExpense;
import static com.example.proyecto_intermedio.Activities.ExpensesActivity.listOfExpenses;
import static com.example.proyecto_intermedio.Activities.ExpensesActivity.listViewOfExpenses;
import static com.example.proyecto_intermedio.Activities.IncomesActivity.adapterListOfIncomes;
import static com.example.proyecto_intermedio.Activities.IncomesActivity.listOfIncomes;
import static com.example.proyecto_intermedio.Activities.IncomesActivity.listViewOfIncomes;

public class Dialog {

    private static String typeOfAdd = "";
    private static String typeOfEdit = "";

    private static void changeTypeOfAdd(String type){
        if (type == "Ingreso"){
            typeOfAdd = "Ingreso";   //
        }else{
            typeOfAdd = "Gasto"; //
        }
    }

    private static void changeTypeOfEdit(String type){
        if (type == "Ingreso"){
            typeOfEdit = "Ingreso";   //
        }else{
            typeOfEdit = "Gasto"; //
        }
    }

    /**
     * Dialogo que aparecera unicamente cuando el usuario presione el boton + para AGREGAR Ingresos o Gastos
     * Si pasa las validaciones correctas de los EditText del dialog se agrega a la lista y se crea un objeto Income o Expense *dependiendo en que pantalla agrego*
     * @param cx Context de la actividad
     * @param whatAdd en formato: "Ingreso" o "Gasto" < Estaticos
     */
    public static void dialogAdd(final Context cx, String whatAdd){ // whatAdd = "Ingreso" o "Gasto"

        changeTypeOfAdd(whatAdd);

        final android.app.Dialog dialog = new android.app.Dialog(cx);
        final EditText dialogName;
        final EditText dialogAmount;
        final EditText dialogDate;
        final EditText dialogDescription;
        Button btnAcept;

        dialog.setContentView(R.layout.customdialog_add);
        dialog.setTitle("Agregar " + whatAdd); // Ejemplo: "Agregar Ingreso" "Agregar Gasto"

        dialogName = dialog.findViewById(R.id.DialogEditTextName);
        dialogAmount = dialog.findViewById(R.id.DialogEditTextAmount);
        dialogDate = dialog.findViewById(R.id.DialogEditTextDate);
        dialogDescription = dialog.findViewById(R.id.DialogEditTextDescription);
        btnAcept = dialog.findViewById(R.id.DialogButtonAceptar);

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogName.getText().toString();
                double amount = Double.parseDouble(dialogAmount.getText().toString());
                String date = dialogDate.getText().toString();
                String description = dialogDescription.getText().toString();

                //--Agregar objecto (LISTA) -- SI typeOfAdd=true == "Ingreso" se agrega un Ingreso, en caso contrario es Gasto
                if (typeOfAdd == "Ingreso"){
                    addIncome(name,amount,date,description,cx);
                }else{
                    addExpense(name,amount,date,description,cx);
                }

                dialog.dismiss();
            }
        });


        dialog.show();
    }



    public static void dialogEdit(final Context cx, final int position, String typeToEdit){
        changeTypeOfEdit(typeToEdit);

        final android.app.Dialog dialog = new android.app.Dialog(cx);
        final EditText dialogName;
        final EditText dialogAmount;
        final EditText dialogDate;
        final EditText dialogDescription;
        Button btnAcept;

        dialog.setContentView(R.layout.customdialog_add);
        dialog.setTitle("Editar " + typeToEdit); // Ejemplo: "Editar Ingreso" "Editar Gasto"

        dialogName = dialog.findViewById(R.id.DialogEditTextName);
        dialogAmount = dialog.findViewById(R.id.DialogEditTextAmount);
        dialogDate = dialog.findViewById(R.id.DialogEditTextDate);
        dialogDescription = dialog.findViewById(R.id.DialogEditTextDescription);

        btnAcept = dialog.findViewById(R.id.DialogButtonAceptar);

        if (typeOfEdit == "Ingreso"){
            dialogName.setText(listOfIncomes.get(position).getNameOfIncome());
            dialogAmount.setText(String.valueOf(listOfIncomes.get(position).getAmount()));
            dialogDate.setText(listOfIncomes.get(position).getDate());
            dialogDescription.setText(listOfIncomes.get(position).getDescription());
        }else{
            dialogName.setText(listOfExpenses.get(position).getNameOfExpense());
            dialogAmount.setText(String.valueOf(listOfExpenses.get(position).getAmount()));
            dialogDate.setText(listOfExpenses.get(position).getDate());
            dialogDescription.setText(listOfExpenses.get(position).getDescription());
        }

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = dialogName.getText().toString();
                double amount = Double.parseDouble(dialogAmount.getText().toString());
                String date = dialogDate.getText().toString();
                String description = dialogDescription.getText().toString();

                if (typeOfEdit == "Ingreso"){
                    editIncome(position,name,amount,date,description,cx);
                    dialog.dismiss();
                }else{
                    editExpense(position,name,amount,date,description,cx);
                    dialog.dismiss();
                }

            }
        });

        dialog.show();

    }

    // -- Faltan validaciones --
    private static void editIncome(int position,String name, double amount, String date, String desc, Context cx){
        listOfIncomes.set(position,listOfIncomes.get(position)).setNameOfIncome(name);
        listOfIncomes.set(position,listOfIncomes.get(position)).setAmount(amount);
        listOfIncomes.set(position,listOfIncomes.get(position)).setDate(date);
        listOfIncomes.set(position,listOfIncomes.get(position)).setDescription(desc);
        adapterListOfIncomes.notifyDataSetChanged();
        Toasty.success(cx, "Se editó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        typeOfEdit = "";
    }

    private static void editExpense(int position,String name, double amount, String date, String desc, Context cx){
        listOfExpenses.set(position,listOfExpenses.get(position)).setNameOfExpense(name);
        listOfExpenses.set(position,listOfExpenses.get(position)).setAmount(amount);
        listOfExpenses.set(position,listOfExpenses.get(position)).setDate(date);
        listOfExpenses.set(position,listOfExpenses.get(position)).setDescription(desc);
        adapterListOfExpense.notifyDataSetChanged();
        Toasty.success(cx, "Se editó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        typeOfEdit = "";

    }

    // -- FALTAN VALIDACIONES --
    private static void addIncome(String name,double amount,String date,String desc,Context cx){
        Income income = new Income(name,amount,desc,date);
        Toasty.success(cx, "Se agregó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        listOfIncomes.add(income);
        typeOfAdd = "";
        refreshListOfIncomes(cx);
    }

    public static void refreshListOfIncomes(Context cx){
        adapterListOfIncomes = new ArrayAdapter<>(cx,android.R.layout.simple_list_item_1,listOfIncomes);
        listViewOfIncomes.setAdapter(adapterListOfIncomes);
        adapterListOfIncomes.notifyDataSetChanged();
    }

    public static void refresListOfExpense(Context cx){
        adapterListOfExpense = new ArrayAdapter<>(cx,android.R.layout.simple_list_item_activated_1,listOfExpenses);
        listViewOfExpenses.setAdapter(adapterListOfExpense);
        adapterListOfExpense.notifyDataSetChanged();

    }

    private static void addExpense(String name,double amount,String date,String desc,Context cx){
        Expense expense = new Expense(name,amount,desc,date);
        listOfExpenses.add(expense);
        refresListOfExpense(cx);
        Toasty.success(cx, "Se agregó un gasto ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
    }



}
