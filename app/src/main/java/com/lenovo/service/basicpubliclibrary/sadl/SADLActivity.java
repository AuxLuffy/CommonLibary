package com.lenovo.service.basicpubliclibrary.sadl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * Created by sunzf
 */
public class SADLActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadl);
        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_simple).setOnClickListener(this);
        findViewById(R.id.btn_header_footer).setOnClickListener(this);
        findViewById(R.id.btn_header_footer_view_type).setOnClickListener(this);
        findViewById(R.id.btn_view_type).setOnClickListener(this);
        findViewById(R.id.btn_touch_drag).setOnClickListener(this);
        findViewById(R.id.btn_simple_adapter).setOnClickListener(this);
        findViewById(R.id.btn_simple_adapter).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                startActivity(new Intent(this, NormalListViewActivity.class));
                break;
            case R.id.btn_simple:
                startActivity(new Intent(this, SlideAndDragListViewActivity.class));
                break;
            case R.id.btn_header_footer:
                startActivity(new Intent(this, HeaderFooterActivity.class));
                break;
            case R.id.btn_header_footer_view_type:
                startActivity(new Intent(this, HeaderFooterViewTypeActivity.class));
                break;
            case R.id.btn_view_type:
                startActivity(new Intent(this, DifferentMenuActivity.class));
                break;
            case R.id.btn_touch_drag:
                startActivity(new Intent(this, ItemDragActivity.class));
                break;
            case R.id.btn_simple_adapter:
                startActivity(new Intent(this, SimpleAdapterActivity.class));
                break;
        }
    }
}
