package com.example.coducatif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class course_content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        findViewById(R.id.course_react).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Afficher un message toast (optionnel)
                Toast.makeText(course_content.this, "Navigating to course_activity", Toast.LENGTH_SHORT).show();

                // Créer un intent pour démarrer course_activity
                Intent intent = new Intent(course_content.this, course_activity.class);
                startActivity(intent);
            }
        });
    }
}
