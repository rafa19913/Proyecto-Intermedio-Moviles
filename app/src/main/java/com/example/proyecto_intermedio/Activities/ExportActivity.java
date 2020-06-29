package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.proyecto_intermedio.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ExportActivity extends AppCompatActivity implements OnSelectDateListener {

    private ImageView arrowBack;
    private EditText dateBegin, dateFinish;
    private Button btnBegin, btnFinish;
    private com.ornach.nobobutton.NoboButton btnGeneratePdf;

    private static String dateAux;

    private static String auxPdfDate = "";

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
        dateBegin = findViewById(R.id.EditTextDateBegin);
        dateFinish = findViewById(R.id.EditTextDateFinish);
        btnBegin = findViewById(R.id.BtnDateBegin);
        btnFinish = findViewById(R.id.BtnDateFinish);
        btnGeneratePdf = findViewById(R.id.BtnExport);
    }

    /**
     * Se agregarn los listeners de exportar al pdf (botones)
     */
    private void addListenersOfExport(){
        arrowBack.setOnClickListener(v -> goHome());

        btnBegin.setOnClickListener(v -> openCalendar("start"));
        btnFinish.setOnClickListener(v -> openCalendar("finish"));
        btnGeneratePdf.setOnClickListener(v -> {
            try {
                generatePDF();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Se genera PDF
     */
    private void generatePDF() throws ParseException {
        String begin = dateBegin.getText().toString();
        String finish = dateFinish.getText().toString();

        Toast.makeText(this, "Begin:" + begin, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Finish:" + finish, Toast.LENGTH_SHORT).show();


        Calendar c1 = Calendar.getInstance();



        c1.set(2020,8,5);

        Toast.makeText(this, "C1:" + c1.getTime(), Toast.LENGTH_SHORT).show();

    }

    private void openCalendar(String startOrFinish){
        if ( startOrFinish.equals("start") ){
            auxPdfDate = "start";
        }else{
            auxPdfDate = "finish";
        }
        openOneDayPicker();
    }


    private void openOneDayPicker() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 10);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(this, this)
                .setPickerType(CalendarView.ONE_DAY_PICKER)
                .setHeaderColor(R.color.white)
                .setHeaderLabelColor(R.color.currentMonthDayColor)
                .setSelectionColor(R.color.daysLabelColor)
                .setPreviousButtonSrc(R.mipmap.ic_chevron_left_black_24dp)
                .setForwardButtonSrc(R.mipmap.ic_chevron_right_black_24dp)
                .setMinimumDate(min)
                .setMaximumDate(max);
        DatePicker oneDayPicker = oneDayBuilder.build();
        oneDayPicker.show();
    }

    @Override
    public void onSelect(List<Calendar> calendars) {
        Stream.of(calendars).forEach(calendar -> dateAux = calendar.getTime().toString());

        if ( auxPdfDate.equals("start") ){
            dateBegin.setText(dateAux);
        }else{
            dateFinish.setText(dateAux);
        }

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
