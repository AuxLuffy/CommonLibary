package com.lenovo.service.basicpubliclibrary.cache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.cache.db.TestDBHelper;

import java.util.List;
import java.util.Map;


/**
 * Created by cx on 2017/10/1.
 */

public class DBActivity extends AppCompatActivity implements View.OnClickListener {

    private Button selectBtn;
    private Button insertBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private TextView contentTv;

    private TestDBHelper testDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        testDBHelper = TestDBHelper.getInstance(this);

        selectBtn = (Button) findViewById(R.id.find);
        insertBtn = (Button) findViewById(R.id.add);
        updateBtn = (Button) findViewById(R.id.update);
        deleteBtn = (Button) findViewById(R.id.delete);
        contentTv = (TextView) findViewById(R.id.text);


        selectBtn.setOnClickListener(this);
        insertBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find:
                List<Map> list = testDBHelper.queryListMap("select * from user", null);
                contentTv.setText(String.valueOf(list));
                break;
            case R.id.add:
                testDBHelper.insert("user", new String[]{"name", "gender", "age"}, new Object[]{"qiangyu", "male", 23});
                break;
            case R.id.update:
                testDBHelper.update("user", new String[]{"name", "gender", "age"}, new Object[]{"yangqiangyu", "male", 24},
                        new String[]{"name"}, new String[]{"qiangyu"});
                break;
            case R.id.delete:
                testDBHelper.delete("user",
                        new String[]{"name"}, new String[]{"qiangyu"});
                break;
        }
    }
}