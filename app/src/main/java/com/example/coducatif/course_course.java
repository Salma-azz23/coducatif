package com.example.coducatif;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.coducatif.R;
import com.example.coducatif_acceuil.R;

public class course_course extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Méthode appelée lors du clic sur l'image "Enroll Now"
    public void openCoursePdf(View view) {
        // Remplacez "course.pdf" par le nom exact de votre fichier PDF dans le dossier "assets"
        Uri pdfUri = Uri.parse("file:///android_asset/course.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        // Vérifie si une application PDF est disponible pour ouvrir le fichier
        Intent chooser = Intent.createChooser(intent, "Open Course PDF");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "Aucune application pour ouvrir les fichiers PDF", Toast.LENGTH_SHORT).show();
        }
    }
}