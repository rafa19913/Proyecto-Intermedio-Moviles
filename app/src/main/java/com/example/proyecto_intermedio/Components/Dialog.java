package com.example.proyecto_intermedio.Components;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.proyecto_intermedio.Activities.HomeActivity;
import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;
import com.example.proyecto_intermedio.SampleClasses.Expense;
import com.example.proyecto_intermedio.SampleClasses.Income;

import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.example.proyecto_intermedio.Activities.ExpensesActivity.adapterListOfExpense;
import static com.example.proyecto_intermedio.Activities.ExpensesActivity.listViewOfExpenses;
import static com.example.proyecto_intermedio.Activities.HomeActivity.onChangeDate;
import static com.example.proyecto_intermedio.Activities.IncomesActivity.adapterListOfIncomes;
import static com.example.proyecto_intermedio.Activities.IncomesActivity.listViewOfIncomes;

public class Dialog implements OnSelectDateListener {


    public static Dialog dialogs = new Dialog();

    private static String typeOfAdd = "";
    private static String typeOfEdit = "";
    private static String dateAux = "";
    private static Context dialogContext;



    private static void changeTypeOfAdd(String type){
        if ( type.equals("Ingreso") ){
            typeOfAdd = "Ingreso";   //
        }else{
            typeOfAdd = "Gasto"; //
        }
    }

    private static void changeTypeOfEdit(String type){
        if ( type.equals("Ingreso") ){
            typeOfEdit = "Ingreso";   //
        }else{
            typeOfEdit = "Gasto"; //
        }
    }

    /**
     * Dialogo que aparecera unicamente cuando el usuario presione el boton + para AGREGAR Ingresos o Gastos
     * Si pasa las validaciones correctas de los EditText del dialog se agrega a la lista y se crea un objeto Income o Expense *dependiendo en que pantalla agrego*
     * @param cx Context de la actividad
     * @param whatAdd en formato: "Ingreso" o "Gasto" < Estáticos
     */
    public void dialogAdd(final Context cx, String whatAdd){ // whatAdd = "Ingreso" o "Gasto"
        dialogContext = cx;

        changeTypeOfAdd(whatAdd);

        final android.app.Dialog dialog = new android.app.Dialog(cx);
        final EditText dialogName;
        final EditText dialogAmount;
        final EditText dialogDate;
        final EditText dialogDescription;
        Button btnAcept;


        dialog.setContentView(R.layout.customdialog_add);
        dialog.setTitle("Agregar " + whatAdd); // Ejemplo: "Agregar Ingreso" "Agregar Gasto"

        dialogName = dialog.findViewById(R.id.DialogEditTextName);
        dialogAmount = dialog.findViewById(R.id.DialogEditTextAmount);
        dialogDate = dialog.findViewById(R.id.DialogEditTextDate);
        dialogDescription = dialog.findViewById(R.id.DialogEditTextDescription);
        btnAcept = dialog.findViewById(R.id.DialogButtonAceptar);


        Button btnAux = dialog.findViewById(R.id.BtnAuxFecha);

        btnAux.setOnClickListener(v -> {
            openOneDayPicker();
        });



        onChangeDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                dialogDate.setText(onChangeDate.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dialogDate.setText(onChangeDate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                dialogDate.setText(onChangeDate.getText().toString());
            }
        });



        btnAcept.setOnClickListener(v -> {
            String name = dialogName.getText().toString();
            double amount = Double.parseDouble(dialogAmount.getText().toString());
            String description = dialogDescription.getText().toString();
            String date = dialogDate.getText().toString();

            //--Agregar objecto (LISTA) -- SI typeOfAdd=true == "Ingreso" se agrega un Ingreso, en caso contrario es Gasto
            if ( typeOfAdd.equals("Ingreso") ){
                addIncome(name,amount,date,description,cx);
            }else{
                addExpense(name,amount,date,description,cx);
            }

            dialog.dismiss();
        });



