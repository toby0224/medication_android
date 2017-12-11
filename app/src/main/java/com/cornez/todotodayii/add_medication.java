package com.cornez.todotodayii;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class add_medication extends Activity {


    private Button btnAdd;
    private EditText etMedName;
    private EditText etDosage;
    private EditText etUnits;
    private EditText etFrequency;
    private EditText etStartDate;
    private EditText etEndDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);




    }


}

/*
        btnAdd = (Button) findViewById(R.id.btnAdd);
        etMedName = (EditText) findViewById(R.id.etMedName);
        etDosage = (EditText) findViewById(R.id.etDosage);
        etUnits = (EditText) findViewById(R.id.etUnits);
        etFrequency = (EditText) findViewById(R.id.etFrequency);
        etStartDate = (EditText) findViewById(R.id.etStartDate);
        etEndDate = (EditText) findViewById(R.id.etEndDate);
*/
