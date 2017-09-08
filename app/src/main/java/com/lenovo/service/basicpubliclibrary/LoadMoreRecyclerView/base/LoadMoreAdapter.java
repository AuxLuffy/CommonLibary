package com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cx on 2016/8/31.
 */
public abstract class LoadMoreAdapter<T> extends RecyclerView.Adapter {


    //outside
    public static final int PULL_LOAD_MORE = 0;
    public static final int LOADING_MORE = 1;
    public static final int DATA_OUT = 2;
    public static final int INVISIBLE = 3;


    //inside
    private int load_more_statu = 0;
    private static final int TYPE_NOR = 0;
    private static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    public boolean isFinish = false;
    public boolean isRequestDate = false;

    //maxpagenum
    private int MAX_PAGE_NUM = 10;
    protected LayoutInflater inflater;
    protected List<T> list;
    protected Context context;
    private OnLoadMoreListener loadMoreListener;

    private View mHeaderView;


    public LoadMoreAdapter(Context context, List<T> list, OnLoadMoreListener loadMoreListener) {
        this.context = context;
        this.list = list;
        this.loadMoreListener = loadMoreListener;

        inflater = LayoutInflater.from(context);
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.footer_view, parent, false);
            return new FooterHolder(view);
        } else if (viewType == TYPE_NOR) {
            return getNormalHolder(parent);
        } else if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new HeaderHolder(mHeaderView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (position == getItemCount() - 1) {

                FooterHolder footerHolder = (FooterHolder) holder;

                if (isFinish) {

                    footerHolder.view.setVisibility(View.VISIBLE);
                    footerHolder.ivLoading.setVisibility(View.GONE);
                    footerHolder.textView.setText("加载完成");

                } else {

                    switch (load_more_statu) {
                        case PULL_LOAD_MORE:
                            footerHolder.view.setVisibility(View.VISIBLE);
                            footerHolder.ivLoading.setVisibility(View.GONE);
                            footerHolder.textView.setText("上拉加载更多");
                            break;
                        case LOADING_MORE:

                            if (!isRequestDate) {
                                isRequestDate = true;
                                loadMoreListener.onLoadMore();
                                footerHolder.view.setVisibility(View.VISIBLE);
                                footerHolder.ivLoading.setVisibility(View.VISIBLE);
                                footerHolder.startAnimation();
                                footerHolder.textView.setText("正在加载...");
                            }

                            break;
                    }
                }
            } else if (getItemViewType(position) == TYPE_HEADER) {
                return;
            } else {
                if (mHeaderView != null) {
                    setHolderData(holder, position - 1);
                } else {
                    setHolderData(holder, position);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            if (mHeaderView == null) {
                return 0;
            } else {
                return 1;
            }

        } else {
            if (mHeaderView == null) {
                return list.size() + 1;
            } else {
                return list.size() + 2;
            }
        }
//        return mHeaderView == null ? list.size() : list.size() + 1;

    }

    @Override
    public int getItemViewType(int position) {

        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else if (mHeaderView != null && position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NOR;
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public void appendData(List<T> append) {
        isRequestDate = false;

        if (append == null || append.size() < MAX_PAGE_NUM) {

            if (append.size() > 0) {
                list.addAll(append);
            }
            setLoadingStatus(DATA_OUT);
            isFinish = true;

        } else {
            list.addAll(append);
            setLoadingStatus(PULL_LOAD_MORE);

        }
    }

    public void refreshData(List<T> newList) {

        this.list = newList;

        isFinish = false;

        notifyDataSetChanged();

    }

    public void removeData(int currentPage,int pos, List<T> newList) {

        if (!newList.contains(this.list.get(pos))) {
            this.list.remove(pos);
            //缺少一个元素添加进来(非第一页页1.下一页已经展示，则一定重复，此时不添加 2.反之，添加到本页最后一个元素位置)
            if(currentPage != 1 && !this.list.contains(newList.get(newList.size()-1))){
                this.list.add(currentPage * MAX_PAGE_NUM - 1,newList.get(newList.size()-1));
            }
        }

        notifyDataSetChanged();

    }

    public void removeDataByPos(int pos) {

        if (-1 != pos) {
            this.list.remove(pos);
            notifyDataSetChanged();
        }
        if (pos == 0) {
            setLoadingStatus(DATA_OUT);
            isFinish = true;
        }

    }

    public void setLoadingStatus(int status) {
        try{
            load_more_statu = status;
            notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class HeaderHolder extends RecyclerView.ViewHolder {

        TextView text;

        public HeaderHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            text = (TextView) itemView.findViewById(R.id.text_goingNum);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {

        public View view;
        @BindView(R.id.tv_loading)
        public TextView textView;
        @BindView(R.id.iv_loading)
        public ImageView ivLoading;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

            startAnimation();
        }

        public void startAnimation() {

            RotateAnimation animation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            animation.setRepeatCount(-1);

            animation.setFillEnabled(true);

            animation.setDuration(1500);

            animation.setInterpolator(new LinearInterpolator());

            ivLoading.startAnimation(animation);
        }

    }


    public interface OnLoadMoreListener {

        void onLoadMore();

    }

    protected abstract RecyclerView.ViewHolder getNormalHolder(ViewGroup parent);

    protected abstract void setHolderData(RecyclerView.ViewHolder holder, int position);


}
