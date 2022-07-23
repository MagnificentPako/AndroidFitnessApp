package de.rub.cs.selab22.a14;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import de.rub.cs.selab22.a14.mood.MoodActivity;
import de.rub.cs.selab22.a14.mood.MoodFeelings;
import de.rub.cs.selab22.a14.settings.I18nAppCompatActivity;
import de.rub.cs.selab22.a14.settings.SettingsActivity;

public class MainActivity extends I18nAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button settingsButton = findViewById(R.id.settings_button);
        Button physicalButton = findViewById(R.id.physical_main_button);
        Button moodButton = findViewById(R.id.mood_button);

        settingsButton.setOnClickListener(view -> goToSettings());
        physicalButton.setOnClickListener(view -> goToPhysicalMain());
        moodButton.setOnClickListener(view -> goToMoodActivity());
    }

    private void goToSettings() {
        Intent switchActivity = new Intent(this, SettingsActivity.class);
        startActivity(switchActivity);
    }

    private void goToMoodActivity() {
        Intent switchActivity = new Intent(this, MoodActivity.class);
        startActivity(switchActivity);
    }
    private void goToPhysicalMain() {
        Intent switchActivity = new Intent(this, Physical_main.class);
        startActivity(switchActivity);
    }
}   
