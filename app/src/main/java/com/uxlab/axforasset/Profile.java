package com.uxlab.axforasset;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    private TextView welcomeText;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);
        welcomeText = findViewById(R.id.welcome_text);
        email = findViewById(R.id.email);

        String username = "Joxx";
        welcomeText.setText("Hi, " + username + "!");
        email.setText(username + "@gmail.com");

        barsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDropdownMenu();
            }
        });

        findViewById(R.id.relative_layout_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropdownMenu.getVisibility() == View.VISIBLE) {
                    toggleDropdownMenu();
                }
            }
        });

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To prevent dropdown menu click from closing it
            }
        });

        toggleDropdownMenu();
    }

    private void toggleDropdownMenu() {
        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        slide_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // No action needed
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dropdownMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // No action needed
            }
        });

        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dropdownMenu.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // No action needed
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // No action needed
            }
        });

        if (dropdownMenu.getVisibility() == View.VISIBLE) {
            dropdownMenu.startAnimation(slide_up);
        } else {
            dropdownMenu.startAnimation(slide_down);
        }
    }
}
