package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFirstAccount();
    }

    /**
     * Abrirá el create_account.xml dónde se asignan los componentes para crear una cuenta
     */
    private void createFirstAccount(){
        Intent in = new Intent(this,NewAccountActivity.class);
        startActivity(in);
    }


}
