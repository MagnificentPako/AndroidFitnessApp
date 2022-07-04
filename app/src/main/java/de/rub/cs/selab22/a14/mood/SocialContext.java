package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.rub.cs.selab22.a14.R;

public class SocialContext extends AppCompatActivity {

    SeekBar social_context_seekbar;
    TextView textViewOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_context);
        TextView personen = findViewById(R.id.personen);
        Spinner spinner = findViewById(R.id.spinner2);
        RadioGroup radioGroup =  findViewById(R.id.radiogroup_sc);

        // radio_Button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){


                    case R.id.yes_radioButton:

                        personen.setVisibility(View.GONE);
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(SocialContext.this, "Ja", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.no_radioButton:
                        personen.setVisibility(View.VISIBLE);
                        spinner.setVisibility(View.VISIBLE);
                        Toast.makeText(SocialContext.this, "Nein ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.persons,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Wählen Sie aus:")){}
                else {
                    String text3 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), text3, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        social_context_seekbar = findViewById(R.id.social_context_seekbar);
        textViewOne = findViewById(R.id.textView12);

        final Handler social_context_seekbar_Handler = new Handler(Looper.getMainLooper());
        social_context_seekbar.getThumb().setAlpha(0);

        social_context_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser) {
                if (progress1 == 0) {
                    textViewOne.setText(progress1 + "%" +"trifft überhaupt nicht zu " );
                }
                else if (progress1 == 100) {
                    textViewOne.setText( progress1 + "%" +"trifft völlig  zu " );
                }
                else{
                    textViewOne.setText(progress1 + "%");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                social_context_seekbar_Handler.removeCallbacksAndMessages(null);
                social_context_seekbar.getThumb().setAlpha(255);



            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button nextButton = findViewById(R.id.next_button_social_context);
        nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.back_button_social_context);
        backButton.setOnClickListener(view -> goToPreviousPage());




    }


    public void goToNextPage() {
        Intent intent = new Intent(this, Context.class);
        startActivity(intent);
    }

    public void goToPreviousPage() {
        Intent intent = new Intent(this, EventAppraisal.class);
        startActivity(intent);
    }
}