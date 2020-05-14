package com.example.colourrush;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout CLayout;
    private int colours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CLayout = (ConstraintLayout) findViewById(R.id.CLayout);

    }

    public void learnOnClick(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        startActivity(intent);
    }

    public void quizOnClick(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    /**
     * Method to Randomly Generate an int to assign a colour to the background when clicked
     * @param view
     */
    public void bgOnClick(View view) {
        Random rnd = new Random();
        colours = rnd.nextInt(5)+1;
        switch(colours){
            case 1 : CLayout.setBackgroundColor(Color.rgb(255,73,73));break;
            case 2 : CLayout.setBackgroundColor(Color.rgb(74,247,88));break;
            case 3 : CLayout.setBackgroundColor(Color.rgb(74,140,247));break;
            case 4 : CLayout.setBackgroundColor(Color.rgb(255,196,79));break;
            default : CLayout.setBackgroundColor(Color.rgb(204,204,204));break;
        }
    }
}
