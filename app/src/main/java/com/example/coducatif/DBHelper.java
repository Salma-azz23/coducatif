package com.example.coducatif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "coducatif.db";
    private static final int DATABASE_VERSION = 1;

    // Nom des tables
    private static final String USERS_TABLE = "users";
    private static final String PROFILE_TABLE = "profiles";

    // SQL pour créer les tables
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email TEXT, " +
                    "password TEXT)";

    private static final String CREATE_PROFILE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + PROFILE_TABLE + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "full_name TEXT, " +
                    "nick_name TEXT, " +
                    "dob TEXT, " +
                    "phone TEXT, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id))";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE);
        onCreate(db);
    }

    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert(USERS_TABLE, null, values);
        db.close();
    }

    // Méthode pour vérifier uniquement l'email
    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email = ?",
                new String[]{email}
        );
        boolean exists = cursor.getCount() > 0; // Vérifie si un enregistrement existe
        cursor.close();
        db.close();
        return exists;
    }

    // Méthode pour vérifier l'email et le mot de passe
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email = ? AND password = ?",
                new String[]{email, password}
        );
        boolean isValid = cursor.getCount() > 0; // Vérifie si un enregistrement existe
        cursor.close();
        db.close();
        return isValid;
    }




    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1; // Valeur par défaut si l'utilisateur n'est pas trouvé
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0); // Récupération de l'ID
        }
        cursor.close();
        db.close();
        return userId;
    }
    public boolean addProfile(int userId, String fullName, String nickName, String dob, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("full_name", fullName);
        values.put("nick_name", nickName);
        values.put("dob", dob);
        values.put("phone", phone);

        long result = db.insert("profiles", null, values);
        db.close();
        return result != -1; // Retourne true si l'insertion a réussi, false sinon
    }

    public void savePin(int userId, String pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pin", pin);
        int rowsAffected = db.update(USERS_TABLE, values, "id = ?", new String[]{String.valueOf(userId)});
        if (rowsAffected == 0) {
            values.put("user_id", userId);
            db.insert("pins", null, values);
        }
        db.close();
    }

    public static class certifActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_certif);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}
