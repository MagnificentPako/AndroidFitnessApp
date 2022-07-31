package de.rub.cs.selab22.a14.fragments.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.helper.ActivityRecorder;

public class RecordFragment extends Fragment {

    private Chronometer chronometer;


    public RecordFragment() {
        super(R.layout.activity_record_activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button startButton = view.findViewById(R.id.recordactivity_button);
        Button stopButton = view.findViewById(R.id.stopactivity_button);

        TextView timerText = view.findViewById(R.id.timer_value);

        if (ActivityRecorder.INSTANCE.isRecording()) {
            chronometer.setBase(SystemClock.elapsedRealtime() - ActivityRecorder.INSTANCE.startTime());
            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);
        }

        startButton.setOnClickListener(v -> {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            startButton.setVisibility(View.GONE);
            stopButton.setVisibility(View.VISIBLE);
            ActivityRecorder.INSTANCE.startRecording();
        });

        stopButton.setOnClickListener(v -> {
            chronometer.stop();
            startButton.setVisibility(View.VISIBLE);
            stopButton.setVisibility(View.GONE);
            ActivityRecorder.INSTANCE.stopRecording();
            ActivityRecorder.INSTANCE.reset();
        });
    }


}
