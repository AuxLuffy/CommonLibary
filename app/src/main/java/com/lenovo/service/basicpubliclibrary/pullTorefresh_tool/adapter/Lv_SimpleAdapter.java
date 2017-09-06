package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter;

import android.content.Context;

import java.util.List;

/**
 * @author 李巷阳
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/1/19 16:40
 */
public abstract class Lv_SimpleAdapter<T> extends Lv_BaseAdapter<T,Lv_BaseViewHolder>  {

    /**
     * 构造函数
     *
     * @param context     上下文
     * @param layoutResId 布局layoutId
     * @param datas       数据源
     */
    public Lv_SimpleAdapter(Context context, int layoutResId, List<T> datas) {
        super(context, layoutResId, datas);
    }

}
