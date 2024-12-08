package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import


public class Quiz1Partie1Activity extends AppCompatActivity {

    private RadioGroup optionsGroup;
    private RadioButton selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1partie1);

        // Initialisation des vues
        optionsGroup = findViewById(R.id.optionsGroup);

        // Ajouter le gestionnaire pour le bouton suivant
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        // Récupère l'ID de l'option sélectionnée
        int selectedId = optionsGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            // Identifie l'option choisie
            selectedOption = findViewById(selectedId);

            // Vérifie si l'utilisateur a choisi la bonne réponse
            String correctAnswer = "Bibliothèque Javascript";
            String userAnswer = selectedOption.getText().toString();

            if (userAnswer.equals(correctAnswer)) {
                // Affiche le message "Succès"
                Toast.makeText(Quiz1Partie1Activity.this, "Succès", Toast.LENGTH_SHORT).show();

                // Redirige vers la page suivante (par exemple, Quiz2Activity)
                Intent intent = new Intent(Quiz1Partie1Activity.this, quiz3Partie1Activity.class);
                startActivity(intent);
                finish(); // Termine l'activité actuelle
            } else {
                // Affiche le message "Faux" et reste sur la même page
                Toast.makeText(Quiz1Partie1Activity.this, "Faux", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Demande à l'utilisateur de sélectionner une option
            Toast.makeText(Quiz1Partie1Activity.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
        }
    }
}
