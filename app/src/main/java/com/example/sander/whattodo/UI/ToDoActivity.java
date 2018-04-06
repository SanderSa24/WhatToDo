package com.example.sander.whattodo.UI;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sander.whattodo.DB.DataSource;
import com.example.sander.whattodo.DB.ToDo;
import com.example.sander.whattodo.R;

public class ToDoActivity extends AppCompatActivity implements ToDoAdapter.OnToDoClickListener {



    private ToDoAdapter mAdapter;
    private Cursor mCursor;
    private RecyclerView mRecyclerView;
    private DataSource mDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the RecyclerView
        mRecyclerView = findViewById(R.id.todoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(100L);
        itemAnimator.setRemoveDuration(100L);
        mRecyclerView.setItemAnimator(itemAnimator);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


        mDataSource = new DataSource(this);
        mDataSource.open();

        updateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity();
            }
        });
    }




    private void updateUI() {
        mCursor =  mDataSource.findAll();
        if (mAdapter == null) {
            mAdapter = new ToDoAdapter (mCursor, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swapCursor(mCursor);
        }
    }


    protected void onResume() {
        super.onResume();
        mDataSource.open();
        updateUI();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mCursor != null && !mCursor.isClosed()) mCursor.close();
        mDataSource.close();
    }

    private void updateActivity(ToDo todo) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("game", todo);
        intent.setAction(Intent.ACTION_EDIT);
        startActivity(intent);
    }

    private void addActivity() {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.setAction(Intent.ACTION_INSERT);
        startActivity(intent);
    }


    @Override
    public void onToDoClick(ToDo todo) {
        updateActivity (todo);
    }


    private ItemTouchHelper.SimpleCallback itemTouchCallback() {
        return new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // unused
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Get the id of the game object on which we performed the swipe operation
                ToDo todo = ((ToDoAdapter.ToDoViewHolder) viewHolder).getToDo();
                // Delete the game object from our database
                mDataSource.delete(todo.getId());
                // Get a new cursor from our database
                Cursor cursor = mDataSource.findAll();
                mAdapter.swapCursor(cursor);
                mAdapter.notifyDataSetChanged();
                // Show a Toast message to inform the user that the game was deleted, note that we
                // are calling makeText from within an anonymous class so we have to explicitly tell
                // it to use GamesActivity.this instead of just this as that points to the anonymous
                // class
                Toast.makeText(ToDoActivity.this, R.string.message_activity_deleted, Toast.LENGTH_SHORT).show();
            }
        };
    }



}
