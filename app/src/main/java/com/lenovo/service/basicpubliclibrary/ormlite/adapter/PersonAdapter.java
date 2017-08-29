package com.lenovo.service.basicpubliclibrary.ormlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;

import java.util.List;

/**
 * Created by xuxiaowei on 2017/8/29.
 */

public class PersonAdapter extends RecyclerView.Adapter{
    private List<Person> list;

    private Context mContext;

    public PersonAdapter(List<Person> list, Context mContext){
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_person, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PersonViewHolder mPersonViewHolder = (PersonViewHolder)holder;
        mPersonViewHolder.textview.setText("姓名："+list.get(position).getName()+"    身高："+list.get(position).getHeight()+"    体重："+list.get(position).getWeight());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder{

        private final TextView textview;

        public PersonViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
