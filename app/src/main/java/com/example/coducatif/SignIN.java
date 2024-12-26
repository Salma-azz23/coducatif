package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignIN extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private LinearLayout googleLoginButton;
    private DBHelper dbHelper;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configuration de Google Sign-In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        // Liaison des vues XML au code Java
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in_button);
        googleLoginButton = findViewById(R.id.login);

        // Initialisation de la base de données
        dbHelper = new DBHelper(this);

        // Gestion du clic sur le bouton "Sign In"
        signInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                boolean validLogin = dbHelper.checkUser(email, password);
                if (validLogin) {
                    Toast.makeText(SignIN.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                    navigateToAcceuil();
                } else {
                    Toast.makeText(SignIN.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignIN.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });

        // Gestion du clic sur le bouton Google Sign-In
        googleLoginButton.setOnClickListener(v -> signIn());
    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToAcceuil();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToAcceuil() {
        finish();
        Intent intent = new Intent(SignIN.this, acceuil.class);
        startActivity(intent);
    }
}
