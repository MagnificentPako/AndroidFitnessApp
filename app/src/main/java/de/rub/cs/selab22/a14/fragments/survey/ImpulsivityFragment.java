package de.rub.cs.selab22.a14.fragments.survey;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Arrays;
import java.util.Map;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.helper.Tuple;

public class ImpulsivityFragment extends Fragment {

    public ImpulsivityFragment() {
        super(R.layout.activity_impulsivity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();

        Button nextButton = view.findViewById(R.id.next_button_imp);
        Button backButton = view.findViewById(R.id.back_button_imp);

        RadioGroup likert_scale1 = view.findViewById(R.id.likert_scale);
        RadioGroup likert_scale2 = view.findViewById(R.id.likert_scale2);

        Arrays.asList(new Tuple(14, likert_scale1), new Tuple(15, likert_scale2)).stream().forEach(tpl -> {
            ((RadioGroup)tpl.getSecond()).setOnCheckedChangeListener((group, checkedId) -> {
                surveyData.put((int) tpl.getFirst(), group.indexOfChild(group.findViewById(checkedId)));
                model.setSurveyData(surveyData);
            });
        });

        model.getSurveyData().observe(getViewLifecycleOwner(), data -> {
            if(data.containsKey(14)) {
                likert_scale1.check(likert_scale1.getChildAt(data.get(14)).getId());
            }
            if(data.containsKey(15)) {
                likert_scale2.check(likert_scale2.getChildAt(data.get(15)).getId());
            }
        });

        nextButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_impulsivityFragment_to_addNotesFragment));
        backButton.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

    }
}
