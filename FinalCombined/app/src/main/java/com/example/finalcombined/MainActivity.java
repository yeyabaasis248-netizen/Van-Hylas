package com.example.finalcombined;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAppetizers).setOnClickListener(v -> openCategory("Appetizers"));
        findViewById(R.id.btnMainCourse).setOnClickListener(v -> openCategory("Main Course"));
        findViewById(R.id.btnDesserts).setOnClickListener(v -> openCategory("Desserts"));
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
    private void openCategory(String category) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
