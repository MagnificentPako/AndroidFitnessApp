package de.rub.cs.selab22.a14.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.UserIdManager;
import de.rub.cs.selab22.a14.navigation.SurveyActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        TextView uuid_text = rootView.findViewById(R.id.userid_text);
        uuid_text.setText("User ID: " + UserIdManager.INSTANCE.getID().toString());
        rootView.findViewById(R.id.home_survey_button).setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), SurveyActivity.class);
            startActivity(i);
        });
        return rootView;
    }

}
