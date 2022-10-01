package com.example.myglass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapterShop extends ArrayAdapter<Occhiale> {

    public ListAdapterShop(Context context, Occhiale[] occhiale){

        super(context, R.layout.list_item_shop, occhiale);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item_shop, null);

        AppCore a = new AppCore();
        ImageView imageView = convertView.findViewById(R.id.occ_img);
        TextView nome = convertView.findViewById(R.id.occ_nome);
        TextView descrizione = convertView.findViewById(R.id.occ_desc);
        TextView prezzo = convertView.findViewById(R.id.occ_prezzo);

        Occhiale occhiale = getItem(position);
        imageView.setImageResource(a.ReturnImgId(occhiale.percorso_immagine));
        nome.setText(occhiale.nome);
        descrizione.setText(occhiale.descrizione);
        prezzo.setText(Float.toString(occhiale.prezzo));

        return convertView;
    }
}
