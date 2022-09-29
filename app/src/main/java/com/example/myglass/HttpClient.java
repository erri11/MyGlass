package com.example.myglass;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClient {

    public Occhiale[] occhiali;
    public OkHttpClient client= new OkHttpClient();
    public final Gson gson = new Gson();

    public HttpClient(){
    }

    public void downloadJson(String url){

        okhttp3.Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Occhiale[] occhiali = gson.fromJson(response.body().string(), (Type) Occhiale[].class);
                    HttpClient.this.SetOcchiali(occhiali);
                    return;
                }
            }
        });
    }

    public void SetOcchiali(Occhiale[] occhiali){
        this.occhiali = occhiali;
        System.out.println(occhiali);
    }

    public Occhiale[] GetOcchiali(){
        while(this.occhiali == null){
            continue;
        }
        return this.occhiali;
    }

    public Occhiale[] GetOcchiali1(){
        while(this.occhiali == null){
            continue;
        }
        return this.occhiali;
    }
}