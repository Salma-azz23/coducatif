package com.example.coducatif;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maine);

        // Appliquer les effets au logo
        ImageView logoImage = findViewById(R.id.logoImage);
        applyGlowAndZoomEffect(logoImage);

        // Redirection après 5 secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MaineActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        }, 5000); // Temps d'attente avant redirection (5 secondes)
    }

    private void applyGlowAndZoomEffect(ImageView logo) {
        // 1. Animation de brillance (transparence)
        AlphaAnimation glow = new AlphaAnimation(0.5f, 1f); // Opacité de 50% à 100%
        glow.setDuration(1000); // Durée d'une transition
        glow.setRepeatCount(AlphaAnimation.INFINITE); // Répète indéfiniment
        glow.setRepeatMode(AlphaAnimation.REVERSE); // Inverse la transition

        // 2. Animation de zoom (légère expansion/contraction)
        ScaleAnimation zoom = new ScaleAnimation(
                1f, 1.05f, // Échelle X (de 100% à 105%)
                1f, 1.05f, // Échelle Y (de 100% à 105%)
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // Pivot X (centre)
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f); // Pivot Y (centre)
        zoom.setDuration(1500); // Durée pour une transition de zoom
        zoom.setRepeatCount(ScaleAnimation.INFINITE); // Répète indéfiniment
        zoom.setRepeatMode(ScaleAnimation.REVERSE); // Inverse l'expansion

        // 3. Lancer les animations
        logo.startAnimation(glow);
        logo.startAnimation(zoom);
    }
}
