package com.lenovo.service.basicpubliclibrary.counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.Random;

public class CounterActivity extends AppCompatActivity {

    private Random mRandom;
    private CounterView counterView;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        mRandom = new Random(); //随机数

        counterView = (CounterView) findViewById(R.id.counterView);
        counterView.setText("Random");
        counterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterView.showAnimation(random(10000), CounterView.getDecimalFormat(count));

                if (count < 4) {
                    count++;
                } else {
                    count = 0;
                }
            }
        });


    }


    public int random(int n) {
        return mRandom.nextInt(n);
    }
}
