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


public class ToDo_Item extends Fragment implements View.OnClickListener{
    protected DBHelper mDBHelper;
    private List<ToDo_Item> list;
    private MyAdapter adapt;
    Button btn_rem;
    ImageButton btnGetInfo;


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
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        // SET UP THE DATABASE
        mDBHelper = new DBHelper(getActivity());
        list = mDBHelper.getAllTasks();
        adapt = new MyAdapter(getActivity(), R.layout.todo_item_fragment, list);
        ListView listView = v.findViewById(R.id.listView1);
        listView.setAdapter(adapt);

        btn_rem = v.findViewById(R.id.btn_rem);
        btn_rem.setOnClickListener(this);

       // btnGetInfo = v.findViewById(R.id.btnGetInfo);
       // btnGetInfo.setOnClickListener(this);


        return v;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_rem)
            deleteDone();

       // if (v.getId() == R.id.btnGetInfo)
          //  GetInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapt.notifyDataSetChanged();
    }

    //BUTTON CLICK EVENT FOR DELETING ALL TODO TASKS
    public void clearTasks(View view) {
        mDBHelper.clearAll(list);
        adapt.notifyDataSetChanged();
        //
    }

    //BUTTON CLICK EVENT FOR DELETING THE FINISHED TODO TASKS
    public void deleteDone() {
        mDBHelper.deleteSelected(list);
        adapt.notifyDataSetChanged();
    }

    //BUTTON CLICK EVENT FOR GET INFO
    public void GetInfo() {



        String s_test2 = "";
        Log.v("SQL output",s_test2);
    }



    //******************* ADAPTER ******************************
    private class MyAdapter extends ArrayAdapter<ToDo_Item> {
        Context context;
        List<ToDo_Item> taskList = new ArrayList<>();

        MyAdapter(Context c, int rId, List<ToDo_Item> objects) {
            super(c, rId, objects);
            taskList = objects;
            context = c;
        }

        //******************* TODO TASK ITEM VIEW ******************************

        /**
         * THIS METHOD DEFINES THE TODO ITEM THAT WILL BE PLACED
         * INSIDE THE LIST VIEW.
         * <p>
         * THE CHECKBOX STATE IS THE IS_DONE STATUS OF THE TODO TASK
         * AND THE CHECKBOX TEXT IS THE TODO_ITEM TASK DESCRIPTION.
         */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox isDoneChBx = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.todo_item_fragment, parent, false);

                isDoneChBx = (CheckBox) convertView.findViewById(R.id.chkStatus);
                convertView.setTag(isDoneChBx);

                isDoneChBx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox cb = (CheckBox) view;
                        ToDo_Item changeTask = (ToDo_Item) cb.getTag();
                        changeTask.setIs_done(cb.isChecked() == true ? 1 : 0);
                        mDBHelper.updateTask(changeTask);
                    }
                });
            } else {
                isDoneChBx = (CheckBox) convertView.getTag();
            }
            ToDo_Item current = taskList.get(position);
            isDoneChBx.setText(current.getDescription());
            isDoneChBx.setChecked(current.getIs_done() == 1 ? true : false);
            isDoneChBx.setTag(current);
            return convertView;
        }

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
