package com.example.coducatif;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chat_community extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;  // Définir une constante pour la sélection d'image

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private ArrayList<Message> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend, buttonAttachImage;  // Déclare buttonAttachImage ici
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_community);

        // Initialisation des vues
        recyclerView = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        buttonAttachImage = findViewById(R.id.buttonAttachImage);

        // Initialisation de la liste des messages
        messageList = new ArrayList<>();

        // Configuration du RecyclerView
        adapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Initialiser la référence à la base de données Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("chats");

        // Écouter les messages depuis Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();  // Réinitialiser la liste des messages
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Message message = snapshot.getValue(Message.class);
                    messageList.add(message);  // Ajouter les messages à la liste
                }
                adapter.notifyDataSetChanged();  // Notifier l'adapter pour mettre à jour l'affichage
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(chat_community.this, "Erreur de récupération des messages", Toast.LENGTH_SHORT).show();
            }
        });

        // Action du bouton Send
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();

                if (!messageText.isEmpty()) {
                    // Ajouter le message à Firebase
                    Map<String, Object> messageData = new HashMap<>();
                    messageData.put("message", messageText);
                    messageData.put("type", "text");
                    messageData.put("timestamp", System.currentTimeMillis());

                    // Sauvegarder le message dans Firebase
                    String messageKey = databaseReference.push().getKey();  // Récupérer la clé du message
                    messageData.put("messageKey", messageKey);  // Ajouter la clé au message
                    databaseReference.child(messageKey).setValue(messageData);  // Ajouter le message dans Firebase

                    // Ajouter le message à la liste localement
                    messageList.add(new Message(messageText, messageKey));

                    // Notifier l'adapter et faire défiler vers le bas
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messageList.size() - 1);

                    // Effacer le champ de texte
                    editTextMessage.setText("");
                } else {
                    Log.d("SendMessage", "Message vide ou champ texte vide");
                }
            }
        });

        // Code pour le bouton d'attachement d'image (que tu as déjà ajouté)
        buttonAttachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();  // Ouvre le sélecteur d'images
            }
        });
    }


    // Gérer la sélection d'image depuis la galerie
    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Gérer le résultat de la sélection d'image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadImageToFirebase(imageUri);
        }
    }

    // Télécharger l'image dans Firebase Storage
    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("chat_images");
        StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        sendMessageWithImage(imageUrl);  // Envoyer le message avec l'URL de l'image
                    });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(chat_community.this, "Échec du téléchargement de l'image", Toast.LENGTH_SHORT).show();
                });
    }

    // Envoyer le message avec l'image
    private void sendMessageWithImage(String imageUrl) {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("message", imageUrl);
        messageData.put("type", "image");
        messageData.put("timestamp", System.currentTimeMillis());

        databaseReference.push().setValue(messageData);  // Ajouter le message image dans la base de données Firebase
    }
}