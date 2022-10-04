package com.example.myglass;

import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.IOException;
import java.sql.RowIdLifetime;

import javax.sql.RowSetListener;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopFragment extends Fragment {
    public Toast messag;
    public  Occhiale[] occhiali;
    public ListAdapterShop adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        messag =  Toast.makeText(getContext(), "Prodotto Eliminato!", Toast.LENGTH_LONG);


        HttpClient c = new HttpClient();
        //richiamare il metodo che seleziona gli elementi del carrello
        c.downloadJson("http://10.0.2.2:4444/loadShop");
        occhiali = c.GetOcchiali();
        while (occhiali == null){
            occhiali = c.GetOcchiali();
        }

        ListView listView = (ListView)view.findViewById(R.id.list_carrello);
        adapter = new ListAdapterShop(getActivity().getApplicationContext(), occhiali);
        listView.setAdapter(adapter);
    }

    public class ListAdapterShop extends ArrayAdapter<Occhiale> {

        public Toast messagOK, messagErr;

        public ListAdapterShop(Context context, Occhiale[] occhiale){

            super(context, R.layout.list_item_shop, occhiale);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_shop, null);



            messagOK =  Toast.makeText(getContext(), "Prodotto eliminato!", Toast.LENGTH_LONG);
            messagErr =  Toast.makeText(getContext(), "Ups! Qualcosa è andato storto :(!", Toast.LENGTH_LONG);

            AppCore a = new AppCore();
            ImageView imageView = convertView.findViewById(R.id.occ_img);
            TextView nome = convertView.findViewById(R.id.occ_nome);
            TextView descrizione = convertView.findViewById(R.id.occ_desc);
            TextView prezzo = convertView.findViewById(R.id.occ_prezzo);
            ListView listaElementi = convertView.findViewById(R.id.list_carrello);

            Occhiale occhiale = getItem(position);
            imageView.setImageResource(a.ReturnImgId(occhiale.percorso_immagine));
            nome.setText(occhiale.nome);
            descrizione.setText(occhiale.descrizione);
            prezzo.setText(Float.toString(occhiale.prezzo));

            ImageButton cestino = convertView.findViewById(R.id.cestino);
            cestino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("elemento eliminato " + occhiale.getOcchiale_id() );
                    StringBuilder url = new StringBuilder("http://10.0.2.2:4444/deleteItem");
                    url.append("?occhiale_id=");
                    url.append(occhiale.getOcchiale_id());
                    deleteItemFunction(url.toString());

                    //prendo l'occhiale da eliminare
                   /* Occhiale o = getItem(position);
                    adapter.remove(o);
                    adapter.notifyDataSetChanged();*/

                }
            });
            return convertView;
        }

        public void deleteItemFunction(String url){
            OkHttpClient client= new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    messagErr.show();
                    System.out.println(e.getMessage());
                    return;
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        String respo = response.body().string();
                        if(respo.equals("true")){
                            messagOK.show();
                            FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                            fr.replace(R.id.navHostFragment, new ShopFragment()).commit();
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
}