package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); // Assurez-vous que le nom du fichier XML est correct

        // Trouver le bouton dans le layout
        ImageButton nextButton = findViewById(R.id.nextButton);

        // Définir un écouteur de clics pour le bouton
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité Intro1Activity
                Intent intent = new Intent(IntroActivity.this, Intro1Activity.class);
                startActivity(intent);
            }
        });
    }
}
