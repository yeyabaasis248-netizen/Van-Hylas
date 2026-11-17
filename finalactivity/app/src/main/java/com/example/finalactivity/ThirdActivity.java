package com.example.finalactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import android.content.Intent;
public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        MaterialButton Enter3 = (MaterialButton) findViewById(R.id.Enter3);
        Enter3.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View view){
                                          Intent intent=new Intent(ThirdActivity.this, MainActivity.class);
                                          startActivity(intent);
                                          Toast.makeText(ThirdActivity.this,"You are ordering for Dine In",Toast.LENGTH_SHORT).show();
                                      }
                                  }

        );
    }}