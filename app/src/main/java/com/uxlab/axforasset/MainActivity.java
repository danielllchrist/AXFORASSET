package com.uxlab.axforasset;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);

        // Set initial visibility
        dropdownMenu.setVisibility(View.GONE);

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


