package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private TextView forgotPasswordText;
    private ImageButton googleLoginButton, appleLoginButton;
    private CheckBox rememberMeCheckbox;

    private GoogleSignInClient googleSignInClient;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.sign_in_button);
        forgotPasswordText = findViewById(R.id.forgot_password);
        googleLoginButton = findViewById(R.id.google_login);
        appleLoginButton = findViewById(R.id.apple_login);
        rememberMeCheckbox = findViewById(R.id.remember_me);

        // Initialisation de Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Gestion du bouton Sign In
        signInButton.setOnClickListener(view -> handleSignIn());

        // Gestion du texte Forgot Password
        forgotPasswordText.setOnClickListener(view -> {
            // Rediriger vers une autre activité pour réinitialiser le mot de passe
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Gestion du bouton Google Sign-In
        googleLoginButton.setOnClickListener(view -> handleGoogleSignIn());

        // Gestion du bouton Apple Sign-In
        appleLoginButton.setOnClickListener(view -> {
            Toast.makeText(this, "Apple Sign-In en cours de développement", Toast.LENGTH_SHORT).show();
        });
    }

    private void handleSignIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Vérification des champs
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("L'email est requis");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Veuillez entrer un email valide");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Le mot de passe est requis");
            return;
        }

        // Simuler une connexion (remplacer par une logique réelle si nécessaire)
        if (email.equals("test@example.com") && password.equals("password123")) {
            Toast.makeText(this, "Connexion réussie!", Toast.LENGTH_SHORT).show();
            // Rediriger vers une activité principale
            Intent intent = new Intent(LoginActivity.this, .class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleGoogleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(Exception.class);
                if (account != null) {
                    Toast.makeText(this, "Connexion Google réussie: " + account.getEmail(), Toast.LENGTH_SHORT).show();
                    // Rediriger vers l'activité principale
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Échec de la connexion Google", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
