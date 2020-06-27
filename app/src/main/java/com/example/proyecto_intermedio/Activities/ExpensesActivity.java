package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyecto_intermedio.Components.Dialog;
import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Expense;


import es.dmoral.toasty.Toasty;

public class ExpensesActivity extends AppCompatActivity {

    private ImageView arrowBack;
    private com.ornach.nobobutton.NoboButton btnAddExpense;
    private Button btnClearExpenses;

    public static ListView listViewOfExpenses;
    public static ArrayAdapter<Expense> adapterListOfExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        asignComponentsOfExpenses();
        addListenersOfExpenses();
        addInformationOfExpenses();

    }

    /**
     * Se agregan los componentes (Botones, Listas) de expenses.xml
     */
    private void asignComponentsOfExpenses(){
        arrowBack = findViewById(R.id.arrowback);
        btnAddExpense = findViewById(R.id.BtnAddExpense);
        btnClearExpenses = findViewById(R.id.BtnClearExpense);
        listViewOfExpenses = findViewById(R.id.ListViewExpenses);
    }

    /**
     * Se agregan los listener de (Agrega gasto, eliminar gastos, editar)
     */
    private void addListenersOfExpenses(){
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
        btnClearExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCheckBoxOfExpenses();
            }
        });
        listViewOfExpenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { editExpense(position); }
        });
    }



    /**
     * Se agrega la lista de ingresos
     */
    private void addInformationOfExpenses(){
        Dialog.refresListOfExpense(this);
    }
    private void cleanCheckBoxOfExpenses(){
        Toasty.success(this, "Se limpiaran los gasto", Toast.LENGTH_SHORT).show();
    }
    private void addExpense(){
        Dialog.dialogAdd(this,"Gasto");
        HomeActivity.updateInformationOfHome();
    }
    private void editExpense(int position){
        Dialog.dialogEdit(this,position,"Gasto");
        HomeActivity.updateInformationOfHome();
    }


    /**
     * Se dirige al home.java
     */
    private void goHome(){
        onBackPressed();
    }

    /**
     * Listener cuando el usuario presiona el botón BACK del dispositivo
     */
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
        HomeActivity.updateInformationOfHome();
    }

}
