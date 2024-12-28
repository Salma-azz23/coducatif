package com.example.coducatif;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class course_activity extends AppCompatActivity {

    // Declare views
    private TextView chapterTitle;
    private TextView titleText1, lessonText1;
    private TextView titleText2, lessonText2;
    private TextView titleText3, lessonText3;
    private TextView titleText4, lessonText4;
    private TextView titleText5, lessonText5;
    private TextView titleText6, lessonText6;
    private TextView titleText7, lessonText7;
    private TextView titleText8, lessonText8;
    private TextView titleText9, lessonText9;
    private TextView titleText10, lessonText10;

    private ImageView photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Initialize views
        chapterTitle = findViewById(R.id.chapterTitle);

        titleText1 = findViewById(R.id.titleText1);
        lessonText1 = findViewById(R.id.lessonText1);
        photo1 = findViewById(R.id.photo1);

        titleText2 = findViewById(R.id.titleText2);
        lessonText2 = findViewById(R.id.lessonText2);
        photo2 = findViewById(R.id.photo2);

        titleText3 = findViewById(R.id.titleText3);
        lessonText3 = findViewById(R.id.lessonText3);
        photo3 = findViewById(R.id.photo3);

        titleText4 = findViewById(R.id.titleText4);
        lessonText4 = findViewById(R.id.lessonText4);
        photo4 = findViewById(R.id.photo4);

        titleText5 = findViewById(R.id.titleText5);
        lessonText5 = findViewById(R.id.lessonText5);
        photo5 = findViewById(R.id.photo5);

        titleText6 = findViewById(R.id.titleText6);
        lessonText6 = findViewById(R.id.lessonText6);
        photo6 = findViewById(R.id.photo6);

        titleText7 = findViewById(R.id.titleText7);
        lessonText7 = findViewById(R.id.lessonText7);
        photo7 = findViewById(R.id.photo7);

        titleText8 = findViewById(R.id.titleText8);
        lessonText8 = findViewById(R.id.lessonText8);
        photo8 = findViewById(R.id.photo8);

        titleText9 = findViewById(R.id.titleText9);
        lessonText9 = findViewById(R.id.lessonText9);
        photo9 = findViewById(R.id.photo9);

        titleText10 = findViewById(R.id.titleText10);
        lessonText10 = findViewById(R.id.lessonText10);
        photo10 = findViewById(R.id.photo10);

        // Set initial content or logic (optional)
        chapterTitle.setText("Introduction to React");
    }
}