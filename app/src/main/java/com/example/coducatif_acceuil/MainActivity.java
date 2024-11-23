// MainActivity.java
package com.example.coducatif_acceuil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coducatif_acceuil.R;
import com.example.coducatif_acceuil.course_activity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the React course card by ID
        LinearLayout reactCourseCard = findViewById(R.id.react_course_card);

        // Set an onClickListener to navigate to CourseActivity
        reactCourseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "React card clicked!");
                Intent intent = new Intent(MainActivity.this, course_activity.class);
                startActivity(intent);
            }
        });
    }
}