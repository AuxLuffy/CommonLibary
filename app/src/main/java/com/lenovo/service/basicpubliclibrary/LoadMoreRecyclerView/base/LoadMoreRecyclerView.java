package com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cx on 2016/8/31.
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private int lastVisibleItem,firstVisibleItem;

    private MyLinearLayoutManager layoutManager;

    private LoadMoreAdapter loadMoreAdapter;

    private boolean isPullDown = false;

    private Context mContext;

    private int downY, mDownY;

    private int MIN_DISTANCE = 30;//设置滑动最小单位

    public LoadMoreRecyclerView(Context context) {
        super(context);
        this.mContext = context;
    }


    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public void setLoadMoreAdapter(LoadMoreAdapter loadMoreAdapter) {

        this.loadMoreAdapter = loadMoreAdapter;

        setAdapter(loadMoreAdapter);

        init();

    }


    private void init() {
        loadMoreAdapter = (LoadMoreAdapter) getAdapter();
        layoutManager = new MyLinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(layoutManager);
        addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        if (layoutManager == null) {
            return;
        }
        if (loadMoreAdapter == null) {
            return;
        }

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == loadMoreAdapter.getItemCount()) {
                    if (isPullDown) {
                        loadMoreAdapter.setLoadingStatus(LoadMoreAdapter.LOADING_MORE);
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 != loadMoreAdapter.getItemCount()) {
                    loadMoreAdapter.setLoadingStatus(LoadMoreAdapter.PULL_LOAD_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
//               当前有headerView 判断被划出屏幕时，显示之前设置的假view
            }
        });
    }

    public boolean getPullDownState() {
        return isPullDown;
    }

    public LoadMoreAdapter getAdapter(){
        return loadMoreAdapter;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

            switch (e.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    isPullDown = false;
                    downY = (int) e.getY();
                    mDownY = (int) e.getY();
                    break;
                case MotionEvent.ACTION_MOVE:

                    int moveY = (int) e.getY();

                    downY = moveY;

                    break;
                case MotionEvent.ACTION_UP:

                    if (mDownY - downY > MIN_DISTANCE) {
                        isPullDown = true;
                    } else {
                        isPullDown = false;
                    }

                    break;
            }


            return super.onTouchEvent(e);

    }

}
