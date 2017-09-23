package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lenovo.service.basicpubliclibrary.R;

import java.util.List;



public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    Context mContext;
    List<Item> mList;

    public void setList(List<Item> list) {
        mList = list;
    }

    public TimeLineAdapter(Context context) {
        mContext = context;
    }

    public TimeLineAdapter(Context context,List<Item> list) {
        mContext = context;
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_staggered_timeline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(mList.get(position).getResId());
        holder.textView.setText(mList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

}
