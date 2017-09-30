package com.example.lenovo.fragmenttabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.fragmenttabhost.fragment.CustomerFragment;
import com.example.lenovo.fragmenttabhost.fragment.MyFragment;
import com.example.lenovo.fragmenttabhost.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lenovo on 2017/9/30.
 */

public class FragTabHostActivity extends AppCompatActivity {

    public static boolean isRegistedNewMsg;
    @BindView(R2.id.id_viewpager)
    ViewPager mIdViewpager;
//    @BindView(R2.id.at_toolbar)
//    AutoLoadToolbarLayout mAtToolbar;
    @BindView(R2.id.iv_shop)
    ImageView mIvShop;
    @BindView(R2.id.tv_shop)
    TextView mTvShop;
    @BindView(R2.id.iv_customer)
    ImageView mIvCustomer;
    @BindView(R2.id.tv_customer)
    TextView mTvCustomer;
    @BindView(R2.id.iv_my)
    ImageView mIvMy;
    @BindView(R2.id.tv_my)
    TextView mTvMy;
    @BindView(R2.id.llyt_shop)
    LinearLayout mLlytShop;
    @BindView(R2.id.llyt_customer)
    LinearLayout mLlytCustomer;
    @BindView(R2.id.llyt_my)
    LinearLayout mLlytMy;
    @BindView(R2.id.v_line)
    View mV_line;

    private List<View> mTabIndicator = new ArrayList<View>();
    private List<TextView> mTabTVIndicator = new ArrayList<TextView>();
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{"掌柜", "潜客", "我的"};
    private FragmentPagerAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragtabhost);
        ButterKnife.bind(this);
        initDatas();
        initViews();
    }




    private void initDatas() {
        // TODO初始化fragment
        mTabs.add(ShopFragment.newInstance(null));
        mTabs.add(CustomerFragment.newInstance(null));
        mTabs.add(MyFragment.newInstance(null));

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };
        initTabIndicator();
    }


    private void initTabIndicator() {
        mTabIndicator.add(mIvShop);
        mTabIndicator.add(mIvCustomer);
        mTabIndicator.add(mIvMy);
        mTabTVIndicator.add(mTvShop);
        mTabTVIndicator.add(mTvCustomer);
        mTabTVIndicator.add(mTvMy);
        mIvShop.setSelected(true);
        mTvShop.setSelected(true);
    }


    private void initViews() {
        mIdViewpager.setAdapter(mAdapter);
        mIdViewpager.setOffscreenPageLimit(4);
        //初始化进来
//        mAtToolbar.setTvCenterTitle(mTitles[0]);
        mIdViewpager.setCurrentItem(0, true);
    }


    /**
     * 重置其他的Tab
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setSelected(false);
            mTabTVIndicator.get(i).setSelected(false);
        }
    }


    @OnClick({R2.id.llyt_shop, R2.id.llyt_customer, R2.id.llyt_my})
    public void onViewClicked(View view) {
        resetOtherTabs();
        int i = view.getId();
        if (i == R.id.llyt_shop) {
            mTabIndicator.get(0).setSelected(true);
            mTabTVIndicator.get(0).setSelected(true);
            mIdViewpager.setCurrentItem(0, false);
            mV_line.setVisibility(View.VISIBLE);

        } else if (i == R.id.llyt_customer) {
            mTabIndicator.get(1).setSelected(true);
            mTabTVIndicator.get(1).setSelected(true);
            mIdViewpager.setCurrentItem(1, false);

            mV_line.setVisibility(View.VISIBLE);


        } else if (i == R.id.llyt_my) {
            mTabIndicator.get(2).setSelected(true);
            mTabTVIndicator.get(2).setSelected(true);
            mIdViewpager.setCurrentItem(2, false);

            mV_line.setVisibility(View.VISIBLE);


        }
    }



}
