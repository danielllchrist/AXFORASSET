package com.uxlab.axforasset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    ArrayList<Asset> assets;
    User user;
    LinearLayout dropdownMenu;
    ImageView barsIcon;
    TextView welcomeText;
    TextView email;
    TextView home_nav;
    TextView items_nav;
    TextView profile_nav;
    TextView logout_nav;
    LinearLayout overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);
        welcomeText = findViewById(R.id.welcome_text);
        email = findViewById(R.id.email);
        home_nav = findViewById(R.id.home_nav);
        items_nav = findViewById(R.id.items_nav);
        profile_nav = findViewById(R.id.profile_nav);
        logout_nav = findViewById(R.id.logout_nav);
        overlay = findViewById(R.id.overlay);

        String username = user.getName();
        welcomeText.setText("Hi, " + username + "!");
        email.setText(username + "@gmail.com");

        barsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDropdownMenu();
            }
        });

        home_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Home.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        items_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Items.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        profile_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Profile.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        logout_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Login.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropdownMenu.getVisibility() == View.VISIBLE) {
                    toggleDropdownMenu();
                }
            }
        });

        dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { }
        });

        toggleDropdownMenu();
    }

    private void toggleDropdownMenu() {
        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        slide_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                dropdownMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dropdownMenu.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        if (dropdownMenu.getVisibility() == View.VISIBLE) {
            dropdownMenu.startAnimation(slide_up);
            overlay.setVisibility(View.GONE);
        } else {
            dropdownMenu.startAnimation(slide_down);
            overlay.setVisibility(View.VISIBLE);
        }
    }
}
