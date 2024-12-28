package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Get the views
        ImageView imageView = findViewById(R.id.centralImage);
        ImageButton nextButton = findViewById(R.id.nextButton);
        TextView skipText = findViewById(R.id.skipText);

        // Apply a smooth scale animation to the image
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_in);
        imageView.startAnimation(scaleAnimation);

        // Next button click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, Intro1Activity.class);
                startActivity(intent);
            }
        });

        // Skip button click listener
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}