package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private ArrayList<Asset> assets;
    private User user;
    EditText usernameEt;
    EditText passwordEt;
    TextView usernameErrorEt;
    TextView passwordErrorEt;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        usernameErrorEt = findViewById(R.id.usernameErrorEt);
        passwordErrorEt = findViewById(R.id.passwordErrorEt);
        loginBtn = findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameErrorEt.setText("");
                passwordErrorEt.setText("");
                if (usernameEt.getText().toString().equals("")){
                    usernameErrorEt.setText("Username can't be empty");
                }
                if (passwordEt.length() < 8){
                    passwordErrorEt.setText("Password must be at least 8 character");
                }
                if (passwordEt.getText().toString().equals("")){
                    passwordErrorEt.setText("Password can't be empty");
                }
                if(!usernameEt.getText().toString().equals("") && !passwordEt.getText().toString().equals("") && passwordEt.length() >= 8){
                    usernameErrorEt.setText("");
                    passwordErrorEt.setText("");
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