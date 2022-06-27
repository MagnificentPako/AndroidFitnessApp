package de.rub.cs.selab22.a14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.settings.SettingsActivity;
import kotlin.collections.IndexedValue;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

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


        setStyleConfig();
        renderActivity("Weekly", 7 );

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
                    renderActivity("Daily", 3 );
                    break;
                case R.id.physical_week_button:
                    renderActivity("Weekly", 7 );
                    break;
                case R.id.physical_month_button:
                    renderActivity("Monthly", 4 );
                    break;
            }

        }
    };

    private void setStyleConfig()
    {
        lc.setDescription(descriptionHandler("Physical Activity", 15f, true));
        lc.setDrawBorders(true);
        lc.setBorderWidth(1f);
        axisHandler();
    }

    private void axisHandler ()
    {
        XAxis xAxis = lc.getXAxis();
        xAxis.setAxisMinimum(1);

        YAxis yAxisLeft = lc.getAxisLeft();
        YAxis yAxisRight = lc.getAxisRight();

        yAxisRight.setAxisMinimum(0);
        yAxisRight.setAxisMaximum(100);
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);

        xAxis.setDrawGridLines(false);
        yAxisRight.setDrawGridLines(false);
        yAxisLeft.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        yAxisRight.setGranularity(1f);
        yAxisLeft.setGranularity(1f);

        xAxis.setDrawAxisLine(false);
    }

    private Description descriptionHandler (String description, float textSize, Boolean active)
    {
        Description des = lc.getDescription();
        des.setTextSize(textSize);
        des.setText(description);
        if (active)
        {
            des.setEnabled(true);
        } else {
            des.setEnabled(false);
        }
        return des;
    }

    private void renderActivity(String label, int length) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        ArrayList<Entry> entryList = createEntryList(length);

        //ArrayList<Entry> entryList2 = createEntryList(length);
        //LineDataSet lineDataSet2 = new LineDataSet(entryList2, "Test");
        //lineDataSet2.setColor(0xff00ff00);
        //dataSets.add(lineDataSet2);

        LineDataSet lineDataSet = new LineDataSet(entryList, label);
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);

        setStyleConfig();
        lc.invalidate();
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