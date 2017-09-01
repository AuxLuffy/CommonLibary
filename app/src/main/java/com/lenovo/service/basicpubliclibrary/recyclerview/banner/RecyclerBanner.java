package com.lenovo.service.basicpubliclibrary.recyclerview.banner;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @description: 自定义轮播图控件
 * @author: 袁东华
 * created at 2016/11/29 18:33
 */
public class RecyclerBanner extends FrameLayout {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    //圆点图片
    //默认圆点
    private GradientDrawable defaultDrawable;
    //选中圆点
    private GradientDrawable selectedDrawable;
    ReyclerAdapter adapter;
    OnPagerClickListener onPagerClickListener;
    private List<String> datas = new ArrayList<>();
    //圆点大小
    private int size;
    //初始X
    private int startX;
    //初始Y
    private int startY;
    private int currentIndex;
    boolean isPlaying;
    //播放图片间隔时间
    private long INTERVAL_TIME = 3000;
    //小圆点默认大小
    private int defaultSize = 7;

    public interface OnPagerClickListener {

        void onClick(String entity, int position);
    }


    private Handler handler = new Handler();
    //播放图片任务
    private Runnable playTask = new Runnable() {

        @Override
        public void run() {
            //立即播放下张图片
            recyclerView.smoothScrollToPosition(++currentIndex);
            //改变圆点的状态和颜色
            changePoint();
            //重复执行此任务
            handler.postDelayed(this, INTERVAL_TIME);
        }
    };

    public RecyclerBanner(Context context) {
        this(context, null);
    }

    public RecyclerBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        size = DisplayUtils.dipTopx(context, defaultSize);
        //创建一个默认的圆点
        defaultDrawable = new GradientDrawable();
        //设置宽高
        defaultDrawable.setSize(size, size);
        //设置半径
        defaultDrawable.setCornerRadius(size);
        //设置颜色
        defaultDrawable.setColor(0xffffffff);

        //创建一个选中的圆点
        selectedDrawable = new GradientDrawable();
        //设置宽高
        selectedDrawable.setSize(size, size);
        //设置半径
        selectedDrawable.setCornerRadius(size);
        //设置颜色
        selectedDrawable.setColor(0xff0094ff);

        //创建图片轮播控件 RecyclerView实现
        recyclerView = new RecyclerView(context);
        //RecyclerView的LayoutParams,和父容器一样大
        LayoutParams vpLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //把RecyclerView添加到父容器中
        addView(recyclerView, vpLayoutParams);
        //左右滑动帮助类和RecyclerView绑定
        new PagerSnapHelper().attachToRecyclerView(recyclerView);


        //创建一个盛放圆点的LinearLayout
        linearLayout = new LinearLayout(context);
        //设置横向展示
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //居中显示
        linearLayout.setGravity(Gravity.CENTER);
        //设置padding
        linearLayout.setPadding(0, 0, 0, 15);
        //LinearLayout的LayoutParams
        LayoutParams linearLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //放到父容器的底部
        linearLayoutParams.gravity = Gravity.BOTTOM;
        //把圆点容器添加到父容器中
        addView(linearLayout, linearLayoutParams);

