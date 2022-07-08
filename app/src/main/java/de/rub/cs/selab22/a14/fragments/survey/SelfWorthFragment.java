package de.rub.cs.selab22.a14.fragments.survey;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Map;

import de.rub.cs.selab22.a14.R;

public class SelfWorthFragment extends Fragment {

    public SelfWorthFragment() {
        super(R.layout.activity_selbst_wert);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();
        Button nextButton = view.findViewById(R.id.next_button_self_worth);
        Button backButton = view.findViewById(R.id.back_button_self_worth);

        TextView rating_scale1 = view.findViewById(R.id.rating_scale1);
        TextView rating_scale2 = view.findViewById(R.id.rating_scale2);

        RatingBar ratingBar1 = view.findViewById(R.id.ratingBar1);
        RatingBar ratingBar2 = view.findViewById(R.id.ratingBar2);

        ratingBar1.setOnRatingBarChangeListener((RatingBar ratingBar, float v, boolean fromUser) -> {
                    rating_scale1.setText(String.valueOf(v));
                    surveyData.put(12,(int) (v * 10));
                    model.setSurveyData(surveyData);
                });

        ratingBar2.setOnRatingBarChangeListener((RatingBar ratingBar, float v, boolean fromUser) -> {
                    rating_scale2.setText(String.valueOf(v));
                    surveyData.put(13, (int) (v * 10));
                    model.setSurveyData(surveyData);
                });

        model.getSurveyData().observe(getViewLifecycleOwner(), data -> {
            if(data.containsKey(12)) {
                ratingBar1.setRating(((float) data.get(12)) / 10);
            }
            if(data.containsKey(13)) {
                ratingBar2.setRating(((float) data.get(13)) / 10);
            }
        });

        nextButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_selfWorthFragment_to_impulsivityFragment));
        backButton.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
    }
}
