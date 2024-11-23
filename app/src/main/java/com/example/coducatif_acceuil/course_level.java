package com.example.coducatif_acceuil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class course_level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_level);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Récupérer les données envoyées
        String level = getIntent().getStringExtra("level");

        // Exemple d'affichage du niveau
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView levelTextView = findViewById(R.id.title);
        levelTextView.setText(level + " Level Selected");

        // Trouver l'icône "back_button"
        ImageView backButton = findViewById(R.id.back_button);

        // Ajouter un clic pour revenir en arrière
        backButton.setOnClickListener(v -> {
            // Terminer l'activité actuelle
            finish();
        });
        // Carte Course
        CardView courseCard = findViewById(R.id.course_card);
        courseCard.setOnClickListener(v -> {
            // Naviguer vers l'activité Course
            Intent intent = new Intent(course_level.this, course_course.class);
            startActivity(intent);
        });

        // Carte Roadmap
        CardView roadmapCard = findViewById(R.id.roadmap_card);
        roadmapCard.setOnClickListener(v -> {
            // Naviguer vers l'activité Roadmap
            Intent intent = new Intent(course_level.this, course_course.class);
            startActivity(intent);
        });
    }
}