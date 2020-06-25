package com.example.proyecto_intermedio.Components;

public class EventOnList {

 /*
 private ArrayList<String> listaEjemplo = new ArrayList<>();
    private ArrayAdapter<String> listaAdapter;
    private ArrayAdapter<String> listaAdapter2;
    private ListView lista;
    Context cx = this;
    com.ornach.nobobutton.NoboButton elimiar;
    ListView listview ;
    Button btnClear;
*/





       /* listview = findViewById(R.id.ListViewGastos);
        btnClear = findViewById(R.id.BtnClear);


        listaEjemplo.add("Ejemplo 1");
        listaEjemplo.add("Ejemplo 2");
        listaEjemplo.add("Ejemplo 3");
        listaEjemplo.add("Ejemplo 4");
        listaEjemplo.add("Ejemplo 1");
        listaEjemplo.add("Ejemplo 2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
                android.R.id.text1, listaEjemplo );
        listview.setAdapter(adapter);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,
                        android.R.id.text1, listaEjemplo );
                listview.setAdapter(adapter);
                listview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            }
        });


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, listaEjemplo );
                listview.setAdapter(adapter);
                listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                        //android:choiceMode="multipleChoice"
                return false;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(cx, "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        listview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(cx, "No se selecciono nda", Toast.LENGTH_SHORT).show();
            }
        });

*/


}
