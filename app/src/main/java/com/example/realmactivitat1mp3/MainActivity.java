package com.example.realmactivitat1mp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        final RealmConfiguration configuration = new RealmConfiguration.Builder().name("sample.realm").schemaVersion(2).migration(new RealmMigrations()).build();
        Realm.setDefaultConfiguration(configuration);
        Realm.getInstance(configuration);

        findViewById(R.id.crear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPersonaActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListPersonaActivity.class);
                startActivity(intent);
            }
        });

    }
}
