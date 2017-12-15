package com.cornez.todotodayii;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ToDo_Item extends Fragment{



    public static ToDo_Item newInstance() {
        ToDo_Item fragment = new ToDo_Item();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.row_item, container, false);

        // SET UP THE DATABASE
       // adapt = new MyAdapter(getActivity(), R.layout.fragment_home, list);
       // ListView listView = v.findViewById(R.id.listView1);
      //  listView.setAdapter(adapt);

 //       btn_rem = v.findViewById(R.id.btn_rem);
//        btn_rem.setOnClickListener(this);

       // btnGetInfo = v.findViewById(R.id.btnGetInfo);
       // btnGetInfo.setOnClickListener(this);


        return v;
    }

/*
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_rem)
            deleteDone();

       // if (v.getId() == R.id.btnGetInfo)
          //  GetInfo();
    }

    */

    @Override
    public void onResume() {
        super.onResume();
    }












    //medication name
    String med_id;
    //
    String dose;
    //
    String units;
    //
    String freq_week;
    //
    String freq_day;

    String startDate;

    String endDate;

    String time;



    private String description;
    private int is_done;


    public ToDo_Item() {
    }

    @SuppressLint("ValidFragment")
    public ToDo_Item(String med_id3,String description3,int is_done3,String freq_week3,String freq_day3, String dose3,String startDate3, String endDate3, String time3, String units3) {
        med_id=med_id3;
        description=description3;
        is_done=is_done3;
        freq_week=freq_week3;
        freq_day=freq_day3;
        dose=dose3;
        startDate=startDate3;
        endDate=endDate3;
        time=time3;
        units=units3;


    }

    //getters and setters

    public String get_med_id() {
        return med_id;
    }

    public void set_med_id(String medid2) {
        med_id = medid2;
    }


    public String getDose() {
        return dose;
    }

    public void setDose(String dose2) {
        dose = dose2;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate2) {
        startDate = startDate2;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate2) {
        endDate = endDate2;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time2) {
        time = time2;
    }


    public String getUnits() {
        return units;
    }

    public void setUnits(String units2) {
        units = units2;
    }


    public String getWeek() {
        return freq_week;
    }

    public void setWeek(String week2) {
        freq_week = week2;
    }

    public String getDay() {
        return freq_day;
    }

    public void setDay(String day2) {
        freq_day = day2;
    }

    public int getIs_done() {
        return is_done;
    }
    public void setIs_done(int done) {
        is_done = done;
    }

    public String getDescription () {
        return description;
    }
    public void setDescription (String desc) {
        description = desc;
    }




























    /*


    //MEMBER ATTRIBUTES
    private int _id;
    private String description;
    private int is_done;

    public ToDo_Item() {
    }

    @SuppressLint("ValidFragment")
    public ToDo_Item(String desc, int done) {
        description = desc;
        is_done = done;
    }

    public int getId2() {
        return _id;
    }
    public void setId(int id) {
        _id = id;
    }

    public String getDescription () {
        return description;
    }
    public void setDescription (String desc) {
        description = desc;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int done) {
        is_done = done;
    }
*/

}
