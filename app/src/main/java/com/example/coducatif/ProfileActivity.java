package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFullName, tvNickName, tvDob, tvPhone;
    private Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialisation des vues
        tvFullName = findViewById(R.id.tvFullName);
        tvNickName = findViewById(R.id.tvNickName);
        tvDob = findViewById(R.id.tvDob);
        tvPhone = findViewById(R.id.tvPhone);
        btnEditProfile = findViewById(R.id.btnEditProfile);

        // Récupérer l'ID utilisateur depuis l'intent ou une session utilisateur
        int userId = getIntent().getIntExtra("userId", -1);

        if (userId != -1) {
            // Charger les informations du profil
            loadProfileData(userId);
        } else {
            Log.e("ProfileActivity", "Invalid userId");
        }

        // Listener pour modifier le profil
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }

    private void loadProfileData(int userId) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase(); // Ouvrir la base de données
            Log.d("ProfileActivity", "userId: " + userId);

            cursor = db.rawQuery(
                    "SELECT full_name, nick_name, dob, phone FROM profiles WHERE user_id = ?",
                    new String[]{String.valueOf(userId)}
            );

            if (cursor != null && cursor.moveToFirst()) {
                int fullNameIndex = cursor.getColumnIndex("full_name");
                int nickNameIndex = cursor.getColumnIndex("nick_name");
                int dobIndex = cursor.getColumnIndex("dob");
                int phoneIndex = cursor.getColumnIndex("phone");

                // Vérifier si les indices des colonnes sont valides
                if (fullNameIndex >= 0 && nickNameIndex >= 0 && dobIndex >= 0 && phoneIndex >= 0) {
                    String fullName = cursor.getString(fullNameIndex);
                    String nickName = cursor.getString(nickNameIndex);
                    String dob = cursor.getString(dobIndex);
                    String phone = cursor.getString(phoneIndex);

                    // Affichage des données dans les TextViews
                    tvFullName.setText(fullName);
                    tvNickName.setText(nickName);
                    tvDob.setText(dob);
                    tvPhone.setText(phone);

                    Log.d("ProfileActivity", "Profile data loaded: " + fullName + ", " + nickName);
                } else {
                    Log.e("ProfileActivity", "Column index not valid");
                }
            } else {
                Log.e("ProfileActivity", "No profile data found for userId: " + userId);
            }
        } catch (Exception e) {
            Log.e("ProfileActivity", "Error loading profile data", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

}
