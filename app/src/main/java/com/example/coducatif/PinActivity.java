package com.example.coducatif;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PinActivity extends AppCompatActivity {

    private TextView pinDigit1, pinDigit2, pinDigit3, pinDigit4;
    private Button continueButton;
    private GridLayout numericKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        pinDigit1 = findViewById(R.id.pinDigit1);
        pinDigit2 = findViewById(R.id.pinDigit2);
        pinDigit3 = findViewById(R.id.pinDigit3);
        pinDigit4 = findViewById(R.id.pinDigit4);
        continueButton = findViewById(R.id.continueButton);
        numericKeyboard = findViewById(R.id.numericKeyboard);

        // Logique pour la saisie du code PIN
        setupPinInput();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = pinDigit1.getText().toString() + pinDigit2.getText().toString() +
                        pinDigit3.getText().toString() + pinDigit4.getText().toString();

                if (pin.length() == 4) {
                    // Enregistrer le code PIN dans la base de données
                    // Pour cela, vous pouvez appeler une méthode dans votre DBHelper
                    // dbHelper.savePin(pin);

                    Toast.makeText(PinActivity.this, "PIN enregistré avec succès", Toast.LENGTH_SHORT).show();
                    finish(); // Ferme l'activité et revient à l'écran précédent
                } else {
                    Toast.makeText(PinActivity.this, "Veuillez entrer un code PIN valide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupPinInput() {
        // Cette méthode gère l'entrée du code PIN à partir du clavier numérique
        for (int i = 0; i < numericKeyboard.getChildCount(); i++) {
            View key = numericKeyboard.getChildAt(i);
            if (key instanceof Button) {
                Button button = (Button) key;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String digit = button.getText().toString();
                        fillPinDigit(digit);
                    }
                });
            }
        }
    }

    private void fillPinDigit(String digit) {
        if (pinDigit1.getText().toString().isEmpty()) {
            pinDigit1.setText(digit);
        } else if (pinDigit2.getText().toString().isEmpty()) {
            pinDigit2.setText(digit);
        } else if (pinDigit3.getText().toString().isEmpty()) {
            pinDigit3.setText(digit);
        } else if (pinDigit4.getText().toString().isEmpty()) {
            pinDigit4.setText(digit);
        }
    }
}
