package com.cornez.todotodayii;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    protected DBHelper mDBHelper;
    private List<ToDo_Item> list;
    private MyAdapter adapt;
    private EditText myTask;

    private Button btnAddMed;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTask = (EditText) findViewById(R.id.etMedName);

        // SET UP THE DATABASE
        mDBHelper = new DBHelper(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = ToDo_Item.newInstance();
                                break;
                            case R.id.navigation_add_med:
                                selectedFragment = add_medication.newInstance();
                                break;
                            case R.id.navigation_about:
                                selectedFragment = add_medication.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ToDo_Item.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);




        /*
        list = mDBHelper.getAllTasks();
        adapt = new MyAdapter(this, R.layout.todo_item_fragment, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
        */

    }

    @Override
    protected void onResume(){
        super.onResume();
        list = mDBHelper.getAllTasks();
        adapt = new MainActivity.MyAdapter(this, R.layout.todo_item_fragment, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
    }


    //BUTTON CLICK EVENT FOR ADDING A TODO TASK
    public void addTaskNow() {
        Log.v("Button 2", "Was clicked" );

        String s = myTask.getText().toString();
        if (s.isEmpty()) {
            Toast.makeText(getApplicationContext(), "A TODO task must be entered.", Toast.LENGTH_SHORT).show();
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



    //BUTTON CLICK EVENT FOR DELETING ALL TODO TASKS
    public void clearTasks(View view) {
        mDBHelper.clearAll(list);
        adapt.notifyDataSetChanged();
        //
    }

    //BUTTON CLICK EVENT FOR DELETING THE FINISHED TODO TASKS
    public void deleteDone(View view) {
        mDBHelper.deleteSelected(list);
        adapt.notifyDataSetChanged();
    }



    // BUTTON CLICK EVENT FOR ADD_MEDICATION
    public void addMedicationScreen(View view) {
        Intent addMedScreenIntent = new Intent(this, add_medication.class);
        startActivity(addMedScreenIntent);

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
         *
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
