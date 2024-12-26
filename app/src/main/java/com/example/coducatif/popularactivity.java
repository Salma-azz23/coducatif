package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class popularactivity extends AppCompatActivity {
    private EditText searchBar;
    private CardView UICard, interactiveUICard, card1, card2, card3, card4, card5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_activity);

        // Initialisation des cartes
        UICard = findViewById(R.id.course_card);
        interactiveUICard = findViewById(R.id.course1);
        card1 = findViewById(R.id.course2);
        card2 = findViewById(R.id.course3);
        card3 = findViewById(R.id.course4);
        card4 = findViewById(R.id.course5);
        card5 = findViewById(R.id.course6);
        searchBar = findViewById(R.id.search_bar);
        ImageView backButton = findViewById(R.id.back_button);

        // Ajouter des actions de clic sur les cartes
        UICard.setOnClickListener(v -> openYouTubeLink("https://youtu.be/Ew7KG2j8eII?si=A9eIpNTNvCjaD9tu"));
        interactiveUICard.setOnClickListener(v -> openActivity(levels.class));
        card1.setOnClickListener(v -> openYouTubeLink("https://youtu.be/vkIOjTFwsRE?si=eJJxIdEFesXSIUx_"));
        card2.setOnClickListener(v -> openYouTubeLink("https://youtu.be/WymwaIqllvw?si=Gu0Xf4a91yBE7-y7"));
        card3.setOnClickListener(v -> openYouTubeLink("https://youtu.be/ZXeG1SVYzi8?si=mP7MA-37LumZR2PZ"));
        card4.setOnClickListener(v -> openYouTubeLink("https://youtu.be/oIwNwST2nVk?si=emAUoIVy2Ha3jayP"));
        card5.setOnClickListener(v -> openYouTubeLink("https://youtu.be/hcTF2HiHl_A?si=65d_Me7zeif8G360"));

        // Ajouter un TextWatcher pour la barre de recherche
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterResults(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité et retourne à l'activité précédente
            }
        });
    }

    // Ouvrir un lien YouTube
    private void openYouTubeLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    // Ouvrir une autre activité
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(popularactivity.this, activityClass);
        startActivity(intent);
    }

    // Filtrer les cartes en fonction de la recherche
    private void filterResults(String query) {
        query = query.toLowerCase();

        // Vérification dynamique des contenus
        UICard.setVisibility("Java Script".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        interactiveUICard.setVisibility("React Js".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        card1.setVisibility("symfony".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        card2.setVisibility("Laravel".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        card3.setVisibility("nest js".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        card4.setVisibility("SAP ERP".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
        card5.setVisibility("Spring".toLowerCase().contains(query) ? View.VISIBLE : View.GONE);
    }

}
