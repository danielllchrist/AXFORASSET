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

    ArrayList<Asset> assets;
    User user;
    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    TextView home_nav;
    TextView profile_nav;
    TextView logout_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        home_nav = findViewById(R.id.home_nav);
        profile_nav = findViewById(R.id.profile_nav);
        logout_nav = findViewById(R.id.logout_nav);

        home_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Items.this, Home.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        profile_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Items.this, Profile.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        logout_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Items.this, Login.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);

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

        toggleDropdownMenu();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),assets));
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
        } else {
            dropdownMenu.startAnimation(slide_down);
        }
    }
}