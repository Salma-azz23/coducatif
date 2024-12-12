package com.example.coducatif;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class Popularactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_activity); // Assurez-vous que votre fichier XML a le bon nom.

        // Récupérez l'ImageView représentant la carte React JS
        ImageView reactCard = findViewById(R.id.course1);

        // Définir l'écouteur de clics sur la carte
        reactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité LevelsActivity
                Intent intent = new Intent(Popularactivity.this, levels.class);
                startActivity(intent);
            }
        });
    }
}
