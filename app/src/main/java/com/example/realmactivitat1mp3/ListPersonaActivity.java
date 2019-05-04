package com.example.realmactivitat1mp3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListPersonaActivity extends AppCompatActivity {

    Realm realm;
    Adapter adapter;
    private ListView lsvData;
    int opcionBusqueda;
    TextView edadText1, edadText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_persona);
        Realm.init(this);
        lsvData = findViewById(R.id.persona_list);
        realm = Realm.getDefaultInstance();

        edadText1 = findViewById(R.id.edad1);
        edadText2 = findViewById(R.id.edad2);


        String[] opcionesSpinnerFiltro = new String[] {
                "Entre Edades", "Edat o menor", "Edat o mayor","Genero" };
        Spinner spinner = findViewById(R.id.filter);
        ArrayAdapter<String> spinneroptions = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcionesSpinnerFiltro);
        spinneroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneroptions);

        String[] opcionesSpinnerGenero = new String[] {
                "Hombre", "Mujer", "Otro"};
        final Spinner spinner2 = findViewById(R.id.genero);
        ArrayAdapter<String> spinneroptions2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcionesSpinnerGenero);
        spinneroptions2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinneroptions2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                findViewById(R.id.genero).setVisibility(View.INVISIBLE);
                findViewById(R.id.edad1).setVisibility(View.INVISIBLE);
                findViewById(R.id.edad2).setVisibility(View.INVISIBLE);
                opcionBusqueda = position;
                if (position == 0) {
                    findViewById(R.id.edad1).setVisibility(View.VISIBLE);
                    findViewById(R.id.edad2).setVisibility(View.VISIBLE);
                }
                if (position == 1 || position == 2) {
                    findViewById(R.id.edad1).setVisibility(View.VISIBLE);
                }
                if (position == 3) {
                    findViewById(R.id.genero).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        adapter = new Adapter(personas);
        if(personas.size()>0) lsvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        findViewById(R.id.filtro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Persona> filtrar = realm.where(Persona.class).findAll();
                int edad1 = 0;
                int edad2 = 0;
                if (!edadText1.getText().toString().isEmpty()) {
                    edad1 = Integer.parseInt(edadText1.getText().toString());
                }
                if (!edadText2.getText().toString().isEmpty()) {
                    edad2 = Integer.parseInt(edadText2.getText().toString());
                }

                if (opcionBusqueda == 0){
                    filtrar = realm.where(Persona.class)
                            .between("edat", edad1,edad2)
                            .findAll();
                }
                else if (opcionBusqueda == 1){
                    filtrar = realm.where(Persona.class)
                            .lessThanOrEqualTo("edat", edad1)
                            .findAll();
                }
                else if (opcionBusqueda == 2){
                    filtrar = realm.where(Persona.class)
                            .greaterThanOrEqualTo("edat", edad1)
                            .findAll();
                }
                else if (opcionBusqueda == 3){
                    filtrar = realm.where(Persona.class)
                            .equalTo("genere", String.valueOf(spinner2.getSelectedItem()))
                            .findAll();

                }

                adapter = new Adapter(filtrar);
                lsvData.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
