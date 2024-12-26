package com.example.coducatif;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class chat_community extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private ArrayList<Message> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_community);

        // Initialisation des vues
        recyclerView = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Initialisation de la liste des messages
        messageList = new ArrayList<>();

        // Configuration du RecyclerView
        adapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Action du bouton Send
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString().trim();

                if (!messageText.isEmpty()) {
                    // Ajouter le message à la liste
                    messageList.add(new Message(messageText, true)); // `true` pour un message envoyé par l'utilisateur

                    // Notifier l'adapter et faire défiler vers le bas
                    adapter.notifyDataSetChanged();

                    // Faire défiler le RecyclerView jusqu'au dernier message
                    recyclerView.scrollToPosition(messageList.size() - 1);

                    // Effacer le champ de texte
                    editTextMessage.setText("");
                }
            }
        });
    }
}