package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.rub.cs.selab22.a14.MainActivity;
import de.rub.cs.selab22.a14.R;

public class LastPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);

        Button doneButton = findViewById(R.id.done_button);
        doneButton.setOnClickListener(view -> goToMainPage());
    }
    public void goToMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}