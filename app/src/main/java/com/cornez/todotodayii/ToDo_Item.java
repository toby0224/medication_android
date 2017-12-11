package com.cornez.todotodayii;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

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


}
