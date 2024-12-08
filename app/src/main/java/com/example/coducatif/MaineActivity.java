package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coducatif.R;


public class MaineActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maine);


        TextView skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(v -> {

            Intent intent = new Intent(MaineActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        });



        findViewById(R.id.nextButton).setOnClickListener(v -> {

            Intent intent = new Intent(MaineActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

