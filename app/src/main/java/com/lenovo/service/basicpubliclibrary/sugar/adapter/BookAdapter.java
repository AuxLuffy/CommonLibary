package com.lenovo.service.basicpubliclibrary.sugar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;
import com.lenovo.service.basicpubliclibrary.sugar.bean.Book;

import java.util.List;

/**
 * Created by xuxiaowei on 2017/9/14.
 */

public class BookAdapter extends RecyclerView.Adapter{
    private List<Book> list;

    private Context mContext;

    public BookAdapter(List<Book> list, Context mContext){
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
        mPersonViewHolder.textview.setText(list.get(position).getIsbn()+"        "+list.get(position).getTitle()+"        "+list.get(position).getEdition());
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
