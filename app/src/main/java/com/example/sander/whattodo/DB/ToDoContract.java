package com.example.sander.whattodo.DB;

import android.provider.BaseColumns;


public final class ToDoContract {
    // Specifies the layout of the database
    private ToDoContract() {}

    /* Inner class that defines the table contents */
    public static class ToDoEntry implements BaseColumns {
        //Labels Table Columns names
        public static final String TABLE_NAME = "ToDos";
        public static final String COLUMN_NAME_ID= "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_INFO = "info";
    }
}

