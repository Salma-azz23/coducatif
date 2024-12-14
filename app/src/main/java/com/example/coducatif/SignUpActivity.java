package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signUpButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Liaison des éléments XML au code Java
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sign_up_button);

        dbHelper = new DBHelper(this);

        // Gestion du clic sur le bouton "Sign Up"
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    boolean userExists = dbHelper.checkUser(email);
                    if (!userExists) {
                        dbHelper.addUser(email, password);
                        Toast.makeText(SignUpActivity.this, "Utilisateur inscrit avec succès", Toast.LENGTH_SHORT).show();

                        // Redirection vers la page de connexion
                        redirectToSignIn();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Méthode pour rediriger vers la page SignIN après une inscription réussie
    public void redirectToSignIn() {
        Intent intent = new Intent(SignUpActivity.this, SignIN.class);
        startActivity(intent);
        finish(); // Terminer l'activité actuelle
    }

    // Méthode associée au clic sur le TextView "LOG IN"
    public void redirectToSignIn(View view) {
        // Vérifie l'ID de la vue cliquée
        if (view.getId() == R.id.sign_up_prompt) {
            // Exécute l'action
            Intent intent = new Intent(SignUpActivity.this, SignIN.class);
            startActivity(intent);
            finish();
        }
    }

}
