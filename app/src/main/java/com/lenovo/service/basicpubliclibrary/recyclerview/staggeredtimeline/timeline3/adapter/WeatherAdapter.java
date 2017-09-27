package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline3.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnMoveAndSwipedListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnStateChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 天气的适配器
 * @author:袁东华 created at 2016/8/30 0030 下午 2:00
 */
public class WeatherAdapter extends Adapter<WeatherAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private List<String> list;
    private Handler handler;

    public WeatherAdapter(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private OnDragListener onDragListener;

    public void setOnDragListener(OnDragListener onDragListener) {
        this.onDragListener = onDragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_time3, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.task_edit.setText(list.get(position));
        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > position) {
                    list.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EditText task_edit;
        private ImageView trash, edit;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            task_edit = (EditText) itemView.findViewById(R.id.task_edit);
            trash = (ImageView) itemView.findViewById(R.id.trash);
            edit = (ImageView) itemView.findViewById(R.id.edit);
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
