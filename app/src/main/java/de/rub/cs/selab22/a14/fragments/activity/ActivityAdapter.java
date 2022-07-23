package de.rub.cs.selab22.a14.fragments.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.entities.DBActivity;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<DBActivity> localDataSet;
    private Locale locale;

    public ActivityAdapter(List<DBActivity> dataSet, Locale l) {
        this.localDataSet = dataSet;
        this.locale = l;
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

        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm a", locale);
        DBActivity activity = localDataSet.get(localDataSet.size() - position - 1);
        holder.getTitle().setText(format.format(activity.start) + " - " + format.format(activity.end));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.activity_row_item_title);
        }

        public TextView getTitle() {
            return title;
        }
    }

}
