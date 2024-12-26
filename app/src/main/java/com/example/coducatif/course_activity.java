package com.example.coducatif;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class course_activity extends AppCompatActivity {
    private EditText searchBar;
    private CardView card1, card2, card3, card4, card5, card6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_activity); // Référence correcte au fichier XML

        // Initialiser les vues
        searchBar = findViewById(R.id.search_bar);

        card1 = findViewById(R.id.course1);
        card2 = findViewById(R.id.course2);
        card3 = findViewById(R.id.course3);
        card4 = findViewById(R.id.course4);
        card5 = findViewById(R.id.course5);
        card6 = findViewById(R.id.course6);

        // Vérifier si les vues sont bien initialisées
        if (searchBar == null) {
            Log.e("Debug", "Search bar not found in the layout!");
        } else {
            Log.d("Debug", "Search bar initialized!");
        }

        // Ajouter le TextWatcher
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String searchText = charSequence.toString().toLowerCase();
                Log.d("Debug", "Search text: " + searchText);

                // Filtrer les catégories
                filterCategories(searchText, "react js", card1);
                filterCategories(searchText, "java script", card2);
                filterCategories(searchText, "symfony", card3);
                filterCategories(searchText, "laravel", card4);
                filterCategories(searchText, "next js", card5);
                filterCategories(searchText, "sap erp", card6);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void filterCategories(String searchText, String categoryName, CardView cardView) {
        if (categoryName.toLowerCase().contains(searchText)) {
            cardView.setVisibility(View.VISIBLE);
            Log.d("Debug", "Showing card for: " + categoryName);
        } else {
            cardView.setVisibility(View.GONE);
            Log.d("Debug", "Hiding card for: " + categoryName);
        }
    }
}
