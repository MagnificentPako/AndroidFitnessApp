package de.rub.cs.selab22.a14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.rub.cs.selab22.a14.mood.MoodFeelings;
import de.rub.cs.selab22.a14.settings.I18nAppCompatActivity;
import de.rub.cs.selab22.a14.settings.SettingsActivity;

public class MainActivity extends I18nAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> goToSettings());
        // mood
        Button moodButton = findViewById(R.id.mood_button);
        moodButton.setOnClickListener(view -> goToMoodActivity());
    }

    private void goToSettings() {
        Intent switchActivity = new Intent(this, SettingsActivity.class);
        startActivity(switchActivity);
    }


    private void goToMoodActivity() {
        Intent switchActivity = new Intent(this, MoodFeelings.class);
        startActivity(switchActivity);
    }
}