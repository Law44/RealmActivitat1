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

public class AddPersonaActivity extends AppCompatActivity {

    EditText etName, etSurname, etAge, etGender;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpersona);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etAge = findViewById(R.id.etAge);

        String[] opcionesSpinner = new String[] {
                "Hombre", "Mujer", "Otro" };
        Spinner spinner = findViewById(R.id.etGender);
        ArrayAdapter<String> spinneroptions = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcionesSpinner);
        spinneroptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneroptions);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = String.valueOf(parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Realm.init(this);

        final Realm realm = Realm.getDefaultInstance();


        findViewById(R.id.crear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();

                Number maxId = realm.where(Persona.class).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;

                Persona persona = new Persona(nextId, etName.getText().toString(), etSurname.getText().toString(), gender, Integer.parseInt((etAge.getText().toString())));
                realm.insertOrUpdate(persona);

                realm.commitTransaction();

                finish();
            }
        });

    }
}
