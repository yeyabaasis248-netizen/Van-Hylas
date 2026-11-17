package com.example.finalactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton Enter = (MaterialButton) findViewById(R.id.Enter);
        MaterialButton Enter2 = (MaterialButton) findViewById(R.id.Enter2);
        Enter.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                         startActivity(intent);
                                     }
                                 }
        );
        Enter2.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View view) {
                                          Intent intent = new Intent(MainActivity.this,
                                                  ThirdActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );

    }}