package com.lenovo.service.basicpubliclibrary.cache;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.jsBridge.common.LocalConstant;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cx on 2017/10/1.
 */

public class SPActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.inputstr)
    EditText inputstr;
    @BindView(R.id.mgs)
    TextView mgs;

    @Override
    protected int bindLayout() {
        return R.layout.activity_sp;
    }

    @Override
    protected void setWidget() {
        title.setText("工厂模式sp存储");
    }

    @OnClick({R.id.save, R.id.get})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                if (!TextUtils.isEmpty(inputstr.getText().toString())) {
                    LocalDataFactory.getManager(LocalDataFactory.LocalDataType.SP).put(LocalConstant.SharePrefer.mgs, inputstr.getText().toString());
                }
                break;
            case R.id.get:
                IDataManager iDataManager = LocalDataFactory.getManager(LocalDataFactory.LocalDataType.SP);
                if (null != iDataManager.find(LocalConstant.SharePrefer.mgs)) {
                    mgs.setText((String) iDataManager.find(LocalConstant.SharePrefer.mgs));
                }
                break;
        }
    }
}
