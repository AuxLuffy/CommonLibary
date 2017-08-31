package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.maillistananimation.MaillistActivity;
import com.lenovo.service.basicpubliclibrary.multitype.bilibili.BilibiliActivity;
import com.lenovo.service.basicpubliclibrary.obtainlocalphoto.LocalPhotoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComponentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_maillist)
    TextView mtv_maillist;
    @BindView(R.id.tv_multitype)
    TextView mTvMultitype;

    @BindView(R.id.tv_localphoto)
    TextView mtv_localphoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        ButterKnife.bind(this);
        init_listener();
    }

    private void init_listener() {
        mtv_maillist.setOnClickListener(this);
        mtv_localphoto.setOnClickListener(this);
        mTvMultitype.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId())
        {
            case R.id.tv_maillist:
                intent.setClass(ComponentActivity.this,
                        MaillistActivity.class);
                startActivity(intent);
            break;
            case R.id.tv_localphoto:
                intent.setClass(ComponentActivity.this,
                        LocalPhotoActivity.class);
                startActivity(intent);
            break;
            case R.id.tv_multitype:
                startActivity(new Intent(ComponentActivity.this, BilibiliActivity.class));
            break;
        }
    }
}
