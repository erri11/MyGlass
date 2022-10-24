package com.example.myglass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PaymentFragment extends Fragment {

    public Toast messagOK, messagErr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);


        messagErr = Toast.makeText(getContext(), "Ups! Qualcosa è andato storto :(!", Toast.LENGTH_LONG);

        ImageView mastercard = view.findViewById(R.id.logo_MC);
        mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagOK = Toast.makeText(getContext(), "Prodotti acquistati con mastercard!", Toast.LENGTH_LONG);
                String url = "http://10.0.2.2:4444/buyItem";
                BuyItemShop(url);
            }
        });

        ImageView visa = view.findViewById(R.id.logo_V);
        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagOK = Toast.makeText(getContext(), "Prodotti acquistati con visa!", Toast.LENGTH_LONG);
                String url = "http://10.0.2.2:4444/buyItem";
                BuyItemShop(url);
            }
        });

        ImageView paypal = view.findViewById(R.id.logo_PP);
        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagOK = Toast.makeText(getContext(), "Prodotti acquistati con PayPal!", Toast.LENGTH_LONG);
                String url = "http://10.0.2.2:4444/buyItem";
                BuyItemShop(url);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void BuyItemShop(String url) {
        OkHttpClient client = new OkHttpClient();
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
                if (response.isSuccessful()) {
                    String respo = response.body().string();
                    if (respo.equals("true")) {

                        messagOK.show();
                        FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                        fr.replace(R.id.navHostFragment, new ShopFragment()).commit();

                    } else {
                        messagErr.show();
                    }
                    return;
                }
            }
        });

    }
}