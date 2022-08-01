package de.rub.cs.selab22.a14;

import static android.os.Build.VERSION_CODES.R;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.LocaleData;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.ResearchPayload;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.DBActivity;
import de.rub.cs.selab22.a14.database.entities.Data;
import de.rub.cs.selab22.a14.helper.ActivityRecorder;
import de.rub.cs.selab22.a14.notification.MyNotificationCenter;
import de.rub.cs.selab22.a14.sensors.SensorCenter;
import de.rub.cs.selab22.a14.settings.LocaleHelper;
import de.rub.cs.selab22.a14.settings.Locales;
import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository;
import dev.b3nedikt.reword.RewordInterceptor;
import dev.b3nedikt.viewpump.ViewPump;

public class App extends Application {

    private static Context appcontext;
    private static Application sApplication;

    public App() {
        ViewPump.init(RewordInterceptor.INSTANCE);
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getAppContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
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
            Data dt = new Data(new DataPoint<>( (float) ((30*(i+1))%100) ), Identifier.ACCELEROMETER_VECTOR_LENGTH);
            dt.timestamp = Date.from(LocalDateTime.now().minusDays(90-i).atZone(ZoneId.systemDefault()).toInstant());
            dao.insertAll(dt);
        }
         */

        SensorCenter.init(getApplicationContext());

        long lastSentLong = preferences.getLong("lastSubmittedData", -1);
        if(isNetworkAvailable()) {
            if(lastSentLong == -1) {
                sendResearchData(new Date());
                preferences.edit().putLong("lastSubmittedData", new Date().getTime()).commit();
            } else {
                Date d = new Date(lastSentLong);
                Duration duration = Duration.between(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), LocalDateTime.now());
                if(duration.toHours() >= 24) {
                    sendResearchData(d);
                    preferences.edit().putLong("lastSubmittedData", new Date().getTime()).commit();            }
            }
        }

        MyNotificationCenter.init();
    }

    private void sendResearchData(Date d) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://webhook.site/96bff07a-cf88-402d-b671-394b21523fbf";
        List<Data> data = DBHelper.INSTANCE.getDataDao().getSince(d);
        List<DBActivity> activityList = DBHelper.INSTANCE.getActivityDao().getSince(d);
        ResearchPayload payload = new ResearchPayload(data, activityList, UserIdManager.INSTANCE.getID());
        Gson gson = new Gson();
        String s = gson.toJson(payload);
        try {
            JSONObject jsonPayload = new JSONObject(s);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, jsonPayload,
                    response -> {
                        System.out.println(response);
                    },
                    error -> {
                        System.out.println(error);
                    });
            queue.add(req);
            queue.start();
        } catch (Exception e) {

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
