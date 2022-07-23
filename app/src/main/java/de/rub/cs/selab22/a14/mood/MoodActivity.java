package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;

public class MoodActivity extends AppCompatActivity {

    LineChart lc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Button dayButton = findViewById(R.id.mood_day_button);
        Button weekButton = findViewById(R.id.mood_week_button);
        Button monthButton = findViewById(R.id.mood_month_button);

        String week = getApplicationContext().getResources().getString(R.string.week);
        String days[] = getApplicationContext().getResources().getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        lc = (LineChart) findViewById(R.id.mood_chart);
        ChartsHelper.renderActivity(lc, "Weekly", createEntryList(7), formatterArray);

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            String week = getApplicationContext().getResources().getString(R.string.week);
            String days[] = getApplicationContext().getResources().getStringArray(R.array.weekdays);
            String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            switch(v.getId()){
                case R.id.mood_day_button:
                    lc = ChartsHelper.renderActivity(lc,"Daily", createEntryList(7),  formatterArray);
                    lc.invalidate();
                    break;
                case R.id.mood_week_button:
                    lc = ChartsHelper.renderActivity(lc,"Weekly", createEntryList(7), formatterArray);
                    lc.invalidate();
                    break;
                case R.id.mood_month_button:
                    lc = ChartsHelper.renderActivity(lc,"Monthly", createEntryList(7), formatterArray);
                    lc.invalidate();
                    break;
            }

        }
    };

    public void mood_record(View view) {

    }

    public void notification_button(View view) {
        Intent intent = new Intent(MoodActivity.this, Notification.class);
        startActivity(intent);
    }

    private ArrayList<Entry> createEntryList (int length)
    {
        ArrayList<Entry> dataVals = new ArrayList<>();
        for (int i = 0; i < length; i++)
        {
            //DB Data to be inserted here instead of randomInt
            Random ran = new Random();
            int randomInt = ran.nextInt(100) + 0;

            dataVals.add(new Entry(i+1, randomInt));
        }
        return dataVals;
    }
}