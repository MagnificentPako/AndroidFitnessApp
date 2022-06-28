package de.rub.cs.selab22.a14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import de.rub.cs.selab22.a14.settings.SettingsActivity;
import kotlin.collections.IndexedValue;

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
        LineDataSet lineDataSet = new LineDataSet(week(), "Activity");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.physical_day_button:
                    renderDayActivity();
                    break;
                case R.id.physical_week_button:
                    renderWeekActivity();
                    break;
                case R.id.physical_month_button:
                    renderMonthActivity();
                    break;
            }

        }
    };

    private void renderDayActivity() {
        LineDataSet lineDataSet = new LineDataSet(day(), "Dayly");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();
    }

    private void renderWeekActivity() {
        LineDataSet lineDataSet = new LineDataSet(week(), "Weekly");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();
    }

    private void renderMonthActivity() {
        LineDataSet lineDataSet = new LineDataSet(month(), "Monthly");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();
    }

    private ArrayList<Entry> day ()
    {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,30));
        dataVals.add(new Entry(2,10));
        dataVals.add(new Entry(3,50));

        return dataVals;
    }

    private ArrayList<Entry> week ()
    {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,30));
        dataVals.add(new Entry(2,10));
        dataVals.add(new Entry(3,50));
        dataVals.add(new Entry(4,70));
        dataVals.add(new Entry(5,40));
        dataVals.add(new Entry(6,40));

        return dataVals;
    }

    private ArrayList<Entry> month ()
    {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,10));
        dataVals.add(new Entry(2,0));
        dataVals.add(new Entry(3,100));

        return dataVals;
    }
}