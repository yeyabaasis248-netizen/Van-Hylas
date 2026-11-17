package com.example.finalactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        MaterialButton Enter = (MaterialButton) findViewById(R.id.Enter);
        Enter.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view){
                                         Intent intent=new Intent(SecondActivity.this, MainActivity.class);
                                         startActivity(intent);

                                         Toast.makeText(SecondActivity.this,"You are ordering for Take Out",Toast.LENGTH_SHORT).show();

                                     }
                                 }

        );
    }}