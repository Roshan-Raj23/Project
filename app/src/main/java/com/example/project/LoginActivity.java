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

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    EditText nameEditText , emailAddressEditText , passwordEditText , repasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        dbHelper = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.name);
        emailAddressEditText = findViewById(R.id.emailAddress);
        passwordEditText = findViewById(R.id.password);
        repasswordEditText = findViewById(R.id.repassword);
    }



    public void MainPage(View view) {

        if (validateFields()) {
            String name = nameEditText.getText().toString().trim();
            String email = emailAddressEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            long result = dbHelper.signUp(name, email, password);

            if (result != -1) {
                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainPage.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "May be you are already Signed up try Login in", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private boolean validateFields() {

        String name = nameEditText.getText().toString().trim();
        String emailAddress = emailAddressEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String repassword = repasswordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(repassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}