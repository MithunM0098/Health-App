package com.example.health_app;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.health_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class User_deatils extends AppCompatActivity {

    private TextInputLayout textInputLayoutDOB;
    TextInputLayout namelayout,agelay,addlay,doclay,contlay,heilay,bplay,oxylay,weilay;
    TextInputEditText name,Age,address,doctor,contact,height,weight,bp,oxygen;
    RadioGroup gender;
    RadioButton male,female;
    Button submit;
    private TextInputEditText textInputEditTextDOB;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    MyUserDeatilsDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_deatils);

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
        height=findViewById(R.id.height);
        heilay=findViewById(R.id.heightlayout);
        doctor=findViewById(R.id.doctorname);
        doclay=findViewById(R.id.doctorlayout);
        weight=findViewById(R.id.weight);
        weilay=findViewById(R.id.weightlayout);
        bp=findViewById(R.id.bp);
        bplay=findViewById(R.id.bplayout);
        oxylay=findViewById(R.id.oxygens);
        oxygen=findViewById(R.id.oxygenlayout);
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

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          int selectedRadioButtonId = gender.getCheckedRadioButtonId();

                                          if (selectedRadioButtonId != -1) {
                                              RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                                              // Get the data associated with the selected radio button
                                              String selectedData = selectedRadioButton.getText().toString();

                                              // Do something with the selected data (e.g., display it or use it in further processing)
                                             // Toast.makeText(getApplicationContext(), "Selected Data: " + selectedData, Toast.LENGTH_SHORT).show();
                                              db.addDetails(name.getText().toString().trim(), Integer.parseInt(Age.getText().toString().trim()),textInputEditTextDOB.getText().toString().trim(),selectedData,address.getText().toString().trim(),
                                                      contact.getText().toString().trim(),doctor.getText().toString().trim(),height.getText().toString().trim(),weight.getText().toString().trim(),
                                                      oxygen.getText().toString().trim(),bp.getText().toString().trim());
                                          }




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
}
