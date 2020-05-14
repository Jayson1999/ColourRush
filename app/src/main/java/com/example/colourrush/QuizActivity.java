package com.example.colourrush;

import android.content.DialogInterface;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private int score = 0;
    private int round = 0;
    private int questions;
    private ImageView question;
    private EditText answer;
    private Button next;
    private String correctAns;
    private Button btnHint;
    private int counterHint = 0;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = (ImageView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        next = (Button) findViewById(R.id.next);
        btnHint = (Button) findViewById(R.id.hint);

        //Initializing TextToSpeech class
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
               //Setting language
                tts.setLanguage(Locale.UK);
            }
        });

        round = generateQuestion(round);

    }

    /**
     * Method to randomly generate an integer and pass on to setColour method to apply the question
     * @param round to get the no. of rounds played
     * @return round variable to get the no. of rounds played
     */
    public int generateQuestion(int round){
        Random rnd = new Random();
        questions = rnd.nextInt(10)+1;
        setColour(questions);
        round++;
        return round;
    }

    /**
     * Method to apply the question by setting colours and storing the correct answer
     * @param questions ramdom integer generated passed from previous method
     */
    public void setColour(int questions){
        switch(questions){
            case 1:question.setImageResource(R.drawable.blue);correctAns="blue";break;
            case 2:question.setImageResource(R.drawable.brown);correctAns="brown";break;
            case 3:question.setImageResource(R.drawable.gold);correctAns="gold";break;
            case 4:question.setImageResource(R.drawable.green);correctAns="green";break;
            case 5:question.setImageResource(R.drawable.grey);correctAns="grey";break;
            case 6:question.setImageResource(R.drawable.orange);correctAns="orange";break;
            case 7:question.setImageResource(R.drawable.pink);correctAns="pink";break;
            case 8:question.setImageResource(R.drawable.purple);correctAns="purple";break;
            case 9:question.setImageResource(R.drawable.red);correctAns="red";break;
            case 10:question.setImageResource(R.drawable.yellow);correctAns="yellow";break;
        }
    }

    /**
     * OnClick method of next question that will generate another new question
     * @param view
     */
    public void nextOnClick(View view) {
        counterHint = 0;  //Reset Hint Counter to 0
        //Reset Hint Button
        btnHint.setBackgroundResource(android.R.drawable.btn_default);
        btnHint.setText("Hint");
        //Check Answer typed in
        if(answer.getText().toString().toLowerCase().equals(correctAns)){
            score = score + 10;
            Toast.makeText(this,"Score + 10",Toast.LENGTH_SHORT).show();
        }
        else{
            score = score - 5;
            Toast.makeText(this,"Score - 5",Toast.LENGTH_SHORT).show();
        }
        answer.setText("");  //Clear EditText field
        round = generateQuestion(round);  //Regenerate a new question
        //At Round 10 "Next" will be replaced with "Finish" text
        if(round==10){
            next.setText("Finish");
            round++;
        }
        //After the final round, Alert Dialog box will pop out with user's score
        else if(round>10){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Your Score");
            if(score<=0){builder.setMessage("Seek a doctor!\n"+score);}
            else if(score<40){builder.setMessage("Seek a teacher!\n"+score);}
            else{builder.setMessage("" + score);}
            builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            builder.show();
        }
    }

    /**
     * onClick Method to allow user to get hint by reducing scores.
     * 1st click will get the 1st letter, 2nd & further clicks will play the voice.
     * @param view
     */
    public void hintOnClick(View view) {
        counterHint++;  //Increment Hint counter when clicked
        //To determine whether it's the first click
        if(counterHint==1) {
            //Provide hint
            String hint = correctAns.substring(0, 1);
            answer.setText(hint.toUpperCase());
            score = score - 3;
            Toast.makeText(this, "Score - 3", Toast.LENGTH_SHORT).show();
            //Set button to Speech Button
            btnHint.setBackgroundResource(R.drawable.speaker);
            btnHint.setText("");
        }
        else{
            //Speak the answer
            tts.speak(correctAns,TextToSpeech.QUEUE_FLUSH,null);
            score = score - 5;
            Toast.makeText(this, "Score - 5", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * onSaveInstanceState method to save important variable's value for state changes
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("question",questions);
        outState.putInt("round",round);
        outState.putInt("hint",counterHint);
        outState.putString("answer",correctAns);
    }

    /**
     * onRestoreInstanceState method to restore saved state after state changes
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questions = savedInstanceState.getInt("question");
        setColour(questions);
        correctAns = savedInstanceState.getString("answer");
        round = savedInstanceState.getInt("round");
        counterHint = savedInstanceState.getInt("hint");
        if(counterHint>=1){
            btnHint.setBackgroundResource(R.drawable.speaker);
            btnHint.setText("");
        }
    }
}
