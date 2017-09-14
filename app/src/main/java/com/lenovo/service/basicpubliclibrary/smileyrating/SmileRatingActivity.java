package com.lenovo.service.basicpubliclibrary.smileyrating;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by tangrenmei on 2017/9/14.
 */

public class SmileRatingActivity extends AppCompatActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {

    private static final String TAG = "SmileRatingActivity";

    private SmileRating mSmileRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smilerating);
        mSmileRating = (SmileRating) findViewById(R.id.ratingView);
        mSmileRating.setOnSmileySelectionListener(this);
        mSmileRating.setOnRatingSelectedListener(this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "MetalMacabre.ttf");
        mSmileRating.setTypeface(typeface);
    }

    @Override
    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                Log.i(TAG, "Bad");
                break;
            case SmileRating.GOOD:
                Log.i(TAG, "Good");
                break;
            case SmileRating.GREAT:
                Log.i(TAG, "Great");
                break;
            case SmileRating.OKAY:
                Log.i(TAG, "Okay");
                break;
            case SmileRating.TERRIBLE:
                Log.i(TAG, "Terrible");
                break;
            case SmileRating.NONE:
                Log.i(TAG, "None");
                break;
        }
    }

    @Override
    public void onRatingSelected(int level, boolean reselected) {
        Log.i(TAG, "Rated as: " + level + " - " + reselected);
    }
}
