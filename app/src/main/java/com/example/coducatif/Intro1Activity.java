package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView; // Importer TextView
import androidx.appcompat.app.AppCompatActivity;

public class Intro1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1); // Assurez-vous que le nom du fichier XML est correct

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
