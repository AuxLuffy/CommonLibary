package com.lenovo.service.basicpubliclibrary.obtainlocalphoto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.List;

/**
 * 每个图片item的布局。
 * @Author 李巷阳
 * Created at 2017/8/21 14:36
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
	final int VIEW_TYPE = 2;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	private String mDirPath;
	public CommonAdapter(Context context, List<T> mDatas)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mDirPath=mDirPath;
	}
	@Override
	public int getCount()
	{
		return mDatas.size()+1;
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		int type = getItemViewType(position);
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent,type);
		switch (type){
			case TYPE_1:
				convert(viewHolder, getItem(0),type);
				break;
			case TYPE_2:
				convert(viewHolder, getItem(position-1),type);
				break;
		}
		return viewHolder.getConvertView();

	}
	public int getItemViewType(int position) {
		if(position==0){
			return TYPE_1;
		}else{
			return TYPE_2;
		}
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE;
	}

	public abstract void convert(ViewHolder helper, T item,int type);

	private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent, int type)
	{	int mItemLayoutId= R.layout.grid_item;
		switch (type){
			case TYPE_1:
				mItemLayoutId= R.layout.grid_item2;
				break;
			case TYPE_2:
				mItemLayoutId=R.layout.grid_item;
				break;
		}
		return ViewHolder.get(mContext, convertView, parent,mItemLayoutId ,
				position);
	}

}
