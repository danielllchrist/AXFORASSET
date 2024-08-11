package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private ArrayList<Asset> assets;
    private User user;
    EditText usernameEt;
    EditText passwordEt;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameEt.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Username can't be empty", Toast.LENGTH_SHORT).show();
                }else if (passwordEt.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                }else if (passwordEt.length() < 8){
                    Toast.makeText(Login.this, "Password must be at least 8 character", Toast.LENGTH_SHORT).show();
                }else {
                    user.setName(usernameEt.getText().toString());
                    user.setEmail(user.getName() + "@gmail.com");
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("user", user);
                    intent.putExtra("assets", assets);
                    startActivity(intent);
                }
            }
        });
    }
}