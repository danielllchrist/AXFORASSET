package com.uxlab.axforasset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class GameAssetDetail extends AppCompatActivity {

    private ArrayList<Asset> assets;
    private User user;
    private ImageView game_image, gameLogo;
    private TextView item_header, item_description;

    private EditText emailInput;
    private Spinner paymentMethodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_asset_detail);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        game_image = findViewById(R.id.game_image);
        item_header = findViewById(R.id.item_header);
        item_description = findViewById(R.id.item_description);
        gameLogo = findViewById(R.id.image_logo);

        Bundle bundle = getIntent().getExtras();

        game_image.setImageResource(bundle.getInt("Image"));
        item_header.setText(bundle.getString("Name"));
        item_description.setText(bundle.getString("LongDesc"));

        emailInput = findViewById(R.id.email_input);
        paymentMethodSpinner = findViewById(R.id.payment_method_spinner);
        Button buyNowButton = findViewById(R.id.buy_now_button);

        // Set up the spinner with payment methods
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        // Add mouse down interaction to the button
        buyNowButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.6f); // Dim the button when pressed
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.setAlpha(1.0f); // Restore alpha when released
                    break;
            }
            return false; // Return false to allow click event to be triggered
        });

        buyNowButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String paymentMethod = paymentMethodSpinner.getSelectedItem().toString();

            if (email.isEmpty()) {
                showEmailDialog("Email must be filled", "Try Again");
            } else {
                showEmailDialog("Confirmation email has been sent to your email!", "Ok");
            }
        });
        gameLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameAssetDetail.this, Home.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
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
        dialogButton.setOnClickListener(v -> {
            dialog.dismiss();
            finish();  // Menutup aktivitas saat ini untuk kembali ke halaman sebelumnya
        });
        dialog.show();
    }

}
