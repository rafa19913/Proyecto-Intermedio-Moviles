package com.example.proyecto_intermedio.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;

/**
 * Clase: HomeActivity se encarga de la interactividad principal con
 * las demas pantallas (Ingresos, Gastos, Mi cuenta) y muestra el Saldo, Gastos, Ingresos, Credito actualizado
 */
public class HomeActivity extends AppCompatActivity {

    public static TextView currentBalance,currentIncomes,currentExpenses,currentBalanceInAccount;
    public static com.ornach.nobobutton.NoboButton btnExpense,btnIncomes,btnMyAccount,btnExport;
    private Intent intent;
    public static TextView onChangeDate;


    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final int CAMERA_PERMISSION_CODE = 100;

    private static Button btnExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnExample = findViewById(R.id.btnExample);

        btnExample.setOnClickListener(v -> {
          //  checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
          //          STORAGE_PERMISSION_CODE);

            checkPermission(Manifest.permission.CAMERA,
                    CAMERA_PERMISSION_CODE);

        });

        /*checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE);

        checkPermission(Manifest.permission.CAMERA,
                CAMERA_PERMISSION_CODE);
        */
        onChangeDate = findViewById(R.id.TextViewOnChange); // Auxiliar para fechas ( Temporal-cambiar )

        asignComponentsOfHome();
        updateInformationOfHome();
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
        btnExport = findViewById(R.id.BtnExport);
    }

    /**
     * Método utilizado en otras clases como: IncomesActivity y ExpensesActivity para actualizar los pagos
     * en los TextViews
     */
    public static void updateInformationOfHome(){
        currentExpenses.setText("$"+String.valueOf(Account.myAccount.getTotalOfExpenses()));
        currentIncomes.setText("$"+String.valueOf(Account.myAccount.getTotalOfIncomes()));
        currentBalance.setText("$"+String.valueOf(Account.myAccount.getCurrentBalance()));
        currentBalanceInAccount.setText("$"+String.valueOf(Account.myAccount.getTotalCurrentBalanceInAccount()));
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
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityExportPDF();
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

    /**
     * Se abre la actividad de exportarPDF de: export_pdf.xml
     */
    private void openActivityExportPDF(){
        intent = new Intent(this,ExportActivity.class);
        startActivity(intent);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Showing the toast message
                Toast.makeText(this,"Camera Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if ( ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }



}
