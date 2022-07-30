package de.rub.cs.selab22.a14.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.fragments.survey.SurveyViewModel;

public class HomeFragment extends Fragment {

    LineChart overview_chart;
    Resources resources;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        rootView.findViewById(R.id.home_survey_button).setOnClickListener(v -> {
            SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
            model.setSurveyData(new HashMap<>());
            Navigation.findNavController(rootView).navigate(R.id.action_bottom_home_to_mood_feelings);
        });

        overview_chart = (LineChart) rootView.findViewById(R.id.overview_chart);
        this.resources = getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        overview_chart = ChartsHelper.renderActivity(overview_chart, createEntryList(7), createEntryList(7), formatterArray);

        return rootView;
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
