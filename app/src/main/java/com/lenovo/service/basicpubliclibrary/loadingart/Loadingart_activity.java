package com.lenovo.service.basicpubliclibrary.loadingart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.loadingart.fragment.Page1Fragment;
import com.lenovo.service.basicpubliclibrary.loadingart.fragment.Page2Fragment;


/**
 * 加载的艺术
 * @Author 李巷阳
 * Created at 2017/9/10 16:42
 */
public class Loadingart_activity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loadingart);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] titles = new String[]{
                    "page1", "page2"
            };

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return Page1Fragment.newInstance();
                } else {
                    return Page2Fragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
