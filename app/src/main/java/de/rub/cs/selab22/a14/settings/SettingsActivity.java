package de.rub.cs.selab22.a14.settings;

import android.os.Bundle;

import de.rub.cs.selab22.a14.R;

public class SettingsActivity extends I18nAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new AppPreferences())
                .commit();
    }

}
