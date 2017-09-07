package com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.base.LoadMoreAdapter;
import com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.base.LoadMoreRecyclerView;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cx on 2017/9/7.
 *recyclerview封装 上拉  下拉  添加请求头  添加footer
 */

public class MyRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    LoadMoreRecyclerView recyclerview;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private TestAdapter adapter;
    private int pageSize = 10, pageNum = 1;
    private List<String> datas,appDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initData();
        initRefresh();
        showData();
    }

    private void initData() {
        datas = new ArrayList<String>();
        appDatas = new ArrayList<String>();
        addData();
    }

    private void addData() {
        appDatas.clear();
        for(int i = 1 ; i <= 100 ; i ++){
            datas.add("item " + (i + ((pageNum-1) * 100)));
            appDatas.add("item" + (i + ((pageNum-1) * 100)));
        }
    }

    private void initRefresh() {
        refresh.setColorSchemeResources(R.color.blue_clean, R.color.orange_deep);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                adapter = null;
                showData();
                refresh.setRefreshing(false);
            }
        });
    }

    private void showData() {

        if (adapter == null) {

            adapter = new TestAdapter(this, datas, new LoadMoreAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    pageNum++;
                    showData();
                }
            });

            recyclerview.setLoadMoreAdapter(adapter);

        } else {
            adapter.appendData(appDatas);

        }
    }

}
