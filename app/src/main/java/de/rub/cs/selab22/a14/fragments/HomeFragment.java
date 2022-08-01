package de.rub.cs.selab22.a14.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.Data;
import de.rub.cs.selab22.a14.foreground.ForegroundService;
import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;
import de.rub.cs.selab22.a14.fragments.survey.SurveyViewModel;

public class HomeFragment extends Fragment {

    LineChart overview_chart;
    Resources resources;
    boolean privacyPolicyAgreed = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences("22a14", Context.MODE_PRIVATE);
        privacyPolicyAgreed = preferences.getBoolean("privacyPolicyAgreed", false);

        rootView.findViewById(R.id.home_survey_button).setOnClickListener(v -> {
            SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
            model.setSurveyData(new HashMap<>());
            Navigation.findNavController(rootView).navigate(R.id.action_bottom_home_to_mood_feelings);
        });

        Button recordButton = rootView.findViewById(R.id.home_recordactivity_button);
        recordButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_home_to_recordFragment));

        overview_chart = (LineChart) rootView.findViewById(R.id.overview_chart);
        this.resources = getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        overview_chart = ChartsHelper.renderActivity(overview_chart, createPhysicalWeeklyEntryList(DBHelper.INSTANCE.getDataDao()), createEntryList(7), formatterArray);

        if (!privacyPolicyAgreed) {
            new AlertDialog.Builder(this.getContext())
                    .setTitle(getString(R.string.privacy_policy))
                    .setMessage(getString(R.string.privacy_policy_text))
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            privacyPolicyAgreed = true;
                            preferences.edit().putBoolean("privacyPolicyAgreed", true).commit();
                        }
                    })
                    .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    })
                    .create().show();
        }

        startService();
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

    public void startService() {
        Intent serviceIntent = new Intent(this.getContext(), ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this.getContext(), serviceIntent);
    }

    private ArrayList<Entry> createPhysicalWeeklyEntryList(DataDao dao) {
        Date since = Date.from(LocalDate.now()
                .with(DayOfWeek.MONDAY)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(LocalDate.now()
                .with(DayOfWeek.SUNDAY)
                .atTime(23, 59)
                .atZone(ZoneId.systemDefault()).toInstant());
        Map<Integer,List<Data>> dailyData = dao.getBetweenByIdentifierByDay(since, until);
        ArrayList<Entry> entries = new ArrayList<>();
        dailyData.forEach((h, d) -> {
            System.out.println(h);
            float sum = 0f;
            for(Data dd : d) {
                sum += dd.dataPoint.<Double>getData().get(0).floatValue();
            }
            entries.add(new Entry(h, sum/d.size()));
        });
        return entries;
    }

}
