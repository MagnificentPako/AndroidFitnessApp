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

public class EventAppraisal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_appraisal);

        // Question 1
        SeekBar seekBarQustionOne = findViewById(R.id.seekBarQuestion1);
        TextView textView1 = findViewById(R.id.mood_textview_question1);
        final Handler seekBarH1 = new Handler(Looper.getMainLooper());
        seekBarQustionOne.getThumb().setAlpha(0);


        seekBarQustionOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint({"SetTextI1", "SetTextI18n"})
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress1, boolean a) {

                if (progress1 == 0) {
                    textView1.setText(progress1 + "%" +"trifft überhaupt nicht zu " );
                }
                else if (progress1 == 100) {
                    textView1.setText( progress1 + "%" +"trifft völlig  zu " );
                }
                else{
                    textView1.setText(progress1 + "%");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarH1.removeCallbacksAndMessages(null);
                seekBarQustionOne.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });

        SeekBar seekBarQuestionTwo = findViewById(R.id.seekBarQuestion2);
        TextView textView2 =findViewById(R.id.mood_textview_question2);
        final Handler seekBarH2 = new Handler(Looper.getMainLooper());
        seekBarQuestionTwo.getThumb().setAlpha(0);

        seekBarQuestionTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @SuppressLint({"SetText2", "SetTextI18n"})
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress2, boolean b) {
                if (progress2 == 0) {
                    textView2.setText(progress2 + "%" +"trifft überhaupt nicht zu " );
                }
                else if (progress2 == 100) {
                    textView2.setText( progress2 + "%" +"trifft völlig  zu " );
                }
                else{
                    textView2.setText(progress2 + "%");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarH2.removeCallbacksAndMessages(null);
                seekBarQuestionTwo.getThumb().setAlpha(255);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });
        Button nextButton = findViewById(R.id.button_appraisal_next);
        nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.button_appraisal_back);
        backButton.setOnClickListener(view -> goToPreviousPage());



    }
    public void goToNextPage() {
        Intent intent = new Intent(EventAppraisal.this, SocialContext.class);
        startActivity(intent);
    }

    public void goToPreviousPage() {


    }

}