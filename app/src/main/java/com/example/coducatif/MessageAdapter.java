package com.example.coducatif;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        Context context = holder.itemView.getContext();  // Récupérer le contexte

        if (message.getType().equals("image")) {
            // Afficher l'image avec Picasso
            Picasso.get().load(message.getText()).into(holder.imageView);  // Utilisez `imageView` dans le layout
            holder.textView.setVisibility(View.GONE); // Cacher le texte si c'est une image
        } else {
            holder.textView.setText(message.getText());
            holder.imageView.setVisibility(View.GONE); // Cacher l'image si c'est du texte
        }

        // Bouton pour modifier le message (si vous avez un bouton pour modifier)
        holder.buttonEdit.setOnClickListener(v -> {
            // Exemple : Demander un nouveau message via une boîte de dialogue
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Modifier le message");

            // Ajouter un champ de texte pour entrer le nouveau message
            final EditText input = new EditText(context);
            input.setText(message.getText()); // Pré-remplir avec le texte actuel
            builder.setView(input);

            // Bouton pour enregistrer le nouveau message
            builder.setPositiveButton("Modifier", (dialog, which) -> {
                String newMessageText = input.getText().toString().trim();
                if (!newMessageText.isEmpty()) {
                    DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference("chats").child(message.getMessageKey());
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("message", newMessageText);

                    messageRef.updateChildren(updates).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Mettre à jour localement
                            messageList.get(position).setText(newMessageText);
                            notifyItemChanged(position);
                            Toast.makeText(context, "Message modifié avec succès", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Erreur de modification", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(context, "Message vide", Toast.LENGTH_SHORT).show();
                }
            });

            // Bouton pour annuler
            builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());
            builder.show();
        });


        // Si tu as un bouton pour supprimer le message
        holder.buttonDelete.setOnClickListener(v -> {
            String messageKey = message.getMessageKey(); // Assure-toi que chaque message ait un messageKey unique
            if (messageKey != null) {
                // Supprimer du Firebase
                FirebaseDatabase.getInstance().getReference("chats").child(messageKey).removeValue()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Supprimer localement
                                messageList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(context, "Message supprimé avec succès", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(context, "Message invalide", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // Classe ViewHolder qui contient les vues du message
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        View buttonEdit;  // Ajouter un bouton Edit (ajoutez-le dans le layout item_message.xml)
        View buttonDelete;  // Ajouter un bouton Delete (ajoutez-le dans le layout item_message.xml)

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewMessage);
            imageView = itemView.findViewById(R.id.imageViewMessage);  // Vous devez ajouter un ImageView dans le layout
            buttonEdit = itemView.findViewById(R.id.buttonEdit);  // Lier le bouton Edit
            buttonDelete = itemView.findViewById(R.id.buttonDelete);  // Lier le bouton Delete
        }
    }

    // Méthode pour éditer un message
    private void editMessage(String messageId, int position, Context context) {
        String newMessageText = "Nouveau message"; // Vous pouvez le récupérer à partir de l'utilisateur
        Map<String, Object> updates = new HashMap<>();
        updates.put("message", newMessageText);

        DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference("chats").child(messageId);
        messageRef.updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Message modifié avec succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Erreur de modification", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Méthode pour supprimer un message
    private void deleteMessage(String messageId, int position, Context context) {
        DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference("chats").child(messageId);
        messageRef.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                messageList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Message supprimé avec succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Erreur de suppression", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
