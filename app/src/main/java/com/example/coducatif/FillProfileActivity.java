package com.example.coducatif;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class FillProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText fullNameEditText, nickNameEditText, dobEditText, emailEditText, phoneEditText;
    private ImageView profileImageView;
    private Button continueButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_profile);

        // Initialisation des vues
        fullNameEditText = findViewById(R.id.fullNameEditText);
        nickNameEditText = findViewById(R.id.nickNameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        profileImageView = findViewById(R.id.profileImage);
        continueButton = findViewById(R.id.continueButton);
        ImageView backArrow = findViewById(R.id.backButton);


        dbHelper = new DBHelper(this);

        // Chargement de l'image sauvegardée (si disponible)
        loadSavedProfileImage();

        // Listener pour afficher le calendrier lors du clic sur dobEditText
        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FillProfileActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Listener pour ouvrir la galerie au clic sur l'image de profil
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Listener pour le bouton continuer
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleContinueButtonClick();
            }
        });
    }

    private void showDatePickerDialog() {
        // Récupération de la date actuelle pour initialiser le calendrier
        final Calendar calendar = Calendar.getInstance();

        // Création du DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                FillProfileActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Formatage de la date en JJ/MM/AAAA
                        String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        dobEditText.setText(formattedDate);
                    }
                },
                calendar.get(Calendar.YEAR), // Année actuelle
                calendar.get(Calendar.MONTH), // Mois actuel
                calendar.get(Calendar.DAY_OF_MONTH) // Jour actuel
        );

        // Affichage du DatePickerDialog
        datePickerDialog.show();
    }

    private void openGallery() {
        // Utilisation de l'API Storage Access Framework (SAF)
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData(); // URI de l'image sélectionnée
            if (selectedImageUri != null) {
                // Afficher l'image dans l'ImageView
                profileImageView.setImageURI(selectedImageUri);

                // Sauvegarder l'URI pour un usage ultérieur
                saveProfileImageUri(selectedImageUri);
            }
        }
    }

    private void saveProfileImageUri(Uri imageUri) {
        // Sauvegarde de l'URI dans SharedPreferences
        SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("profileImageUri", imageUri.toString());
        editor.apply();
    }

    private void loadSavedProfileImage() {
        // Chargement de l'URI sauvegardé
        SharedPreferences preferences = getSharedPreferences("profile", MODE_PRIVATE);
        String savedImageUri = preferences.getString("profileImageUri", null);
        if (savedImageUri != null) {
            profileImageView.setImageURI(Uri.parse(savedImageUri));
        }
    }

    private void handleContinueButtonClick() {
        // Récupération des données entrées par l'utilisateur
        String fullName = fullNameEditText.getText().toString().trim();
        String nickName = nickNameEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        // Validation des champs
        if (fullName.isEmpty() || nickName.isEmpty() || dob.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(FillProfileActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification du numéro de téléphone (10 chiffres)
        if (!phone.matches("\\d{10}")) {
            Toast.makeText(FillProfileActivity.this, "Le numéro de téléphone doit contenir exactement 10 chiffres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification du format de l'email
        if (!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            Toast.makeText(FillProfileActivity.this, "Le format de l'email est incorrect", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification du format de la date de naissance
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            Toast.makeText(FillProfileActivity.this, "La date de naissance doit être au format JJ/MM/AAAA", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification si l'utilisateur existe déjà
        // Vérification si l'utilisateur existe déjà
        if (dbHelper.checkUser(email)) {
            // Enregistrement de l'utilisateur et de son profil
            dbHelper.addUser(email, "defaultPassword"); // Ajouter avec un mot de passe par défaut
            int userId = dbHelper.getUserIdByEmail(email);
            if (userId != -1) {
                dbHelper.addProfile(userId, fullName, nickName, dob, phone);
                Toast.makeText(FillProfileActivity.this, "Profil créé avec succès", Toast.LENGTH_SHORT).show();

                // Redirection vers l'activité suivante
                Intent intent = new Intent(FillProfileActivity.this, PinActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(FillProfileActivity.this, "Erreur lors de la création du profil", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(FillProfileActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
        }

    }
}