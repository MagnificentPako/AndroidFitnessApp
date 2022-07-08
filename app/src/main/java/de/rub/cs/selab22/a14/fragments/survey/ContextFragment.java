package de.rub.cs.selab22.a14.fragments.survey;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Map;

import de.rub.cs.selab22.a14.R;

public class ContextFragment extends Fragment {

    public ContextFragment() {
        super(R.layout.activity_context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.editText);
        Spinner spinner_context = view.findViewById(R.id.spinner_context);
        Button nextButton = view.findViewById(R.id.next_button_context);
        Button backButton = view.findViewById(R.id.back_button_context);

        SurveyViewModel model = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);

        Map<Integer,Integer> surveyData = model.getSurveyData().getValue();

        editText.addTextChangedListener(new TextWatcher() {
            String ctxText = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!ctxText.equals(editText.getText().toString())) {
                    ctxText = editText.getText().toString();
                    model.setContextText(ctxText);
                }
            }
        });

        spinner_context.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!(parent.getItemAtPosition(position).equals("Wählen Sie aus:") ||
                        parent.getItemAtPosition(position).equals("Select from: "))) {
                    editText.setVisibility(View.GONE);
                    String text_context = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getContext(), text_context, Toast.LENGTH_SHORT).show();
                    if (parent.getItemAtPosition(position).equals("Sonstiges") ||
                            parent.getItemAtPosition(position).equals("Others")) {
                        editText.setVisibility(View.VISIBLE);
                    }
                    surveyData.put(11, position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        model.getSurveyData().observe(getViewLifecycleOwner(), data -> {
            if(data.containsKey(11)) {
                spinner_context.setSelection(data.get(11));
            }
        });

        model.getContextText().observe(getViewLifecycleOwner(), ctx -> {
            if(!editText.getText().toString().equals(ctx)) {
                editText.setText(ctx);
            }
        });
        backButton.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
        nextButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_contextFragment_to_selfWorthFragment));
    }
}
