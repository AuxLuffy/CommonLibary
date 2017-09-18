package com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.rankinglist.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @description: 排行榜效果的ItemDecoration
 * @author:袁东华
 * @time:2017/9/16 下午6:05
 */
public class RankingListItemDecoration extends RecyclerView.ItemDecoration {
    //分割线的高度
    private float dividerHeight = 1;
    //画笔
    private Paint paint;
    //分割线的颜色
    private int color;
    //第一个条目顶部是否有分割线
    private boolean firstItemHasDivider;
    //itemView左边的距离
    private float itemViewLeft;
    private Bitmap[] icon;



    public RankingListItemDecoration() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);


    }

    /**
     * outRect等同于 margin 属性，通过复写 getItemOffsets() 方法，
     * 然后指定 outRect 中的 top、left、right、bottom 就可以控制各个方向的间隔了。
     * 注意的是这些属性都是偏移量，是指偏移 ItemView 各个方向的数值。
     *
     * @param outRect 是一个全为 0 的 Rect
     * @param view    RecyclerView 中的 Item
     * @param parent  就是 RecyclerView 本身
     * @param state   state 就是一个状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //判断如果不是第一个条目走这里,因为第一个item顶部不需要分割线
        if (parent.getChildAdapterPosition(view) != 0 || firstItemHasDivider) {
            //设置1px间距
            outRect.top = (int) dividerHeight;


        }else{
            outRect.top = 0;
        }
        outRect.left= (int) itemViewLeft;

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);

            int childAdapterPosition = parent.getChildAdapterPosition(childAt);
            int top = childAt.getTop();
            int bottom = childAt.getBottom();

            if (childAdapterPosition<3){
                c.drawBitmap(icon[childAdapterPosition],itemViewLeft/2,top+(bottom-top)/2,paint);
            }


        }

    }

    /**
     * 设置第一个条目顶部是否有分割线
     *
     * @param firstItemHasDivider
     */
    public void setFirstItemHasDivider(boolean firstItemHasDivider) {
        this.firstItemHasDivider = firstItemHasDivider;
    }

    public float getDividerHeight() {
        return dividerHeight;
    }

    public void setDividerHeight(float dividerHeight) {
        this.dividerHeight = dividerHeight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        //设置画笔的颜色
        paint.setColor(color);
    }

    public float getItemViewLeft() {
        return itemViewLeft;
    }

    public void setItemViewLeft(float itemViewLeft) {
        this.itemViewLeft = itemViewLeft;
    }



    public Bitmap[] getIcon() {
        return icon;
    }

    public void setIcon(Bitmap[] icon) {
        this.icon = icon;
    }
}
