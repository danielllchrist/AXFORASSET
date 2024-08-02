package com.uxlab.axforasset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GameAssetDetail extends AppCompatActivity {
    private EditText emailInput;
    private Spinner paymentMethodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_asset_detail);

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
                showEmailDialog("Email must be filled", "Try Again");
        } else {
                showEmailDialog("Confirmation email has been sent to your email!", "Ok");
            }
        });
    }
    private void showEmailDialog(String message, String buttonText) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_email, null);

        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        dialogMessage.setText(message);

        Button dialogButton = dialogView.findViewById(R.id.dialog_button);
        dialogButton.setText(buttonText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        dialogButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}