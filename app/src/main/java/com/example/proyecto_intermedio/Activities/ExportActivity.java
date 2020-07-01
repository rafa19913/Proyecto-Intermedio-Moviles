package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.proyecto_intermedio.SampleClasses.Account;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ExportActivity extends AppCompatActivity implements OnSelectDateListener {

    private ImageView arrowBack;
    private EditText dateBegin, dateFinish;
    private Button btnBegin, btnFinish;
    private com.ornach.nobobutton.NoboButton btnGeneratePdf;

    private static String dateAux;
    private static String auxPdfDate = "";

    private static String begin;
    private static String finish;
    private static int monthBegin;
    private static int monthFinish;
    private static int dayOfMonthBegin;
    private static int dayOfMonthFinish;
    private static int yearBegin;
    private static int yearFinish;

    private static int currentMonth;
    private static int currentDayOfMonth;
    private static int currentYear;

    private static String nameOfPdf = "";
    private static final String nameOfDirectory = "Proyecto-Intermedio";
    private static File pathRute;
    private static String startingDir = Environment.getExternalStorageDirectory().toString();
    private static Document documentPdf;
    private static File rutePdf;



    private static Calendar minDate = Calendar.getInstance();
    private static Calendar maxDate = Calendar.getInstance();



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
        btnGeneratePdf.setOnClickListener(v -> { generatePDF();});

    }


    private void getValuesOfDates(){
        begin = dateBegin.getText().toString();
        finish = dateFinish.getText().toString();
        monthBegin = getMonthOfStringToNumber(begin);
        monthFinish = getMonthOfStringToNumber(finish);
        dayOfMonthBegin = Integer.parseInt(begin.substring(8,10));
        dayOfMonthFinish = Integer.parseInt(finish.substring(8,10));
        yearBegin = Integer.parseInt(begin.substring(13));
        yearFinish = Integer.parseInt(finish.substring(13));

    }

    /**
     * Se genera PDF
     */
    private void generatePDF(){
        getValuesOfDates();

        minDate = Calendar.getInstance();
        minDate.set(yearBegin,monthBegin,dayOfMonthBegin);

        maxDate = Calendar.getInstance();
        maxDate.set(yearFinish,monthFinish,dayOfMonthFinish);

        nameOfPdf = dayOfMonthBegin+"-"+(monthBegin+1)+"-"+yearBegin+" a " + dayOfMonthFinish+"-"+(monthFinish+1)+"-"+yearFinish+".pdf";

        Toasty.warning(this,"Seleccione la ubicación a guardar",Toasty.LENGTH_SHORT).show();
        chooseDirectoryToSave(this);
    }


    private void chooseDirectoryToSave(Context cx){
        //Toast.makeText(getApplicationContext(),"Boton3",Toast.LENGTH_SHORT).show();
        // Escoger un Directorio
        new ChooserDialog(cx)
                .withFilter(true, false)
                .withStartFile(startingDir)
                // to handle the result(s)
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        pathRute = pathFile;
                        createThePDF();
                        visualizatePDF();
                    }
                })
                .build()
                .show();
    }



    private void visualizatePDF(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // De existir, se abre el pdf.
        if (rutePdf!=null ) {
            File myFile = rutePdf;
            intent.setDataAndType(Uri.fromFile(myFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.startActivity(intent);
        }
        else {
            Toasty.error(this, "¡Error! No hay PDF en la ubicación", Toast.LENGTH_SHORT).show();

        }
    }



    private File getRuta() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            pathRute = new File(pathRute, nameOfDirectory);

            if (pathRute != null) {
                if (!pathRute.mkdirs()) {
                    if (!pathRute.exists()) {
                        return null;
                    }
                }
            }
        }

        return pathRute;
    }
    private File createDirectory(String nombreFichero) throws IOException {
        File ruta = getRuta();
        File fichero = null;
        if (ruta != null)
            fichero = new File(ruta, nombreFichero);
        return fichero;
    }

    //metodo que genera el docuento pdf
    private void createThePDF(){
        documentPdf = new Document();

        // Creamos el fichero con el nombre que deseemos.
        File fichero = null;
        try {
            fichero = createDirectory(nameOfPdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rutePdf = fichero;

        // Creamos el flujo de datos de salida para el fichero donde guardaremos el pdf.
        FileOutputStream ficheroPdf = null;
        try {
            //ficheroPdf = new FileOutputStream(f.getAbsolutePath());
            ficheroPdf = new FileOutputStream(fichero.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Asociamos el flujo que acabamos de crear al documento.
        try {
            PdfWriter.getInstance(documentPdf, ficheroPdf);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        // Abrimos el documento.
        documentPdf.open();

        // creamos las tablas de ponderaciones
        //Tabla de presentacion
        PdfPTable tabla1 = new PdfPTable(4);

        tabla1.addCell("-");
        tabla1.addCell("Ingresos");
        tabla1.addCell("Ingresos");
        tabla1.addCell("-");

        tabla1.addCell("Fecha");
        tabla1.addCell("Título");
        tabla1.addCell("Monto");
        tabla1.addCell("Descripción");

        int amountOfIncomes = Account.myAccount.incomes.size();

        for (int i = 0; i < amountOfIncomes; i++){
            asignCurrentDates(Account.myAccount.incomes.get(i).getDate());
            Calendar currentDate = Calendar.getInstance();
            currentDate.set(currentYear,currentMonth,currentDayOfMonth);
            if (knowIfItsInRange(currentDate)){
                tabla1.addCell(Account.myAccount.incomes.get(i).getDate());
                tabla1.addCell(Account.myAccount.incomes.get(i).getNameOfIncome());
                tabla1.addCell(String.valueOf(Account.myAccount.incomes.get(i).getAmount()));
                tabla1.addCell(Account.myAccount.incomes.get(i).getDescription());
            }
        }

        PdfPTable tabla2 = new PdfPTable(4);

        tabla2.addCell("-");
        tabla2.addCell("Gastos");
        tabla2.addCell("Gastos");
        tabla2.addCell("-");

        tabla2.addCell("Fecha");
        tabla2.addCell("Título");
        tabla2.addCell("Monto");
        tabla2.addCell("Descripción");

        int amountOfExpense = Account.myAccount.expenses.size();

        for (int i = 0; i < amountOfExpense; i++){
            asignCurrentDates(Account.myAccount.expenses.get(i).getDate());
            Calendar currentDate = Calendar.getInstance();
            currentDate.set(currentYear,currentMonth,currentDayOfMonth);
            if (knowIfItsInRange(currentDate)){
                tabla2.addCell(Account.myAccount.expenses.get(i).getDate());
                tabla2.addCell(Account.myAccount.expenses.get(i).getNameOfExpense());
                tabla2.addCell(String.valueOf(Account.myAccount.expenses.get(i).getAmount()));
                tabla2.addCell(Account.myAccount.expenses.get(i).getDescription());
            }
        }


        // Añadimos el texto al pdf
        try {
            String title = "Ingresos y Gastos de " +  dayOfMonthBegin+"-"+(monthBegin+1)+"-"+yearBegin+" hasta " + dayOfMonthFinish+"-"+(monthFinish+1)+"-"+yearFinish;

            documentPdf.add(tabla1);

            documentPdf.newPage();

            documentPdf.add(tabla2);

//            documentPdf.addTitle("Ingresos y Gastos de " +  dayOfMonthBegin+"-"+(monthBegin+1)+"-"+yearBegin+" hasta " + dayOfMonthFinish+"-"+(monthFinish+1)+"-"+yearFinish);


        }catch (DocumentException e){
            e.printStackTrace();
        }

        // Cerramos el documento.
        documentPdf.close();

        //mensaje al usuario para informar de estado del documento
        Toasty.success(this, "PDF creado ¡Éxitosamente!", Toast.LENGTH_SHORT, true).show();

    }


    private void asignCurrentDates(String currentDate){
        currentMonth = getMonthOfStringToNumber(currentDate);
        currentDayOfMonth = Integer.parseInt(currentDate.substring(8,10));
        currentYear = Integer.parseInt(currentDate.substring(13));
    }


    private boolean knowIfItsInRange(Calendar currentDate){
        return minDate.before(currentDate) && maxDate.after(currentDate);
    }



    /**
     * @param date  (En formato "Tue May 01 2020")
     * @return  1 o 2 o 3 o 4 o 5 o 6 o 7 o 8 o 9 o 10 o 11 o 12
     */
    private int getMonthOfStringToNumber(String date){
        String monthString = date.charAt(4) +""+ date.charAt(5) + "" + date.charAt(6);

        switch (monthString){
            case "Jan":
                return 0;
            case "Feb":
                return 1;
            case "Mar":
                return 2;
            case "Apr":
                return 3;
            case "May":
                return 4;
            case "Jun":
                return 5;
            case "Jul":
                return 6;
            case "Aug":
                return 7;
            case "Sep":
                return 8;
            case "Oct":
                return 9;
            case "Nov":
                return 10;
            case "Dec":
                return 11;
            default:
                return 404;

        }

    }



    /**
     * Se convierte el día de STRING a ENTERO
     * param date (En formato "Tue May 01 2020")
     * @return 1 o 2 o 3 o 4 o 5 o 6 o7
     */
    /*
    private int getDayOfWeekStringToNumber(String date){
        String numberDayOfWeekInString = date.charAt(0) +""+ date.charAt(1) + "" + date.charAt(2);

        switch (numberDayOfWeekInString){
            case "Mon":
                return 1;
            case "Tue":
                return 2;
            case "Wed":
                return 3;
            case "Thu":
                return 4;
            case "Fri":
                return 5;
            case "Sat":
                return 6;
            case "Sun":
                return 7;
            default:
                return 0;
        }

    }
    */

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

        dateAux = dateAux.replaceAll("00:00:00","");
        dateAux = dateAux.replaceAll("CDT","");
        dateAux = dateAux.replaceAll("CST","");

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
