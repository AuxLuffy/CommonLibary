package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.cache.CacheFileActivity;
import com.lenovo.service.basicpubliclibrary.cache.DBActivity;
import com.lenovo.service.basicpubliclibrary.cache.SPActivity;
import com.lenovo.service.basicpubliclibrary.cachedemo.LazyCacheActivity;
import com.lenovo.service.basicpubliclibrary.cupboard.CupboardActivity;
import com.lenovo.service.basicpubliclibrary.dbflow.DBFlowActivity;
import com.lenovo.service.basicpubliclibrary.greendao.GreenDaoDemoActivity;
import com.lenovo.service.basicpubliclibrary.litepal.activity.LitepalActivity;
import com.lenovo.service.basicpubliclibrary.ormlite.OrmLiteActivity;
import com.lenovo.service.basicpubliclibrary.properties.PreferencesActivity;
import com.lenovo.service.basicpubliclibrary.realm.RealmActivity;
import com.lenovo.service.basicpubliclibrary.sugar.ScanSDActivity;
import com.lenovo.service.basicpubliclibrary.sugar.SugarActivity;
import com.lenovo.service.basicpubliclibrary.tray.TrayActivity;
import com.lenovo.service.basicpubliclibrary.xmlparse.PullParseActivity;
import com.lenovo.service.basicpubliclibrary.xmlparse.SaxParseActivity;
import com.lenovo.service.basicpubliclibrary.xmlparse.XmlParseActivity;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ormlite;
    private TextView tvDBFlow;
    private TextView greenDao;
    private TextView sugar;
    private TextView realm;
    private TextView cupboard;
    private TextView litepal;
    private TextView tray;
    private TextView preferences;
    private TextView scan_sd_mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ormlite = (TextView) findViewById(R.id.ormlite);
        greenDao = (TextView) findViewById(R.id.greenDao);
        tvDBFlow = (TextView) findViewById(R.id.tvDBFlow);
        sugar = (TextView) findViewById(R.id.sugar);
        realm = (TextView) findViewById(R.id.realm);
        scan_sd_mp3 = (TextView) findViewById(R.id.scan_sd_mp3);

        cupboard = (TextView) findViewById(R.id.cupboard);
        litepal = (TextView) findViewById(R.id.litepal);
        tray = (TextView) findViewById(R.id.tray);
        preferences = (TextView) findViewById(R.id.preferences);

        setOnClickListener();
    }

    private void setOnClickListener() {
        ormlite.setOnClickListener(this);
        greenDao.setOnClickListener(this);
        tvDBFlow.setOnClickListener(this);
        sugar.setOnClickListener(this);
        realm.setOnClickListener(this);
        cupboard.setOnClickListener(this);
        litepal.setOnClickListener(this);
        tray.setOnClickListener(this);
        preferences.setOnClickListener(this);
        scan_sd_mp3.setOnClickListener(this);
        findViewById(R.id.tv_xmldomparse).setOnClickListener(this);
        findViewById(R.id.tv_xmlpullparse).setOnClickListener(this);
        findViewById(R.id.tv_xmlsaxparse).setOnClickListener(this);
        findViewById(R.id.tv_cache).setOnClickListener(this);
        findViewById(R.id.tv_FileCache).setOnClickListener(this);
        findViewById(R.id.tv_sp).setOnClickListener(this);
        findViewById(R.id.tv_db).setOnClickListener(this);

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
            case R.id.litepal:
                Intent litepal = new Intent(this, LitepalActivity.class);
                startActivity(litepal);
                break;
            case R.id.tray:
                Intent tray = new Intent(this, TrayActivity.class);
                startActivity(tray);
                break;
            case R.id.preferences:
                Intent preferences = new Intent(this, PreferencesActivity.class);
                startActivity(preferences);
                break;
            case R.id.scan_sd_mp3:
                Intent scan_mp3 = new Intent(this, ScanSDActivity.class);
                startActivity(scan_mp3);
                break;
            case R.id.tv_xmldomparse:
                Intent xmlParse = new Intent(this, XmlParseActivity.class);
                startActivity(xmlParse);
                break;
            case R.id.tv_xmlpullparse:
                Intent xmlpullParse = new Intent(this, PullParseActivity.class);
                startActivity(xmlpullParse);
                break;
            case R.id.tv_xmlsaxparse:
                Intent xmlsaxParse = new Intent(this, SaxParseActivity.class);
                startActivity(xmlsaxParse);
                break;
            case R.id.tv_cache:
                Intent intentt = new Intent(this, LazyCacheActivity.class);
                startActivity(intentt);
                break;
            case R.id.tv_FileCache:
                Intent cacheIntent = new Intent(this, CacheFileActivity.class);
                startActivity(cacheIntent);
                break;
            case R.id.tv_sp:
                Intent spIntent = new Intent(this, SPActivity.class);
                startActivity(spIntent);
                break;
            case R.id.tv_db:
                Intent dbIntent = new Intent(this, DBActivity.class);
                startActivity(dbIntent);
                break;
        }
    }
}
