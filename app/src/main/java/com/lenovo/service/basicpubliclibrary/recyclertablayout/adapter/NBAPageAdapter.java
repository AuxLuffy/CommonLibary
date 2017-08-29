package com.lenovo.service.basicpubliclibrary.recyclertablayout.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xuxiaowei on 2017/8/28.
 */

public class NBAPageAdapter extends PagerAdapter{
    private List<String> mItems;
    private Context mContext;

    public NBAPageAdapter(List<String> items, Context mContext) {
        this.mItems = items;
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(mContext);
        textView.setText("Page: " + mItems.get(position));
        container.addView(textView);

        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        return mItems.get(position);
    }
}
