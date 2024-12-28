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

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

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

        backButton.setOnClickListener(v -> finish()); // Ferme l'activité

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(this, acceuil.class));
                    return true;
                case R.id.nav_help:
                    startActivity(new Intent(this, chat_community.class));
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    return true;
                default:
                    return false;
            }
        });
    }

    private void openYouTubeLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(PopularActivity.this, activityClass);
        startActivity(intent);
    }

    private void filterResults(String query) {
        query = query.toLowerCase();

        UICard.setVisibility("javascript".contains(query) ? View.VISIBLE : View.GONE);
        interactiveUICard.setVisibility("reactjs".contains(query) ? View.VISIBLE : View.GONE);
        card1.setVisibility("symfony".contains(query) ? View.VISIBLE : View.GONE);
        card2.setVisibility("laravel".contains(query) ? View.VISIBLE : View.GONE);
        card3.setVisibility("nestjs".contains(query) ? View.VISIBLE : View.GONE);
        card4.setVisibility("saperp".contains(query) ? View.VISIBLE : View.GONE);
        card5.setVisibility("spring".contains(query) ? View.VISIBLE : View.GONE);
    }
}
