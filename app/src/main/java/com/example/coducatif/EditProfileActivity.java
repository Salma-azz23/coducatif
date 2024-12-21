package com.example.coducatif;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etFullName, etNickName, etDob, etPhone;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etFullName = findViewById(R.id.etFullName);
        etNickName = findViewById(R.id.etNickName);
        etDob = findViewById(R.id.etDob);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        int userId = getIntent().getIntExtra("userId", -1);

        btnSave.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString();
            String nickName = etNickName.getText().toString();
            String dob = etDob.getText().toString();
            String phone = etPhone.getText().toString();

            DBHelper dbHelper = new DBHelper(this);
            dbHelper.addProfile(userId, fullName, nickName, dob, phone);

            finish(); // Retour à l'activité précédente
        });
    }
}
