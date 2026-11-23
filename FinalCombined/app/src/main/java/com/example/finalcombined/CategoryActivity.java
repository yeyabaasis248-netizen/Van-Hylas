
package com.example.finalcombined;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private final List<CheckBox> foodChecks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String category = getIntent().getStringExtra("CATEGORY");
        if (category == null) category = "Appetizers";

        ((TextView) findViewById(R.id.categoryTitle)).setText(category);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        LinearLayout foodList = findViewById(R.id.foodList);

        for (String food : getFoods(category)) {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setText(food);
            checkBox.setTextSize(18);
            checkBox.setTextColor(Color.BLACK);
            checkBox.setPadding(8, 12, 8, 12);

            foodList.addView(checkBox);
            foodChecks.add(checkBox);
        }

        findViewById(R.id.btnCheckout).setOnClickListener(v -> checkout());
    }

    private String[] getFoods(String category) {
        if ("Main Course".equals(category))
            return new String[]{
                    "Chicken Adobo",
                    "Pork Sisig",
                    "Beef Steak",
                    "Chicken Teriyaki",
                    "Grilled Bangus"
            };

        if ("Desserts".equals(category))
            return new String[]{
                    "Leche Flan",
                    "Halo-Halo",
                    "Ube Halaya",
                    "Mango Float",
                    "Ice Cream"
            };

        return new String[]{
                "Lumpiang Shanghai",
                "French Fries",
                "Chicken Wings",
                "Calamares",
                "Cheese Sticks"
        };
    }

    private void checkout() {
        List<String> selected = new ArrayList<>();

        for (CheckBox checkBox : foodChecks) {
            if (checkBox.isChecked()) {
                selected.add(checkBox.getText().toString());
            }
        }

        if (selected.isEmpty()) {
            Toast.makeText(this,
                    "Please select at least one food item.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this,
                "Selected food: " + android.text.TextUtils.join(", ", selected),
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);
        finish();
    }
}
