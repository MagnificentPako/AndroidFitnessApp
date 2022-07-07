package de.rub.cs.selab22.a14.fragments.survey;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class SurveyViewModel extends ViewModel {
    private final MutableLiveData<Map<Integer, Integer>> surveyData = new MutableLiveData<>();

    public void setSurveyData(Map<Integer, Integer> data) {
        surveyData.setValue(data);
    }

    public LiveData<Map<Integer, Integer>> getSurveyData() {
        if (surveyData.getValue() == null) {
            surveyData.setValue(new HashMap());
        }
        return surveyData;
    }
}
