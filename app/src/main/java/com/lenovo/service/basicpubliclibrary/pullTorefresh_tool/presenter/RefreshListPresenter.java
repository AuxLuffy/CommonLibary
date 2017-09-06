package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter.RefreshAdapter;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.bean.RefreshData;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.contract.RefreshListContract;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.error.AppException;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.widget.PullToRefreshListView.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 刷新的Presenter类
 * @Author 李巷阳
 * Created at 2017/9/1 17:03
 */
public class RefreshListPresenter implements RefreshListContract.Presenter {

    private RefreshListContract.View mView;
    private int currPage = 1;
    private int totalPage = 1;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;
    private RefreshAdapter mAdapter;
    private PullToRefreshLayout mRefreshView;
    private Activity mActivity;

    public RefreshListPresenter(RefreshListContract.View view) {
        mView = view;
        mActivity=(Activity)view;
    }



    /**
     * 下拉刷新
     * @Author 李巷阳
     * Created at 2017/9/1 16:59
     */
    @Override
    public void refreshData() {
        currPage = 1;
        state = STATE_REFREH;
        getCustomerList();
    }


    /**
     * 上拉加载更多
     * @Author 李巷阳
     * Created at 2017/9/1 16:59
     */
    @Override
    public void loadMoreData() {
        currPage = ++currPage;
        state = STATE_MORE;
        getCustomerList();
    }
    /**
     * 初始化下拉刷新控件
     * @Author 李巷阳
     * Created at 2017/9/1 17:03
     */
    @Override
    public void initRefreshLayout(PullToRefreshLayout RefreshView) {
        this.mRefreshView = RefreshView;
        RefreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                    refreshData();// 下拉刷新
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                    loadMoreData();// 上啦加载更多
            }
        });
    }

    /**
     * 加载数据
     * @Author 李巷阳
     * Created at 2017/9/1 17:09
     */
    private void getCustomerList() {
        mView.showLoading();
        Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<RefreshData>>() {
                    @Override
                    public List<RefreshData> call(String s) {
                        List<RefreshData> listdata=new ArrayList<RefreshData>();
                        // 耗时
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for(int x=0;x<20;x++)
                        {
                            RefreshData cd=new RefreshData();
//                            cd.setHead_img("nick_name"+x);
                            cd.setPhone("155"+x);
                            cd.setNick_name("nick_name"+x);
                            cd.setSex(x);
                            listdata.add(cd);
                        }


                        return listdata;
                    }
                }).map(new Func1<List<RefreshData>, List<RefreshData>>() {
            @Override
            public List<RefreshData> call(List<RefreshData> customerDatas) {
                if (customerDatas==null){
                    throw new AppException("获取列表数据失败",1);
                }
                return customerDatas;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Subscriber<List<RefreshData>>() {
                       @Override
                       public void onCompleted() {
                           mView.dismissLoading();
                       }

                       @Override
                       public void onError(Throwable e) {
                           if (e instanceof AppException){
                               AppException ex = (AppException) e;
                               switch (ex.errorCode){
                                   case 1:
                                       Toast.makeText(mActivity,e.getMessage(),Toast.LENGTH_SHORT).show();
                                       break;
                                   default:
                               }
                           }

                       }

                       @Override
                       public void onNext(List<RefreshData> customerDatas) {
                           Log.e("thread",Thread.currentThread().getName()+":3");
                           showWaresData(customerDatas);
                           mRefreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
                           mRefreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                       }
                   });

    }


    private void showWaresData(List<RefreshData> cdata) {
        switch (state) {
            case STATE_NORMAL:
                init_adapter(cdata);
                mView.dismissLoading();
                break;
            case STATE_REFREH:
                init_adapter(cdata);
                mRefreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
                break;
            case STATE_MORE:
                if (mAdapter != null) {
                    mAdapter.addData(cdata);
                }
                mRefreshView.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                break;
        }
    }


    private void init_adapter(List<RefreshData> cdata) {
        if (mAdapter == null) {
            mAdapter = new RefreshAdapter(mActivity, cdata);
            mView.initAdapter(mAdapter);
        } else {
            mAdapter.clearData();
            mAdapter.refreshData(cdata);
        }
    }
}
