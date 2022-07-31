package de.rub.cs.selab22.a14;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.LocaleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.Data;
import de.rub.cs.selab22.a14.helper.ActivityRecorder;
import de.rub.cs.selab22.a14.sensors.SensorCenter;
import de.rub.cs.selab22.a14.settings.LocaleHelper;
import de.rub.cs.selab22.a14.settings.Locales;
import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository;
import dev.b3nedikt.reword.RewordInterceptor;
import dev.b3nedikt.viewpump.ViewPump;

public class App extends Application {

    public App() {
        ViewPump.init(RewordInterceptor.INSTANCE);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences preferences = getSharedPreferences("22a14", Context.MODE_PRIVATE);

        AppLocale.setSupportedLocales(Locales.APP_LOCALES);
        AppLocale.setAppLocaleRepository(new SharedPrefsAppLocaleRepository(this));
        ViewPump.init(RewordInterceptor.INSTANCE);
        DBHelper.init(getApplicationContext());
        UserIdManager.init(preferences);
        ActivityRecorder.init(preferences);

        /* For testing purposes only
        DataDao dao = DBHelper.INSTANCE.getDataDao();
        dao.nukeTable();
        for (int i = 0;i < 90; i++)
        {
            Data dt = new Data(new DataPoint<>( (float) ((90*(i+1))%100) ), Identifier.ACCELEROMETER_VECTOR_LENGTH);
            dt.timestamp = Date.from(LocalDateTime.now().minusDays(90-i).atZone(ZoneId.systemDefault()).toInstant());
            dao.insertAll(dt);
        }
         */

        SensorCenter.init(getApplicationContext());
    }

    /* For testing purposes only
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
     */
}
