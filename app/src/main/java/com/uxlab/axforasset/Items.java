package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Items extends AppCompatActivity {

    private ArrayList<Asset> assets;
    private User user;
    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    private TextView home_nav;
    private TextView profile_nav;
    private TextView logout_nav;
    private LinearLayout overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        // Initialize views and fields
        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        home_nav = findViewById(R.id.home_nav);
        profile_nav = findViewById(R.id.profile_nav);
        logout_nav = findViewById(R.id.logout_nav);
        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        overlay = findViewById(R.id.overlay);

        // Set up navigation listeners
        home_nav.setOnClickListener(view -> {
            Intent intent = new Intent(Items.this, Home.class);
            intent.putExtra("user", user);
            intent.putExtra("assets", assets);
            startActivity(intent);
        });

        profile_nav.setOnClickListener(view -> {
            Intent intent = new Intent(Items.this, Profile.class);
            intent.putExtra("user", user);
            intent.putExtra("assets", assets);
            startActivity(intent);
        });

        logout_nav.setOnClickListener(view -> {
            Intent intent = new Intent(Items.this, Login.class);
            intent.putExtra("user", user);
            intent.putExtra("assets", assets);
            startActivity(intent);
        });

        // Set up dropdown toggle listener
        barsIcon.setOnClickListener(v -> toggleDropdownMenu());

        // Set up click listener for the root layout
        overlay.setOnTouchListener((v, event) -> {
            if (dropdownMenu.getVisibility() == View.VISIBLE) {
                toggleDropdownMenu();
            }
            return false;
        });

        // Initialize the dropdown menu as hidden
        toggleDropdownMenu();

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), assets, user));
        recyclerView.setNestedScrollingEnabled(false);
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
            overlay.setVisibility(View.GONE);
        } else {
            dropdownMenu.startAnimation(slide_down);
            overlay.setVisibility(View.VISIBLE);
        }
    }
}