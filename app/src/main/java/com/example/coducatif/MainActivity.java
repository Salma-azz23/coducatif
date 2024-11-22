import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coducatif.Course;
import com.example.coducatif.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liste des cours
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("React js", "Framework of javascript", "7058/-", "⭐ 4.2 | 7830 Std", R.drawable.react_image));
        courseList.add(new Course("Symfony", "Framework of PHP", "800/-", "⭐ 3.9 | 12680 Std", R.drawable.symfony_image));
        courseList.add(new Course("Laravel", "Framework of PHP", "599/-", "⭐ 4.1 | 990 Std", R.drawable.laravel_image));
        courseList.add(new Course("Next.js", "Advanced Frontend Framework", "499/-", "⭐ 4.9 | 14580 Std", R.drawable.next_image));

        // Adaptateur
        CourseAdapter adapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(adapter);
    }
}
