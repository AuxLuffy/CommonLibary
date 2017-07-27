package com.lenovo.service.basicpubliclibrary.headerfloatanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.adapter.GridLayoutRecyclerViewAdapter;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageMode;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageModeMapping;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.tools.Utils;

import java.util.ArrayList;

/**
 * author: shengtao
 * created on: 2017/07/27 10:03
 * description:
 * 这是一个顶部悬浮吸顶的动画+RecyclerView中的GridLayout布局服用+图片加载使用了工厂方法模式（便于无痛切换图片加载框架）
 */

public class HeaderFloatActivity extends Activity {
    //原始数据和映射后的数据
    public HomePageMode homePageModeOriginal = null;//原始数据
    public ArrayList<HomePageModeMapping> homePageModeMappingArrayList = null;//映射后的数据
    RecyclerView mRecyclerView;
    //顶部的购买会员布局
    private RelativeLayout vipBuyTopLay;
    private ImageView vipAvatarImageview;
    //RecyclerView中的item
    private TextView vip_text_title;
    private TextView vip_text_description;
    private TextView vip_buy_text_right;
    //缩放动画有关
    private float zoom = 1.00f;//缩放倍数
    private float rate=0.01f;//每次放到或者缩小的比率
    private int disy = 0;//实际滑动距离（没办法，回调中返回的就是int类型）
    private int originalHeight; //购买会员整个布局，初始高度
    private int originalPadding;//整个布局的padding
    private int originalWidth3;
    private float originalSize1;//初始字体（因为字体也要缩放）
    private float originalSize2;//初始字体
    private float originalSize3;//初始字体
    private float originalAvatarHeight;//顶部左侧图片高度和宽度

