package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;

public class HomeActivity extends AppCompatActivity {

    public static TextView currentBalance,currentIncomes,currentExpenses,currentBalanceInAccount;
    public static com.ornach.nobobutton.NoboButton btnExpense,btnIncomes,btnMyAccount;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        asignComponentsOfHome();
        addInformationOfHome();
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
     * Se agrega la información a los Text View de la cuenta creada (Pagos, Gastos, Saldo, etc)
     */
    private void addInformationOfHome(){
        currentExpenses.setText(String.valueOf(Account.myAccount.getTotalOfExpenses()));
        currentIncomes.setText(String.valueOf(Account.myAccount.getTotalOfIncomes()));
        currentBalance.setText(String.valueOf(Account.myAccount.getCurrentBalance()));
        currentBalanceInAccount.setText(String.valueOf(Account.myAccount.getTotalCurrentBalanceInAccount()));
    }


    public static void updateInformationOfHome(){
        currentExpenses.setText(String.valueOf(Account.myAccount.getTotalOfExpenses()));
        currentIncomes.setText(String.valueOf(Account.myAccount.getTotalOfIncomes()));
        currentBalance.setText(String.valueOf(Account.myAccount.getCurrentBalance()));
        currentBalanceInAccount.setText(String.valueOf(Account.myAccount.getTotalCurrentBalanceInAccount()));
    }

    /**
     * Se agregan los listeners de home (botones: Gastos, Ingresos, Mi Cuenta)
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
        intent = new Intent(this, IncomesActivity.class);
        startActivity(intent);
    }

    /**
     * Se abre la actividad de gastos de: expenses.xml
     */
    private void openActivityExpenses(){
        intent = new Intent(this,ExpensesActivity.class);
        startActivity(intent);
    }

    /**
     * Se abre la actividad de gastos de: my_account.xml
     */
    private void openActivityMyAccount(){
        intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

}
