package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import de.rub.cs.selab22.a14.R;

public class Impulsivity extends AppCompatActivity {

    RadioGroup likert_scale;
    String printText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impulsivity);

        // likert_scale 1

        final RadioButton r0 = findViewById(R.id.radio_button0);
        final RadioButton r1 = findViewById(R.id.radio_button1);
        final RadioButton r2 = findViewById(R.id.radio_button2);
        final RadioButton r3 = findViewById(R.id.radio_button3);
        final RadioButton r4 = findViewById(R.id.radio_button4);
        final RadioButton r5 = findViewById(R.id.radio_button5);
        final RadioButton r6 = findViewById(R.id.radio_button6);

        // radioButton02 is selected

        r0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "0 trifft nicht zu", Toast.LENGTH_SHORT).show();
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
            }
        });

        // radioButton1 is selected
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "1 trifft nicht zu", Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
            }
        });
        // radioButton2 is selected
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "2 trifft eher nicht zu", Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r1.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
            }
        });
        // radioButton3 is selected
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "3 teils/teils", Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
            }
        });
        // radioButton4 is selected
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "4 trifft eher zu", Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
            }
        });
        // radioButton5 is selected
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "5 trifft zu", Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r6.setChecked(false);
            }
        });
        // radioButton6 is selected
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "6 trifft ganz zu" + r6.getText(), Toast.LENGTH_SHORT).show();
                r0.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);


            }
        });


        // likert_scale 2
        final RadioButton r02 = findViewById(R.id.radio_button02);
        final RadioButton r12 = findViewById(R.id.radio_button12);
        final RadioButton r22 = findViewById(R.id.radio_button22);
        final RadioButton r32 = findViewById(R.id.radio_button32);
        final RadioButton r42 = findViewById(R.id.radio_button42);
        final RadioButton r52 = findViewById(R.id.radio_button52);
        final RadioButton r62 = findViewById(R.id.radio_button62);
        final RadioGroup radioGroup = findViewById(R.id.likert_scale2);
        // radioButton0 is selected

        r02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = r02.getText().toString();
                Toast.makeText(Impulsivity.this, text, Toast.LENGTH_SHORT).show();
                r12.setChecked(false);
                r22.setChecked(false);
                r32.setChecked(false);
                r42.setChecked(false);
                r52.setChecked(false);
                r62.setChecked(false);
            }
        });

       // radioButton12 is selected
        r12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "1 trifft nicht zu", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r22.setChecked(false);
                r32.setChecked(false);
                r42.setChecked(false);
                r52.setChecked(false);
                r62.setChecked(false);
            }
        });
        // radioButton22 is selected
        r22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "2 trifft eher nicht zu", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r12.setChecked(false);
                r32.setChecked(false);
                r42.setChecked(false);
                r52.setChecked(false);
                r62.setChecked(false);
            }
        });
        // radioButton3 is selected
        r32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "3 teils/teils", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r12.setChecked(false);
                r22.setChecked(false);
                r42.setChecked(false);
                r52.setChecked(false);
                r62.setChecked(false);
            }
        });
        // radioButton4 is selected
        r42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "4 trifft eher zu", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r12.setChecked(false);
                r22.setChecked(false);
                r32.setChecked(false);
                r52.setChecked(false);
                r62.setChecked(false);
            }
        });
        // radioButton5 is selected
        r52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "5 trifft zu", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r12.setChecked(false);
                r22.setChecked(false);
                r32.setChecked(false);
                r42.setChecked(false);
                r62.setChecked(false);
            }
        });
        // radioButton62 is selected
        r62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Impulsivity.this, "6 trifft ganz zu", Toast.LENGTH_SHORT).show();
                r02.setChecked(false);
                r12.setChecked(false);
                r22.setChecked(false);
                r32.setChecked(false);
                r42.setChecked(false);
                r52.setChecked(false);

            }
        });
        //Button nextButton = findViewById(R.id.next_button_imp);
        //nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.back_button_imp);
        backButton.setOnClickListener(view -> goToPreviousPage());

    }

    /*public void goToNextPage() {
        Intent intent = new Intent(this, AddNotesActivity.class);
        startActivity(intent);
    }*/

    public void goToPreviousPage() {
        Intent intent = new Intent(this, SelbstWert.class);
        startActivity(intent);
    }
}