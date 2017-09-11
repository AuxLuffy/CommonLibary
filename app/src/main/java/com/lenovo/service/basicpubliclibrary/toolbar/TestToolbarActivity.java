package com.lenovo.service.basicpubliclibrary.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cx on 2017/9/11.
 * 自定义toolbar
 * 本项目已有title  若正常使用需要在style中添加，并去掉toolbar = initToolBar(this, 1, "title", 0);的注释
 *  <!--隐藏原来的ActionBar，为了在本project中使用ToolBar-->
 <item name="windowActionBar">false</item>
 */

public class TestToolbarActivity extends AppCompatActivity{

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
//        toolbar = initToolBar(this, 1, "title", 0);
    }

    @OnClick({R.id.toolbar_imgv_nav})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_imgv_nav:
                finish();
                break;
        }
    }
    /**
     * 不显示标题
     */
    protected Toolbar initToolBar(AppCompatActivity activity, int titleFlag, String title, int titleColor) {
        try{

        toolbar = new Toolbar(activity);
        switch (titleFlag) {
            case 0:
                toolbar.setNoTitle();
                break;
            case 1:
                toolbar.setTitle(title, titleColor);
                break;
            default:
                break;
        }

        }catch (Exception e){
            Log.e("tool",e.toString());
            e.printStackTrace();

        }
        return toolbar;
    }
}
