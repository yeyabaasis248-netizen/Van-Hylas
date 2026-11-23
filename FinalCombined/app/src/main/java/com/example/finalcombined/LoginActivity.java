package com.example.finalcombined;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        MaterialButton loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(v -> {
            String enteredUsername = username.getText().toString().trim();
            String enteredPassword = password.getText().toString();
            if (enteredUsername.equals("vanandhylas") && enteredPassword.equals("foodstuff")) {
                Toast.makeText(this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
