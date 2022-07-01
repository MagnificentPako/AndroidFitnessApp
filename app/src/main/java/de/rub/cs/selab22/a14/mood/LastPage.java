package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.rub.cs.selab22.a14.R;

public class LastPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
    }
    public void done_button(View view){
        Intent intent = new Intent(LastPage.this, MoodActivity.class);
        startActivity(intent);
    }
}