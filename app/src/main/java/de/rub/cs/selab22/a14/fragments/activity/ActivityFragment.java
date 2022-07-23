package de.rub.cs.selab22.a14.fragments.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.daos.ActivityDao;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.DBActivity;

public class ActivityFragment extends Fragment {

    public ActivityFragment() {
        super(R.layout.activity_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.activity_recycler_view);
        ActivityDao dao = DBHelper.INSTANCE.getActivityDao();
        DBActivity activity = new DBActivity(new Date(), (new Date(System.currentTimeMillis() + 1000000)));
        dao.insertAll(activity);
        List<DBActivity> activityList = dao.getAll();
        System.out.println(activityList);
        Locale l = getActivity().getResources().getConfiguration().getLocales().get(0);
        recyclerView.setAdapter(new ActivityAdapter(activityList, l));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
