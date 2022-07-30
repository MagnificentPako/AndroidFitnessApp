package de.rub.cs.selab22.a14.fragments.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.daos.ActivityDao;
import de.rub.cs.selab22.a14.database.entities.DBActivity;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<DBActivity> localDataSet;
    private Locale locale;
    private FragmentManager fragmentManager;

    public ActivityAdapter(List<DBActivity> dataSet, Locale l, FragmentManager manager) {
        this.localDataSet = dataSet;
        this.locale = l;
        this.fragmentManager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ActivityDao dao = DBHelper.INSTANCE.getActivityDao();
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm a", locale);
        DBActivity activity = localDataSet.get(localDataSet.size() - position - 1);
        holder.getTitle().setText(format.format(activity.start) + " - " + format.format(activity.end));

        holder.getDeleteButton().setOnClickListener((v) -> {
            DBActivity tbd = localDataSet.get(position);
            dao.delete(tbd);
            localDataSet.remove(position);
            notifyItemChanged(position);
            notifyItemRangeChanged(position, localDataSet.size());
            holder.itemView.setVisibility(View.GONE);
        });

        holder.getEditButton().setOnClickListener((v) -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(activity.start);
            MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
            MaterialTimePicker startPicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(cal.get(Calendar.HOUR_OF_DAY))
                    .setMinute((cal.get(Calendar.MINUTE)))
                    .setTitleText("Start")
                    .build();

            cal.setTime(activity.end);
            MaterialTimePicker endPicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(cal.get(Calendar.HOUR_OF_DAY))
                    .setMinute((cal.get(Calendar.MINUTE)))
                    .setTitleText("End")
                    .build();
            CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
            builder.setCalendarConstraints(constraintsBuilder.build());
            builder.setSelection(new Pair<>(activity.start.getTime(), activity.end.getTime()));
            MaterialDatePicker picker = builder.build();
            picker.show(fragmentManager, picker.toString());
            picker.addOnPositiveButtonClickListener((dateR) -> {
                Pair<Long, Long> date = (Pair<Long, Long>) dateR;
                startPicker.show(fragmentManager, startPicker.toString());
                startPicker.addOnPositiveButtonClickListener((startT) -> {
                    endPicker.show(fragmentManager, endPicker.toString());
                    endPicker.addOnPositiveButtonClickListener((endR) -> {
                        Calendar startCal = Calendar.getInstance();
                        startCal.setTime(new Date(date.first));
                        startCal.set(Calendar.HOUR_OF_DAY, startPicker.getHour());
                        startCal.set(Calendar.MINUTE, startPicker.getMinute());
                        Calendar endCal = Calendar.getInstance();
                        endCal.setTime(new Date(date.second));
                        endCal.set(Calendar.HOUR_OF_DAY, endPicker.getHour());
                        endCal.set(Calendar.MINUTE, endPicker.getMinute());
                        activity.start = startCal.getTime();
                        activity.end = endCal.getTime();
                        localDataSet.set(localDataSet.size() - position - 1, activity);
                        notifyItemChanged(position);
                        dao.updateAll(activity);
                    });
                });
            });
        });

   }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final Button deleteButton;
        private final Button editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.activity_row_item_title);
            deleteButton = (Button) itemView.findViewById(R.id.row_delete_button);
            editButton = (Button) itemView.findViewById(R.id.row_edit_button);
        }

        public TextView getTitle() {
            return title;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }

        public Button getEditButton() {
            return editButton;
        }
    }

}
