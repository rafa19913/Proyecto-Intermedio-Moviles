package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.proyecto_intermedio.R;

public class NewAccountActivity extends AppCompatActivity {

    public static com.ornach.nobobutton.NoboButton btnAcept;
    public static EditText editTextOwnerName;
    public static EditText editTextBalance;

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
        openHome();
       /* if (returnTrueIfTheFieldsAreCorrect()){
            openHome();
        }else{
            Toast.makeText(this, "Campos incorrectos", Toast.LENGTH_SHORT).show();
        }*/
    }

    /**
     * Se abre el home.xml y el activity Home.Java
     */
    private void openHome(){
        Intent in = new Intent(this,HomeActivity.class);
        startActivity(in);
        //cleanEditTexts();
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
    private boolean returnTrueIfTheFieldsAreCorrect(){
        String ownerName = editTextOwnerName.getText().toString();
        double balance = Double.parseDouble((editTextBalance.getText().toString()));

        if (validateOwner(ownerName) && validateBalance(balance)){
            return true;
        }else{
            return false;
        }
    }


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
