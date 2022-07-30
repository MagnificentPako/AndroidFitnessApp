package de.rub.cs.selab22.a14.fragments.graphs;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;

public class GraphFragment extends Fragment {

    LineChart graph;
    Resources resources;
    boolean physicalOverviewActive = true;

    public GraphFragment() {
        super(R.layout.activity_graph_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button physicalOverview = view.findViewById(R.id.physical_overview_button);
        Button moodOverview = view.findViewById(R.id.mood_overview_button);

        Button dayButton = view.findViewById(R.id.day_button);
        Button weekButton = view.findViewById(R.id.week_button);
        Button monthButton = view.findViewById(R.id.month_button);

        //moodOverview.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_moodOverviewFragment));
        //physicalOverview.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_physicalOverviewFragment));

        graph = (LineChart) view.findViewById(R.id.graph);
        this.resources = view.getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        graph = ChartsHelper.renderActivity(graph, "Weekly", createEntryList(7), formatterArray, "Physical Activity");

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
        moodOverview.setOnClickListener(onClickListener);
        physicalOverview.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            String week = resources.getString(R.string.week);
            String days[] = resources.getStringArray(R.array.weekdays);
            String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            switch(v.getId()){
                case R.id.physical_overview_button:
                    physicalOverviewActive = true;
                    graph = ChartsHelper.renderActivity(graph,"Weekly", createEntryList(7), formatterArray, "Physical Activity");
                    graph.invalidate();
                    break;
                case R.id.mood_overview_button:
                    physicalOverviewActive = false;
                    graph = ChartsHelper.renderActivity(graph, "Weekly", createEntryList(7), formatterArray, "Mood");
                    graph.invalidate();
                    break;
                case R.id.day_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Daily", createEntryList(4), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderActivity(graph, "Daily", createEntryList(4), formatterArray, "Mood");
                    }
                    graph.invalidate();
                    break;
                case R.id.week_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Weekly", createEntryList(7), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderActivity(graph, "Weekly", createEntryList(7), formatterArray, "Mood");
                    }
                    graph.invalidate();
                    break;
                case R.id.month_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Weekly", createEntryList(7), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderActivity(graph, "Monthly", createEntryList(4), formatterArray, "Mood");
                    }
                    graph.invalidate();
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
