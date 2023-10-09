package com.example.health_app;

import static com.example.health_app.User_deatils.count;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button logins;
int REQUEST_CODE=1;
    int countValue=0;
    SharedPreferences sharedPreferences;

    // In User_details activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            countValue = data.getIntExtra("countValue", 0);
            // Do something with countValue
            System.out.println("count is in main" + countValue);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logins=findViewById(R.id.logins);

        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        countValue = sharedPreferences.getInt("count", 0);
        if(countValue==0){
            logins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(MainActivity.this,User_deatils.class);
                    startActivityForResult(i, REQUEST_CODE);
                }
            });
        }else{
            logins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(MainActivity.this,DashBoard.class);
                    startActivity(i);
                }
            });
        }


        System.out.println("count is in main" + countValue);

    }


}