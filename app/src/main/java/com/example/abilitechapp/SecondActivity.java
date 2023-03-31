package com.example.abilitechapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.animation.AlphaAnimation;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;



public class SecondActivity extends AppCompatActivity {

    private Vibrator vibrator; // Declare vibrator object
    private int openLevels = 0;
    private Button Lvl1Button, Lvl2Button, Lvl3Button, Lvl4Button, BigButton, BigButtonNext;
    private ImageView LineObject;
    private TextView t1;
    private TextView progressText;
    private int lvlPrecentge = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView progressText = findViewById(R.id.progressText);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        Lvl1Button = findViewById(R.id.Lvl1Button);
        Lvl2Button = findViewById(R.id.lvl2Button);
        Lvl3Button = findViewById(R.id.lvl3Button);
        Lvl4Button = findViewById(R.id.lvl4Button);

        BigButton = findViewById(R.id.lvlButtonBig);
        BigButtonNext = findViewById(R.id.lvlButtonBigNext);
        LineObject = findViewById(R.id.Line);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE); // Initialize vibrator object

        if(Level1_miniLevel3.levelComplteFlag)
        {
            lvlPrecentge += 25;
            progressBar.setProgress(lvlPrecentge);
            progressText.setText(lvlPrecentge+"%");
            int color = Color.parseColor("#00CC99");
            Lvl3Button.setBackgroundTintList(ColorStateList.valueOf(color));



        }
        // Set the onClickListener for the button
        BigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Replace "myTextView" with the ID of your TextView element
                if (openLevels % 2 == 0) {
                    Lvl1Button.setVisibility(View.VISIBLE);
                    Lvl2Button.setVisibility(View.VISIBLE);
                    Lvl3Button.setVisibility(View.VISIBLE);
                    Lvl4Button.setVisibility(View.VISIBLE);
                    LineObject.setVisibility(View.VISIBLE);
                    AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setDuration(800);
                    fadeIn.setFillBefore(false);
                    fadeIn.setFillAfter(true);
                    Lvl1Button.startAnimation(fadeIn);
                    Lvl2Button.startAnimation(fadeIn);
                    Lvl3Button.startAnimation(fadeIn);
                    Lvl4Button.startAnimation(fadeIn);
                    LineObject.startAnimation(fadeIn);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    BigButtonNext.startAnimation(animation);
                    progressBar.setVisibility(View.VISIBLE);
                    progressText.setVisibility(View.VISIBLE);
                    progressText.setText(lvlPrecentge+"%");
                    progressText.startAnimation(fadeIn);
                    progressBar.startAnimation(fadeIn);
                    progressBar.setProgress(lvlPrecentge);


                } else {
                    AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setDuration(500);
                    fadeOut.setFillBefore(true);
                    fadeOut.setFillAfter(false);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    Lvl1Button.startAnimation(fadeOut);
                    Lvl2Button.startAnimation(fadeOut);
                    Lvl3Button.startAnimation(fadeOut);
                    Lvl4Button.startAnimation(fadeOut);
                    LineObject.startAnimation(fadeOut);
                    BigButtonNext.startAnimation(animation);
                    Lvl1Button.setVisibility(View.INVISIBLE);
                    Lvl2Button.setVisibility(View.INVISIBLE);
                    Lvl3Button.setVisibility(View.INVISIBLE);
                    Lvl4Button.setVisibility(View.INVISIBLE);
                    LineObject.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.startAnimation(fadeOut);
                    progressText.startAnimation(fadeOut);
                    progressText.setVisibility(View.INVISIBLE);



                }

                openLevels++;
            }
        });

        Lvl1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "myTextView" with the ID of your TextView element

            }
        });

        Lvl3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace "myTextView" with the ID of your TextView element

                Intent intent = new Intent(SecondActivity.this, Level1_miniLevel3.class);
                startActivity(intent);
            }
        });

        // Replace "BackButton" with the ID of your button
        Button BackButton = findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to switch to the MainActivity
                if (vibrator.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                }
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