    private Handler mHandler; //处理下来到第一个item完全可见的时候，避免瞬间由小变到原始大小时动画生硬，加上了一个渐变
    //google原生的
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mPaddingTop;
    GridLayoutRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_float);
        initUI();
        mHandler = new Handler();
        getData();
        //aaaaa=VipUtil.dip2px(this,60);

    }

    public void initUI() {
        vipBuyTopLay = (RelativeLayout) findViewById(R.id.vip_buy_top_lay);
        vipAvatarImageview = (ImageView) findViewById(R.id.vip_avatar_imageview);
        vip_text_title = (TextView) findViewById(R.id.vip_text_title);
        vip_text_description = (TextView) findViewById(R.id.vip_text_description);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        vip_buy_text_right=(TextView)findViewById(R.id.vip_buy_text_right);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
        // 设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 模拟刷新完成
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    public void getData() {
        homePageModeOriginal = Utils.jsonStringToModel(Utils.readAsssetText(this, "document.json"));
        homePageModeMappingArrayList = Utils.executeHomePageModeMapping(homePageModeOriginal);
        useRecycleView();
    }

    /**
     * 获取控件宽高、字体大小，注意获取时机（onCreate，onResume获取一般都是0），个人感觉postDelayed300毫秒不太适用，万一300毫秒控件还没有绘制完呢
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        originalHeight = vipBuyTopLay.getHeight();
        originalSize1 = vip_text_title.getTextSize();
        originalSize2 = vip_text_description.getTextSize();
        originalSize3=vip_buy_text_right.getTextSize();
        originalWidth3=vip_buy_text_right.getWidth();
        originalAvatarHeight=vipAvatarImageview.getHeight();
        originalPadding=vipBuyTopLay.getPaddingTop();
        mPaddingTop=mAdapter.getBannerView().getPaddingTop();
    }

    public void useRecycleView() {
        //1-1 new出来GridLayoutManager，TYPE_LCM是最小公倍数
        GridLayoutManager manager = new GridLayoutManager(this, GridLayoutRecyclerViewAdapter.TYPE_LCM);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        //1-2设置每个gridviewItem占据多少比例
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = homePageModeMappingArrayList.get(position).getType();
                if (GridLayoutRecyclerViewAdapter.TYPE_HEADER == type) {
                    return GridLayoutRecyclerViewAdapter.TYPE_LCM;
                }
                //最小公倍数除以每行要显示几个，结果=每行占的比例，比如，最大公倍数是6，而每行要显示3个，那一份就是6/3=2
                return (GridLayoutRecyclerViewAdapter.TYPE_LCM / (type));
            }
        });
        //1-3、为Recycleview设置manager
        mRecyclerView.setLayoutManager(manager);//这里用线性宫格显示 类似于grid view
        //2-1 new出来Adapter
        mAdapter = new GridLayoutRecyclerViewAdapter(this, manager, homePageModeMappingArrayList);
        //2-2 为RecycleView设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //加上滚动的回调监听，以便根据滑动距离，做效果
        mRecyclerView.addOnScrollListener(new RecycleViewScrollListener());
    }

    /**
     * RecyclerView滑动监听
     */
    public class RecycleViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
        //dy>0,上滑
        //dy<0，下滑
        //dy=0,初始态或者滑动到初始态
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);


            //千万不要用mRecyclerView.getChildAt(0).getTop()来判断顶部，因为item会复用，当复用是，得到的可能不是第一个了；一定要用findFirstVisibleItemPosition得到position，然后判断是否等于=0
            //当前可见的第一个item，是否是整个RecyclerView的第一个.firstVisiableItem == 0,第一个可见；firstVisiableItem ≠ 0,第一个不可见；
            int firstVisiableItem = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();//第一个可见的item的postition，根据是否等于0来判断是否是第一个
            //
            int firstCompletelyVisibleItem = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();//第一个完全可见的item的position，根据是否等于0来判断是否到临界态
            Log.e("shengtao","onScrolled"+(firstVisiableItem==0?"第一个可见":"第一个不可见"));
            //缩小，条件：倍数小于0.6，并且是上滑（dy>0上滑）
            if (zoom >= 0.7f && dy > 0) {
                //缩小，并且注意float精度导致的问题
                float temp=zoom;
                zoom = (float) (Math.round((zoom - 0.01f) * 100)) / 100;//保留两位小数
                //购买会员的顶部布局，整体和每个控件，包括字体，开始按zoom缩小
                //setWidgetZoom(zoom);
                setWidgetZoomAnimation(temp,zoom,50);

            }


         /*
            //方法2，也需要一个个设置
            vipBuyTopLay.setScaleX(0.99f);
            vipBuyTopLay.setScaleY(0.99f);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int)(vipBuyTopLay.getHeight()*0.99));
            vipBuyTopLay.setLayoutParams(params);*/

           /* //第一个可见
            if (firstVisiableItem == 0) {

            }
            //第一个不可见
            else {

            }*/
            //第一个完全可见，临界条件
            if (firstCompletelyVisibleItem == 0) {
                Log.e("test","第一个完全可见");
                //放大，条件：倍数小于1.0，并且是下滑（dy<0上滑）
                if (zoom < 1.0f && dy < 0) {
                    Log.e("test","调用增大");
                   /* zoom = (float) (Math.round((zoom +0.01f) * 100)) / 100;//保留两位小数
                    //购买会员的顶部布局，整体和每个控件，包括字体，开始按zoom缩小
                    setWidgetZoom(zoom);*/
                    //为了避免生硬，加上了间隔
                  /*  Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            float temp=zoom;
                            zoom = (float) (Math.round((zoom + 0.03f) * 100)) / 100;
                            //放大一次
                            if (zoom < 1.0f) {
                                setWidgetZoom(zoom);
                                setWidgetZoomAnimation(temp,zoom);
                                Log.e("test","放大一次"+zoom);
                                mHandler.postDelayed(this,50);//为了增加渐变的动画，而不是突兀的从小一下变大
                            } else {
                                zoom = 1.0f;
                                setWidgetZoom(zoom);
                               // Log.e("test","恢复初始状态的临界点");
                            }
                        }
                    };
                    mHandler.postDelayed(runnable,50);//每两秒执行一次runnable.*/




                    setWidgetZoomAnimation(zoom,1.0f,200);
                    zoom=1.0f;




                }

            }
            //每次算上dy，才是实际滑动总距离
            disy += dy;
        }

        /**
         *
         * @param from
         * @param to
         * @param time
         */

        private void setWidgetZoomAnimation(float from,float to,int time) {
            //整体
            vipBuyTopLay.setPivotY(0);
            //ObjectAnimator scaleX = ObjectAnimator.ofFloat(vipBuyTopLay, "scaleX", from, to);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(vipBuyTopLay, "scaleY", from, to);
            AnimatorSet animSet = new AnimatorSet();
            // animSet.play(scaleX).with(scaleY);
            animSet.play(scaleY);
            animSet.setDuration(time);
            animSet.start();
            //左边的头像
            vipAvatarImageview.setPivotX(0);
            ObjectAnimator scaleXAvatar = ObjectAnimator.ofFloat(vipAvatarImageview, "scaleX", from, to);
            AnimatorSet animSetAvatar = new AnimatorSet();
            // animSet.play(scaleX).with(scaleY);
            animSetAvatar.play(scaleXAvatar);
            animSetAvatar.setDuration(time);
            animSetAvatar.start();
            //中间文字1
            vip_text_title.setPivotX(0);
            ObjectAnimator scaleXTitle = ObjectAnimator.ofFloat(vip_text_title, "scaleX", from, to);
            AnimatorSet animSetTitle = new AnimatorSet();
            // animSet.play(scaleX).with(scaleY);
            animSetTitle.play(scaleXTitle);
            animSetTitle.setDuration(time);
            animSetTitle.start();
            //中间文字2
            vip_text_description.setPivotX(0);
            ObjectAnimator scaleXDescription = ObjectAnimator.ofFloat(vip_text_description, "scaleX", from, to);
            AnimatorSet animSetDescription = new AnimatorSet();
            // animSet.play(scaleX).with(scaleY);
            animSetDescription.play(scaleXDescription);
            animSetDescription.setDuration(time);
            animSetDescription.start();
            //右边开通会员o

            vip_buy_text_right.setPivotX(originalWidth3*1.0f);

            ObjectAnimator scaleXRight = ObjectAnimator.ofFloat(vip_buy_text_right, "scaleX", from, to);
            AnimatorSet animSetRight = new AnimatorSet();
            // animSet.play(scaleX).with(scaleY);
            animSetRight.play(scaleXRight);
            animSetRight.setDuration(time);
            animSetRight.start();

           /* vip_buy_text_right.setPivotX(originalWidth3*1.0f);

            vip_buy_text_right.setScaleX(to);*/
            /*ObjectAnimator animator = ObjectAnimator.ofFloat(vip_buy_text_right, "scaleX", from, to);

            animator.setDuration(time);
            animator.start();*/
            Log.e("test","mPaddingTop="+mPaddingTop);

            ValueAnimator anim = ValueAnimator.ofInt((int)(from*mPaddingTop),(int)(to*mPaddingTop));
            anim.setDuration(time);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int currentValue = (int) animation.getAnimatedValue();
                    mAdapter.getBannerView().setPadding(0,currentValue,0,0);
                }
            });
            anim.start();


        }

        /**
         * 设置购买会员布局放大或者缩小
         * @param zoom
         */
        private void setWidgetZoom(float zoom){
            Log.e("test","setWidgetZoom="+zoom);
            //整体
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) (originalHeight * zoom));
            vipBuyTopLay.setPadding((int)(originalPadding*zoom),(int)(originalPadding*zoom),(int)(originalPadding*zoom),(int)(originalPadding*zoom));
            vipBuyTopLay.setLayoutParams(params);
            //左边图片
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams((int) (originalAvatarHeight * zoom), (int) (originalAvatarHeight * zoom));
            params2.addRule(RelativeLayout.CENTER_VERTICAL);
            vipAvatarImageview.setLayoutParams(params2);
            //图片右边文字部分
            vip_text_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, (originalSize1 * zoom));
            vip_text_description.setTextSize(TypedValue.COMPLEX_UNIT_PX, originalSize2 * zoom);
            //右边
            vip_buy_text_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, originalSize3 * zoom);
        }
    }
}
