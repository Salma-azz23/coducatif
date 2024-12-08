package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.coducatif.R;

import androidx.appcompat.app.AppCompatActivity;

public class quiz5Partie2Activity extends AppCompatActivity {

    private RadioGroup optionsGroup;
    private RadioButton selectedOption;

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
        // Récupère l'ID de l'option sélectionnée
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            // Identifie l'option choisie
            selectedOption = findViewById(selectedId);

            // Réponse correcte
            String correctAnswer = " state et props";

            // Réponse utilisateur
            String userAnswer = selectedOption.getText().toString();

            if (userAnswer.equalsIgnoreCase(correctAnswer)) { // Vérifie la réponse
                // Affiche le message "Succès"
                Toast.makeText(quiz5Partie2Activity.this, "Succès", Toast.LENGTH_SHORT).show();

                // Redirige vers la page suivante
                Intent intent = new Intent(quiz5Partie2Activity.this, quiz4Partie2Activity.class); // Remplace NextActivity par la suivante dans le quiz
                startActivity(intent);
                finish(); // Termine l'activité actuelle
            } else {
                // Affiche le message "Faux"
                Toast.makeText(quiz5Partie2Activity.this, "Faux", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Demande à l'utilisateur de sélectionner une option
            Toast.makeText(quiz5Partie2Activity.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }}

