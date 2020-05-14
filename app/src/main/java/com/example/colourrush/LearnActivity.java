package com.example.colourrush;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class LearnActivity extends AppCompatActivity {

    private TextView ColourTV;
    private ConstraintLayout DescL;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        ColourTV = (TextView)findViewById(R.id.ColourTV);
        DescL =(ConstraintLayout)findViewById(R.id.DescL);

        //Initializing TextToSpeech class
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //Setting the voie language as UK English
                tts.setLanguage(Locale.UK);
            }
        });
    }

    public void redOnClick(View view) {
        DescL.setBackgroundColor(Color.RED);
        ColourTV.setText("Red");
        tts.speak("Red",TextToSpeech.QUEUE_FLUSH,null);    //tts speaks Colours based on language set
    }

    public void greenOnClick(View view) {
        DescL.setBackgroundColor(Color.GREEN);
        ColourTV.setText("Green");
        tts.speak("Green",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void blueOnClick(View view) {
        DescL.setBackgroundColor(Color.BLUE);
        ColourTV.setText("Blue");
        tts.speak("Blue",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void yellowOnClick(View view) {
        DescL.setBackgroundColor(Color.YELLOW);
        ColourTV.setText("Yellow");
        tts.speak("Yellow",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void orangeOnClick(View view) {
        DescL.setBackgroundColor(Color.rgb(255,111,22));
        ColourTV.setText("Orange");
        tts.speak("Orange",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void brownOnClick(View view) {
        DescL.setBackgroundColor(Color.rgb(175,95,15));
        ColourTV.setText("Brown");
        tts.speak("Brown",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void pinkOnClick(View view) {
        DescL.setBackgroundColor(Color.rgb(255,192,203));
        ColourTV.setText("Pink");
        tts.speak("Pink",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void purpleOnClick(View view) {
        DescL.setBackgroundColor(Color.rgb(128,0,128));
        ColourTV.setText("Purple");
        tts.speak("Purple",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void goldOnClick(View view) {
        DescL.setBackgroundColor(Color.rgb(255,165,0));
        ColourTV.setText("Gold");
        tts.speak("Gold",TextToSpeech.QUEUE_FLUSH,null);
    }

    public void greyOnClick(View view) {
        DescL.setBackgroundColor(Color.GRAY);
        ColourTV.setText("Grey");
        tts.speak("Grey",TextToSpeech.QUEUE_FLUSH,null);
    }
}
