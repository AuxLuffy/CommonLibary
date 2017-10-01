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

public class CacheFileActivity extends BaseActivity {

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
        title.setText("工厂模式File存储");
    }

    @OnClick({R.id.save, R.id.get})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                if (!TextUtils.isEmpty(inputstr.getText().toString())) {
                    UserTest user = new UserTest();
                    user.setName(inputstr.getText().toString());
                    LocalDataFactory.getManager(LocalDataFactory.LocalDataType.FILE).put(LocalConstant.ObjectName.user, user);
                }
                break;
            case R.id.get:
                IDataManager iDataManager = LocalDataFactory.getManager(LocalDataFactory.LocalDataType.FILE);
                if (null != iDataManager.find(LocalConstant.ObjectName.user)) {
                    UserTest userTest = (UserTest) iDataManager.find(LocalConstant.ObjectName.user);
                    mgs.setText("Object:"+userTest.toString());
                }
                break;
        }
    }
}
