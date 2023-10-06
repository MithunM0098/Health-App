package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
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
                        Intent i1=new Intent(DashBoard.this,DashBoard.class);
                        startActivity(i1);
                        break;
                    case R.id.nav_edit:
                        Intent i=new Intent(DashBoard.this,Edit_profile.class);
                        startActivity(i);
                        break;
                    case R.id.symptoms:
                        Toast.makeText(DashBoard.this, "Symptoms is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.logout:
                        Intent i2=new Intent(DashBoard.this,MainActivity.class);
                        startActivity(i2);
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });


    }
}