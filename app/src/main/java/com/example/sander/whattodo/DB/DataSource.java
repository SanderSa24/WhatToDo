package com.example.sander.whattodo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] ToDo_ALL_COLUMNS = {
            ToDoContract.ToDoEntry.COLUMN_NAME_ID,
            ToDoContract.ToDoEntry.COLUMN_NAME_TITLE,
            ToDoContract.ToDoEntry.COLUMN_NAME_TYPE,
            ToDoContract.ToDoEntry.COLUMN_NAME_INFO};


    // Opens the database to use it
    public void open()  {
        mDatabase = mDBHelper.getWritableDatabase();
    }
    // Closes the database when you no longer need it
    public void close() {
        mDBHelper.close();
    }
    //Constructor
    public DataSource(Context context) {
        mContext = context;
        mDBHelper = new DBHelper(mContext);
    }
    //Save an object in the database
    public void save(ToDo todo) {
        ContentValues values = new ContentValues();
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE, todo.getTitle());
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TYPE, todo.getType());
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_INFO, todo.getInfo());
        // Inserting Row
        mDatabase.insert(ToDoContract.ToDoEntry.TABLE_NAME, null, values);
        mDatabase.close();
    }

    public void update(int id, ToDo todo) {

        ContentValues values = new ContentValues();
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE, todo.getTitle());
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TYPE, todo.getType());
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_INFO, todo.getInfo());

        mDatabase.update(ToDoContract.ToDoEntry.TABLE_NAME, values, ToDoContract.ToDoEntry.COLUMN_NAME_ID + "= ?", new String[]{String.valueOf(id)});
        mDatabase.close(); // Closing mDatabase connection
    }


    /**
     * Finds all game objects.
     *
     * @return a cursor holding the game objects.
     */
    public Cursor findAll() {
        return mDatabase.query(ToDoContract.ToDoEntry.TABLE_NAME, ToDo_ALL_COLUMNS, null, null, null, null, null);
    }

    /**
     * Delete a single entity from the mDatabase.
     *
     * @param id the id of the entity to be deleted.
     */
    public void delete(int id) {

        mDatabase.delete(ToDoContract.ToDoEntry.TABLE_NAME, ToDoContract.ToDoEntry.COLUMN_NAME_ID + " =?",
                new String[]{Integer.toString(id)});

    }

}
