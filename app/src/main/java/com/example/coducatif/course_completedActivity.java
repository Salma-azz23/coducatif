package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class course_completedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_completed);

        // Gestion des insets pour un affichage en plein écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ajouter un écouteur pour l'ImageView
        ImageView courseCompletedImage = findViewById(R.id.course_completed_image);
        courseCompletedImage.setOnClickListener(view -> {
            // Lorsque l'utilisateur clique sur l'image, on passe à l'activité CertifActivity
            Intent intent = new Intent(course_completedActivity.this, certifActivity.class);
            startActivity(intent);
        });
    }
}

