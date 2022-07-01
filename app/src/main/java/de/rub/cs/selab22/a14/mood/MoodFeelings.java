package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import de.rub.cs.selab22.a14.MainActivity;
import de.rub.cs.selab22.a14.R;

public class MoodFeelings extends AppCompatActivity {

    SeekBar seekBar1;
    SeekBar seekBar2;
    SeekBar seekBar3;
    SeekBar seekBar4;
    SeekBar seekBar5;
    SeekBar seekBar6;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    TextView rating_text1;
    private int mProgress;
    private Drawable mThumb;
    private boolean thumb1IsDrag;
    private boolean thumb2IsDrag;
    private boolean thumb3IsDrag;
    private boolean thumb4IsDrag;
    private boolean thumb5IsDrag;
    private boolean thumb6IsDrag;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_feelings);

        Button zurück_button = findViewById(R.id.zurück_button);
        zurück_button.setOnClickListener(view -> go_to_previous_page());
        Button weiter_button = findViewById(R.id.weiter_button);
        weiter_button.setOnClickListener(view -> go_to_next_page());


        //seekbar 2
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        textView1 = (TextView) findViewById(R.id.rating_text1);
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
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        textView2 = (TextView) findViewById(R.id.rating_text2);
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
        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        textView3 = (TextView) findViewById(R.id.rating_text3);
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
        SeekBar seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        textView4 = (TextView) findViewById(R.id.rating_text4);
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
        SeekBar seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        textView5 = (TextView) findViewById(R.id.rating_text5);
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
        SeekBar seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        textView6 = (TextView) findViewById(R.id.rating_text6);
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

    }


    public void go_to_previous_page() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void go_to_next_page() {
        Intent intent = new Intent(MoodFeelings.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isWithinThumb(MotionEvent event, SeekBar seekBar) {
        Rect rcThumb = seekBar.getThumb().getBounds();
        Rect rcDetectTouchArea = new Rect();
        int iWidth = rcThumb.width();
        int iHeight = rcThumb.height();
        rcDetectTouchArea.left = rcThumb.left - iWidth;
        rcDetectTouchArea.right = rcThumb.right + iWidth;
        rcDetectTouchArea.bottom = rcThumb.bottom + iHeight;
        Log.i("TAG", "rcDetectSize.left  = " + rcDetectTouchArea.left + " | rcDetectSize.right = " + rcDetectTouchArea.right + " |  rcDetectSize.bottom = " + rcDetectTouchArea.bottom + " | event.getX()= " + event.getX() + " | event.getY()= " + event.getY() + " | rcThumb  area = " + rcDetectTouchArea.contains((int) event.getX(), (int) event.getY()));
        return rcDetectTouchArea.contains((int) event.getX(), (int) event.getY());
    }
}

