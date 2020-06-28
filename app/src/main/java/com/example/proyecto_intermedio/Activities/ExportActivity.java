package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyecto_intermedio.R;


public class ExportActivity extends AppCompatActivity {

    private ImageView arrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export_pdf);

        asignComponentsOfExport();
        addListenersOfExport();
    }

    /**
     * Se asignan los componentes de exportar a pdf (botones, textivews) de: export_pdf.xml
     */
    private void asignComponentsOfExport(){
        arrowBack = findViewById(R.id.arrowback);
    }

    /**
     * Se agregarn los listeners de exportar al pdf (botones)
     */
    private void addListenersOfExport(){
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
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
