package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginInActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in_button);

        dbHelper = new DBHelper(this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (dbHelper.checkUser(email, password)) {
                    // Connexion réussie, passer à l'écran suivant
                    Intent intent = new Intent(LoginInActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Afficher un message d'erreur
                    Toast.makeText(LoginInActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


