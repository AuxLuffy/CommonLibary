package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李巷阳
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/1/19 16:18
 */
public abstract class Lv_BaseAdapter<T,H extends Lv_BaseViewHolder> extends BaseAdapter {
    protected final Context context;

    protected int layoutResId;

    protected List<T> datas;

    private LayoutInflater mInflater;
    /**
     * 构造函数
     * @param context  上下文
     * @param datas 数据源
     */
    public Lv_BaseAdapter(Context context, List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 构造函数
     * @param context  上下文
     * @param layoutResId 布局layoutId
     * @param datas 数据源
     */
    public Lv_BaseAdapter(Context context, int layoutResId, List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        this.context = context;
        this.layoutResId = layoutResId;
        mInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        if(datas==null || datas.size()<=0)
            return 0;

        return datas.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= datas.size()) return null;
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lv_BaseViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(layoutResId, null);
            holder = new Lv_BaseViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Lv_BaseViewHolder) convertView.getTag();
        }
        T item = getItem(position);
        // 把viewhoder和数据传递过去，通过抽象方法让子类实现。
        convert((H)holder, item);

        return convertView;
    }

    // 刷新数据
    public void refreshData(List<T> listSH){
        this.datas=listSH;
        notifyDataSetChanged();
    }

    // 获取数据源
    public List<T> getDatas(){

        return  datas;
    }
    // 清空数据源
    public void clearData(){
        datas.clear();
        notifyDataSetChanged();
    }

    // 在指定position位置，添加位置
    public void addData(List<T> datas){
        if(datas !=null && datas.size()>0) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }


    /**
     * 子类实现绑定数据
     */
    protected abstract void convert(H viewHoder, T item);

}
