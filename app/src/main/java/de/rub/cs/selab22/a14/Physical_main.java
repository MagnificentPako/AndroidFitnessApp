package de.rub.cs.selab22.a14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.charts.ChartsHelper;
import de.rub.cs.selab22.a14.settings.I18nAppCompatActivity;

public class Physical_main extends I18nAppCompatActivity {

    LineChart lc;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_main);

        Button dayButton = findViewById(R.id.physical_day_button);
        Button weekButton = findViewById(R.id.physical_week_button);
        Button monthButton = findViewById(R.id.physical_month_button);

        lc = (LineChart) findViewById(R.id.physical_chart);
        this.resources = getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        lc = ChartsHelper.renderActivity(lc, createEntryList(7), createEntryList(7), formatterArray);

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            String week = resources.getString(R.string.week);
            String days[] = resources.getStringArray(R.array.weekdays);
            String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            switch(v.getId()){
                case R.id.physical_day_button:
                    lc = ChartsHelper.renderActivity(lc,"Daily", createEntryList(4), formatterArray);
                    lc.invalidate();
                    break;
                case R.id.physical_week_button:
                    lc = ChartsHelper.renderActivity(lc,"Weekly", createEntryList(7), formatterArray);
                    lc.invalidate();
                    break;
                case R.id.physical_month_button:
                    lc = ChartsHelper.renderActivity(lc,"Monthly", createEntryList(4), formatterArray);
                    lc.invalidate();
                    break;
            }

        }
    };

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