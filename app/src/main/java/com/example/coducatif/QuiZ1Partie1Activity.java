package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class QuiZ1Partie1Activity extends AppCompatActivity {

    // Variables pour le quiz
    private TextView questionCounter, questionText;
    private RadioButton option1, option2, option3, option4;
    private RadioGroup optionsGroup1, optionsGroup2, optionsGroup3, optionsGroup4;
    private MaterialButton btnNext;

    private List<Question> questionList; // Liste des questions
    private int currentQuestionIndex = 0; // Index de la question actuelle
    private int score = 0; // Score du joueur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1partie1);

        // Initialisation des vues
        questionCounter = findViewById(R.id.questionCounter);
        questionText = findViewById(R.id.questionText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        optionsGroup1 = findViewById(R.id.optionsGroup1);
        optionsGroup2 = findViewById(R.id.optionsGroup2);
        optionsGroup3 = findViewById(R.id.optionsGroup3);
        optionsGroup4 = findViewById(R.id.optionsGroup4);
        btnNext = findViewById(R.id.btn_next);

        // Chargement des questions
        questionList = loadQuestions();

        // Affichage de la première question
        showQuestion();

        // Gestion du bouton Suivant
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifie si une option est sélectionnée
                if (getSelectedOptionId() == -1) {
                    Toast.makeText(QuiZ1Partie1Activity.this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifie la réponse sélectionnée
                checkAnswer();

                // Passe à la question suivante
                currentQuestionIndex++;

                if (currentQuestionIndex < questionList.size()) {
                    showQuestion(); // Affiche la prochaine question
                } else {
                    // Quiz terminé
                    Intent resultIntent = new Intent(QuiZ1Partie1Activity.this, ScoreActivity.class);
                    resultIntent.putExtra("score", score);
                    resultIntent.putExtra("totalQuestions", questionList.size());
                    startActivity(resultIntent);
                    finish(); // Ferme l'activité
                }
            }
        });
    }

    // Méthode pour charger les questions
    // Méthode pour charger les questions
    private List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "Les Props dans React peuvent ________",
                "Être modifié à l’intérieur du composant",
                "Ne pas être modifié dans le composant",
                "Être modifié dans d’autre composant",
                "Aucune de ces réponses n’est vraie.",
                2 // Index de la bonne réponse (1-based)
        ));
        questions.add(new Question(
                "Quel est l’objet principal utilisé dans ReactJS pour créer des composants ?",
                "Modules",
                "Classes",
                "Props",
                "None of the above",
                2
        ));
        questions.add(new Question(
                "Quel est l’élément principal de l’interface utilisateur dans React ?",
                "State",
                "Props",
                "JSX",
                "DOM",
                3
        ));
        questions.add(new Question(
                "Qu’est-ce que JSX ?",
                "Un type de fichier utilisé dans React",
                "Une extension de JavaScript permettant de créer des éléments HTML",
                "Un format de données utilisé dans React",
                "Un compilateur",
                2
        ));
        questions.add(new Question(
                "Dans React, quel est l'objet qui permet de gérer l’état d’un composant ?",
                "state",
                "setState",
                "props",
                "useState",
                1
        ));
        questions.add(new Question(
                "Dans React, la méthode pour mettre à jour l’état est appelée ________",
                "changeState",
                "setState",
                "updateState",
                "useState",
                2
        ));
        questions.add(new Question(
                "Quelle est la principale différence entre props et state dans React ?",
                "Props sont utilisés pour modifier l’état d’un composant, alors que state est utilisé pour modifier les props",
                "Props sont immuables, tandis que state est mutable",
                "Les props sont utilisés pour partager des données entre les composants enfants et parents, tandis que state gère l’état interne d’un composant",
                "Il n’y a pas de différence",
                3
        ));
        questions.add(new Question(
                "Que fait la méthode `render()` dans un composant React ?",
                "Elle crée des éléments HTML",
                "Elle retourne une représentation JSX du composant",
                "Elle met à jour l’état",
                "Elle démarre l’application React",
                2
        ));
        questions.add(new Question(
                "Que signifie le terme \"Virtual DOM\" dans React ?",
                "Il est utilisé pour mettre à jour l’interface utilisateur en temps réel",
                "C’est une copie légère du DOM réel qui permet d’optimiser les performances",
                "Il est utilisé pour stocker des données côté serveur",
                "Il est une version simplifiée du DOM HTML",
                2
        ));
        questions.add(new Question(
                "Quels sont les deux types principaux de composants dans React ?",
                "Functional et Class",
                "Class et Hooks",
                "Stateful et Stateless",
                "Components et Elements",
                1
        ));
        return questions;
    }


    // Méthode pour afficher une question
    private void showQuestion() {
        // Récupère la question actuelle
        Question currentQuestion = questionList.get(currentQuestionIndex);

        // Met à jour les vues
        questionCounter.setText((currentQuestionIndex + 1) + "/" + questionList.size());
        questionText.setText(currentQuestion.getQuestion());
        option1.setText(currentQuestion.getOption1());
        option2.setText(currentQuestion.getOption2());
        option3.setText(currentQuestion.getOption3());
        option4.setText(currentQuestion.getOption4());

        // Réinitialise la sélection des options
        optionsGroup1.clearCheck();
        optionsGroup2.clearCheck();
        optionsGroup3.clearCheck();
        optionsGroup4.clearCheck();
    }

    // Méthode pour vérifier la réponse
    private void checkAnswer() {
        // Récupère l'option sélectionnée
        int selectedOptionIndex = getSelectedOptionId();

        // Vérifie si la réponse est correcte
        if (selectedOptionIndex == questionList.get(currentQuestionIndex).getCorrectAnswer()) {
            score++; // Incrémente le score si la réponse est correcte
        }
    }

    // Méthode pour obtenir l'option sélectionnée
    private int getSelectedOptionId() {
        // Vérifie dans chaque RadioGroup lequel est sélectionné
        if (optionsGroup1.getCheckedRadioButtonId() != -1) {
            return 1;
        } else if (optionsGroup2.getCheckedRadioButtonId() != -1) {
            return 2;
        } else if (optionsGroup3.getCheckedRadioButtonId() != -1) {
            return 3;
        } else if (optionsGroup4.getCheckedRadioButtonId() != -1) {
            return 4;
        } else {
            return -1; // Aucune option sélectionnée
        }
    }

    // Classe interne pour représenter une question
    static class Question {
        private String question;
        private String option1, option2, option3, option4;
        private int correctAnswer;

        public Question(String question, String option1, String option2, String option3, String option4, int correctAnswer) {
            this.question = question;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public String getOption1() {
            return option1;
        }

        public String getOption2() {
            return option2;
        }

        public String getOption3() {
            return option3;
        }

        public String getOption4() {
            return option4;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
