package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(DashBoard.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_message:
                        Toast.makeText(DashBoard.this, "Message is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.synch:
                        Toast.makeText(DashBoard.this, "Synch is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.trash:
                        Toast.makeText(DashBoard.this, "Trash is Clicked",Toast.LENGTH_SHORT).show();break;

                    default:
                        return true;

                }
                return true;
            }
        });


    }
}