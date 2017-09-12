package com.lenovo.service.basicpubliclibrary.timepicker;

import android.app.Activity;
import android.view.View;




/**
 * 滑轮选择器
 *
 */

public abstract class WheelPicker extends ConfirmPopup<View> {

    /**
     * The Text size.
     */
    protected int textSize = WheelView.TEXT_SIZE;
    /**
     * The Text color normal.
     */
    protected int textColorNormal = WheelView.TEXT_COLOR_NORMAL;
    /**
     * The Text color focus.
     */
    protected int textColorFocus = WheelView.TEXT_COLOR_FOCUS;
    /**
     * The Line color.
     */
    protected int lineColor = WheelView.LINE_COLOR;
    /**
     * The Line visible.
     */
    protected boolean lineVisible = true;
    /**
     * The Offset.
     */
    protected int offset = WheelView.OFF_SET;

    /**
     * Instantiates a new Wheel picker.
     *
     * @param activity the activity
     */
    public WheelPicker(Activity activity) {
        super(activity);
    }

    /**
     * Sets text size.
     *
     * @param textSize the text size
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * Sets text color.
     *
     * @param textColorFocus  the text color focus
     * @param textColorNormal the text color normal
     */
    public void setTextColor(int textColorFocus, int textColorNormal) {
        this.textColorFocus = textColorFocus;
        this.textColorNormal = textColorNormal;
    }

    /**
     * Sets text color.
     *
     * @param textColor the text color
     */
    public void setTextColor(int textColor) {
        this.textColorFocus = textColor;
    }

    /**
     * Sets line visible.
     *
     * @param lineVisible the line visible
     */
    public void setLineVisible(boolean lineVisible) {
        this.lineVisible = lineVisible;
    }

    /**
     * Sets line color.
     *
     * @param lineColor the line color
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Sets offset.
     *
     * @param offset the offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

}
