package com.example.health_app;

import static com.example.health_app.symptoms.checked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MySymptoms extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView mysymptomslistview;
    private List<String> symp;
    private List<String> symps=new LinkedList<>();
    private Set<String> nodup=new LinkedHashSet<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_symptoms);
        drawerLayout = findViewById(R.id.drawer_layout);
        mysymptomslistview=findViewById(R.id.mysymptoms);
        setupToolbarAndNavigationDrawer();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("savedSymptoms", null);

        if (json != null) {
            symp = new LinkedList<>(new Gson().fromJson(json, new TypeToken<List<String>>() {}.getType()));
        } else {
            symp = new LinkedList<>();
        }

        symp.addAll(checked);
        nodup.addAll(symp);
        symps.addAll(nodup);

        // Update the ListView with the symptoms
        MySymptomsAdapter adapter = new MySymptomsAdapter(getApplicationContext(), symps);
        mysymptomslistview.setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();

        // Save symptoms to SharedPreferences when the activity is stopped
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedSymptoms", new Gson().toJson(symps));
        editor.apply();
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
                Intent i1 = new Intent(MySymptoms.this, DashBoard.class);
                startActivity(i1);
                break;
            case R.id.nav_edit:
                Intent i = new Intent(MySymptoms.this, Edit_profile.class);
                startActivity(i);
                break;
            case R.id.symptoms:
                Intent i3 = new Intent(MySymptoms.this, MySymptoms.class);
                startActivity(i3);
                break;
            case R.id.logout:
                Intent i2 = new Intent(MySymptoms.this, MainActivity.class);
                startActivity(i2);
                break;
        }
    }
}