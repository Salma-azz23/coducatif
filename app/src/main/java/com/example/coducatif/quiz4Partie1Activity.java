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
public class quiz4Partie1Activity extends AppCompatActivity {

    private RadioGroup optionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4_partie1);

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
        // Récupère l'ID de l'option sélectionnée
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            // Identifie l'option choisie
            RadioButton selectedOption = findViewById(selectedId);

            // Trouver la CardView associée à l'option choisie
            CardView selectedCardView = (CardView) selectedOption.getParent().getParent();

            // Réponse correcte
            String correctAnswer = "Les composants";

            // Réponse utilisateur
            String userAnswer = selectedOption.getText().toString();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                // Bonne réponse : colorier en vert
                selectedCardView.setCardBackgroundColor(Color.parseColor("#4CAF50"));
                Toast.makeText(quiz4Partie1Activity.this, "Succès", Toast.LENGTH_SHORT).show();

                // Redirige vers la page suivante
                Intent intent = new Intent(quiz4Partie1Activity.this, quiz5Partie1Activity.class);
                startActivity(intent);
                finish(); // Termine l'activité actuelle
            } else {
                // Mauvaise réponse : colorier en rouge
                selectedCardView.setCardBackgroundColor(Color.parseColor("#F44336"));
                Toast.makeText(quiz4Partie1Activity.this, "Faux", Toast.LENGTH_SHORT).show();
            }

            // Désactiver toutes les options
            disableAllOptions();
        } else {
            // Demande à l'utilisateur de sélectionner une option
            Toast.makeText(quiz4Partie1Activity.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }

    private void disableAllOptions() {
        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            View child = optionsGroup.getChildAt(i);
            if (child instanceof CardView) {
                child.setEnabled(false);
            }
        }
    }
}
