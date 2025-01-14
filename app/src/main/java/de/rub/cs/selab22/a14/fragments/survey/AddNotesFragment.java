package de.rub.cs.selab22.a14.fragments.survey;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.entities.Data;

public class AddNotesFragment extends Fragment {

    public AddNotesFragment() {
        super(R.layout.activity_add_notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText text = view.findViewById(R.id.notes_text_view);
        Button nextButton = view.findViewById(R.id.next_button_addnotes);
        Button backButton = view.findViewById(R.id.back_button_addnotes);

        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setNotes(text.getText().toString());
            }
        });

        model.getNotes().observe(getViewLifecycleOwner(), notes -> {
            if(!text.getText().toString().equals(notes)) {
                text.setText(notes);
            }
        });
        backButton.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
        nextButton.setOnClickListener(v -> {
            DataDao dataDao = DBHelper.INSTANCE.getDataDao();
            SurveyData surveyData = new SurveyData(model.getSurveyData().getValue(), model.getContextText().getValue(), model.getNotes().getValue());
            Data data = new Data(new DataPoint(surveyData), Identifier.SURVEY);
            dataDao.insertAll(data);
            Navigation.findNavController(view).popBackStack(R.id.bottom_home, false);
        });
    }

}
