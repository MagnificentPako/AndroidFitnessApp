package de.rub.cs.selab22.a14.fragments.graphs;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;

public class PhysicalOverviewFragment extends Fragment {

    LineChart lc_physical;
    Resources resources;

    public PhysicalOverviewFragment() {
        super(R.layout.activity_physical_overview);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button dayButton = view.findViewById(R.id.physical_day_button);
        Button weekButton = view.findViewById(R.id.physical_week_button);
        Button monthButton = view.findViewById(R.id.physical_month_button);

        lc_physical = (LineChart) view.findViewById(R.id.physical_chart);
        this.resources = view.getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        lc_physical = ChartsHelper.renderActivity(lc_physical, "Weekly", createEntryList(7), formatterArray, "Physical Activity");

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
                    lc_physical = ChartsHelper.renderActivity(lc_physical,"Daily", createEntryList(4), formatterArray, "Physical Activity");
                    lc_physical.invalidate();
                    break;
                case R.id.physical_week_button:
                    lc_physical = ChartsHelper.renderActivity(lc_physical,"Weekly", createEntryList(7), formatterArray, "Physical Activity");
                    lc_physical.invalidate();
                    break;
                case R.id.physical_month_button:
                    lc_physical = ChartsHelper.renderActivity(lc_physical,"Monthly", createEntryList(4), formatterArray, "Physical Activity");
                    lc_physical.invalidate();
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
