package com.example.coducatif;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        // Redirection vers "activity_invite_friends.xml" lors du clic sur l'icône
        inviteFriendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, InviteFriendsActivity.class); // Remplacez par le nom de votre activité
                startActivity(intent);
            }
        });

        // Redirection vers "activity_course_crcq.xml" lors du clic sur le bouton START
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, course_crcq.class);
                startActivity(intent);
            }
        });

        // Redirection vers "popular_activity.xml" lors du clic sur "See All" (Popular Courses)
        seeAllPopularCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, popularcourses.class);
                startActivity(intent);
            }
        });

        // Redirection vers "activity_levels.xml" lors du clic sur la carte Interactive UI Development
        interactiveUICard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, levels.class);
                startActivity(intent);
            }
        });

        // Redirection vers "mentors.xml" lors du clic sur "See All" (Top Mentors)
        seeAllMentors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, mentors.class);
                startActivity(intent);
            }
        });
    }
}
