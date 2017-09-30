package com.lenovo.service.basicpubliclibrary.recyclerview.excel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.Arrays;
import java.util.List;

public class ExcelActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private List<ExcelFragment> list = Arrays.asList(new ExcelFragment(), new ExcelFragment(),
            new ExcelFragment(), new ExcelFragment(), new ExcelFragment());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager.setAdapter(new ExcelPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
    }

    class ExcelPagerAdapter extends FragmentStatePagerAdapter {

        public ExcelPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item:" + position;
        }
    }
}
