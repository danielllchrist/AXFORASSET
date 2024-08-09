package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

public class Home extends AppCompatActivity {

    ViewFlipper carousel;
    GestureDetector gestureDetector;
    LinearLayout dropdownMenu;
    ImageView barsIcon;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.welcome_text);
        carousel = findViewById(R.id.carousel);
        dropdownMenu = findViewById(R.id.dropdown_menu);
        barsIcon = findViewById(R.id.bars_icon);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        String username = "Joxx";
        welcomeText.setText("Welcome, " + username + " !" );

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

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragmentOne(), "Terms");
        vpAdapter.addFragment(new FragmentTwo(), "Conditions");
        viewPager.setAdapter(vpAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Gaya tab aktif
                TextView tabTextView = ((TextView) ((ViewGroup) tab.view).getChildAt(1));
                tabTextView.setTextAppearance(Home.this, R.style.TabActiveStyle);
                tab.view.setBackgroundResource(R.drawable.tab_active_background); // Set custom background for active tab
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Gaya tab tidak aktif
                TextView tabTextView = ((TextView) ((ViewGroup) tab.view).getChildAt(1));
                tabTextView.setTextAppearance(Home.this, R.style.TabDefaultStyle);
                tab.view.setBackgroundColor(Color.TRANSPARENT); // Reset to transparent for non-active tab
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No change needed for reselection
            }
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
        carousel.setFlipInterval(4000);
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