package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class roadmap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap);

        // Lier l'image cliquable
        ImageView roadmapIcon = findViewById(R.id.roadmap);

        // Ajouter un écouteur pour gérer le clic
        roadmapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers ShowRoadmapActivity
                Intent intent = new Intent(roadmap.this, ShowRoadmapActivity.class);
                startActivity(intent);
            }
        });
    }
}
