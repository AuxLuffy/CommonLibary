package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.adapter;

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

import com.bumptech.glide.Glide;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.data.Weather;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnDragListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnItemClickListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnMoveAndSwipedListener;
import com.lenovo.service.basicpubliclibrary.recyclerview.staggeredgrid.listener.OnStateChangedListener;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 天气的适配器
 * @author:袁东华 created at 2016/8/30 0030 下午 2:00
 */
public class WeatherAdapter extends Adapter<WeatherAdapter.ViewHolder> implements OnMoveAndSwipedListener {
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;
    private List<Integer> list;

    public WeatherAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(List<Integer> list) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_staggergrid, parent, false);
        viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(activity).load(list.get(position)).fitCenter().into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    /**
     * @param fromPosition
     * @param toPosition
     * @description:拖拽条目
     * @author:袁东华 created at 2016/8/30 0030 上午 11:26
     */
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        LogUtil.e("开始移动");
        //交换数据的位置
        Collections.swap(list, fromPosition, toPosition);
        //交换RecyclerView中条目的位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    /**
     * @param position
     * @description:删除条目
     * @author:袁东华 created at 2016/8/30 0030 上午 11:48
     */
    @Override
    public void onItemDelete(int position) {
        //删除该条目的数据
        list.remove(position);
        //删除recyclerView中的该条目
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, com.lenovo.service.basicpubliclibrary.recyclerview.item.handle.listener.OnStateChangedListener {
        private ImageView imageView;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());

            }
        }

        /**
         * @description:拖拽,滑动item时调用,可以做改变背景色操作
         * @author:袁东华 created at 2016/8/31 0031 下午 1:51
         */
        @Override
        public void onSelectedChanged() {
            itemView.setAlpha(0.5f);
        }

        /**
         * @description:条目正常状态:拖拽,滑动结束后,背景色恢复正常
         * @author:袁东华 created at 2016/8/31 0031 下午 2:37
         */
        @Override
        public void clearView() {

            itemView.setAlpha(1.0f);
        }

    }

    /**
     * @Description:设置条目点击监听,供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }
}
