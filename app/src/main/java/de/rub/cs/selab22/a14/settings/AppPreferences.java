package de.rub.cs.selab22.a14.settings;

import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;

import de.rub.cs.selab22.a14.App;
import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.fragments.survey.MoodFeelingsFragment;
import de.rub.cs.selab22.a14.notification.MyNotificationCenter;
import de.rub.cs.selab22.a14.sensors.SensorCenter;
import dev.b3nedikt.app_locale.AppLocale;
import dev.b3nedikt.reword.Reword;

public class AppPreferences extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        Context ctx = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(ctx);

        ListPreference notificationPref = new ListPreference(ctx);
        notificationPref.setKey("notificationscenter");
        notificationPref.setTitle(R.string.settings_notifications_description);
        notificationPref.setSummary(R.string.settings_notifications_summary);
        notificationPref.setEntries(R.array.notification_names);
        notificationPref.setEntryValues(R.array.notification_id);
        notificationPref.setOnPreferenceChangeListener((preference, newValue) -> {
            if(newValue.equals("nv")){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 3);
            }
            else if(newValue.equals("1tms")){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 3);
                MyNotificationCenter.INSTANCE.scheduleExactRepeatingNotification(this.getContext(),
                        getString(R.string.notification_title), getString(R.string.notification_text),  24 * 60 * 60 * 1000);
            }
            else if(newValue.equals("2tms")){
                MyNotificationCenter.INSTANCE.cancelAlarm(this.getContext(), 3);
                MyNotificationCenter.INSTANCE.scheduleExactRepeatingNotification(this.getContext(),
                        getString(R.string.notification_title), getString(R.string.notification_text),  12 * 60 * 60 * 1000);
            }
            return true;
        });

        ListPreference setFrequencyPref = new ListPreference(ctx);
        setFrequencyPref.setKey("frequency");
        setFrequencyPref.setTitle(R.string.settings_frequency_description);
        setFrequencyPref.setSummary(R.string.settings_frequency_summary);
        setFrequencyPref.setEntries(R.array.frequency_names);
        setFrequencyPref.setEntryValues(R.array.frequency_id);
        setFrequencyPref.setOnPreferenceChangeListener((preference, newValue) -> {
            if(newValue.equals("high")){
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.accelerometer,
                        SensorCenter.INSTANCE.HIGH_FREQUENCY);
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.stepCounter,
                        SensorCenter.INSTANCE.HIGH_FREQUENCY);
            }
            else if(newValue.equals("normal")){
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.accelerometer,
                        SensorCenter.INSTANCE.NORMAL_FREQUENCY);
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.stepCounter,
                        SensorCenter.INSTANCE.NORMAL_FREQUENCY);

            }
            else if(newValue.equals("low")){
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.accelerometer,
                        SensorCenter.INSTANCE.LOW_FREQUENCY);
                SensorCenter.INSTANCE.setFrequency(SensorCenter.INSTANCE.stepCounter,
                        SensorCenter.INSTANCE.LOW_FREQUENCY);

            }
            return true;
        });

        Preference feedbackPref = new Preference(ctx);
        feedbackPref.setKey("feedback");
        feedbackPref.setTitle(R.string.settings_feedback_title);
        feedbackPref.setSummary(R.string.settings_feedback_description);

        Preference privacyPolicyPref = new Preference(ctx);
        privacyPolicyPref.setTitle(R.string.privacy_policy);
        privacyPolicyPref.setEnabled(true);
        privacyPolicyPref.setOnPreferenceClickListener((preference) -> {
            new AlertDialog.Builder(this.getContext())
                    .setTitle(getString(R.string.privacy_policy))
                    .setMessage(getString(R.string.privacy_policy_text))
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
            return true;
        });

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
        screen.addPreference(setFrequencyPref);
        screen.addPreference(feedbackPref);
        screen.addPreference(localePref);
        screen.addPreference(privacyPolicyPref);
        screen.addPreference(userIdPref);
        setPreferenceScreen(screen);
    }

}
