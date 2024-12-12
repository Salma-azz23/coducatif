package com.example.coducatif;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.coducatif_acceuil.R;


public class quiz5Partie2Activity extends AppCompatActivity {

    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5_partie2);

        // Initialisation des vues
        optionsGroup = findViewById(R.id.optionsGroup);

        // Ajouter le gestionnaire pour le bouton suivant
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(); // Appelle la méthode pour vérifier la réponse
            }
        });
    }

    private void checkAnswer() {
        // Réponse correcte
        String correctAnswer = "state et props";

        // Récupère l'ID de l'option sélectionnée
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedOption = findViewById(selectedId);

            // Récupérer le parent `CardView` de l'option sélectionnée
            CardView selectedCardView = (CardView) selectedOption.getParent().getParent();

            // Réponse utilisateur
            String userAnswer = selectedOption.getText().toString();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                // Si la réponse est correcte, mettre la carte en vert
                selectedCardView.setCardBackgroundColor(Color.parseColor("#4CAF50"));
                Toast.makeText(quiz5Partie2Activity.this, "Succès", Toast.LENGTH_SHORT).show();

                // Redirige vers la page suivante
                Intent intent = new Intent(quiz5Partie2Activity.this, course_completedActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité actuelle
            } else {
                // Si la réponse est incorrecte, mettre la carte en rouge
                selectedCardView.setCardBackgroundColor(Color.parseColor("#F44336"));
                Toast.makeText(quiz5Partie2Activity.this, "Faux", Toast.LENGTH_SHORT).show();
            }

            // Désactiver toutes les options après validation
            for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                View child = optionsGroup.getChildAt(i);
                if (child instanceof CardView) {
                    child.setEnabled(false);
                }
            }
        } else {
            // Aucun élément sélectionné
            Toast.makeText(quiz5Partie2Activity.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }
}

