package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuggestionPage extends AppCompatActivity {

    EditText emailText , suggestionText , nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggestion_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        suggestionText = findViewById(R.id.feedback);

    }

    public void MainPage(View view) {

        if (validateFields()){
            Toast.makeText(this, "Suggestion saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this , MainPage.class);
            startActivity(intent);
        }

    }


    private boolean validateFields() {

        String name = nameText.getText().toString().trim();
        String emailAddress = emailText.getText().toString().trim();
        String text = suggestionText.getText().toString().trim();

        if (TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(text) || TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}