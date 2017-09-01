package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.contract;


import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter.RefreshAdapter;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.widget.PullToRefreshListView.PullToRefreshLayout;

/**
 * Created by lenovo on 2017/8/31.
 */

public interface RefreshListContract {


    interface Presenter {

        void refreshData();

        void loadMoreData();

        void initRefreshLayout(PullToRefreshLayout RefreshView);
    }

    interface View {
        void showLoading();

        void dismissLoading();

        void initAdapter(RefreshAdapter adapter);


    }



}
