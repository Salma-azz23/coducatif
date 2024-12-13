package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Intro2Activity extends AppCompatActivity {

    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2); // Assurez-vous que le nom du layout XML est correct

        // Initialiser le bouton
        getStartedButton = findViewById(R.id.sign_in_button);

        // Définir un OnClickListener pour le bouton "Get Started"
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer SignUpActivity lorsqu'on clique sur le bouton
                Intent intent = new Intent(Intro2Activity.this, SignUpActivity.class);
                startActivity(intent);
                finish(); // Terminer l'activité actuelle pour ne pas permettre de revenir à la page précédente
            }
        });
    }
}
