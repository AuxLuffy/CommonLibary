package com.lenovo.service.basicpubliclibrary.floatingmenu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunzf
 */
public class FloatingActionActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

        mTabLayout = (TabLayout) findViewById(R.id.home_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new DemoCircleMenuFragment());
        fragments.add(new DemoLineMenuFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, new String[]{"Circle", "Line"});
        mViewPager.setAdapter(adapter);
        mViewPager.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }
}
