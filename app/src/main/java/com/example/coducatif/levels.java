package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.ImageView;

import com.example.coducatif_acceuil.R;


public class levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        // Définir des actions pour chaque carte de niveau
        CardView beginnerCard = findViewById(R.id.beginner_card);
        CardView intermediateCard = findViewById(R.id.intermediate_card);
        CardView advancedCard = findViewById(R.id.advanced_card);
        CardView expertCard = findViewById(R.id.expert_card);

        // Clic pour Beginner
        beginnerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, course_crcq.class);
                intent.putExtra("level", "Beginner");
                startActivity(intent);
            }
        });

        // Clic pour Intermediate
        intermediateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, course_crcq.class);
                intent.putExtra("level", "Intermediate");
                startActivity(intent);
            }
        });

        // Clic pour Advanced
        advancedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, course_crcq.class);
                intent.putExtra("level", "Advanced");
                startActivity(intent);
            }
        });

        // Clic pour Expert
        expertCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, course_crcq.class);
                intent.putExtra("level", "Expert");
                startActivity(intent);
            }
        });
        // Trouver l'icône "back_button"
        ImageView backButton = findViewById(R.id.back_button);

        // Ajouter un clic pour revenir en arrière
        backButton.setOnClickListener(v -> {
            // Terminer l'activité actuelle
            finish();
        });
    }
}