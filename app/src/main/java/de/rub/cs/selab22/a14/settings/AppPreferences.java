package de.rub.cs.selab22.a14.settings;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.fragments.survey.MoodFeelingsFragment;
import de.rub.cs.selab22.a14.notification.MyNotificationCenter;
import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.reword.Reword;

public class AppPreferences extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        Context ctx = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(ctx);

        /*
        notificationPref.setOnPreferenceChangeListener((preference, newValue) -> {
            if(newValue == "nv"){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 2);
            }
            else if(newValue == "1tms"){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 2);
                MyNotificationCenter.INSTANCE.scheduleInexactRepeatingNotification(this,
                        "", "", new Intent(this.getContext()., MoodFeelingsFragment.class),
                        0, AlarmManager.INTERVAL_DAY);

            }
            else if(newValue == "2tms"){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 2);
                MyNotificationCenter.INSTANCE.scheduleInexactRepeatingNotification(this,
                        "", "", new Intent(this.getClass(), MoodFeelingsFragment.class),
                        0, AlarmManager.INTERVAL_HALF_DAY);
            }

            return true;
        });
        */

        ListPreference notificationPref = new ListPreference(ctx);
        notificationPref.setKey("settingsnotifications");
        notificationPref.setTitle(R.string.settings_notifications_description);
        notificationPref.setEntries(R.array.notification_names);
        notificationPref.setEntryValues(R.array.notification_id);

        Preference feedbackPref = new Preference(ctx);
        feedbackPref.setKey("feedback");
        feedbackPref.setTitle(R.string.settings_feedback_title);
        feedbackPref.setSummary(R.string.settings_feedback_description);

        Preference userIdPref = new Preference(ctx);
        userIdPref.setTitle("User ID");
        userIdPref.setSummary(UserIdManager.INSTANCE.getID().toString());
        userIdPref.setEnabled(false);

        ListPreference localePref = new ListPreference(ctx);
        localePref.setKey("locale");
        localePref.setTitle(R.string.settings_locale_title);
        localePref.setEntries(R.array.language_names);
        localePref.setEntryValues(R.array.language_id);
        localePref.setOnPreferenceChangeListener((preference, newValue) -> {
            if(preference.getKey().equals("locale")) {
                AppLocale.setDesiredLocale(new Locale((String) newValue));
                Reword.reword(getActivity().getWindow().getDecorView().findViewById(android.R.id.content));
                Navigation.findNavController(getView()).navigate(R.id.action_appPreferences_self, savedInstanceState);
            }
            return true;
        });

        screen.addPreference(notificationPref);
        screen.addPreference(feedbackPref);
        screen.addPreference(localePref);
        screen.addPreference(userIdPref);

        setPreferenceScreen(screen);

    }

}
