package de.rub.cs.selab22.a14.fragments.graphs;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.charts.ChartsHelper;
import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.Data;
import de.rub.cs.selab22.a14.fragments.survey.SurveyViewModel;

public class GraphFragment extends Fragment {

    LineChart graph;
    Resources resources;
    boolean physicalOverviewActive = true;

    Button physicalOverview;
    Button moodOverview;
    Button dayButton;
    Button weekButton;
    Button monthButton;

    public GraphFragment() {
        super(R.layout.activity_graph_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        physicalOverview = view.findViewById(R.id.physical_overview_button);
        moodOverview = view.findViewById(R.id.mood_overview_button);
        Button surveyButton = view.findViewById(R.id.graph_survey_button);
        Button recordButton = view.findViewById(R.id.graph_recordactivity_button);

        dayButton = view.findViewById(R.id.day_button);
        weekButton = view.findViewById(R.id.week_button);
        monthButton = view.findViewById(R.id.month_button);

        surveyButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_nested_survey_graph));
        recordButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_bottom_graphs_to_recordFragment));

        graph = (LineChart) view.findViewById(R.id.graph);
        this.resources = view.getResources();

        String week = resources.getString(R.string.week);
        String days[] = resources.getStringArray(R.array.weekdays);
        String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
        DataDao dao = DBHelper.INSTANCE.getDataDao();
        graph = ChartsHelper.renderActivity(graph, "Weekly", createPhysicalWeeklyEntryList(dao), formatterArray, "Physical Activity");
        physicalOverview.setAlpha(.5f);
        physicalOverview.setEnabled(false);
        weekButton.setAlpha(.5f);
        weekButton.setEnabled(false);

        dayButton.setOnClickListener(onClickListener);
        weekButton.setOnClickListener(onClickListener);
        monthButton.setOnClickListener(onClickListener);
        moodOverview.setOnClickListener(onClickListener);
        physicalOverview.setOnClickListener(onClickListener);

        view.findViewById(R.id.graph_survey_button).setOnClickListener(v -> {
            SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
            model.setSurveyData(new HashMap<>());
            Navigation.findNavController(view).navigate(R.id.action_bottom_graphs_to_nested_survey_graph);
        });

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            String week = resources.getString(R.string.week);
            String days[] = resources.getStringArray(R.array.weekdays);
            String formatterArray[] = { week, days[0], days[1], days[2], days[3], days[4], days[5], days[6]};
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            DataDao dao = DBHelper.INSTANCE.getDataDao();

            ArrayList<String> moodLabels = new ArrayList(Arrays.asList("", "", "", "", "", ""));

            switch(v.getId()){
                case R.id.physical_overview_button:
                    physicalOverviewActive = true;
                    graph = ChartsHelper.renderVariableActivity(graph, moodLabels, createMoodWeeklyEntryList(dao), formatterArray, "Mood", "weekly");
                    graph.invalidate();

                    physicalOverview.setAlpha(.5f);
                    moodOverview.setAlpha(1f);
                    physicalOverview.setEnabled(false);
                    moodOverview.setEnabled(true);

                    dayButton.setAlpha(1f);
                    dayButton.setEnabled(true);
                    weekButton.setAlpha(.5f);
                    weekButton.setEnabled(false);
                    monthButton.setAlpha(1f);
                    monthButton.setEnabled(true);

                    break;
                case R.id.mood_overview_button:
                    physicalOverviewActive = false;
                    graph = ChartsHelper.renderVariableActivity(graph, moodLabels, createMoodWeeklyEntryList(dao), formatterArray, "Mood", "weekly");
                    graph.invalidate();

                    moodOverview.setAlpha(.5f);
                    physicalOverview.setAlpha(1f);
                    moodOverview.setEnabled(false);
                    physicalOverview.setEnabled(true);

                    dayButton.setAlpha(1f);
                    dayButton.setEnabled(true);
                    weekButton.setAlpha(.5f);
                    weekButton.setEnabled(false);
                    monthButton.setAlpha(1f);
                    monthButton.setEnabled(true);

                    break;
                case R.id.day_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Daily", createPhysicalDailyEntryList(dao), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderVariableActivity(graph, moodLabels, createMoodDailyEntryList(dao), formatterArray, "Mood", "daily");
                    }
                    graph.invalidate();

                    dayButton.setAlpha(.5f);
                    dayButton.setEnabled(false);
                    weekButton.setAlpha(1f);
                    weekButton.setEnabled(true);
                    monthButton.setAlpha(1f);
                    monthButton.setEnabled(true);

                    break;
                case R.id.week_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Weekly", createPhysicalWeeklyEntryList(dao), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderVariableActivity(graph, moodLabels, createMoodWeeklyEntryList(dao), formatterArray, "Mood", "weekly");
                    }
                    graph.invalidate();

                    dayButton.setAlpha(1f);
                    dayButton.setEnabled(true);
                    weekButton.setAlpha(.5f);
                    weekButton.setEnabled(false);
                    monthButton.setAlpha(1f);
                    monthButton.setEnabled(true);

                    break;
                case R.id.month_button:
                    if (physicalOverviewActive) {
                        graph = ChartsHelper.renderActivity(graph,"Monthly", createPhysicalMonthlyEntryList(dao), formatterArray, "Physical Activity");
                    }
                    else {
                        graph = ChartsHelper.renderVariableActivity(graph, moodLabels, createMoodMonthlyEntryList(dao), formatterArray, "Mood", "monthly");
                    }
                    graph.invalidate();

                    dayButton.setAlpha(1f);
                    dayButton.setEnabled(true);
                    weekButton.setAlpha(1f);
                    weekButton.setEnabled(true);
                    monthButton.setAlpha(.5f);
                    monthButton.setEnabled(false);

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

    private ArrayList<Entry> createPhysicalDailyEntryList(DataDao dao) {
        Date since = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(LocalDate.now().atTime(23, 59).atZone(ZoneId.systemDefault()).toInstant());
        //List<Data> dailyData = dao.getBetweenByIdentifier(since, until, Identifier.ACCELEROMETER_VECTOR_LENGTH);
        Map<Integer,List<Data>> dailyData = dao.getBetweenByIdentifierByHour(since, until);
        ArrayList<Entry> entries = new ArrayList<>();
        dailyData.forEach((h, d) -> {
            float sum = 0f;
            for(Data dd : d) {
                sum +=  dd.dataPoint.<Double>getData().get(0).floatValue();
            }
            entries.add(new Entry(h, sum/d.size()));
        });
        return entries;
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

    private ArrayList<Entry> createPhysicalMonthlyEntryList(DataDao dao) {
        LocalDate init = LocalDate.now();
        Date since = Date.from(init
                .withDayOfMonth(1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(init
                .withDayOfMonth(init.getMonth().length(init.isLeapYear()))
                .atTime(23, 59)
                .atZone(ZoneId.systemDefault()).toInstant());
        int i = 0;
        Map<Integer,List<Data>> dailyData = dao.getBetweenByIdentifierByWeek(since, until);
        ArrayList<Entry> entries = new ArrayList<>();
        for (Map.Entry<Integer, List<Data>> entry : dailyData.entrySet()) {
            Integer integer = entry.getKey();
            List<Data> data = entry.getValue();
            float sum = 0;
            for(Data d : data) {
                sum += d.dataPoint.<Double>getData().get(0).floatValue();
            }
            entries.add(new Entry(i, sum));
            i++;
        }
        return entries;
    }
/*
    private ArrayList<ArrayList<Entry>> createMoodDailyEntryList(DataDao dao) {
        Date since = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(LocalDate.now().atTime(23, 59).atZone(ZoneId.systemDefault()).toInstant());
        List<Data> dataList = dao.getBetweenByIdentifier(since, until, Identifier.SURVEY);
        ArrayList<ArrayList<Entry>> entries = new ArrayList<>();
        int acc = 0;
        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(i -> entries.add(new ArrayList<Entry>()));
        dataList.sort(Comparator.comparing(o -> o.timestamp));
        for(Data d : dataList) {
            Map<String, Object> dataSet = d.dataPoint.<Map<String, Object>>getData().get(0);
            Map<String, Double> data = (Map<String, Double>) dataSet.get("surveyData");
            int finalAcc = acc;
            Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(i -> {
                System.out.println(data.keySet());
                if(data.containsKey(String.valueOf(i))) {
                    System.out.println("Found key: " + i);
                    entries.get(i).add(new Entry(finalAcc,data.get(String.valueOf(i)).intValue()));
                }
            });
            acc++;
        }
        return entries;
    }
 */

    private ArrayList<ArrayList<Entry>> createMoodDailyEntryList(DataDao dao) {
        Date since = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(LocalDate.now().atTime(23, 59).atZone(ZoneId.systemDefault()).toInstant());
        List<Data> dailyData = dao.getBetweenByIdentifier(since, until, Identifier.SURVEY);
        ArrayList<ArrayList<Entry>> entries = new ArrayList<>();
        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(i -> entries.add(new ArrayList<Entry>()));
        dailyData.stream()
                .collect(Collectors.groupingBy(
                        d -> d.timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getHour(),
                        LinkedHashMap::new,
                        Collectors.toList())).forEach((hour, data) -> {
                            ArrayList<Integer> accs = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
                            data.forEach(data1 -> {
                                Map<String, Object> dataSet = data1.dataPoint.<Map<String, Object>>getData().get(0);
                                Map<String, Double> dd = (Map<String, Double>) dataSet.get("surveyData");
                                Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                                    if(dd.containsKey(String.valueOf(integer))) {
                                        accs.set(integer, accs.get(integer) + dd.get(String.valueOf(integer)).intValue());
                                    }
                                });
                            });
                            Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                                entries.get(integer).add(new Entry(hour, accs.get(integer)/data.size()));
                            });
                        });
        return entries;
    }

    private ArrayList<ArrayList<Entry>> createMoodWeeklyEntryList(DataDao dao) {
        Date since = Date.from(LocalDate.now()
                .with(DayOfWeek.MONDAY)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(LocalDate.now()
                .with(DayOfWeek.SUNDAY)
                .atTime(23, 59)
                .atZone(ZoneId.systemDefault()).toInstant());
        List<Data> dailyData = dao.getBetweenByIdentifier(since, until, Identifier.SURVEY);
        ArrayList<ArrayList<Entry>> entries = new ArrayList<>();
        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(i -> entries.add(new ArrayList<Entry>()));
        dailyData.stream()
                .collect(Collectors.groupingBy(
                        d -> d.timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getDayOfWeek().getValue(),
                        LinkedHashMap::new,
                        Collectors.toList())).forEach((hour, data) -> {
                            System.out.println(hour);
                    ArrayList<Integer> accs = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
                    data.forEach(data1 -> {
                        Map<String, Object> dataSet = data1.dataPoint.<Map<String, Object>>getData().get(0);
                        Map<String, Double> dd = (Map<String, Double>) dataSet.get("surveyData");
                        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                            if(dd.containsKey(String.valueOf(integer))) {
                                accs.set(integer, accs.get(integer) + dd.get(String.valueOf(integer)).intValue());
                            }
                        });
                    });
                    Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                        entries.get(integer).add(new Entry(hour, accs.get(integer)/data.size()));
                    });
                });
        return entries;
    }

    private ArrayList<ArrayList<Entry>> createMoodMonthlyEntryList(DataDao dao) {
        LocalDate init = LocalDate.now();
        Date since = Date.from(init
                .withDayOfMonth(1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        Date until = Date.from(init
                .withDayOfMonth(init.getMonth().length(init.isLeapYear()))
                .atTime(23, 59)
                .atZone(ZoneId.systemDefault()).toInstant());
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfMonth();
        List<Data> dailyData = dao.getBetweenByIdentifier(since, until, Identifier.SURVEY);
        ArrayList<ArrayList<Entry>> entries = new ArrayList<>();
        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(i -> entries.add(new ArrayList<Entry>()));
        dailyData.stream()
                .collect(Collectors.groupingBy(
                        d -> d.timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().get(woy),
                        LinkedHashMap::new,
                        Collectors.toList())).forEach((hour, data) -> {
                    System.out.println(hour);
                    ArrayList<Integer> accs = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
                    data.forEach(data1 -> {
                        Map<String, Object> dataSet = data1.dataPoint.<Map<String, Object>>getData().get(0);
                        Map<String, Double> dd = (Map<String, Double>) dataSet.get("surveyData");
                        Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                            if(dd.containsKey(String.valueOf(integer))) {
                                accs.set(integer, accs.get(integer) + dd.get(String.valueOf(integer)).intValue());
                            }
                        });
                    });
                    Arrays.asList(0, 1, 2, 3, 4, 5).stream().forEach(integer -> {
                        entries.get(integer).add(new Entry(hour, accs.get(integer)/data.size()));
                    });
                });
        return entries;
    }
}
