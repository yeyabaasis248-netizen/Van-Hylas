package com.example.vanhaylas;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private LinearLayout panel;
    private TextView totalText;

    private RadioGroup orderTypeGroup;
    private RadioButton dineInButton;
    private RadioButton takeOutButton;

    private final List<CheckBox> checks = new ArrayList<>();
    private final List<RadioGroup> groups = new ArrayList<>();
    private final List<CheckBox> tubItems = new ArrayList<>();

    private final DecimalFormat money = new DecimalFormat("#,##0.00");

    private static final int TAKEOUT_CHARGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.menuContainer);
        totalText = findViewById(R.id.totalText);

        buildMenu();

        findViewById(R.id.orderButton)
                .setOnClickListener(v -> showSummary());

        updateTotal();
    }


    private void buildMenu() {
        buildOrderTypeSelection();
        buildMainMenu();

        // Uncomment to show additional menu-board items.
        // buildBoardMenu();

        buildFooter();
    }


    private void buildOrderTypeSelection() {
        section("Order Type");

        orderTypeGroup = new RadioGroup(this);
        orderTypeGroup.setOrientation(RadioGroup.HORIZONTAL);

        dineInButton = new RadioButton(this);
        dineInButton.setId(View.generateViewId());
        dineInButton.setText("Dine-in");
        styleButton(dineInButton);

        takeOutButton = new RadioButton(this);
        takeOutButton.setId(View.generateViewId());
        takeOutButton.setText("Take-out");
        styleButton(takeOutButton);

        orderTypeGroup.addView(dineInButton);
        orderTypeGroup.addView(takeOutButton);

        // Dine-in is selected by default.
        orderTypeGroup.check(dineInButton.getId());

        orderTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            updateTotal();

            if (checkedId == dineInButton.getId()) {
                Toast.makeText(
                        MainActivity.this,
                        "Dine-in selected. No take-out charges.",
                        Toast.LENGTH_SHORT
                ).show();
            } else if (checkedId == takeOutButton.getId()) {
                Toast.makeText(
                        MainActivity.this,
                        "Take-out selected. ₱10 extra per tub.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        panel.addView(orderTypeGroup);
    }

    private boolean isTakeOut() {
        return orderTypeGroup != null
                && takeOutButton != null
                && orderTypeGroup.getCheckedRadioButtonId()
                == takeOutButton.getId();
    }

    private void buildMainMenu() {

        section("VH Fajitas");
        item("Fajitas", 95);
        item("Small bilao", 950);
        item("Large bilao", 1900);

        section("VH Meals with Rice");
        item("Pork ribs", 105);
        item("Burger steak (2pcs)", 105);
        item("Chicken teriyaki", 105);
        item("Pork Sisig", 105);
        item("Bangus Sisig", 105);
        item("Bagnet", 105);
        item("Chicken long (with gravy)", 105);
        item("Pork steak", 105);

        section("VH Tubs (good for 2 pax, no rice)");
        takeoutNote();

        tubItem("Pork ribs", 190);
        tubItem("Burger steak (4pcs)", 190);
        tubItem("Chicken teriyaki", 190);
        tubItem("Pork Sisig", 190);
        tubItem("Bangus Sisig", 190);
        tubItem("Chicken long (with gravy)", 190);
        tubItem("Pork steak (2pcs)", 190);

        section("VH Ramen (served with egg & veggies)");
        item("Street ramen (kikiam & squidballs)", 125);
        item("Fish ramen (fish cake & fish tofu)", 145);
        item("Ribs ramen (VH ribs)", 165);
        radioGroup("Spiciness", "Mild (L1)", "Medium (L2)", "Strong (L3)");

        section("Cheesy Teokbokki");
        takeoutNote();

        tubItem("Tub for 2 pax", 180);
        radioGroup("Spiciness", "Sweet", "Mild", "Strong");

        section("Add-ons");
        item("Add-on 1", 10);
        item("Korean", 20);
        item("Add-on 3", 10);
    }

    private void buildBoardMenu() {

        section("Noodles");
        item("Mami", 0);
        item("Mami with egg", 0);
        item("Spicy Soba", 0);
        item("Siomai Soup", 0);
        item("Bagnet ramen", 0);

        section("Sandwiches");
        item("Beef Hamburger", 0);
        item("Beef Cheeseburger", 0);
        item("Footlong", 0);
        item("Tender Juicy Jumbo Hotdog", 0);
        item("Van & Hyla's Mix", 0);
        item("Garden Salad Sandwich", 0);

        section("Other Choices");
        item("Spaghetti with Chicken", 0);
        item("Spaghetti with Shanghai", 0);
        item("Spaghetti with Fries", 0);
        item("Spaghetti Solo", 0);
        item("French Fries", 0);

        section("Juices");
        item("Regular Lemonade", 0);
        item("Blue Lemonade", 0);
        item("Cucumber", 0);
        item("Iced Tea", 0);
        item("Shirley Temple", 0);

        section("Shakes");
        item("Coffee", 0);
        item("Chocolate", 0);
        item("Strawberry", 0);
    }


    private void takeoutNote() {
        TextView note = new TextView(this);

        note.setText(
                "Note: An additional ₱10 take-out charge applies " +
                        "to each tub. This charge applies to take-out orders only."
        );

        note.setTextSize(13f);
        note.setTextColor(color(R.color.brand_yellow));
        note.setTypeface(note.getTypeface(), Typeface.ITALIC);
        note.setPadding(0, dp(4), 0, dp(10));

        panel.addView(note);
    }

    // --------------------------------------------------
    // FOOTER
    // --------------------------------------------------

    private void buildFooter() {
        TextView contact = new TextView(this);

        contact.setText(
                "For orders, DM us on our page or call\n" +
                        "540-4802\n" +
                        "0917-7099809"
        );

        contact.setTextColor(color(R.color.brand_blue_dark));
        contact.setGravity(Gravity.CENTER);
        contact.setPadding(0, dp(12), 0, dp(24));

        container.addView(contact);
    }


    private void section(String title) {
        panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setBackgroundResource(R.drawable.board_bg);
        panel.setPadding(dp(16), dp(14), dp(16), dp(10));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        lp.setMargins(0, 0, 0, dp(14));
        panel.setLayoutParams(lp);

        TextView heading = new TextView(this);
        heading.setText(title);
        heading.setAllCaps(true);
        heading.setTextSize(18f);
        heading.setLetterSpacing(0.04f);
        heading.setTypeface(heading.getTypeface(), Typeface.BOLD);
        heading.setTextColor(color(R.color.brand_yellow));

        panel.addView(heading);

        View line = new View(this);
        line.setBackgroundColor(color(R.color.brand_yellow));

        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(2)
        );

        lineParams.setMargins(0, dp(6), 0, dp(6));
        panel.addView(line, lineParams);

        container.addView(panel);
    }

    private void subHeading(String text) {
        TextView tv = new TextView(this);

        tv.setText(text);
        tv.setTextSize(14f);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextColor(color(R.color.brand_yellow));
        tv.setPadding(0, dp(12), 0, dp(2));

        panel.addView(tv);
    }

    // --------------------------------------------------
    // MENU ITEMS
    // --------------------------------------------------

    private void item(String name, int price) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        CheckBox cb = new CheckBox(this);
        cb.setText(name);
        cb.setTag(price);
        styleButton(cb);

        cb.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
        ));

        cb.setOnCheckedChangeListener((button, isChecked) -> {
            updateTotal();

            String message = isChecked
                    ? button.getText() + " added to your order."
                    : button.getText() + " removed from your order.";

            Toast.makeText(
                    MainActivity.this,
                    message,
                    Toast.LENGTH_SHORT
            ).show();
        });

        checks.add(cb);
        row.addView(cb);

        if (price > 0) {
            TextView priceText = new TextView(this);

            priceText.setText(
                    "₱" + String.format(Locale.US, "%,d", price)
            );

            priceText.setTextSize(15f);
            priceText.setTypeface(priceText.getTypeface(), Typeface.BOLD);
            priceText.setTextColor(color(R.color.brand_yellow));

            priceText.setOnClickListener(v -> cb.toggle());

            row.addView(priceText);
        }

        panel.addView(row);
    }

    private void tubItem(String name, int price) {
        item(name, price);

        CheckBox tub = checks.get(checks.size() - 1);
        tubItems.add(tub);
    }


    private void radioGroup(String label, String... options) {
        subHeading(label);

        RadioGroup group = new RadioGroup(this);
        group.setOrientation(RadioGroup.VERTICAL);
        group.setTag(label + " (" + sectionTitle() + ")");

        RadioButton none = new RadioButton(this);
        none.setId(View.generateViewId());
        none.setText("None");
        styleButton(none);
        group.addView(none);

        for (String option : options) {
            RadioButton rb = new RadioButton(this);
            rb.setId(View.generateViewId());
            rb.setText(option);
            styleButton(rb);
            group.addView(rb);
        }

        group.check(none.getId());

        groups.add(group);
        panel.addView(group);
    }

    private String sectionTitle() {
        TextView heading = (TextView) panel.getChildAt(0);
        String title = heading.getText().toString();

        int paren = title.indexOf("  (");

        return paren > 0
                ? title.substring(0, paren)
                : title;
    }

    // --------------------------------------------------
    // STYLING
    // --------------------------------------------------

    private void styleButton(CompoundButton button) {
        ColorStateList tint = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        color(R.color.brand_yellow),
                        color(R.color.white)
                }
        );

        CompoundButtonCompat.setButtonTintList(button, tint);

        button.setTextColor(color(R.color.white));
        button.setTextSize(16f);
    }

    private int color(int resId) {
        return ContextCompat.getColor(this, resId);
    }

    private int dp(int value) {
        return (int) (
                value * getResources().getDisplayMetrics().density
        );
    }

    // --------------------------------------------------
    // TOTAL CALCULATION
    // --------------------------------------------------

    private void updateTotal() {
        int total = 0;

        for (CheckBox cb : checks) {
            if (cb.isChecked()) {
                total += (Integer) cb.getTag();
            }
        }

        // Add ₱10 for every selected tub only on take-out orders.
        if (isTakeOut()) {
            for (CheckBox tub : tubItems) {
                if (tub.isChecked()) {
                    total += TAKEOUT_CHARGE;
                }
            }
        }

        totalText.setText("Total: ₱" + money.format(total));
    }

    private void showSummary() {
        StringBuilder sb = new StringBuilder();
        boolean hasItems = false;

        String orderType = isTakeOut() ? "Take-out" : "Dine-in";

        sb.append("Order type: ")
                .append(orderType)
                .append("\n\n");

        for (CheckBox cb : checks) {
            if (cb.isChecked()) {
                int price = (Integer) cb.getTag();

                sb.append("☑ ")
                        .append(cb.getText())
                        .append(" - ₱")
                        .append(money.format(price))
                        .append("\n");

                hasItems = true;

                if (isTakeOut() && tubItems.contains(cb)) {
                    sb.append("   Take-out charge: ₱")
                            .append(money.format(TAKEOUT_CHARGE))
                            .append("\n");
                }
            }
        }

        if (!hasItems) {
            Toast.makeText(
                    MainActivity.this,
                    "Please select at least one menu item.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        for (RadioGroup group : groups) {
            RadioButton selected = group.findViewById(
                    group.getCheckedRadioButtonId()
            );

            if (selected != null
                    && !selected.getText().toString().equals("None")) {

                sb.append("• ")
                        .append(group.getTag())
                        .append(": ")
                        .append(selected.getText())
                        .append("\n");
            }
        }

        sb.append("\n").append(totalText.getText());

        new AlertDialog.Builder(this)
                .setTitle("Your Order")
                .setMessage(sb.toString())
                .setPositiveButton("Call to confirm", (dialog, which) -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Order saved successfully!",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent = new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:09177099809")
                    );

                    startActivity(intent);
                })
                .setNegativeButton("Back", null)
                .show();
    }
}
