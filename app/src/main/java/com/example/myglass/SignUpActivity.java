package com.example.myglass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {

    public Toast messag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView btn = findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        } );

        TextView btn_signup = findViewById(R.id.button_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                messag =  Toast.makeText(SignUpActivity.this, "Username già esistente!", Toast.LENGTH_LONG);
                //prendo l'username dell'utente
                EditText editTextName = findViewById(R.id.inputName);
                String name = String.valueOf(editTextName.getText());

                //prendo la password dell'utente
                EditText editTextsurname = findViewById(R.id.inputSurname);
                String surname = String.valueOf(editTextsurname.getText());

                //prendo l'email
                EditText editTextEmail = findViewById(R.id.inputEmail);
                String email = String.valueOf(editTextEmail.getText());

                //prendo l'username
                EditText editTextUsername = findViewById(R.id.inputUsername);
                String username = String.valueOf(editTextUsername.getText());

                //prendo la password
                EditText editTextPassword = findViewById(R.id.inputPassword);
                String password = String.valueOf(editTextPassword.getText());

                //http://127.0.1.1:4444/addUser?username=marco&password=cretino
                StringBuilder url = new StringBuilder("http://10.0.2.2:4444/addUser");
                url.append("?username=");
                url.append(username);
                url.append("&");
                url.append("password=");
                url.append(password);
                url.append("&");
                url.append("nome=");
                url.append(name);
                url.append("&");
                url.append("cognome=");
                url.append(surname);
                url.append("&");
                url.append("email=");
                url.append(email);
                SignUserDb(url.toString());
            }
        } );
    }

    public void SignUserDb(String url){
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
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
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