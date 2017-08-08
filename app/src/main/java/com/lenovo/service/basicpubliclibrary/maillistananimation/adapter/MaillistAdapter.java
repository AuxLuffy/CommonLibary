package com.lenovo.service.basicpubliclibrary.maillistananimation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.maillistananimation.bean.MaillistBean;

import java.util.List;

/**
 * Created by lenovo on 2017/8/3.
 */

public class MaillistAdapter extends BaseAdapter {

    private List<MaillistBean> list;
    private Context context;

    public MaillistAdapter(List<MaillistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_maillistadapter, null);
            holder = new ViewHolder();
            holder.nametv = (TextView) convertView.findViewById(R.id.contactName);
            holder.numbertv = (TextView) convertView.findViewById(R.id.contactNumber);
            holder.nametv.setText(list.get(position).getName());
            holder.numbertv.setText(list.get(position).getNumber());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.nametv.setText(list.get(position).getName());
            holder.numbertv.setText(list.get(position).getNumber());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nametv;
        TextView numbertv;
    }

}
