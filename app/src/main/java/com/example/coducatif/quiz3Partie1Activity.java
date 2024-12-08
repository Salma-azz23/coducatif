package com.example.coducatif;



import com.example.coducatif.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class quiz3Partie1Activity extends AppCompatActivity {

    private RadioGroup optionsGroup;
    private RadioButton selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3_partie1);

        // Initialisation des vues
        optionsGroup = findViewById(R.id.optionsGroup);

        // Ajouter le gestionnaire pour le bouton suivant
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
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
            selectedOption = findViewById(selectedId);

            // Réponse correcte
            String correctAnswer = "DOM virtuel";

            // Réponse utilisateur
            String userAnswer = selectedOption.getText().toString();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) { // Vérifie la réponse
                // Affiche le message "Succès"
                Toast.makeText(quiz3Partie1Activity.this, "Succès", Toast.LENGTH_SHORT).show();

                // Redirige vers la page suivante
                Intent intent = new Intent(quiz3Partie1Activity.this, quiz4Partie1Activity.class);
                startActivity(intent);
                finish(); // Termine l'activité actuelle
            } else {
                // Affiche le message "Faux"
                Toast.makeText(quiz3Partie1Activity.this, "Faux", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Demande à l'utilisateur de sélectionner une option
            Toast.makeText(quiz3Partie1Activity.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }
}