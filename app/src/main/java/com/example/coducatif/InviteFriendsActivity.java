package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

        // Initialisation de l'icône WhatsApp
        ImageView whatsappIcon = findViewById(R.id.whatsapp);

        // Action sur clic de l'icône WhatsApp
        whatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Message à envoyer via WhatsApp
                String message = "Hey! Join me to play this fun game: [Your Game Link]";

                // Formater l'URL pour WhatsApp (encodage du message)
                String encodedMessage = Uri.encode(message);

                // Créer l'URL pour ouvrir WhatsApp avec le message
                String url = "https://api.whatsapp.com/send?text=" + encodedMessage;

                // Créer l'Intent pour ouvrir WhatsApp avec le message
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(url)); // Utilisation de l'URL pour envoyer le message

                // Vérifier si WhatsApp est installé
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent); // Lancer l'intent pour envoyer le message
                } else {
                    // Si WhatsApp n'est pas installé, afficher un message
                    Toast.makeText(InviteFriendsActivity.this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
                    // Optionnel: Ouvrir le Play Store pour installer WhatsApp
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp"));
                    startActivity(playStoreIntent);
                }
            }
        });
    }
}
