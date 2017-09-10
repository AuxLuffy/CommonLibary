package com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.LoadMoreRecyclerView.base.LoadMoreAdapter;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cx on 2017/9/7.
 */

public class TestAdapter extends LoadMoreAdapter<String> {

    public TestAdapter(Context context, List<String> list, LoadMoreAdapter.OnLoadMoreListener loadMoreListener) {

        super(context, list, loadMoreListener);

    }

    @Override
    protected RecyclerView.ViewHolder getNormalHolder(ViewGroup parent) {

        View view = inflater.inflate(R.layout.activity_recycler_listitem, parent, false);

        MachineHolder holder = new MachineHolder(view);

        return holder;
    }

    @Override
    protected void setHolderData(RecyclerView.ViewHolder holder, final int position) {

        MachineHolder machineHolder = (MachineHolder) holder;

        machineHolder.text.setText(list.get(position).toString());

        machineHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //do Something
            }
        });

    }

    class MachineHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;

        public MachineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
