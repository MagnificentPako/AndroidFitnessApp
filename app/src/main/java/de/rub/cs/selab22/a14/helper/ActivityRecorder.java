package de.rub.cs.selab22.a14.helper;

import android.content.SharedPreferences;

import java.util.Date;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.entities.DBActivity;

public class ActivityRecorder {

    public static ActivityRecorder INSTANCE;
    private SharedPreferences prefs;

    private ActivityRecorder(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public boolean isRecording() {
        return prefs.getBoolean("activityRecording", false);
    }

    public void startRecording() {
        if(!isRecording()) {
            prefs.edit()
                    .putBoolean("activityRecording", true)
                    .putLong("activityStart", new Date().getTime())
                    .commit();
        }
    }

    public void stopRecording() {
        if(isRecording()) {
            prefs.edit()
                .putBoolean("activityRecording", false)
                .commit();
            Date since = new Date(prefs.getLong("activityStart", 0));
            DBActivity activity = new DBActivity(since, new Date());
            DBHelper.INSTANCE.getActivityDao().insertAll(activity);
        }
    }

    public void reset() {
        prefs.edit()
                .putBoolean("activityRecording", false)
                .putLong("activityStart", 0l)
                .commit();
    }

    public static void init(SharedPreferences prefs) {
        if(INSTANCE == null) {
            INSTANCE = new ActivityRecorder(prefs);
        }
    }

}
