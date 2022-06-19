package de.rub.cs.selab22.a14.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.ViewPumpAppCompatDelegate;
import androidx.preference.PreferenceManager;

import java.util.Locale;

import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.reword.Reword;

public abstract class I18nAppCompatActivity extends AppCompatActivity {

    private AppCompatDelegate delegate = null;

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        if(delegate == null) {
            delegate = new ViewPumpAppCompatDelegate(super.getDelegate(), this, AppLocale::wrap);
        }
        return delegate;
    }

    @Override
    public void onResume() {
        super.onResume();
        Reword.reword(getWindow().getDecorView().findViewById(android.R.id.content));
    }

}
