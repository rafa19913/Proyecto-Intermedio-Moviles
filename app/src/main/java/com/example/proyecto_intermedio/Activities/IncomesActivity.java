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

public class IncomesActivity extends AppCompatActivity {

    private ImageView arrowBack;
    private com.ornach.nobobutton.NoboButton btnDeleteIncomes,btnEditIncomes,btnAddIncomes;
    private Button btnClear;
    private ListView listOfIncomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomes);


        asignComponentsOfIncomes();
        addListenersOfIncomes();

    }

    /**
     * Se agregan los componentes (Botones, Listas) de: incomes.xml
     */
    private void asignComponentsOfIncomes(){
        arrowBack = findViewById(R.id.arrowback);
        btnDeleteIncomes = findViewById(R.id.BtnDeleteIncome);
        btnEditIncomes = findViewById(R.id.BtnEditIncome);
        btnAddIncomes = findViewById(R.id.BtnAddIncome);
        btnClear = findViewById(R.id.BtnClearIncomes);
        listOfIncomes = findViewById(R.id.ListViewIncomes);
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
        btnDeleteIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIncome();
            }
        });
        btnEditIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editIncome();
            }
        });
        btnAddIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteIncome();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCheckBoxListOfIncomes();
            }
        });
    }

    private void cleanCheckBoxListOfIncomes(){
        Toasty.success(this, "Se Limpiaran los checkbox", Toast.LENGTH_SHORT).show();
    }

    private void addIncome(){
        Toasty.success(this, "Se agregará Ingreso", Toast.LENGTH_SHORT).show();
    }

    private void editIncome(){
        Toasty.success(this, "Se editara Ingreso", Toast.LENGTH_SHORT).show();
    }

    private void deleteIncome(){
        Toasty.success(this, "Se eliminarán Ingreso", Toast.LENGTH_SHORT).show();
    }


    private void goHome(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

}
