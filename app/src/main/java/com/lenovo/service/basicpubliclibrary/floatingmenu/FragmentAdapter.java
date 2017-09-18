package com.lenovo.service.basicpubliclibrary.floatingmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by sunzf
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String tabTitles[];

    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragments, String[] tabTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
