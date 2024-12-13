package com.example.coducatif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "coducatif.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USERS_TABLE = "users";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)";

    public DBHelper(Context context) {
        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_TABLE);
    }

    public void addUser(String email, String password) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert(USERS_TABLE, null, values);
    }

    public boolean checkUser(String email) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE + " WHERE email=?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String email, String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE + " WHERE email=? AND password=?", new String[]{email, password});
        return cursor.getCount() > 0;
    }
}
