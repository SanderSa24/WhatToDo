package com.example.sander.whattodo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todos.db";
    public static final int DATABASE_VERSION = 1;

    // Creating the table
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + ToDoContract.ToDoEntry.TABLE_NAME +
                    "(" +
                    ToDoContract.ToDoEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + ToDoContract.ToDoEntry.COLUMN_NAME_TITLE + " TEXT, "
                    + ToDoContract.ToDoEntry.COLUMN_NAME_TYPE + " TEXT, "
                    + ToDoContract.ToDoEntry.COLUMN_NAME_INFO + " TEXT )";


    //Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ToDoContract.ToDoEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
