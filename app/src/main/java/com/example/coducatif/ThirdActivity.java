package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);


        TextView skipText = findViewById(R.id.skipText);
        skipText.setOnClickListener(v -> {

            Intent intent = new Intent(ThirdActivity.this, MaineActivity.class);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.nextButton).setOnClickListener(view -> {

            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.skipText).setOnClickListener(view -> {
            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            startActivity(intent);
        });
        ImageButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {

            Intent intent = new Intent(ThirdActivity.this, MaineActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
