package de.rub.cs.selab22.a14.fragments.survey;


import java.util.Map;

public class SurveyData {
    public Map<Integer, Integer> surveyData;
    public String contextText;
    public String notes;

    public SurveyData(Map<Integer, Integer> data, String ctx, String notes) {
        this.surveyData = data;
        this.contextText = ctx;
        this.notes = notes;
    }
}
