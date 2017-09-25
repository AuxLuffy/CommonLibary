package com.lenovo.service.basicpubliclibrary.recyclerview.suspension2.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.service.basicpubliclibrary.recyclerview.suspension2.listener.GroupListener;
import com.lenovo.service.basicpubliclibrary.utils.LogUtil;

/**
 * @description: 重写分割线
 * @author:袁东华
 * @time:2017/9/15 下午5:32
 */
public class SuspensionItemDecoration extends RecyclerView.ItemDecoration {
    //分割线的高度
    private float dividerHeight = 1;
    //分割线画笔
    private Paint dividerPaint;
    //分组画笔
    private Paint groupPaint;
    //分组的文字画笔
    private TextPaint textPaint;
    //分割线的颜色
    private int color;
    //分组的颜色
    private int groupColor;
    //分组的高度
    private float groupHeight;


    private int groupTextSize;

    private int groupTextColor;


    private GroupListener groupListener;


    public SuspensionItemDecoration(GroupListener listener) {
        this.groupListener = listener;
        dividerPaint = new Paint();
        //设置抗锯齿
        dividerPaint.setAntiAlias(true);
        groupPaint = new Paint();
        //设置抗锯齿
        groupPaint.setAntiAlias(true);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.LEFT);

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
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        //只有是同一组的第一个条目上方才会显示悬浮栏
        if (isFirstInGroup(childAdapterPosition)) {
            //设置悬浮栏的高度
            outRect.top = (int) groupHeight;
        } else {
            //其他的条目设置分割线的高度
            outRect.top = (int) dividerHeight;
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //获取所有item的个数
        int itemCount = state.getItemCount();
        //获取当前屏幕item的个数
        int childCount = parent.getChildCount();

        String lastGroupName;
        String currentGroupName = null;


        //遍历item
        for (int i = 0; i < childCount; i++) {
            //获取每一个条目
            View childAt = parent.getChildAt(i);
            //获取条目对应的索引
            int childAdapterPosition = parent.getChildAdapterPosition(childAt);
            lastGroupName = currentGroupName;
            currentGroupName = getGroupName(childAdapterPosition);

            if (currentGroupName == null || TextUtils.equals(lastGroupName, currentGroupName)) {
                //绘制分割线
                int top = childAt.getTop();
                if (top < groupHeight) {
                    //itemView的顶部小于分组的高度时跳过绘制
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
                c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, dividerPaint);

            } else {
                //绘制悬浮栏
                //判断悬浮栏的底部
                float titleBottom = Math.max(groupHeight, childAt.getTop());
                if (childAdapterPosition + 1 < itemCount) {
                    //获取下一个分组
                    String nextGroupName = getGroupName(childAdapterPosition + 1);
                    int viewBottom = childAt.getBottom();

                    if (!currentGroupName.equals(nextGroupName) && titleBottom > viewBottom) {
                        titleBottom = viewBottom;
                    }


                }
                //根据top绘制分组的背景
                float dividerLeft = parent.getPaddingLeft();
                float dividerRight = parent.getWidth() - parent.getPaddingRight();
                c.drawRect(dividerLeft, titleBottom - groupHeight, dividerRight, titleBottom, groupPaint);

//                Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
//                //文字垂直居中
//                float baseLine = titleBottom - (groupHeight - (fontMetrics.bottom - fontMetrics.top)) / 2 - fontMetrics.bottom;
//                //获取文字的宽度
//                float textWidth = textPaint.measureText(currentGroupName);
//                c.drawText(currentGroupName, dividerLeft + 20, baseLine, textPaint);


                View groupView = getGroupView(childAdapterPosition);
                if (groupView == null) {
                    continue;
                }
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) (dividerRight - dividerLeft), (int) groupHeight);
                groupView.setLayoutParams(layoutParams);
                groupView.setDrawingCacheEnabled(true);
                groupView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                groupView.layout(0,0,(int) (dividerRight - dividerLeft), (int) groupHeight);
                groupView.buildDrawingCache();

                LogUtil.e("悬浮栏的宽度:"+groupView.getWidth());

                Bitmap drawingCache = groupView.getDrawingCache();

                c.drawBitmap(drawingCache,dividerLeft,titleBottom - groupHeight, null);


            }


        }
    }

    /**
     * 获取分组的View
     *
     * @param position
     * @return
     */
    public View getGroupView(int position) {
        if (groupListener != null) {
            return groupListener.getGroupView(position);
        } else {
            return null;
        }
    }

    /**
     * 判断是不是新组,判断是不是组中的第一个位置
     *
     * @param position
     * @return
     */
    public boolean isFirstInGroup(int position) {
        if (position == 0) {
            return true;
        } else {
            String lastGroupName = getGroupName(position - 1);
            String groupName = getGroupName(position);
            return !TextUtils.equals(lastGroupName, groupName);
        }
    }

    public String getGroupName(int position) {

        if (groupListener != null) {
            return groupListener.getGroupName(position);
        }
        return null;
    }


    public static class Builder {
        private SuspensionItemDecoration suspensionItemDecoration;

        private Builder(GroupListener listener) {
            suspensionItemDecoration = new SuspensionItemDecoration(listener);
        }

        public static Builder init(GroupListener listener) {
            return new Builder(listener);
        }


        public Builder setGroupHeight(float groupHeight) {
            suspensionItemDecoration.groupHeight = groupHeight;

            return this;
        }


        public Builder setGroupListener(GroupListener groupListener) {
            suspensionItemDecoration.groupListener = groupListener;
            return this;
        }


        public Builder setGroupColor(int groupColor) {
            suspensionItemDecoration.groupColor = groupColor;
            suspensionItemDecoration.groupPaint.setColor(groupColor);
            return this;
        }


        public Builder setGroupTextSize(int groupTextSize) {
            suspensionItemDecoration.groupTextSize = groupTextSize;
            suspensionItemDecoration.textPaint.setTextSize(groupTextSize);
            return this;
        }


        public Builder setGroupTextColor(int groupTextColor) {
            suspensionItemDecoration.groupTextColor = groupTextColor;
            suspensionItemDecoration.textPaint.setColor(groupTextColor);
            return this;
        }

        public Builder setDividerHeight(float dividerHeight) {
            suspensionItemDecoration.dividerHeight = dividerHeight;
            return this;
        }


        public Builder setColor(int color) {
            suspensionItemDecoration.color = color;
            //设置画笔的颜色
            suspensionItemDecoration.dividerPaint.setColor(color);
            return this;
        }

        public SuspensionItemDecoration build() {
            return suspensionItemDecoration;
        }
    }

}
