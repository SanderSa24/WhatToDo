package com.example.sander.whattodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sander.whattodo.UI.ToDoActivity;
import com.example.sander.whattodo.UI.UpdateActivity;
import com.example.sander.whattodo.R;

public class MainActivity extends AppCompatActivity {
    //Local variables
    private int currentImageIndex = 0;
    private int[] mImageNames;
    private ImageView mImageView;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextButton = findViewById(R.id.nextButton);
        mPrevButton = findViewById(R.id.prevButton);
        mAddButton = findViewById(R.id.addButton);
        mImageView = findViewById(R.id.imageView);

        mImageNames = new int[]{R.drawable.games, R.drawable.gym, R.drawable.movie,R.drawable.pub,R.drawable.reading,R.drawable.youtube};

        // Define what happens when the user clicks the "next image" button
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex++;
                if (currentImageIndex >= mImageNames.length) {
                    currentImageIndex = 0;
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex--;

                if (currentImageIndex < 0) {
                    currentImageIndex = 2;
                }
                mImageView.setImageResource(mImageNames[currentImageIndex]);
            }

        });


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ToDoActivity.class));

            }


        });
    }




}

