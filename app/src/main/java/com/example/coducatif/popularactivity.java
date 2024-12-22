package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class popularactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_activity);

        // Récupérer la carte avec l'ID "course_card"
        CardView UICard = findViewById(R.id.course_card);
        CardView interactiveUICard = findViewById(R.id.course1);
        CardView card1 = findViewById(R.id.course2);
        CardView card2 = findViewById(R.id.course3);
        CardView card3 = findViewById(R.id.course4);
        CardView card4 = findViewById(R.id.course5);
        CardView card5 = findViewById(R.id.course6);

        // Ajouter un OnClickListener pour rediriger vers le lien YouTube
        UICard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/Ew7KG2j8eII?si=A9eIpNTNvCjaD9tu";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        interactiveUICard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popularactivity.this, levels.class); // Remplacez par le nom de votre activité
                startActivity(intent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/vkIOjTFwsRE?si=eJJxIdEFesXSIUx_";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/WymwaIqllvw?si=Gu0Xf4a91yBE7-y7";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/ZXeG1SVYzi8?si=mP7MA-37LumZR2PZ";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/oIwNwST2nVk?si=emAUoIVy2Ha3jayP";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/hcTF2HiHl_A?si=65d_Me7zeif8G360";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });

    }
}
