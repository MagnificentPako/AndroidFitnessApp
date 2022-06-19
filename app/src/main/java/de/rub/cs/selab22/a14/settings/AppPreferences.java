package de.rub.cs.selab22.a14.settings;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;

import de.rub.cs.selab22.a14.MainActivity;
import de.rub.cs.selab22.a14.R;
import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.reword.Reword;

public class AppPreferences extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        Context ctx = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(ctx);

        SwitchPreferenceCompat notificationPref = new SwitchPreferenceCompat(ctx);
        notificationPref.setKey("notifications");
        notificationPref.setTitle(R.string.settings_notifications_description);

        Preference feedbackPref = new Preference(ctx);
        feedbackPref.setKey("feedback");
        feedbackPref.setTitle(R.string.settings_feedback_title);
        feedbackPref.setSummary(R.string.settings_feedback_description);

        ListPreference localePref = new ListPreference(ctx);
        localePref.setKey("locale");
        localePref.setTitle(R.string.settings_locale_title);
        localePref.setOnPreferenceChangeListener(this);
        localePref.setEntries(R.array.language_names);
        localePref.setEntryValues(R.array.language_id);

        screen.addPreference(notificationPref);
        screen.addPreference(feedbackPref);
        screen.addPreference(localePref);

        setPreferenceScreen(screen);
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
        if(preference.getKey().equals("locale")) {
            AppLocale.setDesiredLocale(new Locale((String) newValue));
            Reword.reword(getActivity().getWindow().getDecorView().findViewById(android.R.id.content));
            Intent intent = getActivity().getIntent();
            getActivity().overridePendingTransition(0,0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getActivity().finish();
            getActivity().overridePendingTransition(0,0);
            getActivity().startActivity(intent);
        }
        return true;
    }
}
