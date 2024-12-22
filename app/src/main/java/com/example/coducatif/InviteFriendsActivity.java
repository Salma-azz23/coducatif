package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InviteFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        // Initialisation de la flèche de retour
        ImageView backArrow = findViewById(R.id.backArrow);

        // Action sur clic de la flèche retour
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité et retourne à l'activité précédente
            }
        });

        // Initialisation de l'icône Google
        ImageView googleIcon = findViewById(R.id.Google);

        // Action sur clic de l'icône Google
        googleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Création d'un Intent pour ouvrir Google
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(intent); // Lancement de l'Intent
            }
        });
    }
}


