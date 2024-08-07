package com.uxlab.axforasset;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


//    private Button buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }
}
