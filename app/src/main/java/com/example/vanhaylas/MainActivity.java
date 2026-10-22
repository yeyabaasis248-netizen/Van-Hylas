package com.example.vanhaylas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    CheckBox checkOne, checkTwo, checkThree, checkFour, checkFive;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int padding20 = (int) (20 * v.getResources().getDisplayMetrics().density);
            v.setPadding(
                    systemBars.left + padding20,
                    systemBars.top + padding20,
                    systemBars.right + padding20,
                    systemBars.bottom + padding20
            );
            return insets;
        });

        radioGroup = findViewById(R.id.radioGroup);
        checkOne = findViewById(R.id.checkOne);
        checkTwo = findViewById(R.id.checkTwo);
        checkThree = findViewById(R.id.checkThree);
        checkFour = findViewById(R.id.checkFour);
        checkFive = findViewById(R.id.checkFive);
        btnShow = findViewById(R.id.btnShow);   // the Pay button

        // RADIO BUTTON: toast when a radio button is selected
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = findViewById(checkedId);
            if (selected != null) {
                Toast.makeText(MainActivity.this,
                        selected.getText() + " ",
                        Toast.LENGTH_SHORT).show();
            }
        });


        checkOne.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Fajitas added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Fajitas removed", Toast.LENGTH_SHORT).show();
            }
        });


        checkTwo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Spaghetti added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Spaghetti removed", Toast.LENGTH_SHORT).show();
            }
        });

        checkThree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Beef Steak added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Beef Steak removed", Toast.LENGTH_SHORT).show();
            }
        });

        checkFour.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Korean Ramen added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Korean Ramen removed", Toast.LENGTH_SHORT).show();
            }
        });

        checkFive.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Teobokki added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Teobokki removed", Toast.LENGTH_SHORT).show();
            }
        });


        // PAY BUTTON: toast "Order Complete"
        btnShow.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Order Complete", Toast.LENGTH_SHORT).show();
        });
    }
}