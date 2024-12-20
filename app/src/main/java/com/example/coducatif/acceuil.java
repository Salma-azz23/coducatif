package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coducatif.R;

public class acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        // Initialisation des vues
        ImageView inviteFriendsIcon = findViewById(R.id.invite_friends_icon);
        TextView startButton = findViewById(R.id.start_button);
        TextView seeAllPopularCourses = findViewById(R.id.see_all_popular_courses);
        LinearLayout interactiveUICard = findViewById(R.id.interactive_ui_card);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView seeAllMentors = findViewById(R.id.see_all_mentors);
        ImageView menuIcon = findViewById(R.id.menu_icon);

        // Logique du menu
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(acceuil.this, v);
                popupMenu.getMenu().add(0, 1, 0, "Profile");
                popupMenu.getMenu().add(0, 2, 0, "Courses");
                popupMenu.getMenu().add(0, 3, 0, "Community");
                popupMenu.getMenu().add(0, 4, 0, "Mentors");
                popupMenu.getMenu().add(0, 5, 0, "Logout");

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

        // Redirections pour les clics
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

    // Méthodes pour gérer les actions du menu
    private void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class); // Remplacez par l'activité correcte
        startActivity(intent);
    }

    private void openCourses() {
        Intent intent = new Intent(this, popularactivity.class); // Remplacez par l'activité correcte
        startActivity(intent);
    }

    private void openCommunity() {
        Intent intent = new Intent(this, InviteFriendsActivity.class); // Remplacez par l'activité correcte
        startActivity(intent);
    }

    private void openMentors() {
        Intent intent = new Intent(this, mentors.class); // Remplacez par l'activité correcte
        startActivity(intent);
    }

    private void logout() {
        // Logique pour la déconnexion
        Log.d("Menu", "User logged out");
    }
}
