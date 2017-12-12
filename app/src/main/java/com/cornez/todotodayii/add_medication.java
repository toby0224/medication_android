package com.cornez.todotodayii;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class add_medication extends Fragment implements View.OnClickListener{

    protected DBHelper mDBHelper;
    private List<ToDo_Item> list;
    public MyAdapter adapt;
    private EditText myTask;

    Button btnAdd;
    EditText etMedName;
    EditText etDosage;
    EditText etUnits;
    EditText etFrequency;
    EditText etStartDate;
    EditText etEndDate;

    public static add_medication newInstance() {
        add_medication fragment = new add_medication();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_add_medication, container, false);

        //View v2 = inflater.inflate(R.layout.todo_item_fragment, container, false);

        //View v3 = inflater.inflate(R.layout.fragment_home, container, false);


        Button b = (Button) v.findViewById(R.id.btnAdd);
        b.setOnClickListener(this);

        // TASK 2: ESTABLISH REFERENCES TO THE UI
        //      ELEMENTS LOCATED ON THE LAYOUT
        myTask = (EditText) v.findViewById(R.id.etMedName);

        // TASK 3: SET UP THE DATABASE
        mDBHelper = new DBHelper(getActivity());
        /*
        list = mDBHelper.getAllTasks();
        adapt = new MyAdapter(this, R.layout.todo_item, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
        */

      //  list = mDBHelper.getAllTasks();
      //  adapt = new MyAdapter(getActivity(), R.layout.todo_item_fragment, list);
      //  ListView listTask = (ListView) getActivity().findViewById(R.id.listView1);
      //  listTask.setAdapter(adapt);

        list = mDBHelper.getAllTasks();
        adapt = new MyAdapter (this, R.layout.todo_item_fragment, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);

        return v;
    }


    //BUTTON CLICK EVENT FOR DELETING ALL TODO TASKS
    public void clearTasks(View view) {
        mDBHelper.clearAll(list);
        adapt.notifyDataSetChanged();
    }

    //BUTTON CLICK EVENT FOR DELETING THE FINISHED TODO TASKS
    public void deleteDone(View view) {
        mDBHelper.deleteSelected(list);
        adapt.notifyDataSetChanged();
    }

    //BUTTON CLICK EVENT FOR ADDING A TODO TASK
    //addTaskNow
    @Override
    public void onClick(View v) {
        String s = myTask.getText().toString();
        if (s.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "A TODO task must be entered.", Toast.LENGTH_SHORT).show();
        } else {

            //BUILD A NEW TASK ITEM AND ADD IT TO THE DATABASE
            ToDo_Item task = new ToDo_Item(s, 0);
            mDBHelper.addToDoItem(task);



            // CLEAR OUT THE TASK EDITVIEW
            myTask.setText("");

            // ADD THE TASK AND SET A NOTIFICATION OF CHANGES
            adapt.add(task);
            adapt.notifyDataSetChanged();


        }
    }




    //******************* ADAPTER ******************************
    private class MyAdapter extends ArrayAdapter<ToDo_Item> {
        Context context;
        List<ToDo_Item> taskList = new ArrayList<ToDo_Item>();

        public MyAdapter(Context c, int rId, List<ToDo_Item> objects) {
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

        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox isDoneChBx = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity()
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
