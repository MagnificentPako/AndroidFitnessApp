package de.rub.cs.selab22.a14;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.UserIdManager;
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
    }

}
