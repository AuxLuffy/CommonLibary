package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.lenovo.service.basicpubliclibrary.cupboard.CupboardActivity;
import com.lenovo.service.basicpubliclibrary.dbflow.DBFlowActivity;
import com.lenovo.service.basicpubliclibrary.greendao.GreenDaoDemoActivity;
import com.lenovo.service.basicpubliclibrary.ormlite.OrmLiteActivity;
import com.lenovo.service.basicpubliclibrary.realm.RealmActivity;
import com.lenovo.service.basicpubliclibrary.sugar.SugarActivity;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ormlite;
    private TextView tvDBFlow;
    private TextView greenDao;
    private TextView sugar;
    private TextView realm;
    private TextView cupboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ormlite = (TextView) findViewById(R.id.ormlite);
        greenDao = (TextView) findViewById(R.id.greenDao);
        tvDBFlow = (TextView) findViewById(R.id.tvDBFlow);
        sugar = (TextView) findViewById(R.id.sugar);
        realm = (TextView) findViewById(R.id.realm);
        cupboard = (TextView) findViewById(R.id.cupboard);

        setOnClickListener();
    }

    private void setOnClickListener() {
        ormlite.setOnClickListener(this);
        greenDao.setOnClickListener(this);
        tvDBFlow.setOnClickListener(this);
        sugar.setOnClickListener(this);
        realm.setOnClickListener(this);
        cupboard.setOnClickListener(this);

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
            case R.id.tvDBFlow:
                Intent dbflow = new Intent(this, DBFlowActivity.class);
                startActivity(dbflow);
                break;
            case R.id.sugar:
                Intent sugar = new Intent(this, SugarActivity.class);
                startActivity(sugar);
                break;
            case R.id.realm:
                Intent realm = new Intent(this, RealmActivity.class);
                startActivity(realm);
                break;
            case R.id.cupboard:
                Intent cupboard = new Intent(this, CupboardActivity.class);
                startActivity(cupboard);
                break;
        }
    }
}
