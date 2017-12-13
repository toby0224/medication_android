package com.cornez.todotodayii;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

public class add_medication extends Fragment implements View.OnClickListener {

    Button myButton;
    private Button btnAdd;
    private EditText etMedName;
    private EditText etFrequency_weekly;
    private EditText etDosage;
    private EditText etUnits;
    private EditText etFrequency;
    private EditText etStartDate;
    private EditText etEndDate;

    // properties
    private Context mContext;

    // db
    protected DBHelper mDBHelper;


    public static add_medication newInstance() {
        add_medication fragment = new add_medication();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mDBHelper = new DBHelper(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_add_medication, container, false);
        View v = inflater.inflate(R.layout.fragment_add_medication, container, false);

        // views
        myButton = v.findViewById(R.id.btnAdd);
        etMedName = v.findViewById(R.id.etMedName);
        etFrequency_weekly = v.findViewById(R.id.etFrequency_weekly);
        etDosage = v.findViewById(R.id.etDosage);
        etEndDate = v.findViewById(R.id.etEndDate);
        etStartDate = v.findViewById(R.id.etStartDate);
        etUnits = v.findViewById(R.id.etUnits);
        etFrequency = v.findViewById(R.id.etFrequency_weekly);

        //listeners
        myButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd)
            addTaskNow();
    }

    //BUTTON CLICK EVENT FOR ADDING A TODO TASK
    public void addTaskNow() {
        String s = etMedName.getText().toString();
        String s2 = etFrequency_weekly.getText().toString();

        if (s.isEmpty()) {
            Toast.makeText(mContext, "ALL FIELDS MUST BE FILLED.", Toast.LENGTH_SHORT).show();
        } else {
            //BUILD A NEW TASK ITEM AND ADD IT TO THE DATABASE
            ToDo_Item task = new ToDo_Item(s, 0);
//            ToDo_Item task2 = new ToDo_Item(s2, 0);

            mDBHelper.addToDoItem(task);
        //    mDBHelper.addToDoItem(task2);

            Toast.makeText(mContext, "Medication added", Toast.LENGTH_SHORT).show();

            // CLEAR FORM
            etMedName.setText("");
            etFrequency_weekly.setText("");
            etStartDate.setText("");
            etEndDate.setText("");
            etUnits.setText("");
            etFrequency.setText("");
            etDosage.setText("");
        }
    }
}
