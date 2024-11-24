import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coducatif.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter CourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> courseList = Arrays.asList(
                "React JS - Framework of JavaScript",
                "React JS - Framework of JavaScript", // Doublon
                "Symfony - Framework of PHP",
                "Laravel - Framework of PHP",
                "Next.js - Advanced Framework Frontend"
        );

        // Supprimez les doublons
        List<String> uniqueCourseList = new ArrayList<>(new HashSet<>(courseList));

        // Ajoutez un log pour vérifier la liste après suppression des doublons
        Log.d("MainActivity", "Liste après suppression des doublons : " + uniqueCourseList);

        // Configurez l'adaptateur
        CourseAdapter = new CourseAdapter(uniqueCourseList);
        recyclerView.setAdapter(CourseAdapter);
    }

