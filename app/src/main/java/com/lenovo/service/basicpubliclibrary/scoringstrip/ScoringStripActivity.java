package com.lenovo.service.basicpubliclibrary.scoringstrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;

public class ScoringStripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring_strip);
        CustomRatingbar ratingbar = (CustomRatingbar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new CustomRatingbar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(int rating) {
                //滑动的回调
            }
        });
    }
}
