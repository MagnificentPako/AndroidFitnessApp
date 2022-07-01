package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.rub.cs.selab22.a14.R;

public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
    }
    public void mood_record(View view) {
        Intent intent = new Intent(MoodActivity.this, MoodFeelings.class);
        startActivity(intent);
    }

    public void notification_button(View view) {
        Intent intent = new Intent(MoodActivity.this, Notification.class);
        startActivity(intent);
    }
}