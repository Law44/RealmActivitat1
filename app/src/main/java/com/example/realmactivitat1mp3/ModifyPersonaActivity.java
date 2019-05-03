package com.example.realmactivitat1mp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmResults;

public class ModifyPersonaActivity extends AppCompatActivity {

    EditText etName, etSurname, etAge, etGender;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_persona);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etAge = findViewById(R.id.etAge);

        String[] arraySpinner = new String[] {
                "Hombre", "Mujer", "Otro" };
        Spinner s = findViewById(R.id.etGender);
        ArrayAdapter<String> spinneroptions = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinneroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(spinneroptions);


        final String nom = getIntent().getStringExtra("ModificarNombre");
        final String cognom = getIntent().getStringExtra("ModificarApellido");
        final String edat = getIntent().getStringExtra("ModificarEdat");
        final String genere = getIntent().getStringExtra("ModificarGenere");

        for (int i = 0; i < arraySpinner.length; i++) {
            if (arraySpinner[i].equals(genere)){
                s.setSelection(i);
            }
        }

        etName.setText(nom);
        etSurname.setText(cognom);
        etAge.setText(edat);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = String.valueOf(parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final int id = getIntent().getIntExtra("ModificarId",0);

        Realm.init(this);

        final Realm realm = Realm.getDefaultInstance();


        findViewById(R.id.modificar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                Persona persona = new Persona(id, etName.getText().toString(), etSurname.getText().toString(), gender, Integer.parseInt((etAge.getText().toString())));
                realm.insertOrUpdate(persona);

                realm.commitTransaction();

                finish();
            }
        });

        findViewById(R.id.eliminar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                RealmResults<Persona> eliminar = realm.where(Persona.class)
                        .equalTo("id", id)
                        .findAll();

                eliminar.deleteAllFromRealm();

                realm.commitTransaction();

                finish();
            }
        });

    }
}
