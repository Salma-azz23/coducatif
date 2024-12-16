package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        levelTextView.setText(level + " Level Selected");

        // Ajouter le comportement pour le bouton retour
        findViewById(R.id.back_button).setOnClickListener(v -> finish());



        // Associer chaque autre carte à une activité spécifique
        findViewById(R.id.course_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, course_content.class)));

        findViewById(R.id.roadmap_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, roadmap.class)));
        findViewById(R.id.quiz_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, Quiz1Partie1Activity.class)));
        findViewById(R.id.community_card).setOnClickListener(v -> startActivity(new Intent(course_crcq.this, InviteFriendsActivity.class)));
    }
}

