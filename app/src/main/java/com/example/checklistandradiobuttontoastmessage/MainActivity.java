package com.example.checklistandradiobuttontoastmessage;

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
    CheckBox checkOne, checkTwo;
    Button btnShow;

    // ===== PRICES (change these to your real prices) =====
    static final String CURRENCY = "₱";
    static final double FAJITAS_PRICE = 150.00;
    static final double SPAGHETTI_PRICE = 120.00;

    // Radio button prices, in the same order as they appear in the RadioGroup
    // 1st radio button = 50, 2nd = 80, 3rd = 100
    static final double[] RADIO_PRICES = {50.00, 80.00, 100.00};

    // ===== Running values =====
    double radioPrice = 0;
    double fajitasTotal = 0;
    double spaghettiTotal = 0;

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
        btnShow = findViewById(R.id.btnShow);   // the Pay button

        // RADIO BUTTON: toast with name, price, and current total
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = findViewById(checkedId);
            if (selected != null) {
                int index = group.indexOfChild(selected);
                if (index >= 0 && index < RADIO_PRICES.length) {
                    radioPrice = RADIO_PRICES[index];
                } else {
                    radioPrice = 0;
                }
                Toast.makeText(MainActivity.this,
                        selected.getText() + " - " + formatPrice(radioPrice)
                                + "\nTotal: " + formatPrice(getTotal()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // CHECKBOX 1: Fajitas
        checkOne.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fajitasTotal = FAJITAS_PRICE;
                Toast.makeText(MainActivity.this,
                        "Fajitas added (" + formatPrice(FAJITAS_PRICE) + ")"
                                + "\nTotal: " + formatPrice(getTotal()),
                        Toast.LENGTH_SHORT).show();
            } else {
                fajitasTotal = 0;
                Toast.makeText(MainActivity.this,
                        "Fajitas removed (-" + formatPrice(FAJITAS_PRICE) + ")"
                                + "\nTotal: " + formatPrice(getTotal()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // CHECKBOX 2: Spaghetti
        checkTwo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                spaghettiTotal = SPAGHETTI_PRICE;
                Toast.makeText(MainActivity.this,
                        "Spaghetti added (" + formatPrice(SPAGHETTI_PRICE) + ")"
                                + "\nTotal: " + formatPrice(getTotal()),
                        Toast.LENGTH_SHORT).show();
            } else {
                spaghettiTotal = 0;
                Toast.makeText(MainActivity.this,
                        "Spaghetti removed (-" + formatPrice(SPAGHETTI_PRICE) + ")"
                                + "\nTotal: " + formatPrice(getTotal()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // PAY BUTTON: toast "Order Complete" with the final total
        btnShow.setOnClickListener(v -> {
            double total = getTotal();
            if (total == 0) {
                Toast.makeText(MainActivity.this,
                        "Please select an item first",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Order Complete\nTotal: " + formatPrice(total),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // Adds everything together
    private double getTotal() {
        return radioPrice + fajitasTotal + spaghettiTotal;
    }

    // Turns 150.0 into "₱150.00"
    private String formatPrice(double amount) {
        return CURRENCY + String.format("%.2f", amount);
    }
}