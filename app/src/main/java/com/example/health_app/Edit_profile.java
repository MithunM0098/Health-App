package com.example.health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class Edit_profile extends AppCompatActivity {
    private TextInputLayout textInputLayoutDOB;
    TextInputLayout namelayout,agelay,addlay,doclay,contlay;
    TextInputEditText name,Age,address,doctor,contact;
    RadioGroup gender;
    RadioButton male,female;
    Button submit;
    private TextInputEditText textInputEditTextDOB;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    MyUserDeatilsDB db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db=new MyUserDeatilsDB(getApplicationContext());
        namelayout=findViewById(R.id.namelayout);
        name=findViewById(R.id.name);
        Age=findViewById(R.id.age);
        agelay=findViewById(R.id.agelayout);
        gender=findViewById(R.id.radioGroupGender);
        male=findViewById(R.id.radioButtonMale);
        female=findViewById(R.id.radioButtonFemale);
        address=findViewById(R.id.address);
        addlay=findViewById(R.id.addresslayout);
        contact=findViewById(R.id.contact);
        contlay=findViewById(R.id.contactlayout);
        doctor=findViewById(R.id.doctorname);
        doclay=findViewById(R.id.doctorlayout);

        textInputEditTextDOB = findViewById(R.id.textInputEditTextDOB);
        textInputLayoutDOB=findViewById(R.id.textInputLayoutDOB);
        submit=findViewById(R.id.submit);


        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        textInputLayoutDOB.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        getUserData();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = gender.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    // Get the data associated with the selected radio button
                    String selectedData = selectedRadioButton.getText().toString();

                    // Do something with the selected data (e.g., display it or use it in further processing)
                    // Toast.makeText(getApplicationContext(), "Selected Data: " + selectedData, Toast.LENGTH_SHORT).show();
                   // db.deleteAllData();
                    Cursor cursor = db.readAllData();
                    db.UpdateData("1",name.getText().toString().trim(), Integer.parseInt(Age.getText().toString().trim()),textInputEditTextDOB.getText().toString().trim(),selectedData,address.getText().toString().trim(),
                            contact.getText().toString().trim(),doctor.getText().toString().trim());
                }



                Intent i=new Intent(Edit_profile.this,DashBoard.class);
                startActivity(i);

            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String formattedDate = dateFormat.format(calendar.getTime());
                textInputEditTextDOB.setText(formattedDate);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()); // Optional: Set a max date
        datePickerDialog.show();
    }

    public void getUserData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                name.setText(cursor.getString(1));
                Age.setText(cursor.getString(2));
                textInputEditTextDOB.setText(cursor.getString(3));
                String gender = cursor.getString(4);
                if (gender.equals("Male")) {
                    male.setChecked(true);
                } else if (gender.equals("Female")) {
                    female.setChecked(true);
                }
                address.setText(cursor.getString(5));
                contact.setText(cursor.getString(6));
                doctor.setText(cursor.getString(7));
            }
        }
    }
}