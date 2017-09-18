package com.lenovo.service.basicpubliclibrary.recyclerview.item.decoration.line.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @description: 重写分割线
 * @author:袁东华
 * @time:2017/9/15 下午5:32
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    //分割线的高度
    private float dividerHeight = 1;
    //画笔
    private Paint paint;
    //分割线的颜色
    private int color;
    //第一个条目顶部是否有分割线
    private boolean firstItemHasDivider;

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

    public DividerItemDecoration() {
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
            /**
             * 在这里outRect.top = 1; 所以每个 ItemView 之间有 1 px 的空隙，
             * 而这 1 px 空隙透露了下面背景色，所以看起来就像是分隔线，这实现了简单的分隔线效果，
             * 但这种方法分隔线的效果只能取决于背景色，如果我要定制分割线的颜色呢？这个时候就要讲到一个新的方法名 onDraw()。
             */

        } else {
        }

    }

    /**
     * 每一个 View 中 onDraw() 是很重要的一个方法，用来绘制组件的UI效果，所以在 ItemDecocration 中它自然也是用来绘制外观的
     * 但是怎么绘制呢?其实它是配合前面的 getItemOffsets 方法一起使用的,
     * 1.getItemOffsets 撑开了 ItemView 的上下左右间隔区域，
     * 2.而 onDraw 方法通过计算每个 ItemView 的坐标位置与它的 outRect 值来确定它要绘制内容的区间
     * <p>
     * 举个例子:设计一个高度为5px的红色分割线,那么我们就需要在每个ItemView的top位置上画一个5px高度的矩形,然后填充为红色,就ok了.
     * <p>
     * 需要注意的一点是 getItemOffsets 是针对每一个 ItemView，
     * 而 onDraw 方法却是针对 RecyclerView 本身，所以在 onDraw 方法中需要遍历屏幕上可见的 ItemView，
     * 分别获取它们的位置信息，然后分别的绘制对应的分割线。
     *
     * @param c      Canvas 不止能画直线, 画圆、画矩形、画弧形、绘制图片都不在话下
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //获取item的个数
        int childCount = parent.getChildCount();
        //遍历item
        for (int i = 0; i < childCount; i++) {
            //获取每一个条目
            View childAt = parent.getChildAt(i);
            //获取条目对应的索引
            int childAdapterPosition = parent.getChildAdapterPosition(childAt);

            if (childAdapterPosition == 0 && !firstItemHasDivider) {
                //第一个item,不用绘制
                continue;
            }
            //计算分割线的顶部坐标
            float dividerTop = childAt.getTop() - dividerHeight;
            //计算分割线的左边坐标
            float dividerLeft = parent.getPaddingLeft();
            //计算分割线的底部坐标
            float dividerBottom = childAt.getTop();
            //计算分割线的右边坐标
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            //绘制分割线矩形
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);


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
}
