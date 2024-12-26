package com.example.coducatif;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView; // Assurez-vous d'importer TextView aussi
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
public class Intro2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2); // Assurez-vous que le nom du fichier XML est correct


        ImageView imageView = findViewById(R.id.centralImage);

// Créer une animation de mise à l'échelle avec un interpolateur
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 2f);

        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleX.setDuration(2000);
        scaleY.setDuration(2000);

        scaleX.start();
        scaleY.start();



        // Trouver le bouton "sign_in_button" dans le layout
        Button signInButton = findViewById(R.id.sign_in_button);

        // Trouver la TextView "skipText" dans le layout
        TextView skipText = findViewById(R.id.skipText);

        // Définir un écouteur de clics pour le bouton "Sign In"
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité SignUpActivity lorsque le bouton est cliqué
                Intent intent = new Intent(Intro2Activity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Définir un écouteur de clics pour la TextView "Skip"
        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité SignUpActivity lorsque "Skip" est cliqué
                Intent intent = new Intent(Intro2Activity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}

