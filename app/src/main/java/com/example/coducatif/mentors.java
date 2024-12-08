package com.example.coducatif;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mentors extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentors);

        // Initialiser les vues
        ImageView backButton = findViewById(R.id.back_button);
        EditText searchMentors = findViewById(R.id.searchMentors);
        Button tabCourses = findViewById(R.id.tabcourses);
        Button tabMentors = findViewById(R.id.tabmentors);
        ImageView karImage = findViewById(R.id.kar);
        ImageView amanImage = findViewById(R.id.aman);
        ImageView jamaImage = findViewById(R.id.jama);
        ImageView robImage = findViewById(R.id.rob);
        ImageView johnImage = findViewById(R.id.john);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Gérer le clic sur le bouton retour
        backButton.setOnClickListener(view -> {
            Toast.makeText(this, "Back button clicked", Toast.LENGTH_SHORT).show();
            // Ajoutez ici votre logique pour revenir en arrière
        });

        // Gérer le clic sur l'onglet "Courses"
        tabCourses.setOnClickListener(view -> {
            Toast.makeText(this, "Courses tab clicked", Toast.LENGTH_SHORT).show();
            // Ajoutez ici votre logique pour afficher les cours
        });

        // Gérer le clic sur l'onglet "Mentors"
        tabMentors.setOnClickListener(view -> {
            Toast.makeText(this, "Mentors tab clicked", Toast.LENGTH_SHORT).show();
            // Ajoutez ici votre logique pour afficher les mentors
        });

        // Gérer les clics sur les images des mentors
        karImage.setOnClickListener(view -> {
            Toast.makeText(this, "Kar clicked", Toast.LENGTH_SHORT).show();
        });

        amanImage.setOnClickListener(view -> {
            Toast.makeText(this, "Aman clicked", Toast.LENGTH_SHORT).show();
        });

        jamaImage.setOnClickListener(view -> {
            Toast.makeText(this, "Jama clicked", Toast.LENGTH_SHORT).show();
        });

        robImage.setOnClickListener(view -> {
            Toast.makeText(this, "Rob clicked", Toast.LENGTH_SHORT).show();
        });

        johnImage.setOnClickListener(view -> {
            Toast.makeText(this, "John clicked", Toast.LENGTH_SHORT).show();
        });


    }
}

