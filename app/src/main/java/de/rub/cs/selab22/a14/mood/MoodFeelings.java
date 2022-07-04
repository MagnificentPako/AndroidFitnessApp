package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import de.rub.cs.selab22.a14.R;

public class MoodFeelings extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_feelings);


        //seekbar 1
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        textView1 = findViewById(R.id.rating_text1);

        final Handler seekbarH1 = new Handler(Looper.getMainLooper());
        seekBar1.getThumb().setAlpha(0);


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView1.setText(progress + "%");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH1.removeCallbacksAndMessages(null);
                seekBar1.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekbar 2
        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        textView2 = findViewById(R.id.rating_text2);

        final Handler seekbarH2 = new Handler(Looper.getMainLooper());
        seekBar2.getThumb().setAlpha(0);


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView2.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH2.removeCallbacksAndMessages(null);
                seekBar2.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        // Seekbar 3
        SeekBar seekBar3 = findViewById(R.id.seekBar3);
        textView3 = findViewById(R.id.rating_text3);

        final Handler seekbarH3 = new Handler(Looper.getMainLooper());
        seekBar3.getThumb().setAlpha(0);


        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView3.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH3.removeCallbacksAndMessages(null);
                seekBar3.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        // Seekbar 4
        SeekBar seekBar4 = findViewById(R.id.seekBar4);
        textView4 =findViewById(R.id.rating_text4);

        final Handler seekbarH4 = new Handler(Looper.getMainLooper());
        seekBar4.getThumb().setAlpha(0);


        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView4.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH4.removeCallbacksAndMessages(null);
                seekBar4.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        // Seekbar 5
        SeekBar seekBar5 = findViewById(R.id.seekBar5);
        textView5 =findViewById(R.id.rating_text5);

        final Handler seekbarH5 = new Handler(Looper.getMainLooper());
        seekBar5.getThumb().setAlpha(0);


        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView5.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH5.removeCallbacksAndMessages(null);
                seekBar5.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        // Seekbar 6
        SeekBar seekBar6 = findViewById(R.id.seekBar6);
        textView6 = findViewById(R.id.rating_text6);

        final Handler seekbarH6 = new Handler(Looper.getMainLooper());
        seekBar6.getThumb().setAlpha(0);


        seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView6.setText(progress + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbarH6.removeCallbacksAndMessages(null);
                seekBar6.getThumb().setAlpha(255);


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        Button backButton = findViewById(R.id.zurück_button);
        backButton.setOnClickListener(view -> goToPreviousPage());
        Button nextButton = findViewById(R.id.weiter_button);
        nextButton.setOnClickListener(view -> goToNextPage());

    }


    public void goToPreviousPage() {
        Intent intent = new Intent(this, MoodActivity.class);
        startActivity(intent);
    }

    public void goToNextPage() {
        Intent intent = new Intent(this, EventAppraisal.class);
        startActivity(intent);
    }

}
