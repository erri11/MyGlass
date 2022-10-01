package com.example.myglass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public Toast messag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btn_signup = findViewById(R.id.signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        } );

        Button btn_login = findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messag =  Toast.makeText(LoginActivity.this, "Username o Password Errati!", Toast.LENGTH_LONG);

                //prendo l'username dell'utente
                EditText editTextusername = findViewById(R.id.inputUsername);
                String username = String.valueOf(editTextusername.getText());

                //prendo la password dell'utente
                EditText editTextpassword = findViewById(R.id.inputPassword);
                String password = String.valueOf(editTextpassword.getText());

                //http://127.0.1.1:4444/checkUser?username=marco&password=cretino
                StringBuilder url = new StringBuilder("http://10.0.2.2:4444/checkUser");
                url.append("?username=");
                url.append(username);
                url.append("&");
                url.append("password=");
                url.append(password);
                CheckUserDb(url.toString());
            }
        } );
    }

    public void CheckUserDb(String url){
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
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else{
                        messag.show();
                    }
                    return;
                }
            }
        });

    }
}