        dialog.show();
    }

    public void dialogEdit(final Context cx, final int position, String type){
        dialogContext = cx;
        changeTypeOfEdit(type);

        final android.app.Dialog dialog = new android.app.Dialog(cx);
        final EditText dialogName;
        final EditText dialogAmount;
        final EditText dialogDate;
        final EditText dialogDescription;

        dialog.setContentView(R.layout.customdialog_edit);
        dialog.setTitle("Editar " + type); // Ejemplo: "Editar Ingreso" "Editar Gasto"

        dialogName = dialog.findViewById(R.id.DialogEditTextName);
        dialogAmount = dialog.findViewById(R.id.DialogEditTextAmount);
        dialogDate = dialog.findViewById(R.id.DialogEditTextDate);
        dialogDescription = dialog.findViewById(R.id.DialogEditTextDescription);

        com.ornach.nobobutton.NoboButton btnEdit = dialog.findViewById(R.id.BtnEdit);
        com.ornach.nobobutton.NoboButton btnDelete = dialog.findViewById(R.id.BtnDelete);



        Button btnAux = dialog.findViewById(R.id.BtnAuxFecha);

        btnAux.setOnClickListener(v -> {
            openOneDayPicker();
        });


        onChangeDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                dialogDate.setText(onChangeDate.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dialogDate.setText(onChangeDate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                dialogDate.setText(onChangeDate.getText().toString());
            }
        });


        if ( typeOfEdit.equals("Ingreso") ){
            dialogName.setText(Account.myAccount.incomes.get(position).getNameOfIncome());
            dialogAmount.setText(String.valueOf(Account.myAccount.incomes.get(position).getAmount()));
            dialogDate.setText(Account.myAccount.incomes.get(position).getDate());
            dialogDescription.setText(Account.myAccount.incomes.get(position).getDescription());
        }else{
            dialogName.setText(Account.myAccount.expenses.get(position).getNameOfExpense());
            dialogAmount.setText(String.valueOf(Account.myAccount.expenses.get(position).getAmount()));
            dialogDate.setText(Account.myAccount.expenses.get(position).getDate());
            dialogDescription.setText(Account.myAccount.expenses.get(position).getDescription());
        }


         btnDelete.setOnClickListener(v -> {
             if ( typeOfEdit.equals("Ingreso") ){
                 Account.myAccount.incomes.remove(position);
                 Toasty.success(cx, "Se eliminó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
                 refreshListOfIncomes(cx);
                 HomeActivity.updateInformationOfHome();
             }else{
                 Account.myAccount.expenses.remove(position);
                 Toasty.success(cx, "Se eliminó un gasto ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
                refresListOfExpense(cx);
                 HomeActivity.updateInformationOfHome();
             }
             dialog.dismiss();
         });

        btnEdit.setOnClickListener(v -> {
            String name = dialogName.getText().toString();
            double amount = Double.parseDouble(dialogAmount.getText().toString());
            String date = dialogDate.getText().toString();
            String description = dialogDescription.getText().toString();

            if ( typeOfEdit.equals("Ingreso") ){
                editIncome(position,name,amount,date,description,cx);
                HomeActivity.updateInformationOfHome();
            }else{
                editExpense(position,name,amount,date,description,cx);
                HomeActivity.updateInformationOfHome();

            }
            dialog.dismiss();

        });

        dialog.show();

    }


    // -- Faltan validaciones --
    private static void editIncome(int position,String name, double amount, String date, String desc, Context cx){
        Account.myAccount.incomes.set(position,Account.myAccount.incomes.get(position)).setNameOfIncome(name);
        Account.myAccount.incomes.set(position,Account.myAccount.incomes.get(position)).setAmount(amount);
        Account.myAccount.incomes.set(position,Account.myAccount.incomes.get(position)).setDate(date);
        Account.myAccount.incomes.set(position,Account.myAccount.incomes.get(position)).setDescription(desc);
        adapterListOfIncomes.notifyDataSetChanged();
        Toasty.success(cx, "Se editó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        typeOfEdit = "";
    }

    private static void editExpense(int position,String name, double amount, String date, String desc, Context cx){
        Account.myAccount.expenses.set(position,Account.myAccount.expenses.get(position)).setNameOfExpense(name);
        Account.myAccount.expenses.set(position,Account.myAccount.expenses.get(position)).setAmount(amount);
        Account.myAccount.expenses.set(position,Account.myAccount.expenses.get(position)).setDate(date);
        Account.myAccount.expenses.set(position,Account.myAccount.expenses.get(position)).setDescription(desc);
        adapterListOfExpense.notifyDataSetChanged();
        Toasty.success(cx, "Se editó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        typeOfEdit = "";
    }

    // -- FALTAN VALIDACIONES --
    private static void addIncome(String name,double amount,String date,String desc,Context cx){
        Income income = new Income(name,amount,desc,date);

        Toasty.success(cx, "Se agregó un ingreso ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        Account.myAccount.incomes.add(income);
        typeOfAdd = "";
        refreshListOfIncomes(cx);
        HomeActivity.updateInformationOfHome();
    }

    public static void refreshListOfIncomes(Context cx){
        adapterListOfIncomes = new ArrayAdapter<>(cx,android.R.layout.simple_list_item_1,Account.myAccount.incomes);
        listViewOfIncomes.setAdapter(adapterListOfIncomes);
        adapterListOfIncomes.notifyDataSetChanged();
        HomeActivity.updateInformationOfHome();
    }

    public static void refresListOfExpense(Context cx){
        adapterListOfExpense = new ArrayAdapter<>(cx,android.R.layout.simple_list_item_activated_1,Account.myAccount.expenses);
        listViewOfExpenses.setAdapter(adapterListOfExpense);
        adapterListOfExpense.notifyDataSetChanged();
        HomeActivity.updateInformationOfHome();

    }

    private static void addExpense(String name,double amount,String date,String desc,Context cx){
        Expense expense = new Expense(name,amount,desc,date);
        Account.myAccount.expenses.add(expense);
        refresListOfExpense(cx);
        Toasty.success(cx, "Se agregó un gasto ¡Éxitosamente!", Toast.LENGTH_SHORT).show();
        HomeActivity.updateInformationOfHome();
    }



    private void openOneDayPicker() {
        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -5);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 10);

        DatePickerBuilder oneDayBuilder = new DatePickerBuilder(dialogContext, this)
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
        dateAux = dateAux.replaceAll("GMT","");
        dateAux = dateAux.replaceAll("GMT-5","");
        dateAux = dateAux.replaceAll("GMT-6","");
        onChangeDate.setText(dateAux);
        onChangeDate.setAlpha(0);

    }



}
