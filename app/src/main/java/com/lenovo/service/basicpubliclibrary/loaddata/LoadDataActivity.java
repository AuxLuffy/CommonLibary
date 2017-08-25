package com.lenovo.service.basicpubliclibrary.loaddata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;


public class LoadDataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView normalBtn, zhihuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load);

        normalBtn = (TextView) findViewById(R.id.normalBtn);
        zhihuBtn = (TextView) findViewById(R.id.zhihuBtn);

        normalBtn.setOnClickListener(this);
        zhihuBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.normalBtn:
                intent = new Intent(LoadDataActivity.this, Sample1Activity.class);
                break;
            case R.id.zhihuBtn:
                intent = new Intent(LoadDataActivity.this, Sample2Activity.class);
                break;
        }

        startActivity(intent);
    }
}
