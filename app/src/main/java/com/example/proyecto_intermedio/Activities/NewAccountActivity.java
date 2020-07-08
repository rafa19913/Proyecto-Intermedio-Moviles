package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.*;

import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;

import es.dmoral.toasty.Toasty;

public class NewAccountActivity extends AppCompatActivity {

    public static com.ornach.nobobutton.NoboButton btnAcept;
    public static EditText editTextOwnerName;
    public static EditText editTextBalance;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        asignComponentsOfNewAccount();

    }


    /**
     * Se asignan los componentes de la nueva cuenta (boton, editText) de: create_account.xml
     * Adicionalmente el listener de aceptar
     */
    private void asignComponentsOfNewAccount(){
        btnAcept = findViewById(R.id.BtnAceptNewAccount);
        editTextOwnerName = findViewById(R.id.EditTextOwnerName);
        editTextBalance = findViewById(R.id.EditTextBalance);


        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewAccountActivity.this, "EXAMPLE", Toast.LENGTH_SHORT).show();
                createNewAccount();
            }
        });
    }

    /**
     * Se crea la cuenta SI y SÓLO SI los campos son correctos
     */
    private void createNewAccount(){
        if(returnTrueIfTheFieldsAreCorrect()){
            openHome();
        }
    }

    private boolean returnTrueIfTheFieldsAreCorrect(){
        boolean resOwner, resBalance;
        Pattern REOwnerName = Pattern.compile("[a-zA-Z]{3,50}");
        Matcher validateOwnerName = REOwnerName.matcher(editTextOwnerName.getText());
        Pattern REBalance = Pattern.compile("[1-9]{1}[0-9]*.[0-9]{2}");
        Matcher validateBalance = REBalance.matcher(editTextBalance.getText());

        resOwner = validateOwnerName.find();
        resBalance = validateBalance.find();

        if(!resOwner){
            Toasty.warning(this, "¡Utilize solo caracteres alfabeticos y mas de 3 letras para el nombre!", Toast.LENGTH_LONG, true).show();
        }
        if(!resBalance){
            Toasty.warning(this, "¡Ingrese una cantidad numerica incluyendo centavos!", Toast.LENGTH_LONG, true).show();
        }


        return resOwner&&resBalance;
    }

    /**
     * Se abre el home.xml y el activity Home.Java
     */
    private void openHome(){
        createObjectAccount();
        in = new Intent(this,HomeActivity.class);
        startActivity(in);

        //cleanEditTexts();
    }

    /**
     * Se crea el objeto Account de Account.java
     */
    private void createObjectAccount(){
        String name = editTextOwnerName.getText().toString();
        double balance = Double.parseDouble(editTextBalance.getText().toString());
        Account.myAccount = new Account(name,balance);
        Toasty.success(this, "¡Cuenta creada correctamente!", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * Se limpian los edittext de Propietario y
     */
    private void cleanEditTexts(){
        editTextBalance.setText("");
        editTextOwnerName.setText("");
    }

    /**
     * Valida los campos vacios y correctos de (Nombre del propietario y Saldo)
     */


    /**
     * Válida el saldo del que no empieze con puntos, negativos, etc.
     * @param balance en formato "500", "1500", "2000"
     * @return True o False
     */
    private static boolean validateBalance(double balance){
        return true;
    }

    /**
     * Válida el nombre del propietario que (números, campos vacios, etc)
     * @param name en formato "Rafael", "Juan", "Karina"
     * @return True o False
     */
    private static boolean validateOwner(String name){
        return true;
    }

}
