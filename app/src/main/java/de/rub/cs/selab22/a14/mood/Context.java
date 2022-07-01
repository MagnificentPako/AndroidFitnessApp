package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import de.rub.cs.selab22.a14.R;


public class Context extends AppCompatActivity {
    private RadioGroup radioGroup;

    Button button;
    Button sonstiges;
    Spinner spinner_context;
    private static final int option0=0;
    private static final int option1=1;
    private static final int option2=2;
    private static final int option3=3;
    private static final int option4=4;
    private static final int option5=5;
    private static final int option6=6;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        EditText editText = findViewById(R.id.editText);

        spinner_context = findViewById(R.id.spinner_context);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.context,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_context.setAdapter(adapter1);
        spinner_context.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Wählen Sie aus:")||
                        parent.getItemAtPosition(position).equals("Select from: ")){}
                else if (parent.getItemAtPosition(position).equals("Sonstiges") ||
                        parent.getItemAtPosition(position).equals("Others"))
                {
                    editText.setVisibility(View.VISIBLE);

                }
                else {
                    String text_context = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), text_context , Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



       // Button nextButton = findViewById(R.id.next_button_context);
        //nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.back_button_context);
        backButton.setOnClickListener(view -> goToPreviousPage());
    }
    /*public void goToNextPage() {
        Intent intent = new Intent(Context.this, SelbstWert.class);
        startActivity(intent);
    }*/

    public void goToPreviousPage() {
        Intent intent = new Intent(Context.this, SocialContext.class);
        startActivity(intent);
    }

    public void others_button(View view) {
    }


}