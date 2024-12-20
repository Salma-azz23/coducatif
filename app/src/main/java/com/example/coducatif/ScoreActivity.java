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

        // Calculer et afficher la performance
        TextView performanceTextView = findViewById(R.id.performanceTextView);
        String performance = calculatePerformance(score, totalQuestions);
        performanceTextView.setText(performance);

        // Afficher le score
        TextView scoreDisplayTextView = findViewById(R.id.scoreDisplayTextView);
        scoreDisplayTextView.setText("🎯 Votre score : " + score + "/" + totalQuestions);


        // Action du bouton Réessayer
        MaterialButton btnRetry = findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(v -> {
            Intent retryIntent = new Intent(ScoreActivity.this, QuiZ1Partie1Activity.class);
            startActivity(retryIntent);
            finish();
        });
    }

    // Méthode pour calculer la performance
    private String calculatePerformance(int score, int totalQuestions) {
        float percentage = (float) score / totalQuestions * 100;

        if (percentage >= 90) {
            return "Excellent ! 🌟 Vous êtes un génie !";
        } else if (percentage >= 70) {
            return "Bon travail ! 👍 Continuez comme ça.";
        } else if (percentage >= 50) {
            return "Passable 🙂 Essayez encore pour mieux faire.";
        } else {
            return "Peut mieux faire Ne vous découragez pas !";
        }
    }
}




