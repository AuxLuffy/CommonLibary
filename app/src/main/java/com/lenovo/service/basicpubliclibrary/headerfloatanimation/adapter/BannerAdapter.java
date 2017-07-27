package com.lenovo.service.basicpubliclibrary.headerfloatanimation.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageMode;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.pattern.LoadImageSingleton;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/26.
 */
public class BannerAdapter extends PagerAdapter {
    private ArrayList<View> mViewList = new ArrayList<View>();
    private ArrayList<HomePageMode.CategoryOne.CategoryTwo> mDataList=new ArrayList<HomePageMode.CategoryOne.CategoryTwo>();
    private Context mContext;

    public BannerAdapter(Context context, ArrayList<HomePageMode.CategoryOne.CategoryTwo> dataList) {
        if(null!=dataList&&dataList.size()>0) {
            int dataNum=dataList.size();
            if (dataNum == 2) {
                for(int i=0;i<dataNum*2;i++){
                    mDataList.add(dataList.get(i%dataNum));
                }

            } else {
                mDataList = dataList;
            }

        }
        mContext=context;
        addView();

    }
    public void addView(){
        //mList=list;//这样会传递引用，此处的修改，会影响mainActivity中的值
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       /* for (int i = 0; i < list.size(); i++) {
            mList.add(list.get(i));
        }*/
        int totalNum=0;
        if(2==mDataList.size()){
            totalNum=mDataList.size()*2;
        }
        else{
            totalNum=mDataList.size();
        }

        for(int i=0;i<totalNum;i++){
            ViewGroup viewGroup=null;
            viewGroup=(ViewGroup)inflater.inflate(R.layout.vip_banner_item, null);//每次都要inflate一次
            mViewList.add(viewGroup);
        }
        //如果数据只有两个，会产生bug，所以，变为4个即可
      /*  if (mDataList.size() == 2) {
            for (int i = 0; i < mDataList.size(); i++) {
                mViewList.add((View) inflater.inflate(R.layout.vip_banner_item, null));
            }
        }*/
    }

    @Override
    public int getCount() {
        if (1 == mDataList.size()) {
            return 1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int index = position % mDataList.size();
        ViewGroup viewGroup = (ViewGroup) mViewList.get(index);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.imageView1);
        LoadImageSingleton.getInstance().displayImage(mContext,mDataList.get(index).getImage_url(), imageView);
       /* if (index == 0) {
            imageView.setImageResource(R.drawable.item07);
        } else if (index == 1) {
            imageView.setImageResource(R.drawable.item01);
        } else if (2 == index) {
            imageView.setImageResource(R.drawable.item02);
        } else if (3 == index) {
            imageView.setImageResource(R.drawable.item03);
        } else if (4 == index) {
            imageView.setImageResource(R.drawable.item04);
        } else if (5 == index) {
            imageView.setImageResource(R.drawable.item05);
        } else if (6 == index) {
            imageView.setImageResource(R.drawable.item06);
        }*/
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(viewGroup, 0);
        return viewGroup;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView(mViewList.get(position % mDataList.size()));
    }
}
