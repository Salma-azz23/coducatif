package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class acceuil extends AppCompatActivity {

    private static final String PROFILE_TABLE = "profile"; // Ensure the correct table name
    private DBHelper dbHelper;  // Declare a DBHelper instance
    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil); // Use your XML layout

        // Initialize DBHelper
        dbHelper = new DBHelper(this);  // Create an instance of DBHelper

        // Get the user ID (For now, using a static example method)
        int userId = getUserId(); // This method should return the actual user ID

        // Retrieve the full_name for the current user
        String fullName = getFullName(userId);

        // Update the TextView with the full name
        TextView welcomeTextView = findViewById(R.id.welcome_text_view);
        welcomeTextView.setText("Salut, " + fullName + " 🎮");

        // Initialize UI elements
        ImageView inviteFriendsIcon = findViewById(R.id.invite_friends_icon);
        TextView startButton = findViewById(R.id.start_button);
        TextView seeAllPopularCourses = findViewById(R.id.see_all_popular_courses);
        LinearLayout interactiveUICard = findViewById(R.id.interactive_ui_card);
        LinearLayout IMG = findViewById(R.id.img);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView seeAllMentors = findViewById(R.id.see_all_mentors);
        ImageView menuIcon = findViewById(R.id.menu_icon);

        // Set up the menu click logic
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(acceuil.this, v);
                popupMenu.getMenu().add(0, 1, 0, "Profile");
                popupMenu.getMenu().add(0, 2, 0, "Courses");
                popupMenu.getMenu().add(0, 3, 0, "Community");
                popupMenu.getMenu().add(0, 4, 0, "Mentors");

                // Ajouter et styliser "Logout" en rouge
                MenuItem logoutItem = popupMenu.getMenu().add(0, 5, 0, "Logout");
                SpannableString logoutText = new SpannableString("Logout");
                logoutText.setSpan(new ForegroundColorSpan(Color.RED), 0, logoutText.length(), 0); // Appliquer la couleur rouge
                logoutItem.setTitle(logoutText);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 1:
                                openProfile();
                                return true;
                            case 2:
                                openCourses();
                                return true;
                            case 3:
                                openCommunity();
                                return true;
                            case 4:
                                openMentors();
                                return true;
                            case 5:
                                logout();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show(); // Afficher le menu
            }
        });

        IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String youtubeLink = "https://youtu.be/Ew7KG2j8eII?si=A9eIpNTNvCjaD9tu";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        });
        searchBar = findViewById(R.id.search);

        // Ajouter un TextWatcher pour détecter les changements de texte
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim().toLowerCase();

                // Vérifiez si le texte correspond à l'un des mots-clés
                if (query.equals("react") || query.equals("java script") ||
                        query.equals("java") || query.equals("symfony") || query.equals("laravel") ||
                        query.equals("next js") ||query.equals("next") || query.equals("sap") ||
                        query.equals("erp") || query.equals("spring")) {

                    // Rediriger vers PopularActivity
                    Intent intent = new Intent(acceuil.this, popularactivity.class);
                    intent.putExtra("keyword", query); // Envoyer le mot-clé en extra (facultatif)
                    startActivity(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        // Set up the click listeners for navigation
        inviteFriendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, InviteFriendsActivity.class);
                startActivity(intent);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("StartButton", "Button clicked");
                Intent intent = new Intent(acceuil.this, course_crcq.class);
                startActivity(intent);
            }
        });

        seeAllPopularCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, popularactivity.class);
                startActivity(intent);
            }
        });

        interactiveUICard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, levels.class);
                startActivity(intent);
            }
        });

        seeAllMentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, mentors.class);
                startActivity(intent);
            }
        });
    }

    // Methods to handle menu actions
    private void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openCourses() {
        Intent intent = new Intent(this, popularactivity.class);
        startActivity(intent);
    }

    private void openCommunity() {
        Intent intent = new Intent(this, InviteFriendsActivity.class);
        startActivity(intent);
    }

    private void openMentors() {
        Intent intent = new Intent(this, mentors.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(this, SignIN.class);
        startActivity(intent);
    }

    // Method to get the current user ID (for now it's just a placeholder)
    private int getUserId() {
        return 1; // Example: Return the user ID (adjust based on your actual data)
    }

    public String getFullName(int userId) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String fullName = "Etudiant"; // Valeur par défaut

        try {
            db = dbHelper.getReadableDatabase(); // Ouvrir la base de données
            Log.d("getFullName", "userId: " + userId);

            cursor = db.rawQuery(
                    "SELECT full_name FROM profiles WHERE user_id = ?",
                    new String[]{String.valueOf(userId)}
            );

            if (cursor != null && cursor.moveToFirst()) {
                int fullNameColumnIndex = cursor.getColumnIndex("full_name");
                if (fullNameColumnIndex >= 0) {
                    fullName = cursor.getString(fullNameColumnIndex);
                    Log.d("getFullName", "Full name found: " + fullName);
                } else {
                    Log.e("getFullName", "Column 'full_name' not found in cursor");
                }
            } else {
                Log.e("getFullName", "Cursor is null or no data found for userId: " + userId);
            }
        } catch (Exception e) {
            Log.e("getFullName", "Error fetching full name", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return fullName;
    }


}