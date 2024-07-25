package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private Spinner paymentMethodSpinner;
//    private Button buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.email_input);
        paymentMethodSpinner = findViewById(R.id.payment_method_spinner);
        Button buyNowButton = findViewById(R.id.buy_now_button);

        // Set up the spinner with payment methods
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        buyNowButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String paymentMethod = paymentMethodSpinner.getSelectedItem().toString();

            if (email.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();
                // Handle purchase logic here
            }
        });

    }
}
