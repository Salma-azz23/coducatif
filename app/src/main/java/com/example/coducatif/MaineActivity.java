package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MaineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maine); // Assurez-vous que le fichier XML s'appelle `activity_main.xml`

        // Handler pour exécuter une action après 5 secondes (5000 millisecondes)
        new Handler().postDelayed(() -> {
            // Rediriger vers l'activité IntroActivity
            Intent intent = new Intent(MaineActivity.this, IntroActivity.class);
            startActivity(intent);
            finish(); // Termine l'activité actuelle pour ne pas revenir en arrière
        }, 5000); // 5 secondes
    }
}
