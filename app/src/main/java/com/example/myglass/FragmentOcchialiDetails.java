package com.example.myglass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentOcchialiDetails extends Fragment {

    private Occhiale occhiale;
    public FragmentOcchialiDetails(Occhiale occhiale, int position) {
        super(position);
        this.occhiale = occhiale;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_occhiali_details, container, false);
        AppCore a = new AppCore();
        ImageView imageView = view.findViewById(R.id.occ_img);
        TextView nome = view.findViewById(R.id.occ_nome);
        TextView descrizione = view.findViewById(R.id.occ_desc);
        TextView prezzo = view.findViewById(R.id.occ_prezzo);

        imageView.setImageResource(a.ReturnImgId(occhiale.percorso_immagine));
        nome.setText(occhiale.nome);
        descrizione.setText(occhiale.descrizione);
        prezzo.setText(Float.toString(occhiale.prezzo));

        return view;
    }
}