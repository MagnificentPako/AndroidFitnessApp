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

    public GraphFragment() {
        super(R.layout.activity_graph_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button physicalOverview = view.findViewById(R.id.physical_overview_button);
        Button moodOverview = view.findViewById(R.id.mood_overview_button);

        moodOverview.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_moodOverviewFragment));
        physicalOverview.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_physicalOverviewFragment));
    }
}
