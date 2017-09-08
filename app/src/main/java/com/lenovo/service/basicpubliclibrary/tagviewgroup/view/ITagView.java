package com.lenovo.service.basicpubliclibrary.tagviewgroup.view;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public interface ITagView {

    // set tag direction
    void setDirection(DIRECTION direction);

    // get tag dirction
    DIRECTION getDirection();

    int getMeasuredWidth();

    int getMeasuredHeight();

    int getTop();

    int getLeft();

    int getRight();

    int getBottom();

    void layout(int left, int top, int right, int bottom);

}

