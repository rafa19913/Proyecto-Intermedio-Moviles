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

import static com.example.proyecto_intermedio.Components.Dialog.dialogs;

/**
 * Clase: ExpensesActivity se encarga de los gastos agregados, editados
 * en base a: expenses.xml
 */
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

    /**
     * Se manda llamar el dialogAdd para agregar un nuevo Gasto
     */
    private void addExpense(){
        dialogs.dialogAdd(this,"Gasto");
        HomeActivity.updateInformationOfHome();
    }

    /**
     * Se manda llamar el dialogEdit para abrir el dialog y editar un Gasto en base
     * @param position del item seleccionado de la lista
     */
    private void editExpense(int position){
        dialogs.dialogEdit(this,position,"Gasto");
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
