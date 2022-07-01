package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.rub.cs.selab22.a14.R;

public class AddNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        changeText();



    }

    private void changeText() {
        @SuppressLint("WrongViewCast")
        final EditText text = findViewById(R.id.notes_text_view);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text.getText().clear();
            }
        });

        Button nextButton = findViewById(R.id.next_button_addnotes);
        nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.back_button_addnotes);
        backButton.setOnClickListener(view -> goToPreviousPage());

    }

    public void goToNextPage() {

        Intent intent = new Intent(AddNotesActivity.this, LastPage.class);
        startActivity(intent);
    }

    public void goToPreviousPage() {
        Intent intent = new Intent(AddNotesActivity.this, Impulsivity.class);
        startActivity(intent);
    }

}