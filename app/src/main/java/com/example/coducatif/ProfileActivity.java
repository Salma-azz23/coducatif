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
    private DBHelper dbHelper;

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

        dbHelper = new DBHelper(this);  // Créer une instance de DBHelper

        // Obtenez l'ID utilisateur (par exemple, avec une méthode statique ici)
        int userId = getUserId(); // Cette méthode doit renvoyer l'ID utilisateur réel

        // Récupérer toutes les informations du profil pour l'utilisateur actuel
        UserProfile userProfile = getUserProfile(userId);

        // Mettre à jour les TextViews avec les données du profil
        tvFullName.setText( userProfile.getFullName());
        tvNickName.setText( userProfile.getNickName());
        tvDob.setText( userProfile.getDob());
        tvPhone.setText( userProfile.getPhone());

        // Listener pour modifier le profil
        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }

    // Méthode pour obtenir l'ID utilisateur actuel (ici c'est juste un exemple)
    private int getUserId() {
        return 1; // Exemple : Retourner l'ID utilisateur (ajustez en fonction de vos données réelles)
    }

    // Méthode pour récupérer toutes les informations du profil
    public UserProfile getUserProfile(int userId) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        UserProfile userProfile = new UserProfile("Student", "N/A", "01/01/2000", "0000000000"); // Valeurs par défaut

        try {
            db = dbHelper.getReadableDatabase(); // Ouvrir la base de données en lecture
            Log.d("getUserProfile", "userId: " + userId);

            cursor = db.rawQuery(
                    "SELECT full_name, nick_name, dob, phone FROM profiles WHERE user_id = ?",
                    new String[]{String.valueOf(userId)}
            );

            if (cursor != null && cursor.moveToFirst()) {
                int fullNameColumnIndex = cursor.getColumnIndex("full_name");
                int nickNameColumnIndex = cursor.getColumnIndex("nick_name");
                int dobColumnIndex = cursor.getColumnIndex("dob");
                int phoneColumnIndex = cursor.getColumnIndex("phone");

                if (fullNameColumnIndex >= 0 && nickNameColumnIndex >= 0 && dobColumnIndex >= 0 && phoneColumnIndex >= 0) {
                    String fullName = cursor.getString(fullNameColumnIndex);
                    String nickName = cursor.getString(nickNameColumnIndex);
                    String dob = cursor.getString(dobColumnIndex);
                    String phone = cursor.getString(phoneColumnIndex);

                    userProfile = new UserProfile(fullName, nickName, dob, phone);
                    Log.d("getUserProfile", "Profile found: " + userProfile);
                } else {
                    Log.e("getUserProfile", "One or more columns not found in cursor");
                }
            } else {
                Log.e("getUserProfile", "Cursor is null or no data found for userId: " + userId);
            }
        } catch (Exception e) {
            Log.e("getUserProfile", "Error fetching user profile", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return userProfile;
    }
}