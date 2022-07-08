package de.rub.cs.selab22.a14.fragments.survey;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class SurveyViewModel extends ViewModel {
    private final MutableLiveData<Map<Integer, Integer>> surveyData = new MutableLiveData<>();
    private final MutableLiveData<String> contextText = new MutableLiveData<>();
    private final MutableLiveData<String> notes = new MutableLiveData<>();

    public void setSurveyData(Map<Integer, Integer> data) {
        surveyData.setValue(data);
    }

    public void setContextText(String s) {
        contextText.setValue(s);
    }

    public void setNotes(String s) {
        notes.setValue(s);
    }

    public LiveData<Map<Integer, Integer>> getSurveyData() {
        if (surveyData.getValue() == null) {
            surveyData.setValue(new HashMap());
        }
        return surveyData;
    }

    public LiveData<String> getContextText() {
        if(contextText.getValue() == null) {
            contextText.setValue("");
        }
        return contextText;
    }

    public LiveData<String> getNotes() {
        if(notes.getValue() == null) {
            notes.setValue("");
        }
        return notes;
    }

}
