package com.example.coducatif;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

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
                String fullName = fullNameEditText.getText().toString();
                String nickName = nickNameEditText.getText().toString();
                String dob = dobEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                if (fullName.isEmpty() || nickName.isEmpty() || dob.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(FillProfileActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkUser(email)) {
                        Toast.makeText(FillProfileActivity.this, "Cet utilisateur existe déjà", Toast.LENGTH_SHORT).show();
                    } else {
                        dbHelper.addUser(email, "defaultPassword");
                        int userId = getUserIdByEmail(email);

                        if (userId != -1) {
                            dbHelper.addProfile(userId, fullName, nickName, dob, phone);
                            Toast.makeText(FillProfileActivity.this, "Profil créé avec succès", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(FillProfileActivity.this, "Erreur lors de la création du profil", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private int getUserIdByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM " + dbHelper.getUsersTableName() + " WHERE email=?", new String[]{email});
        int userId = -1;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            }
            cursor.close();
        }
        db.close();
        return userId;
    }
}
