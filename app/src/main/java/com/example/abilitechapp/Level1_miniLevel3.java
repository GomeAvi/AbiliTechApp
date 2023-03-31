package com.example.abilitechapp;

import com.example.abilitechapp.Algorithm;
import com.example.abilitechapp.ColoringData;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.speech.RecognizerIntent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class Level1_miniLevel3 extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ImageView myVoiceBtn;

    TextView myTextTv;
    TextView wordText;
    private Button NextButton;
    Button instructionSpeaker;
    TextToSpeech tts;


    private TextView progressText;

    int count = 0;
    private int lvlPrecentge = 0;
    public static boolean levelComplteFlag = false;
    ArrayList<String> wordList = new ArrayList<String>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_mini_level3);

        wordList.add("Mom");
        wordList.add("Dad");
        wordList.add("Brother");
        wordList.add("Sister");
        wordList.add("Love");
        wordList.add("More");
        wordList.add("Eating");
        wordList.add("Fork");
        wordList.add("Food");
        wordList.add("Water");
        Collections.shuffle(wordList);
        wordList.add("Test");

        NextButton = findViewById(R.id.NextButton);
        ProgressBar progressBar = findViewById(R.id.progressBarMiniLvl);
        TextView progressText = findViewById(R.id.progressText3);

        if (count<9) {
            wordText = findViewById(R.id.instruText);
        }
        myTextTv = findViewById(R.id.tv_speech_to_text);
        myVoiceBtn = findViewById(R.id.iv_mic);

        wordText.setText(wordList.get(count));
        myVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


        Button BackButton = findViewById(R.id.backButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Level1_miniLevel3.this, SecondActivity.class);
                startActivity(intent);
            }
        });

            instructionSpeaker=(Button)findViewById(R.id.SpeakerImg);
            tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        tts.setLanguage(Locale.UK);
                    }
                }
            });

        NextButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (!levelComplteFlag) {
                count++;
                lvlPrecentge += 10;
                if (count < 10) {
                    wordText.setText(wordList.get(count));
                    AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setDuration(1000);
                    fadeOut.setFillBefore(true);
                    fadeOut.setFillAfter(false);
                    NextButton.startAnimation(fadeOut);
                    NextButton.setVisibility(View.INVISIBLE);
                    progressBar.setProgress(lvlPrecentge);
                    progressText.setText(lvlPrecentge + "%");
                    myTextTv.setVisibility(View.INVISIBLE);

                } else {
                    wordText.setVisibility(View.INVISIBLE);
                    myVoiceBtn.setVisibility(View.INVISIBLE);
                    instructionSpeaker.setVisibility(View.INVISIBLE);
                    levelComplteFlag = true;
                    NextButton.setText("Complete!");

                }
            }
            else {
                Intent intent = new Intent(Level1_miniLevel3.this, SecondActivity.class);
                startActivity(intent);
            }
        }
    });

        instructionSpeaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (count < 9) {
                    String toSpeak = wordList.get(count);
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });


    }

    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");
        NextButton.setVisibility(View.VISIBLE);
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case REQUEST_CODE_SPEECH_INPUT: {
                    if (resultCode == RESULT_OK && null != data) {
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String resultStr = result.get(0);
                        myTextTv.setVisibility(View.VISIBLE);
                        myTextTv.setText(resultStr);
                        ArrayList<Integer> accuracy;
                        try {
                            accuracy = Algorithm.getAccuracy(Algorithm.BinSearch(getBaseContext(), "cmudict.txt", wordList.get(count)), Algorithm.BinSearch(getBaseContext(), "cmudict.txt", resultStr));  // shurat hamahatz
                        }
                        catch(IOException e){
                            throw new RuntimeException(e);
                        }
                        ArrayList<Integer> indexColors = ColoringData.getSyllablesFamily(wordList.get(count));
                        SpannableStringBuilder builder = new SpannableStringBuilder();
                        String str;
                        for(int i=0; i<accuracy.size(); i++){
                            if (i < accuracy.size()-1)
                                str = wordList.get(count).substring(indexColors.get(i), indexColors.get(i+1));
                            else {
                                str = wordList.get(count).substring(indexColors.get(i));
                            }
                            SpannableString spannableString = new SpannableString(str);

                            switch(accuracy.get(i)){
                                case 0:
                                    spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, str.length(), 0);
                                    break;
                                case 1:
                                    spannableString.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, str.length(), 0);
                                    break;
                                case 2:
                                    spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, str.length(), 0);
                                    break;
                                default:
                                    break;
                            }
                            builder.append(spannableString);
                        }
                        wordText.setText(builder, TextView.BufferType.SPANNABLE);

                    }
                    if (!levelComplteFlag) {
                        NextButton.setVisibility(View.VISIBLE);
                    }
                    break;
                }

            }
        }
}
