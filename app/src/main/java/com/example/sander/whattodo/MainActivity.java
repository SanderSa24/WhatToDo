package com.example.sander.whattodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Local variables
    private int currentImageIndex = 0;
    private int[] mImageNames;
    private ImageView mImageView;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheckButton;
    private RadioGroup mGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNextButton = findViewById(R.id.nextButton);
        mPrevButton = findViewById(R.id.prevButton);
        mCheckButton = findViewById(R.id.checkButton);
        mImageView = findViewById(R.id.imageView);
        mGroup = findViewById(R.id.radioGroup);

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


        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = mGroup.getCheckedRadioButtonId();
                View radioButton = mGroup.findViewById(radioButtonID);
                int answerIndex = mGroup.indexOfChild(radioButton);
                checkAnswer(answerIndex);
            }


        });
    }

    private void checkAnswer(int answer) {
        String message;
        if (answer == currentImageIndex) {
            message = "Great";
        } else {
            message = "Wrong";
        }
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}

