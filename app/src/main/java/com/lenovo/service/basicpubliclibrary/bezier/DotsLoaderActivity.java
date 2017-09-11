package com.lenovo.service.basicpubliclibrary.bezier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.widget.DotsLoaderView;

public class DotsLoaderActivity extends AppCompatActivity {

    private DotsLoaderView dotsLoaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dots_loader);
        dotsLoaderView = (DotsLoaderView) findViewById(R.id.dotsLoaderView);
        dotsLoaderView.show();

        //dotsLoaderView.hide();
    }
}
