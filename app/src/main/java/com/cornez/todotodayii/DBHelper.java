package com.cornez.todotodayii;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class DBHelper extends SQLiteOpenHelper {
    //TASK 1: DEFINE THE DATABASE AND TABLE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "toDo_Today";
    private static final String DATABASE_TABLE = "toDo_Items";


    //TASK 2: DEFINE THE COLUMN NAMES FOR THE TABLE
    private static final String KEY_TASK_ID = "med_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IS_DONE = "is_done";
    private static final String KEY_FREQUENCY_WEEK = "frequency_week";
    private static final String KEY_FREQUENCY_DAY = "frequency_day";
    private static final String KEY_DOSAGE = "dosage";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_END_DATE = "end_date";
    private static final String KEY_TIME = "time";
    private static final String KEY_UNITS = "units";






    private int taskCount;

    public DBHelper (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_IS_DONE + " INTEGER"
                + KEY_FREQUENCY_WEEK + " STRING"
                + KEY_FREQUENCY_DAY + " STRING"
                + KEY_DOSAGE + " STRING"
                + KEY_UNITS + " STRING"
                + KEY_START_DATE + " STRING"
                + KEY_END_DATE + " STRING"
                + KEY_TIME + " STRING"
                + KEY_UNITS + " STRING"
                 + ")";

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // DROP OLDER TABLE IF EXISTS
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // CREATE TABLE AGAIN
        onCreate(database);
    }


    //********** DATABASE OPERATIONS:  ADD, EDIT, DELETE
    // Adding new task
    public void addToDoItem(ToDo_Item task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(KEY_DESCRIPTION, task.getDescription()); // task name

        //ADD KEY-VALUE PAIR INFORMATION FOR
        //IS_DONE VALUE: 0- NOT DONE, 1 - IS DONE
        values.put(KEY_IS_DONE, task.getIs_done());

        // INSERT THE ROW IN THE TABLE
        db.insert(DATABASE_TABLE, null, values);
        taskCount++;

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<ToDo_Item> getAllTasks() {

        //GET ALL THE TASK ITEMS ON THE LIST
        List<ToDo_Item> todoList = new ArrayList<ToDo_Item>();

        //SELECT ALL QUER
        // Y FROM THE TABLE
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // LOOP THROUGH THE TODO TASKS
        if (cursor.moveToFirst()) {
            do {
                ToDo_Item task = new ToDo_Item();
                task.set_med_id(cursor.getString(0));
                task.setDescription(cursor.getString(1));
                task.setIs_done(cursor.getInt(2));
                task.setWeek(cursor.getString(0));
                task.setDay(cursor.getString(0));
                task.setDose(cursor.getString(0));
                task.setStartDate(cursor.getString(0));
                task.setEndDate(cursor.getString(0));
                task.setTime(cursor.getString(0));
                task.setUnits(cursor.getString(0));
                todoList.add(task);
            } while (cursor.moveToNext());
        }

        // RETURN THE LIST OF TASKS FROM THE TABLE
        return todoList;
    }

    public void clearAll(List<ToDo_Item> list) {
        //GET ALL THE LIST TASK ITEMS AND CLEAR THEM
        list.clear();

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, new String[]{});
        db.close();
    }

    public void deleteSelected(List<ToDo_Item> list) {

        for(Iterator<ToDo_Item> i=list.iterator() ; i.hasNext();){
            ToDo_Item item=i.next();
            if(item.getIs_done()==1) i.remove();
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_IS_DONE+"=1", new String[]{});
        db.close();
    }

    public void updateTask(ToDo_Item task) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_IS_DONE, task.getIs_done());
        db.update(DATABASE_TABLE, values, KEY_TASK_ID + " = ?", new String[]{String.valueOf(task.get_med_id())});
        db.close();
    }

}
