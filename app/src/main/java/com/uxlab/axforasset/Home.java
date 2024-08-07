package com.uxlab.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Home extends AppCompatActivity {

    ViewFlipper carousel;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        carousel = findViewById(R.id.carousel);

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
    }

    private void slideImage(int image) {
        ImageView iv = new ImageView(Home.this);
        iv.setBackgroundResource(image);
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
}