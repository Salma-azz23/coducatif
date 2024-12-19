package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignIN extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private ImageButton googleLoginButton, appleLoginButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Liaison des vues XML au code Java
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in_button);
        googleLoginButton = findViewById(R.id.google_login);
        appleLoginButton = findViewById(R.id.apple_login);

        // Initialisation de la base de données
        dbHelper = new DBHelper(this);

        // Gestion du clic sur le bouton "Sign In"
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // Utilisation de la méthode correcte "checkUser"
                    boolean validLogin = dbHelper.checkUser(email, password);
                    if (validLogin) {
                        Toast.makeText(SignIN.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                        // Redirection vers une autre activité (HomeActivity par exemple)
                        Intent intent = new Intent(SignIN.this, acceuil.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignIN.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignIN.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Gestion du clic sur le bouton de connexion Google
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder pour la connexion Google
                Toast.makeText(SignIN.this, "Connexion via Google non implémentée", Toast.LENGTH_SHORT).show();
            }
        });

        // Gestion du clic sur le bouton de connexion Apple
        appleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder pour la connexion Apple
                Toast.makeText(SignIN.this, "Connexion via Apple non implémentée", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
