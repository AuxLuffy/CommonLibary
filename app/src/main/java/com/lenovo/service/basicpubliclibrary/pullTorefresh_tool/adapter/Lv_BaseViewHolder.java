package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 李巷阳
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/1/19 16:23
 */
public class Lv_BaseViewHolder {
    // 声明一个数据,保存view
    private SparseArray<View> views;
    private View convertView;


    public Lv_BaseViewHolder(View convertView) {
        this.convertView = convertView;
        this.views = new SparseArray<View>();
    }


    // 通过ID,获取布局中的textview
    public TextView getTextView(int viewId) {
        return retrieveView(viewId);
    }
    // 通过ID,获取布局中的Button
    public Button getButton(int viewId) {
        return retrieveView(viewId);
    }
    // 通过ID,获取布局中的ImageView
    public ImageView getImageView(int viewId) {
        return retrieveView(viewId);
    }
    // 通过ID,获取布局中的View
    public View getView(int viewId) {
        return retrieveView(viewId);
    }

    // 相当于FindViewById.把数据加入到views数组里
    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
