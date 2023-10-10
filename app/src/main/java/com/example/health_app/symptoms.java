package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class symptoms extends AppCompatActivity {
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;
    Button submitButton;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        cb1 = findViewById(R.id.blocked_nosecb);
        cb2 = findViewById(R.id.sneezingcb);
        cb3 = findViewById(R.id.itchynosecb);
        cb4 = findViewById(R.id.runnynosecb);
        cb5 = findViewById(R.id.wheezingcb);
        cb6 = findViewById(R.id.chesttightcb);
        cb7 = findViewById(R.id.shortbreathcb);
        cb8 = findViewById(R.id.wakeupcb);
        cb9 = findViewById(R.id.tirednesscb);
        submitButton = findViewById(R.id.sub);
        drawerLayout = findViewById(R.id.drawer_layout);

        setupToolbarAndNavigationDrawer();

        // Initialize SharedPreferences
//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//
//        // Retrieve and set the checkbox states
//        cb1.setChecked(sharedPreferences.getBoolean("cb1_state", false));
//        cb2.setChecked(sharedPreferences.getBoolean("cb2_state", false));
//        cb3.setChecked(sharedPreferences.getBoolean("cb3_state", false));
//        cb4.setChecked(sharedPreferences.getBoolean("cb4_state", false));
//        cb5.setChecked(sharedPreferences.getBoolean("cb5_state", false));
//        cb6.setChecked(sharedPreferences.getBoolean("cb6_state", false));
//        cb7.setChecked(sharedPreferences.getBoolean("cb7_state", false));
//        cb8.setChecked(sharedPreferences.getBoolean("cb8_state", false));
//        cb9.setChecked(sharedPreferences.getBoolean("cb9_state", false));

        // Set OnCheckedChangeListener for cb1
//        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Save the state in SharedPreferences
//                sharedPreferences.edit().putBoolean("cb1_state", isChecked).apply();
//
//                // Handle checkbox state change
//                if (isChecked) {
//                    Toast.makeText(symptoms.this, "Blocked Nose is checked", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(symptoms.this, "Blocked Nose is not checked", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        // Similar OnCheckedChangeListener setup for other checkboxes (cb2 to cb9).

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here, you can submit the information or perform any other actions based on the checkbox states.
                if (cb1.isChecked()) {
                    // The "Blocked Nose" checkbox is checked
                    // Add code to handle this case
                }

                // Repeat for other checkboxes if needed
            }
        });
    }

    private void setupToolbarAndNavigationDrawer() {
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        // Set up navigation drawer icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Set up navigation item click listener
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleNavigationItemSelected(item);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent i1 = new Intent(symptoms.this, DashBoard.class);
                startActivity(i1);
                break;
            case R.id.nav_edit:
                Intent i = new Intent(symptoms.this, Edit_profile.class);
                startActivity(i);
                break;
            case R.id.symptoms:
                Toast.makeText(symptoms.this, "Symptoms is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent i2 = new Intent(symptoms.this, MainActivity.class);
                startActivity(i2);
                break;
        }
    }

}
