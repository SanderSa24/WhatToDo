package com.example.sander.whattodo.UI;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sander.whattodo.DB.DataSource;
import com.example.sander.whattodo.DB.ToDo;
import com.example.sander.whattodo.MainActivity;
import com.example.sander.whattodo.R;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private ToDo todo;
    private ArrayAdapter typeAdapter;
    private EditText titleInput;
    private EditText infoInput;
    private Spinner typeSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Find all our views from within our layout
        titleInput = findViewById(R.id.inputActivityTitle);
        infoInput = findViewById(R.id.inputActivityInfo);
        typeSpinner = findViewById(R.id.spinnerActivityType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        typeAdapter = ArrayAdapter.createFromResource(this, R.array.activity_type,
                R.layout.support_simple_spinner_dropdown_item);
        // Set the adapter to the spinner
        typeSpinner.setAdapter(typeAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // Based on the action we will add or update a activity
        if (Objects.equals(getIntent().getAction(), Intent.ACTION_INSERT)) {
            // We are adding a new activity
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveActivity();
                }
            });
        } else {
            // We are updating an existing activity so start by retrieving it from the intent
            todo = (ToDo) getIntent().getSerializableExtra("activity");
            // Set the values for the views
            titleInput.setText(todo.getTitle());
            infoInput.setText(todo.getInfo());
            // Get the position of the activity status within the adapter
            int spinnerPosition = typeAdapter.getPosition(todo.getType());
            typeSpinner.setSelection(spinnerPosition);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateActivity();
                }
            });
        }

    }


    public void saveActivity() {
        String title = titleInput.getText().toString();
        String info = infoInput.getText().toString();
        String activityType = typeSpinner.getSelectedItem().toString();

        if (title.isEmpty()) {
            titleInput.setError(getString(R.string.error_activity_add_title_required));
        }  else {
            ToDo todo = new ToDo(title, activityType, info);

            DataSource dataSource = new DataSource(this);
            dataSource.open();
            dataSource.save(todo);

            Toast.makeText(this, R.string.message_activity_saved, Toast.LENGTH_LONG).show();

            finish();
        }
    }

    private void updateActivity() {
        // Get the input from the Views
        String title = titleInput.getText().toString();
        String info = infoInput.getText().toString();
        String activityType = typeSpinner.getSelectedItem().toString();

        // Validate that the title and platform is not empty

        if (title.isEmpty()) {
            titleInput.setError(getString(R.string.error_activity_add_title_required));
        }  else {
            ToDo updatedToDo = new ToDo(title, activityType, info);

            DataSource dataSource = new DataSource(this);
            dataSource.open();
            dataSource.update(todo.getId(), updatedToDo);

            Toast.makeText(this, R.string.message_activity_modified, Toast.LENGTH_LONG).show();

            finish();
        }
    }


}
