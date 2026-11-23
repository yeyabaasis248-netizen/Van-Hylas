
package com.example.finalcombined;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    Button btnTakeOut, btnDineIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        btnTakeOut = findViewById(R.id.btnTakeOut);
        btnDineIn = findViewById(R.id.btnDineIn);

        btnTakeOut.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, MainActivity.class);
            intent.putExtra("ORDER_TYPE", "Take-out");
            startActivity(intent);
        });

        btnDineIn.setOnClickListener(v -> {
            Intent intent = new Intent(FourthActivity.this, MainActivity.class);
            intent.putExtra("ORDER_TYPE", "Dine-in");
            startActivity(intent);
        });
    }
}
