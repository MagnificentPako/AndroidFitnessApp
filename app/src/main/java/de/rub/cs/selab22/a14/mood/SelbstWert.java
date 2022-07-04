package de.rub.cs.selab22.a14.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import de.rub.cs.selab22.a14.R;

public class SelbstWert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selbst_wert);

        TextView rating_scale1 = findViewById(R.id.rating_scale1);
        TextView rating_scale2 = findViewById(R.id.rating_scale2);

        final RatingBar ratingBar1 = findViewById(R.id.ratingBar1);
        final RatingBar ratingBar2 = findViewById(R.id.ratingBar2);


        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                rating_scale1.setText(String.valueOf(v));


            }

        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                rating_scale2.setText(String.valueOf(v));


            }

        });




        Button nextButton = findViewById(R.id.next_button_self_worth);
        nextButton.setOnClickListener(view -> goToNextPage());

        Button backButton = findViewById(R.id.back_button_self_worth);
        backButton.setOnClickListener(view -> goToPreviousPage());
    }
    public void goToNextPage() {
        Intent intent = new Intent(SelbstWert.this, Impulsivity.class);
        startActivity(intent);
    }

    public void goToPreviousPage() {
        Intent intent = new Intent(SelbstWert.this, Context.class);
        startActivity(intent);
    }
}