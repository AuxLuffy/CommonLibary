package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter.RefreshAdapter;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.contract.RefreshListContract;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.presenter.RefreshListPresenter;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.widget.PullToRefreshListView.PullToRefreshLayout;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.widget.PullToRefreshListView.PullableListView;

/**
 * Created by lenovo on 2017/9/1.
 */

public class PullTorefreshActivity extends Activity implements RefreshListContract.View{


    ImageView mPullIcon;
    PullableListView mContentView;
    PullToRefreshLayout mRefreshView;

    private RefreshListPresenter mPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        init_view();
        init_presenter();
    }


    private void init_view() {
        mPullIcon= (ImageView) findViewById(R.id.pull_icon);
        mContentView= (PullableListView) findViewById(R.id.content_view);
        mRefreshView= (PullToRefreshLayout) findViewById(R.id.refresh_view);
    }


    private void init_presenter() {
        mPresenter = new RefreshListPresenter(this);
        mPresenter.initRefreshLayout(mRefreshView);// 把PullToRefreshLayout设置到Presenter。
        mRefreshView.setSelected(false);// 设置去掉点击效果。
    }

    /**
     * 弹出加载框
     * @Author 李巷阳
     * Created at 2017/9/1 16:55
     */
    @Override
    public void showLoading() {
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

    }
    /**
     * 关闭加载框
     * @Author 李巷阳
     * Created at 2017/9/1 16:56
     */
    @Override
    public void dismissLoading() {
        mProgressDialog.dismiss();

    }

    /**
     * 初始化adapter
     * @Author 李巷阳
     * Created at 2017/9/1 16:59
     */
    @Override
    public void initAdapter(RefreshAdapter adapter) {
        mContentView.setAdapter(adapter);
    }



}
