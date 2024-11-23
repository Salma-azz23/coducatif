package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);


        TextView skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
            finish();
        });


        findViewById(R.id.nextButton).setOnClickListener(v -> {

            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intent);
            finish();
        });
    }
}


