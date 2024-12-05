package com.example.coducatif.;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> {
            Intent intent = new Intent(IntroActivity.this, Intro1Activity.class);
            startActivity(intent);
        });
    }
}

