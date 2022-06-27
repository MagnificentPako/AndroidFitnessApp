package de.rub.cs.selab22.a14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

        lc = (LineChart) findViewById(R.id.physical_chart);
        LineDataSet lineDataSet = new LineDataSet(dataValues1(), "Activity");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();
    }

    private ArrayList<Entry> dataValues1 ()
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
}