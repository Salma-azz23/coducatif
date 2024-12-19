package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coducatif.R;

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

        // Redirection vers InviteFriendsActivity
        inviteFriendsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acceuil.this, InviteFriendsActivity.class);
                startActivity(intent);
            }
                });

// Redirection vers course_crcq
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(acceuil.this, course_crcq.class);
            startActivity(intent);
        });

// Redirection vers popularcourses
        seeAllPopularCourses.setOnClickListener(v -> {
            Intent intent = new Intent(acceuil.this, popularactivity.class);
            startActivity(intent);
        });

// Redirection vers levels
        interactiveUICard.setOnClickListener(v -> {
            Intent intent = new Intent(acceuil.this, levels.class);
            startActivity(intent);
        });

// Redirection vers mentors
        seeAllMentors.setOnClickListener(v -> {
            Intent intent = new Intent(acceuil.this, mentors.class);
            startActivity(intent);
        });

    }
}
