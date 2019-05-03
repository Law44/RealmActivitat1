package com.example.realmactivitat1mp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.realmactivitat1mp3.Persona;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by jordi on 24/01/17.
 */

public class Adapter extends RealmBaseAdapter<Persona> implements ListAdapter {

    public Adapter(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);

    }

    private static class ViewHolder {
        TextView txvNom;
        TextView txvCognoms;
        TextView txvEdat;
        TextView txvGenere;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txvNom = convertView.findViewById(R.id.text1);
            viewHolder.txvCognoms = convertView.findViewById(R.id.text2);
            viewHolder.txvEdat = convertView.findViewById(R.id.text3);
            viewHolder.txvGenere = convertView.findViewById(R.id.text4);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Persona item = adapterData.get(position);
        viewHolder.txvNom.setText("Nom: " + item.nom);
        viewHolder.txvCognoms.setText("Cognoms: " + item.cognoms);
        viewHolder.txvEdat.setText("Edat: " + String.valueOf(item.edat));
        viewHolder.txvGenere.setText("Genere: " + item.genere);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), ModifyPersonaActivity.class);
                intent.putExtra("ModificarNombre", item.nom);
                intent.putExtra("ModificarApellido", item.cognoms);
                intent.putExtra("ModificarEdat", String.valueOf(item.edat));
                intent.putExtra("ModificarId", item.id);
                intent.putExtra("ModificarGenere", item.genere);


                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}