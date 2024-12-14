package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Intro2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2); // Assurez-vous que le nom du fichier XML est correct

        // Trouver le bouton dans le layout
        Button signInButton = findViewById(R.id.sign_in_button);  // Utiliser l'ID correct

        // Définir un écouteur de clics pour le bouton
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité SignUpActivity lorsque le bouton est cliqué
                Intent intent = new Intent(Intro2Activity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
