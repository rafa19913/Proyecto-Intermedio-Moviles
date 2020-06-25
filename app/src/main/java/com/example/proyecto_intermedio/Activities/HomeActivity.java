package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyecto_intermedio.R;

public class HomeActivity extends AppCompatActivity {

    public static TextView currentBalance,currentIncomes,currentExpenses,currentBalanceInAccount;
    public static com.ornach.nobobutton.NoboButton btnExpense,btnIncomes,btnMyAccount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        asignComponentsOfHome();
        addInformationOfAccount();
        addListenersOfButtonsHome();
    }


    /**
    * Se asignan los componentes del Home (botones, TextViews) de: home.xml
    */
    private void asignComponentsOfHome(){
        currentBalance = findViewById(R.id.TextViewCurrentBalance);
        currentIncomes = findViewById(R.id.TextViewCurrentIncomes);
        currentExpenses = findViewById(R.id.TextViewCurrentExpenses);
        currentBalanceInAccount = findViewById(R.id.TextViewCurrentBalanceInAccount);
        btnExpense = findViewById(R.id.BtnExpenses);
        btnIncomes = findViewById(R.id.BtnIncomes);
        btnMyAccount = findViewById(R.id.BtnMyAccount);
    }

    /**
     * Se agrega la información de la cuenta creada por PRIMERA VEZ
     */
    private void addInformationOfAccount(){
        currentBalanceInAccount.setText(NewAccountActivity.editTextBalance.getText().toString());
    }

    /**
     * Se agregan los listeners de home (Gastos, Ingresos, Mi Cuenta)
     */
    private void addListenersOfButtonsHome(){
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityExpenses();
            }
        });
        btnIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityIncomes();
            }
        });
        btnMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMyAccount();
            }
        });
    }


    /**
     * Se abre la actividad de gastos de: incomes.xml
     */
    private void openActivityIncomes(){
        Intent intent = new Intent(this, IncomesActivity.class);
        startActivity(intent);
    }

    /**
     * Se abre la actividad de gastos de: expenses.xml
     */
    private void openActivityExpenses(){
        Intent intent = new Intent(this,ExpensesActivity.class);
        startActivity(intent);
    }

    /**
     * Se abre la actividad de gastos de: my_account.xml
     */
    private void openActivityMyAccount(){
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

}
