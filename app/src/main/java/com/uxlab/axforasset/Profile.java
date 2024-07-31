package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Profile extends AppCompatActivity {

    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    private boolean isMenuVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);

        // Set initial visibility
        dropdownMenu.setVisibility(View.GONE);
        isMenuVisible = false;

        // Set click listener
        barsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuVisible) {
                    dropdownMenu.setVisibility(View.GONE);
                } else {
                    dropdownMenu.setVisibility(View.VISIBLE);
                }
                isMenuVisible = !isMenuVisible;
            }
        });
    }
}