package com.example.coducatif;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coducatif.Message;
import com.example.coducatif.MessageAdapter;
import com.example.coducatif.R;

import java.util.ArrayList;
import java.util.List;

public class chat_community extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_community);

        recyclerView = findViewById(R.id.recyclerViewMessages);
        EditText editTextMessage = findViewById(R.id.editTextMessage);
        ImageButton buttonSend = findViewById(R.id.buttonSend);

        // Initialisation des messages
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Gérer l'envoi de messages
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextMessage.getText().toString();
                if (!messageText.isEmpty()) {
                    // Ajouter un nouveau message envoyé par l'utilisateur
                    messageList.add(new Message(messageText, true));
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messageList.size() - 1); // Faire défiler vers le dernier message
                    editTextMessage.setText(""); // Effacer le champ de texte
                }
            }
        });
    }
}
