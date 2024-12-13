package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signUpButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sign_up_button);

        dbHelper = new DBHelper(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Vérifier si les champs ne sont pas vides
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier si l'email a un format valide
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignUpActivity.this, "Email invalide", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier si le mot de passe a une longueur suffisante
                if (password.length() < 6) {
                    Toast.makeText(SignUpActivity.this, "Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérifier si l'utilisateur existe déjà
                boolean userExists = dbHelper.checkUser(email);
                if (!userExists) {
                    dbHelper.addUser(email, password);
                    Toast.makeText(SignUpActivity.this, "Utilisateur inscrit avec succès", Toast.LENGTH_SHORT).show();

                    // Passer à MainActivity
                    Intent intent = new Intent(SignUpActivity.this, FillProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
