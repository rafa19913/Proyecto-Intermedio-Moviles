package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyecto_intermedio.R;

import es.dmoral.toasty.Toasty;

public class ExpensesActivity extends AppCompatActivity {

    private ImageView arrowBack;
    private com.ornach.nobobutton.NoboButton btnDeleteExpense,btnEditExpense,btnAddExpense;
    private Button btnClearExpenses;
    private ListView listOfExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses);

        asignComponentsOfExpenses();
        addListenersOfExpenses();
    }

    /**
     * Se agregan los componentes (Botones, Listas) de expenses.xml
     */
    private void asignComponentsOfExpenses(){
        arrowBack = findViewById(R.id.arrowback);
        btnDeleteExpense = findViewById(R.id.BtnDeleteExpense);
        btnEditExpense = findViewById(R.id.BtnEditExpense);
        btnAddExpense = findViewById(R.id.BtnAddExpense);
        btnClearExpenses = findViewById(R.id.BtnClearExpense);
        listOfExpenses = findViewById(R.id.ListViewExpenses);
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
        btnDeleteExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExpense();
            }
        });
        btnEditExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editExpenses();
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
    }


    private void cleanCheckBoxOfExpenses(){
        Toasty.success(this, "Se limpiaran los gasto", Toast.LENGTH_SHORT).show();
    }

    private void addExpense(){
        Toasty.success(this, "Se agregará gasto", Toast.LENGTH_SHORT).show();
    }

    private void editExpenses(){
        Toasty.success(this, "Se editara gasto", Toast.LENGTH_SHORT).show();
    }

    private void deleteExpense(){
        Toasty.success(this, "Se eliminarán gastos", Toast.LENGTH_SHORT).show();
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
    }

}
