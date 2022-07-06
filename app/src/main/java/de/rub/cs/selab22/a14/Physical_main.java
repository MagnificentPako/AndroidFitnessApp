package de.rub.cs.selab22.a14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.charts.ChartsHelper;

public class Physical_main extends AppCompatActivity {

    LineChart lc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_main);

        Button dayButton = findViewById(R.id.physical_day_button);
        Button weekButton = findViewById(R.id.physical_week_button);
        Button monthButton = findViewById(R.id.physical_month_button);

        lc = (LineChart) findViewById(R.id.physical_chart);
        lc = ChartsHelper.renderActivity(lc,"Weekly", createEntryList(7));

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            switch(v.getId()){
                case R.id.physical_day_button:
                    lc = ChartsHelper.renderActivity(lc,"Daily", createEntryList(3) );
                    lc.invalidate();
                    break;
                case R.id.physical_week_button:
                    lc = ChartsHelper.renderActivity(lc,"Weekly", createEntryList(7) );
                    lc.invalidate();
                    break;
                case R.id.physical_month_button:
                    lc = ChartsHelper.renderActivity(lc,"Monthly", createEntryList(4) );
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