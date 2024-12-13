package com.example.coducatif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "coducatif.db";
    private static final int DATABASE_VERSION = 1;

    // Nom de la table
    private static final String USERS_TABLE = "users";
    private static final String PROFILE_TABLE = "profiles"; // Nouveau tableau pour les profils

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

    // Getter pour USERS_TABLE
    public String getUsersTableName() {
        return USERS_TABLE;
    }

    // Ajouter un utilisateur
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert(USERS_TABLE, null, values);
        db.close();
    }

    // Ajouter un profil
    public void addProfile(int userId, String fullName, String nickName, String dob, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("full_name", fullName);
        values.put("nick_name", nickName);
        values.put("dob", dob);
        values.put("phone", phone);
        db.insert(PROFILE_TABLE, null, values);
        db.close();
    }

    // Vérifier si l'utilisateur existe
    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE + " WHERE email=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
}
