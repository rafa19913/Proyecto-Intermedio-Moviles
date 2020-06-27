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
import com.example.proyecto_intermedio.SampleClasses.Income;


import es.dmoral.toasty.Toasty;

public class IncomesActivity extends AppCompatActivity {

    private ImageView arrowBack;
    private com.ornach.nobobutton.NoboButton btnAddIncomes;
    private Button btnClear;

    public static ListView listViewOfIncomes;
    public static ArrayAdapter<Income> adapterListOfIncomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomes);

        asignComponentsOfIncomes();
        addListenersOfIncomes();
        addInformationOfIncomes();

    }

    /**
     * Se agregan los componentes (Botones, Listas) de: incomes.xml
     */
    private void asignComponentsOfIncomes(){
        arrowBack = findViewById(R.id.arrowback);
        btnAddIncomes = findViewById(R.id.BtnAddIncome);
        btnClear = findViewById(R.id.BtnClearIncomes);
        listViewOfIncomes = findViewById(R.id.ListViewIncomes);
    }

    /**
     * Se agregan los listener de (Agrega ingreso, eliminar ingreso, editar ingreso)
     */
    private void addListenersOfIncomes(){
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        btnAddIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncome();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCheckBoxListOfIncomes();
            }
        });
        listViewOfIncomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { editIncome(position); }
        });
    }



    /**
     * Se agrega la lista de ingresos
     */
    private void addInformationOfIncomes(){
        Dialog.refreshListOfIncomes(this);
    }

    private void cleanCheckBoxListOfIncomes(){
        Toasty.success(this, "Se Limpiaran los checkbox", Toast.LENGTH_SHORT).show();
    }

    private void addIncome(){
        Dialog.dialogAdd(this,"Ingreso");
        HomeActivity.updateInformationOfHome();
    }

    private void editIncome(int position){
        Dialog.dialogEdit(this,position,"Ingreso");
        HomeActivity.updateInformationOfHome();
    }


    private void goHome(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
        HomeActivity.updateInformationOfHome();
    }

}
