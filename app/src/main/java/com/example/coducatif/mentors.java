package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mentors extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentors);
        CardView card6 = findViewById(R.id.course9);
        CardView card7 = findViewById(R.id.course10);
        CardView card8 = findViewById(R.id.course11);
        CardView card9 = findViewById(R.id.course12);
        Button cour = findViewById(R.id.tabcourses);

        cour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mentors.this, course_crcq.class); // Remplacez par le nom de votre activité
                startActivity(intent);
            }
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String Link = "https://www.linkedin.com/in/yassine-abhir-862b74316?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(intent);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String Link = "https://www.linkedin.com/in/baabouj?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(intent);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String Link = "https://www.linkedin.com/in/eduardoandrediogo?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(intent);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien YouTube
                String Link = "https://www.linkedin.com/in/marwane-laks?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(intent);
            }
        });





    }
}

