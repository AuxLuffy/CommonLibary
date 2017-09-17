package com.lenovo.service.basicpubliclibrary.recyclerview.staggeredtimeline.timeline1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;


/**
 *@description: 瀑布流式时光轴效果
 *@author:袁东华
 *@time:2017/9/17 下午5:46
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    //间距
    private int distance;
    //时光轴节点图片
    private Drawable nodeDrawable;
    //时光轴的纵线
    private  Drawable verticalLine;
    //时光轴的横线
    private Drawable horizontalLine;
    //itemView距离屏幕的左右间距
    private int distanceBetweenScreenAndItem;


    public ItemDecoration(Context context) {
        mContext = context;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = distance;
        outRect.right = distance;
        outRect.bottom = 3 * distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = distance;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 4 * distance;
        }
        //itemView的索引是偶数的时候重置outRect.left ,也就是设置itemView的左间距
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = distanceBetweenScreenAndItem;
        } else {
            //itemView的索引是奇数的时候重置outRect.right ,也就是设置itemView的右间距
            outRect.right = distanceBetweenScreenAndItem;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVerticalLine(c, parent);
        drawHorizontalLine(c, parent);
    }

    /**
     * 绘制纵线
     * @param c
     * @param parent
     */
    public void drawVerticalLine(Canvas c, RecyclerView parent) {
        //获取RecyclerView的top
        final int top = parent.getPaddingTop();
        //获取RecyclerView的bottom
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        //获取RecyclerView的宽度
        final int parentWidth = parent.getMeasuredWidth();

        verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1,bottom);
        verticalLine.draw(c);
    }

    /**
     * 绘制横线
     * @param c
     * @param parent
     */
    public void drawHorizontalLine(Canvas c, RecyclerView parent) {
        //获取RecyclerView的宽度
        final int parentWidth = parent.getMeasuredWidth();
        //获取当前屏幕itemView的个数
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //-1最后一个不画
            final View child = parent.getChildAt(i);
            //时间节点的top
            final int top = child.getTop() + (child.getBottom() - child.getTop()) / 2 - nodeDrawable.getIntrinsicHeight() / 2;
            //时间节点的bottom
            final int bottom = top + nodeDrawable.getIntrinsicHeight();
            //横线的left
            int horizontalLineLeft = child.getRight();
            //横线的right
            int horizontalLineRight = parentWidth / 2-nodeDrawable.getIntrinsicWidth()/2;
            //横线的top
            int horizontalLineTop = child.getTop() + (child.getBottom() - child.getTop()) / 2;

            if (child.getLeft()<parentWidth/2) {
                horizontalLineLeft = parentWidth / 2;
                horizontalLineRight = child.getLeft();
            }

            horizontalLine.setBounds(horizontalLineLeft, horizontalLineTop, horizontalLineRight, horizontalLineTop + 2);
            horizontalLine.draw(c);

            int drawableLeft = parentWidth / 2 - nodeDrawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + nodeDrawable.getIntrinsicWidth() / 2;

            nodeDrawable.setBounds(drawableLeft, top, drawableRight, bottom);
            nodeDrawable.draw(c);
        }
    }

    public int getDistanceBetweenScreenAndItem() {
        return distanceBetweenScreenAndItem;
    }

    public void setDistanceBetweenScreenAndItem(int distanceBetweenScreenAndItem) {
        this.distanceBetweenScreenAndItem = distanceBetweenScreenAndItem;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Drawable getNodeDrawable() {
        return nodeDrawable;
    }

    public void setNodeDrawable(Drawable nodeDrawable) {
        this.nodeDrawable = nodeDrawable;
    }

    public Drawable getVerticalLine() {
        return verticalLine;
    }

    public void setVerticalLine(Drawable verticalLine) {
        this.verticalLine = verticalLine;
    }

    public Drawable getHorizontalLine() {
        return horizontalLine;
    }

    public void setHorizontalLine(Drawable horizontalLine) {
        this.horizontalLine = horizontalLine;
    }
}