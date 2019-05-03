package com.example.realmactivitat1mp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListPersonaActivity extends AppCompatActivity {

    Realm realm;
    Adapter adapter;
    private ListView lsvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_persona);
        Realm.init(this);
        lsvData = findViewById(R.id.persona_list);
        realm = Realm.getDefaultInstance();

        final RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        adapter = new Adapter(personas);
        if(personas.size()>0) lsvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
