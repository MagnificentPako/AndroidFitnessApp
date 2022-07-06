package de.rub.cs.selab22.a14.charts;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ChartsHelper {
    public static LineChart renderActivity(LineChart lc, String label, ArrayList<Entry> entryList) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        //ArrayList<Entry> entryList2 = createEntryList(length);
        //LineDataSet lineDataSet2 = new LineDataSet(entryList2, "Test");
        //lineDataSet2.setColor(0xff00ff00);
        //dataSets.add(lineDataSet2);

        LineDataSet lineDataSet = new LineDataSet(entryList, label);
        lineDataSet.setColor(getIntFromColor(0,0,0));
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lc.setData(data);

        setStyleConfig(lc, "Physical Activity", true);

        return lc;
    }

    private static int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000;
        Green = (Green << 8) & 0x0000FF00;
        Blue = Blue & 0x000000FF;

        return 0xFF000000 | Red | Green | Blue;
    }

    public static LineChart setStyleConfig(LineChart lc, String description, boolean active)
    {
        lc.setDescription(descriptionHandler(lc, "Physical Activity", 15f, true));
        lc.setDrawBorders(true);
        lc.setBorderWidth(1f);
        //lc.setScaleEnabled(false);
        lc.setTouchEnabled(true);
        lc.setPinchZoom(false);
        lc.setDoubleTapToZoomEnabled(false);
        axisHandler(lc);

        return lc;
    }

    private static void axisHandler (LineChart lc)
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

    private static Description descriptionHandler (LineChart lc, String description, float textSize, Boolean active)
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
}
