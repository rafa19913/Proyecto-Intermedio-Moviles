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

import static com.example.proyecto_intermedio.Components.Dialog.dialogs;

/**
 * Clase: IncomesActivity se encarga de los ingresos agregados, editados
 * en base a: expenses.xml
 */
public class IncomesActivity extends AppCompatActivity {

    private ImageView arrowBack;
    private com.ornach.nobobutton.NoboButton btnAddIncomes;

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
        listViewOfIncomes = findViewById(R.id.ListViewIncomes);
    }

    /**
     * Se agregan los listener de (Agrega ingreso, eliminar ingreso, editar ingreso, seleccion de lista)
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

    /**
     * Se manda llamar el dialogAdd para abrir el dialog y agregar un nuevo Ingreso
     */
    private void addIncome(){
        dialogs.dialogAdd(this,"Ingreso");
        HomeActivity.updateInformationOfHome();
    }

    /**
     * Se manda llamar el dialogEdit para abrir el dialog y editar un Ingreso
     * @param position del item seleccionado de la lista
     */
    private void editIncome(int position){
        dialogs.dialogEdit(this,position,"Ingreso");
        HomeActivity.updateInformationOfHome();
    }

    /**
     * Se dirige al HomeActivity.java
     */
    private void goHome(){
        onBackPressed();
    }

    /**
     * Listener cuando el usuario presiona el botón back del dispositivo
     */
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
        HomeActivity.updateInformationOfHome();
    }

}
