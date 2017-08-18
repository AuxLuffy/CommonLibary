package com.lenovo.service.basicpubliclibrary.obtainlocalphoto.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.LinkedList;
import java.util.List;


public class GirdItemAdapter extends CommonAdapter<String>{
	private String mDirPath;
	private Context context;
	public static List<String> mSelectedImage = new LinkedList<String>();
	private int maxSize=5;
	public GirdItemAdapter(Context context, List<String> mDatas, String mDirPath, int maxSize) {
		super(context,mDatas);
		this.maxSize=maxSize;
		this.mDirPath=mDirPath;
		this.context=context;
	}
	public void changeData(List<String> mDatas, String mDirPath){
		this.mDatas = mDatas;
		this.mDirPath=mDirPath;
		notifyDataSetChanged();
	}

	@Override
	public void convert(ViewHolder helper, String item, int type) {
		switch (type) {
			case TYPE_1:
				final LinearLayout mImageView = helper.getView(R.id.id_item_image2);
				mImageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						onPhotoSelectedListener.takePhoto();
					}
				});
				break;
			case TYPE_2:
				final ImageView myImageView = helper.getView(R.id.id_item_image);
				final ImageButton myImageSelected = helper.getView(R.id.id_item_select);
				myImageSelected.setImageResource(R.drawable.picture_unselected);
				myImageView.setImageResource(R.drawable.pictures_no);
				myImageView.setBackgroundResource(R.drawable.pictures_no);
				helper.setImageByUrl(R.id.id_item_image,mDirPath + "/"+item);
				myImageView.setColorFilter(null);
				myImageView.setOnClickListener(new MyOnClickListener(helper,item));
				if (mSelectedImage.contains(mDirPath + "/" + item)){
					myImageSelected.setImageResource(R.drawable.pictures_selected);
					myImageView.setColorFilter(Color.parseColor("#77000000"));
				} else{
					myImageSelected.setImageResource(R.drawable.picture_unselected);
					myImageView.setColorFilter(Color.parseColor("#00000000"));
				}
				break;

			default:
				break;
		}
	}

	class MyOnClickListener implements OnClickListener {
		String item;
		ViewHolder helper;
		ImageView myitemImageView ;
		ImageButton myitemImageSelected;
		MyOnClickListener(ViewHolder helper,String item){
			this.item=item;
			this.helper=helper;
			myitemImageSelected=helper.getView(R.id.id_item_select);
			myitemImageView= helper.getView(R.id.id_item_image);
		}
		@Override
		public void onClick(View v) {
			if (mSelectedImage.contains(mDirPath + "/" + item)){
				mSelectedImage.remove(mDirPath + "/" + item);
				myitemImageSelected.setImageResource( R.drawable.picture_unselected);
				myitemImageView.setColorFilter(null);
			} else{
				if(mSelectedImage.size()>=maxSize){
					Toast.makeText(context, "最多上传"+maxSize+"张", Toast.LENGTH_SHORT).show();
					return ;
				}
				mSelectedImage.add(mDirPath + "/" + item);
				myitemImageSelected.setImageResource( R.drawable.pictures_selected);
				myitemImageView.setColorFilter(Color.parseColor("#77000000"));
			}
			onPhotoSelectedListener.photoClick(mSelectedImage);
		}

	}
	public OnPhotoSelectedListener onPhotoSelectedListener;
	public void setOnPhotoSelectedListener(OnPhotoSelectedListener onPhotoSelectedListener){
		this.onPhotoSelectedListener=onPhotoSelectedListener;
	}
	public  interface OnPhotoSelectedListener{
		public void photoClick(List<String> number);
		public void takePhoto();
	}

}
