import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coducatif.Course;
import com.example.coducatif_acceuil.R;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course); // Votre layout principal contenant le RecyclerView

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Configuration en liste verticale

        // Création des données
        List<Object> items = new ArrayList<>();

        // Ajout d'une section "Frontend"
        items.add("Frontend");
        items.add(new Course("React JS", "Framework of JavaScript", "7058/-", "⭐ 4.2   |   7830 Std"));
        items.add(new Course("Next.js", "Advanced Framework", "499/-", "⭐ 4.9   |   14580 Std"));

        // Ajout d'une section "Backend"
        items.add("Backend");
        items.add(new Course("Symfony", "Framework of PHP", "800/-", "⭐ 3.9   |   12680 Std"));
        items.add(new Course("Laravel", "Framework of PHP", "599/-", "⭐ 4.2   |   990 Std"));

        // Initialisation de l'adapter
        CourseAdapter adapter = new CourseAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
