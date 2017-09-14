package com.lenovo.service.basicpubliclibrary.timepicker;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;

/**
 * 底部弹窗基类
 */
public abstract class BottomPopup<V extends View> {
    /**
     * The constant MATCH_PARENT.
     */
    protected static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    /**
     * The constant WRAP_CONTENT.
     */
    protected static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * The Activity.
     */
    protected Activity activity;
    /**
     * The Screen.
     */
    protected ScreenHelper.Screen screen;
    protected Popup popup;

    /**
     * Gets view.
     *
     * @return the view
     */
    protected abstract V getView();

    /**
     * Instantiates a new Bottom popup.
     *
     * @param activity the activity
     */
    public BottomPopup(Activity activity) {
        this.activity = activity;
        this.screen = ScreenHelper.getScreenPixels(activity);
        popup = new Popup(activity);
    }

    /**
     * 弹出窗显示之前调用
     */
    private void onShowPrepare() {
        setContentViewBefore();
        V view = getView();
        popup.setContentView(view);// 设置弹出窗体的布局
        if (isFixedHeight()) {
            popup.setSize(screen.widthPixels, screen.heightPixels / 2);
        } else {
            popup.setSize(screen.widthPixels, WRAP_CONTENT);
        }
        setContentViewAfter(view);
    }

    /**
     * 是否固定高度为屏幕的一半
     *
     * @return the boolean
     */
    protected boolean isFixedHeight() {
        return false;
    }

    /**
     * Sets content view before.
     */
    protected void setContentViewBefore() {
    }

    /**
     * Sets content view after.
     *
     * @param contentView the content view
     */
    protected void setContentViewAfter(V contentView) {
    }

    /**
     * Sets animation.
     *
     * @param animRes the anim res
     */
    public void setAnimationStyle(@StyleRes int animRes) {
        popup.setAnimationStyle(animRes);
    }

    /**
     * Sets on dismiss listener.
     *
     * @param onDismissListener the on dismiss listener
     */
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        popup.setOnDismissListener(onDismissListener);
    }

    /**
     * Sets size.
     *
     * @param width  the width
     * @param height the height
     */
    public void setSize(int width, int height) {
        popup.setSize(width, height);
    }

    /**
     * Is showing boolean.
     *
     * @return the boolean
     */
    public boolean isShowing() {
        return popup.isShowing();
    }

    /**
     * Show.
     */
    public void show() {
        onShowPrepare();
        popup.show();
    }

    /**
     * Dismiss.
     */
    public void dismiss() {
        popup.dismiss();
    }

}
