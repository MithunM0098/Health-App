package com.example.health_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class vitals extends AppCompatActivity {

    TextInputLayout heightlayout,weightlayout,oxygenlayout,bplayout;
    TextInputEditText height,weight,oxygen,bp;
    Button submit;
    MyUserDeatilsDB db;
    private DrawerLayout drawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
        drawerLayout = findViewById(R.id.drawer_layout);
        db=new MyUserDeatilsDB(getApplicationContext());
        heightlayout=findViewById(R.id.heightlayout);
        weightlayout=findViewById(R.id.weightlayout);
        oxygenlayout=findViewById(R.id.oxygenlayout);
        bplayout=findViewById(R.id.bplayout);

        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        oxygen=findViewById(R.id.oxygen);
        bp=findViewById(R.id.bp);

        submit=findViewById(R.id.submit);
        getUserData();
        setupToolbarAndNavigationDrawer();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.UpdateData2("1",height.getText().toString().trim(),weight.getText().toString().trim(),oxygen.getText().toString().trim(),bp.getText().toString().trim());
                Intent i=new Intent(vitals.this,DashBoard.class);
                startActivity(i);
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
    public void getUserData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                height.setText(cursor.getString(8));
                weight.setText(cursor.getString(9));
                oxygen.setText(cursor.getString(10));
                bp.setText(cursor.getString(11));

            }
        }
    }

    private void handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent i1 = new Intent(vitals.this, DashBoard.class);
                startActivity(i1);
                break;
            case R.id.nav_edit:
                Intent i = new Intent(vitals.this, Edit_profile.class);
                startActivity(i);
                break;
            case R.id.symptoms:
                Toast.makeText(vitals.this, "Symptoms is Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent i2 = new Intent(vitals.this, MainActivity.class);
                startActivity(i2);
                break;
        }
    }
}
