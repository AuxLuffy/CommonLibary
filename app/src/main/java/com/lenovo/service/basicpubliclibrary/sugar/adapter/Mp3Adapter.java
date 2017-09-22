package com.lenovo.service.basicpubliclibrary.sugar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;

import java.io.File;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/9/22.
 */

public class Mp3Adapter extends RecyclerView.Adapter {

    private List<File> list;
    private Context mContext;

    public Mp3Adapter(List<File> list, Context mContext){
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_person, parent, false);
        Mp3ViewHolder viewHolder = new Mp3ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mp3ViewHolder mPm3ViewHolder = (Mp3ViewHolder)holder;
        mPm3ViewHolder.textview.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Mp3ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textview;

        public Mp3ViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.textview);
        }
    }

}
