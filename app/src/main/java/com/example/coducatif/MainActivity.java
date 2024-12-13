package com.example.coducatif;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView textViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de DBHelper et TextView
        dbHelper = new DBHelper(this);
        textViewUsers = findViewById(R.id.textViewUsers);

        // Afficher les emails des utilisateurs dans la TextView
        String usersList = getUsersList();
        textViewUsers.setText(usersList);
    }

    private String getUsersList() {
        StringBuilder users = new StringBuilder();
        // Ouvrir la base de données en mode lecture
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Exécuter la requête SQL pour obtenir tous les emails
        Cursor cursor = db.rawQuery("SELECT email FROM " + dbHelper.getUsersTableName(), null);

        // Vérifier que la colonne "email" existe
        int emailColumnIndex = cursor.getColumnIndex("email");

        if (emailColumnIndex == -1) {
            // La colonne "email" n'existe pas
            users.append("La colonne 'email' n'existe pas dans la table.");
        } else {
            // Parcourir les résultats et ajouter les emails à la liste
            if (cursor.moveToFirst()) {
                do {
                    String email = cursor.getString(emailColumnIndex);
                    users.append(email).append("\n");  // Ajouter l'email à la liste avec un saut de ligne
                } while (cursor.moveToNext());
            } else {
                users.append("Aucun utilisateur trouvé.");
            }
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        return users.toString();  // Retourner la liste des utilisateurs sous forme de texte
    }
}
