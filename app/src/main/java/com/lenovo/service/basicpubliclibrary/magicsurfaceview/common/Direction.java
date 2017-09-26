package com.lenovo.service.basicpubliclibrary.magicsurfaceview.common;

/**
 * Created by tangrenmei on 2017/9/25.
 */

public class Direction {
    public final static int LEFT = 0;
    public final static int TOP = 1;
    public final static int RIGHT = 2;
    public final static int BOTTOM = 3;

    public static boolean isVertical(int direction) {
        return direction == TOP || direction == BOTTOM;
    }
}
