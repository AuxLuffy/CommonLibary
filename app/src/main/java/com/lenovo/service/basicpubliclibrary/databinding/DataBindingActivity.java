package com.lenovo.service.basicpubliclibrary.databinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.lenovo.service.basicpubliclibrary.R;

public class DataBindingActivity extends AppCompatActivity {


    private MainBinding bind;
    private MainModel mainModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.main, null, true);
        setContentView(view);
        bind = MainBinding.bind(view);
        mainModel = new MainModel(this);
        bind.setData(mainModel);
        attachButtonListener();
    }

    private void attachButtonListener() {
        bind.loginOrCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainModel.loginClicked();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ensureModelDataIsLodaed();
    }

    private void ensureModelDataIsLodaed() {
        if (!mainModel.isLoaded()) {
            mainModel.loadAsync();
        }
    }
}
