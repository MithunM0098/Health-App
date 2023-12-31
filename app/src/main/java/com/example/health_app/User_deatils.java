package com.example.health_app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    static int count=0;
    private SharedPreferences sharedPreferences;
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
                                          if (validateInputs()) {
                                              int selectedRadioButtonId = gender.getCheckedRadioButtonId();

                                              if (selectedRadioButtonId != -1) {
                                                  RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                                                  // Get the data associated with the selected radio button
                                                  String selectedData = selectedRadioButton.getText().toString();

                                                  // Do something with the selected data (e.g., display it or use it in further processing)
                                                  // Toast.makeText(getApplicationContext(), "Selected Data: " + selectedData, Toast.LENGTH_SHORT).show();
                                                  db.deleteAllData();
                                                  db.addDetails(name.getText().toString().trim(), Integer.parseInt(Age.getText().toString().trim()), textInputEditTextDOB.getText().toString().trim(), selectedData, address.getText().toString().trim(),
                                                          contact.getText().toString().trim(), doctor.getText().toString().trim(), height.getText().toString().trim(), weight.getText().toString().trim(),
                                                          oxygen.getText().toString().trim(), bp.getText().toString().trim());
                                              }


                                              sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                                              count = sharedPreferences.getInt("count", 0);
                                              count = count + 1;
                                              SharedPreferences.Editor editor = sharedPreferences.edit();
                                              editor.putInt("count", count);
                                              editor.apply();
                                              System.out.println("count is" + count);
                                              // In DashBoard activity
                                              Intent intent = new Intent();
                                              intent.putExtra("countValue", count);
                                              setResult(RESULT_OK, intent);
                                              finish();

                                              Intent i = new Intent(User_deatils.this, DashBoard.class);
                                              startActivity(i);
                                          }
                                      }
        });

    }

    private boolean validateInputs() {
        // Validate name
        if (name.getText().toString().trim().isEmpty()) {
            name.setError("Name cannot be empty");
            return false;
        }

        // Validate age
        if (Age.getText().toString().trim().isEmpty()) {
            Age.setError("Age cannot be empty");
            return false;
        }

        // Validate Date of Birth
        if (textInputEditTextDOB.getText().toString().trim().isEmpty()) {
            textInputEditTextDOB.setError("Date of Birth cannot be empty");
            return false;
        }

        // Validate gender
        if (gender.getCheckedRadioButtonId() == -1) {
            textInputEditTextDOB.setError("Please select gender");
            return false;
        }

        // Add similar checks for other fields as needed
        if(address.getText().toString().isEmpty()){
            address.setError("Address cannot be empty");
            return false;
        }
        if(contact.getText().toString().isEmpty()){
            contact.setError("mobile number cannot be empty");
            return false;
        } else if(contact.getText().toString().trim().length()<10){
            contact.setError("mobile number too short");
            return false;
        }else if(contact.getText().toString().trim().length()>10){
            contact.setError("mobile number too large");
            return false;
        }
        return true;
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
