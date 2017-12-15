package com.cornez.todotodayii;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class add_medication extends Fragment implements View.OnClickListener {

    Button myButton;
    private EditText etMedName;
    private EditText etFrequency_weekly;
    private EditText etDosage;
    private EditText etUnits;
    private EditText etStartDate;
    private EditText etFrequency_day;
    private EditText etEndDate;
    private EditText etTime;

    // properties
    private Context mContext;

    // db
    protected DBHelper mDBHelper;


    ArrayList<ToDo_Item> ToDo_Items;
    ListView listView;


    private static CustomAdapter adapter;



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
       // View v = inflater.inflate(R.layout.fragment_add_medication, container, false);
        View v = inflater.inflate(R.layout.fragment_add_medication, container, false);


        ToDo_Items= new ArrayList<>();


       // adapter= new CustomAdapter(dataModels,getActivity().getApplicationContext());

//        listView.setAdapter(adapter);

        // views
        myButton = v.findViewById(R.id.btnAdd);
        etMedName = v.findViewById(R.id.etMedName);

        etFrequency_weekly = v.findViewById(R.id.etFrequency_weekly);
        etFrequency_day = v.findViewById(R.id.etFrequency_day);
        etTime = v.findViewById(R.id.etTime);

        etDosage = v.findViewById(R.id.etDosage);
        etEndDate = v.findViewById(R.id.etEndDate);
        etStartDate = v.findViewById(R.id.etStartDate);
        etUnits = v.findViewById(R.id.etUnits);

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
        String s1 = etMedName.getText().toString();
        String s2 = etFrequency_weekly.getText().toString();
        String s3 = etFrequency_day.getText().toString();
        String s4 = etDosage.getText().toString();
        String s5 = etUnits.getText().toString();
        String s6 = etStartDate.getText().toString();
        String s7 = etEndDate.getText().toString();
        String s8 = etTime.getText().toString();


        //String s2 = etFrequency_weekly.getText().toString();

        if (s1.isEmpty()) {

            Toast.makeText(getActivity(), "Must Fill Everything!",
                    Toast.LENGTH_LONG).show();

        } else {
            //BUILD A NEW TASK ITEM AND ADD IT TO THE DATABASE
           // ToDo_Item task = new ToDo_Item(s,0);

            ToDo_Items.add(new ToDo_Item(s1,"",0,s2,s3,s4,s5,s6,s7,s8));

//            ToDo_Item task2 = new ToDo_Item(s2, 0);

        //    mDBHelper.addToDoItem(task2);

            Toast.makeText(getActivity(), "Medication Added!",
                    Toast.LENGTH_LONG).show();

            // CLEAR FORM
            etMedName.setText("");
            etFrequency_weekly.setText("");
            etStartDate.setText("");
            etEndDate.setText("");
            etUnits.setText("");
            etFrequency_weekly.setText("");
            etDosage.setText("");
        }
    }

    public void snackBarNow(View v) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ToDo_Item dataModel= ToDo_Items.get(position);

                Snackbar.make(v, dataModel.get_med_id()+"\n"+dataModel.getDay()+" API: "+dataModel.get_med_id(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

}
