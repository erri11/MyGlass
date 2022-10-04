package com.example.myglass;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentOcchialiDetails extends Fragment {
    public Toast messagOK;
    public Toast messagErr;

    private Occhiale occhiale;
    public FragmentOcchialiDetails(Occhiale occhiale, int position) {
        super(position);
        this.occhiale = occhiale;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        messagOK =  Toast.makeText(getContext(), "Prodotto aggiunto al carrello!", Toast.LENGTH_LONG);
        messagErr =  Toast.makeText(getContext(), "Ups! qualcosa è andato storto :(", Toast.LENGTH_LONG);

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


        Button buttonOccSole = (Button)view.findViewById(R.id.button_addshop);
        buttonOccSole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder url = new StringBuilder("http://10.0.2.2:4444/addShop");
                url.append("?occhiale_id=");
                url.append(occhiale.getOcchiale_id());
                addShopFunction(url.toString());
            }
        });

        return view;
    }

    public void addShopFunction(String url){
        OkHttpClient client= new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String respo = response.body().string();
                    if(respo.equals("true")){
                        messagOK.show();
                        // startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else{
                        messagErr.show();
                    }
                    return;
                }
            }
        });

    }
}