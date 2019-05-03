package com.example.realmactivitat1mp3;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Persona extends RealmObject {

    @PrimaryKey
    public int id;

    public Persona(int id, String nom, String cognoms, String genere, int edat) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.genere = genere;
        this.edat = edat;
    }

    public Persona(){

    }

    public String nom;
    public String cognoms;
    public String genere;
    public int edat;
}
