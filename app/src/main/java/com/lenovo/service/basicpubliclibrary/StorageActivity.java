package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.greendao.GreenDaoDemoActivity;
import com.lenovo.service.basicpubliclibrary.ormlite.OrmLiteActivity;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ormlite;
    private TextView greenDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ormlite = (TextView) findViewById(R.id.ormlite);
        greenDao = (TextView) findViewById(R.id.greenDao);

        setOnClickListener();
    }

    private void setOnClickListener() {
        ormlite.setOnClickListener(this);
        greenDao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ormlite:
                Intent intent = new Intent(this, OrmLiteActivity.class);
                startActivity(intent);
                break;
            case R.id.greenDao:
                Intent green = new Intent(this, GreenDaoDemoActivity.class);
                startActivity(green);
                break;
        }
    }
}
