package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.maillistananimation.MaillistActivity;
import com.lenovo.service.basicpubliclibrary.multitype.bilibili.BilibiliActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComponentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_maillist)
    TextView mtv_maillist;
    @BindView(R.id.tv_multitype)
    TextView mTvMultitype;
    @BindView(R.id.tv_h5_activity)
    TextView mTvH5Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        ButterKnife.bind(this);
        init_listener();
    }

    private void init_listener() {
        mtv_maillist.setOnClickListener(this);
        mTvMultitype.setOnClickListener(this);
        mTvH5Activity.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_maillist:
                Intent intent=new Intent(ComponentActivity.this, MaillistActivity.class);
                startActivity(intent);
            break;
            case R.id.tv_multitype:
                startActivity(new Intent(ComponentActivity.this, BilibiliActivity.class));
            break;
            case R.id.tv_h5_activity:
                Intent intent1 = new Intent(ComponentActivity.this, H5Activity.class);
                intent1.putExtra(H5Activity.H5_URL, "http://www.jianshu.com/");
                startActivity(intent1);
                break;
        }
    }
}
