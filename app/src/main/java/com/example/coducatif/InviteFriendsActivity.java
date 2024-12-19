package com.example.coducatif;

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
    }
}


