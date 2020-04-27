package com.example.seniorcitizen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Button pickdate ,calculate ,enddate;
    private int year;
    private int month , inmale=0,infemale=0 , seniormale=21900 , seniorfemale=18980;
    private int dayOfMonth;
    private String date1;
    private String date2;
    private TextView daterslt ,dateresult1;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar ,calendar1;
    private RadioGroup radioSexGroup;
    private RadioButton male ,female ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickdate=findViewById(R.id.pickdte);
        calculate=findViewById(R.id.calculate);
        daterslt=findViewById(R.id.datereslt);
        dateresult1=findViewById(R.id.datereslt1);
        enddate=findViewById(R.id.pickcurrent);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        male = (RadioButton)findViewById(R.id.radioButton);
        female = (RadioButton)findViewById(R.id.radioButton2);


        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(male.isChecked())
                {
                    Toast.makeText(MainActivity.this, "Male", Toast.LENGTH_SHORT).show();
                    inmale=1;
                }
                else if (female.isChecked()){
                    Toast.makeText(MainActivity.this, "Female", Toast.LENGTH_SHORT).show();
                    infemale=1;
                }
            }
        });



        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calendar = Calendar.getInstance();


                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                daterslt.setText("Your DOB -" +day + "/" + (month + 1) + "/" + year);
                                date1=(day + "/" + (month + 1) + "/" + year);
                                System.out.println("date1"+date1);



                            }
                        }, year, month, dayOfMonth);
               // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();


            }
        });

                enddate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateresult1.setText("");
                        calendar1 = Calendar.getInstance();


                        year = calendar1.get(Calendar.YEAR);
                        month = calendar1.get(Calendar.MONTH);
                        dayOfMonth = calendar1.get(Calendar.DAY_OF_MONTH);
                        datePickerDialog = new DatePickerDialog(MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                        dateresult1.setText("TO Date -"+day + "/" + (month + 1) + "/" + year);
                                        date2=(day + "/" + (month + 1) + "/" + year);
                                        System.out.println("date1"+date2);


                                    }
                                }, year, month, dayOfMonth);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        datePickerDialog.show();

                    }
                });


                calculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            if (inmale==1 || infemale==1) {
                                try {
                                Date chkdate1;
                                Date chkdate2;
                                SimpleDateFormat dates = new SimpleDateFormat("dd/mm/yyyy");

                                chkdate1 = dates.parse(String.valueOf(date1));

                                chkdate2 = dates.parse(String.valueOf(date2));

                                System.out.println("date1" + chkdate1);
                                System.out.println("date1" + chkdate2);


                                long difference = Math.abs(chkdate1.getTime() - chkdate2.getTime());
                                System.out.println("diff" + difference);

                                long differenceDates = difference / (24 * 60 * 60 * 1000);
                                String dayDifference = Long.toString(differenceDates);

                                //textView.setText("The difference between two dates is " + dayDifference + " days");
                                Toast.makeText(getApplicationContext(), dayDifference, Toast.LENGTH_LONG).show();



                                if (inmale==1 ){
                                    if (differenceDates>=seniormale){
                                        Intent intent = new Intent(MainActivity.this ,Senior.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Intent intent = new Intent(MainActivity.this ,Notsenior.class);
                                        startActivity(intent);
                                    }
                                    }
                                else if(infemale==1){
                                    if (differenceDates>=seniorfemale){
                                        Intent intent = new Intent(MainActivity.this ,Senior.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Intent intent = new Intent(MainActivity.this ,Notsenior.class);
                                        startActivity(intent);
                                    }

                                }


                            }

                                catch (Exception exception) {
                                    Toast.makeText(getApplicationContext(), "Unable to find difference\n Pick Dates", Toast.LENGTH_SHORT).show();
                                }}

                            else {
                                Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_LONG).show();
                            }



                     }

                });

       


        }


    }



