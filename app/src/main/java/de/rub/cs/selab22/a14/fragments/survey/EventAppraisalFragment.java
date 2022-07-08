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
import de.rub.cs.selab22.a14.helper.Tuple;

public class EventAppraisalFragment extends Fragment {

    public EventAppraisalFragment() {
        super(R.layout.activity_event_appraisal);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();
        Arrays.asList( new Triple(R.id.seekBarQuestion1, R.id.mood_textview_question1, 6)
                     , new Triple(R.id.seekBarQuestion2, R.id.mood_textview_question2, 7))
        .stream().forEach(x -> {
            SeekBar seekBar = view.findViewById((int) x.getFirst());
            TextView textView = view.findViewById((int) x.getSecond());

            model.getSurveyData().observe(getViewLifecycleOwner(), item -> {
                if(item.containsKey(x.getThird())) {
                    seekBar.setProgress(item.get((int) x.getThird()));
                }
            });

            seekBar.getThumb().setAlpha(0);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    surveyData.put((int) x.getThird(), progress);
                    model.setSurveyData(surveyData);
                    if (progress == 0) {
                        textView.setText(progress + "%" +"trifft überhaupt nicht zu " );
                    }
                    else if (progress == 100) {
                        textView.setText( progress + "%" +"trifft völlig  zu " );
                    }
                    else{
                        textView.setText(progress + "%");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    seekBar.getThumb().setAlpha(255);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        });
        view.findViewById(R.id.button_appraisal_back).setOnClickListener(x ->
                Navigation.findNavController(view).popBackStack());
        view.findViewById(R.id.button_appraisal_next).setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_eventAppraisalFragment2_to_socialContextFragment)
        );
    }
}