        //设置水平滑动的LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        //创建适配器
        adapter = new ReyclerAdapter();
        //给recyclerView设置适配器
        recyclerView.setAdapter(adapter);
        //左右滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int first = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int last = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                //确定居中View的位置
                if (currentIndex != (first + last) / 2) {
                    currentIndex = (first + last) / 2;
                    //改变圆点的状态及颜色
                    changePoint();
                }
            }
        });
    }

    /**
     * @description: 供外部调用的接口, 点击当前居中的条目View接口
     * @author: 袁东华
     * created at 2016/11/28 15:23
     */
    public void setOnPagerClickListener(OnPagerClickListener onPagerClickListener) {
        this.onPagerClickListener = onPagerClickListener;
    }

    public synchronized void setPlaying(boolean playing) {
        if (!isPlaying && playing && adapter != null && adapter.getItemCount() > 2) {
            //开始执行播放任务
            handler.postDelayed(playTask, INTERVAL_TIME);
            isPlaying = true;
        } else if (isPlaying && !playing) {
            //结束播放任务
            handler.removeCallbacksAndMessages(null);
            isPlaying = false;
        }
    }

    public int setDatas(List<String> datas) {
        //禁止播放
        setPlaying(false);
        //旧数据清除
        this.datas.clear();
        //移除圆点
        linearLayout.removeAllViews();
        //设置新数据
        if (datas != null) {
            this.datas.addAll(datas);
        }
        if (this.datas.size() > 1) {
            //当前view索引设置初始值
            currentIndex = this.datas.size() * 10000;
            //刷新图片轮播
            adapter.notifyDataSetChanged();
            //滚动到当前索引位置
            recyclerView.scrollToPosition(currentIndex);
            //创建圆点
            for (int i = 0; i < this.datas.size(); i++) {
                //创建ImageView
                ImageView img = new ImageView(getContext());
                //给ImageView设置LayoutParams
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                /*//设置左右间距
                lp.leftMargin = size / 2;
                lp.rightMargin = size / 2;*/
                //设置图片,是否显示默认图片还是选中的图片
//                img.setImageDrawable(i == 0 ? selectedDrawable : defaultDrawable);
                img.setImageResource(i == 0 ? R.drawable.article_banner_selector : R.drawable.article_banner_default);
                lp.setMargins(10, 0, 10, 0);

                //把圆点的ImageView添加到父容器横向的linearLayout中
                linearLayout.addView(img, lp);
            }
            //准备完毕,开始执行播放任务
            setPlaying(true);
        } else {
            //如果没有数据,只刷新下
            currentIndex = 0;
            adapter.notifyDataSetChanged();
        }
        return this.datas.size();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //分发事件
        switch (ev.getAction()) {

            //手指按下
            case MotionEvent.ACTION_DOWN:
                //获取初始坐标
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                //暂停播放任务
                setPlaying(false);
                //不允许父容器拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            //手指移动
            case MotionEvent.ACTION_MOVE:
                //获取移动坐标
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                //获取移动距离
                int disX = moveX - startX;
                int disY = moveY - startY;
                //暂停播放任务
                setPlaying(false);
                //如果横向滑动的距离的2倍>纵向滑动的距离,不允许父容器拦截事件
                getParent().requestDisallowInterceptTouchEvent(2 * Math.abs(disX) > Math.abs(disY));
                break;
            //手指抬起
            case MotionEvent.ACTION_UP:
                //手指事件取消
            case MotionEvent.ACTION_CANCEL:
                //开始播放任务
                setPlaying(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @description: 次控件附加到window上的时候调用此方法, 但是在onDraw之前
     * @author: 袁东华
     * created at 2016/11/28 15:59
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //开始播放任务
        setPlaying(true);
    }

    /**
     * @description: 当此控件从window上分离时调用
     * @author: 袁东华
     * created at 2016/11/28 16:01
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //暂停播放任务
        setPlaying(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == View.GONE) {
            // 停止轮播
            setPlaying(false);
        } else if (visibility == View.VISIBLE) {
            // 开始轮播
            setPlaying(true);
        }
        super.onWindowVisibilityChanged(visibility);
    }

    /**
     * @description: 内置适配器, 图片轮播的适配器
     * @author: 袁东华
     * created at 2016/11/28 16:05
     */
    private class ReyclerAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //创建展示轮播图的ImageView
            ImageView img = new ImageView(parent.getContext());
            //设置轮播图的ImageViewd的LayoutParams
            RecyclerView.LayoutParams l = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //设置缩放类型
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(l);
            //设置个id
            img.setId(R.id.icon);
            //点击图片
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //把点击事件传递给外部
                    if (onPagerClickListener != null) {
                        onPagerClickListener.onClick(datas.get(currentIndex % datas.size()), currentIndex % datas.size());
                    }
                }
            });
            return new RecyclerView.ViewHolder(img) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView img = (ImageView) holder.itemView.findViewById(R.id.icon);

            if (Util.isOnMainThread()) {
                Glide
                        .with(img.getContext())
                        .load(datas.get(position % datas.size()))
                        .centerCrop()
                        .placeholder(R.drawable.img_loading)
                        .into(img);
            }


        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size() < 2 ? datas.size() : Integer.MAX_VALUE;
        }
    }

    /**
     * @description: 左右滑动帮助类 获取目标条目View的位置
     * @author: 袁东华
     * created at 2016/11/28 15:11
     */
    private class PagerSnapHelper extends LinearSnapHelper {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
            //获取目标位置
            int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            //获取居中View
            final View currentView = findSnapView(layoutManager);
            //目标位置有效,并且居中View不为空
            if (targetPos != RecyclerView.NO_POSITION && currentView != null) {
                //获取居中View的位置
                int currentPostion = layoutManager.getPosition(currentView);
                //获取第一个可见条目View的的位置
                int first = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                //获取最后一个可见条目View的位置
                int last = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                //先确定当前居中条目View的位置,然后用这个位置去确定目标条目View的位置
                //判断当前居中的View的位置,是偏左的View的位置还是偏右View的位置
                currentPostion = targetPos < currentPostion ? last : (targetPos > currentPostion ? first : currentPostion);
                //判断目标条目View的位置
                targetPos = targetPos < currentPostion ? currentPostion - 1 : (targetPos > currentPostion ? currentPostion + 1 : currentPostion);
            }
            return targetPos;
        }
    }

    private void changePoint() {
        //圆点个数>0
        if (linearLayout != null && linearLayout.getChildCount() > 0) {
            //遍历圆点
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                //判断是否设置默认图片是否设置选中图片
                ((ImageView) linearLayout.getChildAt(i)).setImageResource(i == currentIndex % datas.size() ? R.drawable.article_banner_selector : R.drawable.article_banner_default);
            }
        }
    }
}