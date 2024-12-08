package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.coducatif.R;


public class course_crcq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_crcq);
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
        // Initialisation des cartes
        LinearLayout courseCard = findViewById(R.id.course_card);
        LinearLayout roadmapCard = findViewById(R.id.roadmap_card);
        LinearLayout quizCard = findViewById(R.id.quiz_card);
        LinearLayout communityCard = findViewById(R.id.community_card);

        // Redirection vers "activity_course_content"
        courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_crcq.this, course_content.class); // Classe CourseContent
                startActivity(intent);
            }
        });

        // Redirection vers "activity_roadmap"
        roadmapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_crcq.this, roadmap.class); // Classe Roadmap
                startActivity(intent);
            }
        });

        // Redirection vers "Quiz1Partie1Activity"
        quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_crcq.this, Quiz1Partie1Activity.class); // Classe Quiz1Partie1Activity
                startActivity(intent);
            }
        });

        // Redirection vers "InviteFriendsActivity"
        communityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_crcq.this, InviteFriendsActivity.class); // Classe InviteFriendsActivity
                startActivity(intent);
            }
        });
    }
}