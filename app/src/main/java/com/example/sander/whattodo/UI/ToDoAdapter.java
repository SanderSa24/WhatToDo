package com.example.sander.whattodo.UI;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sander.whattodo.DB.ToDo;
import com.example.sander.whattodo.DB.ToDoContract;
import com.example.sander.whattodo.R;


public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private Cursor cursor;
    private OnToDoClickListener onToDoClickListener;

    public interface OnToDoClickListener {
        void onToDoClick(ToDo todo);
    }


    public ToDoAdapter(Cursor cursor, OnToDoClickListener onToDoClickListener) {
        this.cursor = cursor;
        this.onToDoClickListener = onToDoClickListener;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate our item_activity layout
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        // Instantiate a ToDoViewHolder and pass our layout as it's view
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        // Move the cursor to the right position
        cursor.moveToPosition(position);
        // Create a activity object from the cursor's data
        ToDo todo = new ToDo();
        todo.setId(cursor.getInt(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_ID)));
        todo.setTitle(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE)));
        todo.setType(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_TYPE)));
        todo.setInfo(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_INFO)));
        // Bind the activity object to the view
        holder.bind(todo);
    }

    @Override
    public int getItemCount() {
        return (cursor == null ? 0 : cursor.getCount());
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }



    /**
     * A wrapper class representing a single view or row within our RecyclerView. The ViewHolder
     * holds a reference to all the views and the activity object.
     */
    class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ToDo todo;
        private final TextView title;
        private final TextView type;

        ToDoViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.activityTitle);
            type = view.findViewById(R.id.activityType);
            view.setOnClickListener(this);

        }

        public ToDo getToDo() {
            return todo;
        }

        void bind(final ToDo todo) {
            this.todo = todo;
            title.setText(todo.getTitle());
            type.setText(todo.getType());

        }

        @Override
        public void onClick(View view) {
            // Move the cursor to the right position
            cursor.moveToPosition( getAdapterPosition());
            // Create a activity object from the cursor's data
            ToDo todo = new ToDo();
            todo.setId(cursor.getInt(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_ID)));
            todo.setTitle(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE)));
            todo.setInfo(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_INFO)));
            todo.setType(cursor.getString(cursor.getColumnIndex(ToDoContract.ToDoEntry.COLUMN_NAME_TYPE)));
            onToDoClickListener.onToDoClick(todo);
        }
    }
}


