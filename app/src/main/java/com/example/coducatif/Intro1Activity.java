package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Intro1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1); // Assurez-vous que le nom du fichier XML est correct

        // Animation de l'image centrale avec une rotation et un zoom
        ImageView imageView = findViewById(R.id.centralImage);
        imageView.setAlpha(0f); // Début avec alpha = 0
        imageView.animate()
                .alpha(1f) // Fait apparaître l'image progressivement
                .rotation(360) // Effectue une rotation de 360°
                .scaleX(1.5f) // Agrandit l'image en largeur
                .scaleY(1.5f) // Agrandit l'image en hauteur
                .setDuration(2000) // Durée de l'animation
                .start();

        // Animation du titre avec une translation du bas vers le haut
        TextView titleText = findViewById(R.id.titleText);
        titleText.setTranslationY(100f);
        titleText.animate()
                .translationY(0f)
                .setDuration(1000)
                .setStartDelay(500) // Délai avant de commencer l'animation
                .start();

        // Animation du sous-titre avec un léger fondu
        TextView subtitleText = findViewById(R.id.subtitleText);
        subtitleText.setAlpha(0f); // Début avec alpha = 0
        subtitleText.animate()
                .alpha(1f) // Fait apparaître le texte progressivement
                .setDuration(1500)
                .setStartDelay(1000) // Délai avant de commencer l'animation
                .start();

        // Trouver le bouton ImageButton dans le layout
        ImageButton nextButton = findViewById(R.id.nextButton);
        // Trouver la TextView "skipText" dans le layout
        TextView skipText = findViewById(R.id.skipText);

        // Définir un écouteur de clics pour le bouton nextButton
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité Intro2Activity
                Intent intent = new Intent(Intro1Activity.this, Intro2Activity.class);
                startActivity(intent);
            }
        });

        // Définir un écouteur de clics pour la TextView "skipText"
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité SignUpActivity lorsque "Skip" est cliqué
                Intent intent = new Intent(Intro1Activity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}