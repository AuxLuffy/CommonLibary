package com.lenovo.service.basicpubliclibrary.panoramimageview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

/**
 * Created by tangrenmei on 2017/9/21.
 */

public class PanoramImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panoramimage);
    }

    public void openRecyclerViewSample(View view) {
        startActivity(new Intent(this, RecyclerViewSampleActivity.class));
    }

    public void openVerticalSample(View view) {
        startActivity(new Intent(this, VerticalSampleActivity.class));
    }

    public void openHorizontalSample(View view) {
        startActivity(new Intent(this, HorizontalSampleActivity.class));
    }
}
