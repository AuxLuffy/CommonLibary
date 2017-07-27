package com.lenovo.service.basicpubliclibrary.headerfloatanimation.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.control.ImageHandler;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageMode;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageModeMapping;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.pattern.LoadImageSingleton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shengtao on 2016/4/18.
 */
public class GridLayoutRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    GridLayoutManager manager;
    /*private HomePageMode mHomePageModeOriginal;//原始数据*/
    private ArrayList<HomePageModeMapping> mHomePageModeMappings;//映射后的数据
    public static final int TYPE_LCM = 6;//几种类型（每行个数)的最小公倍数
    /**
     * 类型
     */
    public static final int TYPE_HEADER = 0;//header的情况
    public static final int TYPE_TITLE = 1;//title
    public static final int TYPE_THREE = 3;//每行两个
    public static final int TYPE_TWO = 2;//每行三个
    /**
     * Banner
     */
    //
    ArrayList<HomePageMode.CategoryOne.CategoryTwo> list;
    ImageView[] imageViews;
    public ImageHandler handler ;
    public GridBannerViewHolder gridBannerViewHolder;
    private View bannerView;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    /**
     * @param context
     * @param manager 需要manager来得到getSpanSizeLookup().getSpanIndex，以便得出是不是每行第一个
     */
    public GridLayoutRecyclerViewAdapter(Context context, GridLayoutManager manager, ArrayList<HomePageModeMapping> homePageModeMappingArrayList) {
        mContext = context;
        this.manager = manager;
        mLayoutInflater = LayoutInflater.from(context);
        handler=new ImageHandler(new WeakReference<GridLayoutRecyclerViewAdapter>(this));
        //构造方法，应该只执行一次，那么可以在这里处理数据
        mHomePageModeMappings = homePageModeMappingArrayList;
    }

    public View getBannerView() {
        return bannerView;
    }

    /**
     * 根据类型决定creat哪种ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //header，就是banner部分
        if (viewType == TYPE_HEADER) {
            bannerView=mLayoutInflater.inflate(R.layout.vip_banner_viewpager, null, false);
            return new GridBannerViewHolder(bannerView);
        }
        //title部分
        if (viewType == TYPE_TITLE) {
            return new GridTitleViewHolder(mLayoutInflater.inflate(R.layout.vip_recycleview_grid_title, null, false));
        }
        //两个占一行的布局
        else if (viewType == TYPE_TWO) {
            return new GridItemViewHolder(mLayoutInflater.inflate(R.layout.vip_recycleview_grid_item_two, null, false));
        }
        //三个占一行的布局
        else if (viewType == TYPE_THREE) {
            return new GridItemViewHolder(mLayoutInflater.inflate(R.layout.vip_recycleview_grid_item_three, null, false));
        }
        else {
            return null;
        }
    }
    public void setBannerData(int position,GridBannerViewHolder holder){

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list=mHomePageModeMappings.get(position).getBanner();

        /*for(int i=0;i<mHomePageModeMappings.get(position).getBanner().size();i++){
            ViewGroup viewGroup=null;
            viewGroup=(ViewGroup)inflater.inflate(R.layout.item01, null);//每次都要inflate一次
            list.add(viewGroup);
        }*/
    }

    /**
     * 添加banner底部的诸多指示点
     * @param gridBannerViewHolder
     */
    public void addDots(GridBannerViewHolder gridBannerViewHolder) {
        imageViews = new ImageView[list.size()];
        if(list.size()>1) {
            gridBannerViewHolder.group.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                ImageView  imageView = new ImageView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 0, 10, 0);
                imageView.setLayoutParams(params);
                imageViews[i] = imageView;
                if (i == 0) {
                    imageView.setBackgroundResource(R.drawable.page_indicator_focused);
                } else {
                    imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
                }
                gridBannerViewHolder.group.addView(imageView);
            }
        }
    }

    public void setAdapter(GridBannerViewHolder gridBannerViewHolder) {
        gridBannerViewHolder.viewPager.setAdapter(new BannerAdapter(mContext,list));
        gridBannerViewHolder.viewPager.addOnPageChangeListener(new BannerViewPagerChangeListener());
        int mid = Integer.MAX_VALUE / 2;
        gridBannerViewHolder.viewPager.setCurrentItem(mid - ((mid + 1) % list.size() - 1));
        if (list.size() > 1) {
            handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        }
    }

    /**
     * 为选中的点点，设置背景色
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < imageViews.length; i++) {
            if (i == selectItems) {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
    /**
     * 处理控件的显示，以及界面的逻辑
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int mType = getItemViewType(position);
        if(mType==TYPE_HEADER){
            gridBannerViewHolder = (GridBannerViewHolder) holder;
            setBannerData(position,gridBannerViewHolder);
            addDots(gridBannerViewHolder);
            setAdapter(gridBannerViewHolder);
        }
        else if (mType == TYPE_THREE || mType == TYPE_TWO) {
            GridItemViewHolder gridItemViewHolder = (GridItemViewHolder) holder;
            //设置中间的分割线
            int spanIndexOnHorizontal = manager.getSpanSizeLookup().getSpanIndex(position, TYPE_LCM);//如果这个值为0，说明是最左边的item
            if (0 != spanIndexOnHorizontal) {
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(5, 0, 0, 0);
                gridItemViewHolder.getHolderLayout().setLayoutParams(lp);

            }
            //记得恢复状态，否则一旦布局复用，不满足这一条件的，也可能因为复用了if里的布局，导致非中间item出现左margin
            else{
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, 0);
                gridItemViewHolder.getHolderLayout().setLayoutParams(lp);
            }
            //if(mType == TYPE_TWO) {
                String imageUrl = mHomePageModeMappings.get(position).getImage_url();
                LoadImageSingleton.getInstance().displayImage(mContext, imageUrl, gridItemViewHolder.mItemImageView);
            //}

        } else if (mType == TYPE_TITLE) {
            GridTitleViewHolder gridTitleViewHolder = (GridTitleViewHolder) holder;
            gridTitleViewHolder.mItemTitle.setText("正在热播");
        }


    }

    /**
     * 返回约定好的type，每个item有一个type，
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return mHomePageModeMappings.get(position).getType()/*mType[position]*/;
    }
    public class BannerViewPagerChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrollStateChanged(int arg0) {

            switch (arg0) {
                //正在拖动页面时执行此处
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                    break;
                //未拖动页面时执行此处
                case ViewPager.SCROLL_STATE_IDLE:
                    if(list.size()>1) {
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    }
                    break;

                default:
                    break;
            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
        @Override
        public void onPageSelected(int position) {
            //当前选择的是position % list.size()
            if(2==list.size()) {
                setImageBackground((position % list.size() % 2));
            }
            else if(list.size()>2){
                setImageBackground(position % list.size());
            }
            handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
        }
    }
    /**
     * 返回总个数，而不是listview中，多少种类型的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mHomePageModeMappings.size()/*.length*/;
    }

    /**
     * gridlayout itemView的ViewHolder
     */
    public class GridItemViewHolder extends ViewHolder {
        private View mView;
        public View getHolderLayout() {
            return mView;
        }
        ImageView mItemImageView;
        GridItemViewHolder(View view) {
            super(view);
            mView = view;

            mItemImageView = (ImageView) view.findViewById(R.id.vip_image_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"onClick:"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * title 的ViewHolder
     */

    public class GridTitleViewHolder extends ViewHolder {

        TextView mItemTitle;

        GridTitleViewHolder(View view) {
            super(view);
            mItemTitle = (TextView) view.findViewById(R.id.text_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"onClick:"+getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     *banner的viewHolder
     */
    public class GridBannerViewHolder extends  ViewHolder{
        public ViewPager viewPager;
        public ViewGroup group;
        GridBannerViewHolder(View view){
            super(view);
            viewPager= (ViewPager) view.findViewById(R.id.viewPager);
            group=(ViewGroup) view.findViewById(R.id.viewGroup);
        }
    }
}
