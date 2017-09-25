package com.lenovo.service.basicpubliclibrary.recyclerview.suspension.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnMoveAndSwipedListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnStateChangedListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.suspension.bean.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 悬浮效果适配器
 * @author:袁东华
 * @time:2017/9/23 下午4:28
 */
public class SuspensionAdapter extends Adapter<SuspensionAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private  List<City.ProvincesBean.CitysBean> city;

    public SuspensionAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData( List<City.ProvincesBean.CitysBean> city) {
        this.city = city;
        notifyDataSetChanged();
    }

    private OnDragListener onDragListener;

    public void setOnDragListener(OnDragListener onDragListener) {
        this.onDragListener = onDragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_suspension, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {





        holder.title_tv.setText(city.get(position).getCitysName());


    }

    @Override
    public int getItemCount() {

        return city!=null?city.size():0;

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title_tv;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }

    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }
}
