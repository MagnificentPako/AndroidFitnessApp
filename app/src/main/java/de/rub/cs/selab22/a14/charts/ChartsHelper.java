package de.rub.cs.selab22.a14.charts;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import de.rub.cs.selab22.a14.R;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class ChartsHelper {
    public static LineChart renderActivity(LineChart lc, String label, ArrayList<Entry> entryList, String[] strings, String description) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        System.out.println("Oben: " + label);

        //ArrayList<Entry> entryList2 = createEntryList(length);
        //LineDataSet lineDataSet2 = new LineDataSet(entryList2, "Test");
        //lineDataSet2.setColor(0xff00ff00);
        //dataSets.add(lineDataSet2);

        setStyleConfig(lc, description, true, label, strings);

        LineDataSet lineDataSet = new LineDataSet(entryList, label);
        lineDataSet.setColor(getIntFromColor(0,0,0));
        lineDataSet.setDrawIcons(false);

        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lc.setData(data);

        return lc;
    }

    public static LineChart renderActivity(LineChart lc, ArrayList<Entry> entryList1, ArrayList<Entry> entryList2, String[] strings) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        String firstLabel="";
        String secondLabel="";

        LineDataSet lineDataSet = new LineDataSet(entryList1, firstLabel);
        lineDataSet.setColor(getIntFromColor(0,0,0));
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);
        lineDataSet.setFormSize(0f);
        lc.setDrawMarkers(true);

        dataSets.add(lineDataSet);


        LineDataSet lineDataSet2 = new LineDataSet(entryList2, secondLabel);
        lineDataSet2.setColor(getIntFromColor(255,0,0));
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setFormSize(0f);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);
        lc.setData(data);

        setStyleConfig(lc, "Overview", true, "weekly", strings);

        return lc;
    }

    public static LineChart renderVariableActivity(LineChart lc, ArrayList<String> labels, ArrayList<ArrayList<Entry>> entryEntryList, String[] strings, String mood) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        Random random = new Random();

        int i = 0;
        for (ArrayList<Entry> el : entryEntryList) {
            LineDataSet lds = new LineDataSet(el, labels.get(i));
            lds.setColor(getIntFromColor(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            lds.setDrawCircles(false);
            lds.setDrawValues(false);
            lds.setFormSize(0f);
            dataSets.add(lds);
            i++;

        }
        lc.setDrawMarkers(true);

        LineData data = new LineData(dataSets);
        lc.setData(data);

        setStyleConfig(lc, "Mood", true, "weekly", strings);

        return lc;
    }

    private static int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000;
        Green = (Green << 8) & 0x0000FF00;
        Blue = Blue & 0x000000FF;

        return 0xFF000000 | Red | Green | Blue;
    }

    public static LineChart setStyleConfig(LineChart lc, String description, boolean active, String label, String[] strings)
    {
        lc.setDescription(descriptionHandler(lc, description, 15f, true));
        lc.setDrawBorders(true);
        lc.setBorderWidth(1f);
        //lc.setScaleEnabled(false);
        lc.setTouchEnabled(true);
        lc.setPinchZoom(false);
        lc.setDoubleTapToZoomEnabled(false);
        axisHandler(lc, label, strings);

        return lc;
    }

    private static void axisHandler (LineChart lc, String label, String[] strings)
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

        yAxisRight.setValueFormatter(new YAxisFormatter());
        switch (label.toLowerCase(Locale.ROOT)) {
            case "daily":
                xAxis.setValueFormatter(new DayValueFormatter());
                break;
            case "weekly":
                xAxis.setValueFormatter(new WeekValueFormatter(strings));
                break;
            case "monthly":
                xAxis.setValueFormatter(new MonthValueFormatter(strings));
                break;
            default:
                xAxis.setValueFormatter(new DefaultValueFormatter());
                break;
        }
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

    private static class DayValueFormatter extends ValueFormatter {
        private String daytime[] = {"0","1","2","3","4","5","6","7","8","4"};
        //private String days[] = {"Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su"};
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return (String.valueOf((int)value) + ":00");
        }
    }

    private static class WeekValueFormatter extends ValueFormatter {

        private String[] dayStrings;
        WeekValueFormatter(String[] dayStrings){this.dayStrings = dayStrings;}

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            String days[] = dayStrings;

            return String.valueOf(value);//days[(int) value-1];
        }
    }

    private static class MonthValueFormatter extends ValueFormatter {

        private String[] dayStrings;
        MonthValueFormatter(String[] dayStrings){this.dayStrings = dayStrings;}

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            String week = dayStrings[0];
            return week + " " + (int) value ;
        }
    }

    private static class DefaultValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return String.valueOf(value);
        }
    }
    private static class YAxisFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return " ";
        }
    }
}
