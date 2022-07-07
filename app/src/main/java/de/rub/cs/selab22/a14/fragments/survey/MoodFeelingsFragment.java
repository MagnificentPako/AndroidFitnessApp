package de.rub.cs.selab22.a14.fragments.survey;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.helper.Triple;

public class MoodFeelingsFragment extends Fragment {

    public MoodFeelingsFragment() {
        super(R.layout.activity_mood_feelings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();
        Arrays.asList( new Triple(R.id.seekBar1, R.id.rating_text1, 0)
                     , new Triple(R.id.seekBar2, R.id.rating_text2, 1)
                     , new Triple(R.id.seekBar3, R.id.rating_text3, 2)
                     , new Triple(R.id.seekBar4, R.id.rating_text4, 3)
                     , new Triple(R.id.seekBar5, R.id.rating_text5, 4)
                     , new Triple(R.id.seekBar6, R.id.rating_text6, 5))
        .stream().forEach((y) -> {
            Triple<Integer, Integer, Integer> x = y;
            SeekBar seekbar = view.findViewById(x.getFirst());
            TextView textview = view.findViewById( x.getSecond());
            seekbar.getThumb().setAlpha(0);

            model.getSurveyData().observe(getViewLifecycleOwner(), item -> {
                if(item.containsKey(x.getThird())) {
                    seekbar.setProgress(item.get(x.getThird()));
                }
            });

            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textview.setText(progress + "%");
                    surveyData.put(x.getThird(), progress);
                    model.setSurveyData(surveyData);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    seekbar.getThumb().setAlpha(255);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        });
        view.findViewById(R.id.feelings_next_button).setOnClickListener((v) -> {
            Navigation.findNavController(view).navigate(R.id.action_feelings_to_appraisal);
        });
    }
}
