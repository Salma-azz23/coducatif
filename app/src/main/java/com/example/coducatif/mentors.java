package com.example.coducatif;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.Intent;
import android.net.Uri;

public class mentors extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentors);

        // Find all CardViews
        CardView card6 = findViewById(R.id.course9);
        CardView card7 = findViewById(R.id.course10);
        CardView card8 = findViewById(R.id.course11);
        CardView card9 = findViewById(R.id.course12);
        CardView card10 = findViewById(R.id.course13);
        CardView card11 = findViewById(R.id.course14);
        Button cours= findViewById(R.id.tabcourses);

        // Set up the search bar
        EditText searchBar = findViewById(R.id.search_bar);
        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité et retourne à l'activité précédente
            }
        });

        // Define card click listeners (LinkedIn profile links)
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/yassine-abhir-862b74316";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/baabouj";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/eduardoandrediogo";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/marwane-laks";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/ermich-reda?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.linkedin.com/in/fadoi-nhaila-93a61a110?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });

        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mentors.this, course_crcq.class);
                startActivity(intent);
            }
        });

        // Search functionality to filter results based on the category
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String searchText = charSequence.toString().toLowerCase();

                // Show/hide cards based on the text entered in the search bar
                filterMentors(searchText, "yassine abhir", card6);
                filterMentors(searchText, "omar", card7);
                filterMentors(searchText, "eduardo diogo", card8);
                filterMentors(searchText, "marwane", card9);
                filterMentors(searchText, "reda ermich", card10);
                filterMentors(searchText, "Fadoi Nohaila", card11);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    // Method to filter cards based on the mentor name
    private void filterMentors(String searchText, String mentorName, CardView cardView) {
        if (mentorName.toLowerCase().contains(searchText)) {
            cardView.setVisibility(View.VISIBLE); // Show card if it matches the search
        } else {
            cardView.setVisibility(View.GONE); // Hide card if it doesn't match
        }
    }
}
