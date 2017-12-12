package com.cornez.todotodayii;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class add_medication extends Fragment implements View.OnClickListener {
    Button myButton;

        private Button btnAdd;
    private EditText etMedName;
    private EditText etDosage;
    private EditText etUnits;
    private EditText etFrequency;
    private EditText etStartDate;
    private EditText etEndDate;

    public static add_medication newInstance() {
        add_medication fragment = new add_medication();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_add_medication, container, false);


        View v = inflater.inflate(R.layout.fragment_add_medication, container, false);

        myButton = (Button) v.findViewById(R.id.btnAdd);
        myButton.setOnClickListener(this);
        return v;


    }

    @Override
    public void onClick(View v) {
        Context mContext = getActivity();
        ((MainActivity)mContext).addTaskNow();

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
