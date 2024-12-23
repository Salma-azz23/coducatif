package com.example.coducatif;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        // Retrieve the profile information to pre-fill the EditText fields
        DBHelper dbHelper = new DBHelper(this);
        Profile currentProfile = dbHelper.getProfile(userId);

        if (currentProfile != null) {
            // Populate the fields with the current profile data
            etFullName.setText(currentProfile.getFullName());
            etNickName.setText(currentProfile.getNickName());
            etDob.setText(currentProfile.getDob());
            etPhone.setText(currentProfile.getPhone());
        } else {
            Toast.makeText(this, "Profile not found", Toast.LENGTH_SHORT).show();
        }

        // When the user clicks Save, update the profile
        btnSave.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString();
            String nickName = etNickName.getText().toString();
            String dob = etDob.getText().toString();
            String phone = etPhone.getText().toString();

            // Call the method to update the profile in the database
            boolean isUpdated = dbHelper.updateProfile(userId, fullName, nickName, dob, phone);

            if (isUpdated) {
                Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity and return to the previous one
            } else {
                Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
