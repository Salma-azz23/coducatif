package com.example.coducatif;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class FillProfileActivity extends AppCompatActivity {

    private EditText fullNameEditText, nickNameEditText, dobEditText, emailEditText, phoneEditText;
    private Button continueButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profile);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        nickNameEditText = findViewById(R.id.nickNameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        continueButton = findViewById(R.id.continueButton);

        dbHelper = new DBHelper(this);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs des champs
                String fullName = fullNameEditText.getText().toString();
                String nickName = nickNameEditText.getText().toString();
                String dob = dobEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                // Vérification que tous les champs sont remplis
                if (fullName.isEmpty() || nickName.isEmpty() || dob.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(FillProfileActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Vérification si l'utilisateur existe déjà dans la base de données
                    if (dbHelper.checkUser(email)) {
                        Toast.makeText(FillProfileActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    } else {
                        // Ajout de l'utilisateur avec un mot de passe par défaut
                        dbHelper.addUser(email, "defaultPassword");
                        int userId = dbHelper.getUserIdByEmail(email);  // Appel de la méthode dans DBHelper

                        // Si l'utilisateur a bien été ajouté, on crée son profil
                        if (userId != -1) {
                            dbHelper.addProfile(userId, fullName, nickName, dob, phone);
                            Toast.makeText(FillProfileActivity.this, "Profil créé avec succès", Toast.LENGTH_SHORT).show();

                            // Création de l'intent pour naviguer vers PinActivity
                            Intent intent = new Intent(FillProfileActivity.this, PinActivity.class);
                            startActivity(intent); // Lancement de l'activité PinActivity
                            finish(); // Facultatif: vous pouvez fermer cette activité si vous le souhaitez
                        } else {
                            Toast.makeText(FillProfileActivity.this, "Erreur lors de la création du profil", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}
