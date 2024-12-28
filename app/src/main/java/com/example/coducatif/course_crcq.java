package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class course_crcq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_crcq);

        // Récupérer et afficher le niveau
        String level = getIntent().getStringExtra("level");
        TextView levelTextView = findViewById(R.id.title);

        if (level != null && !level.trim().isEmpty()) {
            levelTextView.setText(level + " Level Selected");
        } else {
            levelTextView.setText(""); // Afficher une chaîne vide ou un texte par défaut
        }

        // Ajouter le comportement pour le bouton retour
        findViewById(R.id.back_button).setOnClickListener(v -> finish());

        // Associer chaque autre carte à une activité spécifique
        findViewById(R.id.course_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, course_content.class)));
        findViewById(R.id.roadmap_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, roadmap.class)));
        findViewById(R.id.quiz_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, QuiZ1Partie1Activity.class)));
        findViewById(R.id.community_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, chat_community.class)));
    }
}