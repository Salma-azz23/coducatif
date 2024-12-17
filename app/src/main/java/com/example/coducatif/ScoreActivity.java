package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Récupérer le score et le nombre total de questions
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Afficher le score
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Votre score: " + score + "/" + totalQuestions);

        // Action du bouton Réessayer
        MaterialButton btnRetry = findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(v -> {
            Intent retryIntent = new Intent(ScoreActivity.this, QuiZ1Partie1Activity.class);
            startActivity(retryIntent);
            finish();
        });
    }
}



