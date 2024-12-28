package com.example.coducatif;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class certifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certif);

        // Initialisation du bouton de téléchargement
        Button downloadButton = findViewById(R.id.sign_in_button);

        // Action sur clic du bouton
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'image à partir des ressources drawable
                Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.certificate); // Remplacez par le nom de votre image

                // Copie de l'image dans le stockage local
                try {
                    File file = new File(getExternalFilesDir(null), "certificat.png"); // Emplacement où l'image sera stockée
                    FileOutputStream outputStream = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); // Compresser l'image et l'écrire dans le fichier
                    outputStream.close();

                    // Vérifier si l'image a été copiée avec succès
                    if (file.exists()) {
                        Toast.makeText(certifActivity.this, "Image copied successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(certifActivity.this, "Error: Image not found", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Informer le système que l'image a été ajoutée
                    MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), null);

                    // Lancer l'Intent pour ouvrir l'image
                    Uri imageUri = Uri.fromFile(file);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(imageUri, "image/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); // Optionnel : empêche de revenir à l'activité précédente
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(certifActivity.this, "Error opening image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
