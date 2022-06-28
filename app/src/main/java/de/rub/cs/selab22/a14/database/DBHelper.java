package de.rub.cs.selab22.a14.database;

import android.content.Context;

import androidx.room.Room;

import de.rub.cs.selab22.a14.database.daos.ActivityDao;
import de.rub.cs.selab22.a14.database.daos.DataDao;

public class DBHelper {

    public static DBHelper INSTANCE;
    AppDatabase database;

    public DBHelper(Context ctx) {
        database = Room.databaseBuilder(ctx, AppDatabase.class, "22a14db")
                    .allowMainThreadQueries()
                    .build();
    }

    public static void init(Context ctx) {
        if(INSTANCE == null) {
            INSTANCE = new DBHelper(ctx);
        }
    }

    public DataDao getDataDao() {
        return database.dataDao();
    }

    public ActivityDao getActivityDao() {
        return database.activityDao();
    }
}
