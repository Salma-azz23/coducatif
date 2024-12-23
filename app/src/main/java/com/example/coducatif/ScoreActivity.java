package com.example.coducatif;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private MaterialButton btnRetry;
    private MaterialButton btnCertified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);




        // Récupérer les scores
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        Log.d("ScoreActivity", "Score reçu : " + score + ", Total Questions : " + totalQuestions);

        // Mise à jour des textes
        TextView performanceTextView = findViewById(R.id.performanceTextView);
        TextView scoreDisplayTextView = findViewById(R.id.scoreDisplayTextView);

        String performanceMessage = calculatePerformance(score, totalQuestions);
        performanceTextView.setText(performanceMessage);
        scoreDisplayTextView.setText("🎯 Votre score : " + score + "/" + totalQuestions);

        // Jouer un son
        playCelebrationSound(score, totalQuestions);

        // Animation du trophée
        animateTrophy();

        // Trouver les boutons
        btnRetry = findViewById(R.id.btn_retry);
        btnCertified = findViewById(R.id.btn_certified);

        // Modifier la visibilité des boutons en fonction du score
        float percentage = ((float) score / totalQuestions) * 100;

        Log.d("ScoreActivity", "Score : " + score + ", Total Questions : " + totalQuestions);
        Log.d("ScoreActivity", "Pourcentage : " + percentage);

        if (percentage >= 50) {
            Log.d("ScoreActivity", "Score >= 50%, afficher 'Certifié'");
            btnRetry.setVisibility(MaterialButton.GONE);
            btnCertified.setVisibility(MaterialButton.VISIBLE);
        } else {
            Log.d("ScoreActivity", "Score < 50%, afficher 'Réessayer'");
            btnRetry.setVisibility(MaterialButton.VISIBLE);
            btnCertified.setVisibility(MaterialButton.GONE);
        }

        // Action du bouton "Réessayer"
        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, QuiZ1Partie1Activity.class);
            startActivity(intent);
            finish();
        });

        // Action du bouton "Certifié"
        btnCertified.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreActivity.this, certifActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private String calculatePerformance(int score, int totalQuestions) {
        float percentage = (float) score / totalQuestions * 100;
        Log.d("ScoreActivity", "Pourcentage : " + percentage);

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

    private void playCelebrationSound(int score, int totalQuestions) {
        MediaPlayer mediaPlayer;
        if ((float) score / totalQuestions >= 0.7) {
            mediaPlayer = MediaPlayer.create(this, R.raw.tryagain); // Son de victoire
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.bravo); // Son de consolation
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    private void animateTrophy() {
        ImageView trophyImageView = findViewById(R.id.trophyImageView);
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        trophyImageView.startAnimation(bounceAnimation);
    }

    // Animation pour chaque étoile
    private void animateStar(ImageView star) {
        // Créez une animation de translation pour faire tomber l'étoile
        TranslateAnimation animation = new TranslateAnimation(0, 0, -1000, 1000);
        animation.setDuration(2000); // Durée de la chute
        animation.setRepeatCount(0); // Ne pas répéter
        animation.setFillAfter(true); // Maintenir la position finale

        // Appliquer l'animation à l'étoile
        star.startAnimation(animation);
    }
}


