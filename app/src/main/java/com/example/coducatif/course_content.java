package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class course_content extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);
    }

    // Méthode pour ouvrir le fichier PDF
    public void openCoursePdf(View view) {
        try {
            // Copier le fichier PDF dans le stockage interne
            File pdfFile = copyPdfToInternalStorage("course.pdf");

            if (pdfFile.exists()) {
                // Obtenir l'URI sécurisé à l'aide de FileProvider
                Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pdfFile);

                // Créer un Intent pour ouvrir le fichier PDF
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(pdfUri, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Vérifier si une application PDF est disponible
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Aucune application pour ouvrir les fichiers PDF", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Le fichier PDF est introuvable", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de l'ouverture du PDF", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour copier le fichier PDF depuis les assets vers le stockage interne
    private File copyPdfToInternalStorage(String assetFileName) throws IOException {
        File pdfFile = new File(getFilesDir(), assetFileName);
        if (!pdfFile.exists()) {
            try (InputStream inputStream = getAssets().open(assetFileName);
                 FileOutputStream outputStream = new FileOutputStream(pdfFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            }
        }
        return pdfFile;
    }
}
