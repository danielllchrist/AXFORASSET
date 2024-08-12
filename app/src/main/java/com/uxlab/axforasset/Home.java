package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ArrayList<Asset> assets;
    private User user;
    private ViewFlipper carousel;
    private GestureDetector gestureDetector;
    private LinearLayout dropdownMenu;
    private ImageView barsIcon;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView welcomeText;
    private TextView items_nav;
    private TextView profile_nav;
    private TextView logout_nav;
    private LinearLayout overlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        assets = (ArrayList<Asset>) getIntent().getSerializableExtra("assets", ArrayList.class);
        user = getIntent().getParcelableExtra("user", User.class);

        welcomeText = findViewById(R.id.welcome_text);
        carousel = findViewById(R.id.carousel);
        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        items_nav = findViewById(R.id.items_nav);
        profile_nav = findViewById(R.id.profile_nav);
        logout_nav = findViewById(R.id.logout_nav);
        overlay = findViewById(R.id.overlay);

        items_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Items.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        profile_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        logout_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Login.class);
                intent.putExtra("user", user);
                intent.putExtra("assets", assets);
                startActivity(intent);
            }
        });

        String username = user.getName();
        welcomeText.setText("Welcome, " + username + "!" );

        int[] images = {R.drawable.c1, R.drawable.c2, R.drawable.c3};

        for (int i = 0; i < images.length; i++) {
            slideImage(images[i]);
        }

        gestureDetector = new GestureDetector(this, new GestureListener());
        carousel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        barsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDropdownMenu();
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

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragmentOne(), "Terms");
        vpAdapter.addFragment(new FragmentTwo(), "Conditions");
        viewPager.setAdapter(vpAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tabTextView = ((TextView) ((ViewGroup) tab.view).getChildAt(1));
                tabTextView.setTextAppearance(Home.this, R.style.TabActiveStyle);
                tab.view.setBackgroundResource(R.drawable.tab_active_background);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView tabTextView = ((TextView) ((ViewGroup) tab.view).getChildAt(1));
                tabTextView.setTextAppearance(Home.this, R.style.TabDefaultStyle);
                tab.view.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        tabLayout.selectTab(tabLayout.getTabAt(1));
        tabLayout.selectTab(tabLayout.getTabAt(0));
    }

    private void slideImage(int image) {
        ShapeableImageView iv = new ShapeableImageView(Home.this);
        iv.setImageResource(image);

        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel.Builder()
                .setAllCorners(CornerFamily.ROUNDED, 30)
                .build();

        iv.setShapeAppearanceModel(shapeAppearanceModel);

        carousel.addView(iv);
        carousel.setAutoStart(true);
        carousel.setFlipInterval(2000);
        carousel.setInAnimation(Home.this, R.anim.slide_in_left);
        carousel.setOutAnimation(Home.this, R.anim.slide_out_right);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    // Swipe kanan
                    carousel.setInAnimation(Home.this, R.anim.slide_in_right);
                    carousel.setOutAnimation(Home.this, R.anim.slide_out_left);
                    carousel.showPrevious();
                } else {
                    // Swipe kiri
                    carousel.setInAnimation(Home.this, R.anim.slide_in_left);
                    carousel.setOutAnimation(Home.this, R.anim.slide_out_right);
                    carousel.showNext();
                }
                return true;
            }
            return false;
        }
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