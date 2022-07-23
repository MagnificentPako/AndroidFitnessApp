package de.rub.cs.selab22.a14.fragments.survey;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Map;

import de.rub.cs.selab22.a14.R;

public class SocialContextFragment extends Fragment {

    public SocialContextFragment() {
        super(R.layout.activity_social_context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();

        TextView personen = view.findViewById(R.id.personen);
        Spinner spinner = view.findViewById(R.id.spinner2);
        RadioGroup radioGroup = view.findViewById(R.id.radiogroup_sc);
        SeekBar social_context_seekbar = view.findViewById(R.id.social_context_seekbar);
        TextView textViewOne = view.findViewById(R.id.textView12);
        Button nextButton = view.findViewById(R.id.next_button_social_context);
        Button backButton = view.findViewById(R.id.back_button_social_context);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch(checkedId) {
                case R.id.yes_radioButton:
                    personen.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    surveyData.put(8, 0);
                    break;
                case R.id.no_radioButton:
                    personen.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    surveyData.put(8, 1);
                    break;
            }
            model.setSurveyData(surveyData);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Wählen Sie aus:")){}
                else {
                    String text3 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(view.getContext(), text3, Toast.LENGTH_SHORT).show();
                    surveyData.put(9, position);
                    model.setSurveyData(surveyData);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        social_context_seekbar.getThumb().setAlpha(0);

        social_context_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser) {
                if (progress1 == 0) {
                    textViewOne.setText(progress1 + "%" +"trifft überhaupt nicht zu " );
                }
                else if (progress1 == 100) {
                    textViewOne.setText( progress1 + "%" +"trifft völlig  zu " );
                }
                else{
                    textViewOne.setText(progress1 + "%");
                }
                surveyData.put(10, progress1);
                model.setSurveyData(surveyData);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                social_context_seekbar.getThumb().setAlpha(255);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        model.getSurveyData().observe(getViewLifecycleOwner(), data -> {
            if(data.containsKey(8)) {
                radioGroup.check(data.get(8) == 1 ? R.id.no_radioButton : R.id.yes_radioButton);
            }
            if(data.containsKey(9)) {
                spinner.setSelection(data.get(9));
            }
            if(data.containsKey(10)) {
                social_context_seekbar.setProgress(data.get(10));
            }
        });

        backButton.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
        nextButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_socialContextFragment_to_contextFragment));
    }
}
