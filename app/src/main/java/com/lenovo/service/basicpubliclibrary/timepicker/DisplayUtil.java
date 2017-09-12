package com.lenovo.service.basicpubliclibrary.timepicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.App;


/**
 * 屏幕大小、距离单位转换工具类
 */
public class DisplayUtil {
    private static final DisplayMetrics metrics = App.getInstance().getResources().getDisplayMetrics();

    public static int getScreenHeight() {
        return metrics.heightPixels;
    }

    public static int getScreenWidth() {
        return metrics.widthPixels;
    }

    /**
     * px to dip
     *
     * @param pxValue
     *
     * @return
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / metrics.density + 0.5f);
    }

    /**
     * dip to px
     *
     * @param dipValue
     *
     * @return
     */
    public static int dip2px(float dipValue) {
        return (int) (dipValue * metrics.density + 0.5f);
    }

    /**
     * px to sp
     *
     * @param pxValue
     *
     * @return
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / metrics.scaledDensity + 0.5f);
    }

    /**
     * sp to px
     *
     * @param spValue
     *
     * @return
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * metrics.scaledDensity + 0.5f);
    }

    /**
     * @category 通过字符大小获取单个字符的Px
     * @param context
     * @param fontSize
     * @return
     */
    public static float getWidthFontSize(Context context, int fontSize) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(fontSize);
        textView.setText("单");
        return getcharacterWidth(textView);
    }

    /**
     * @category 获取文本字符串的像素大小
     * @param text
     * @return
     */
    public static float getcharacterWidth(TextView text) {
        if (null == text || "".equals(text)) {
            return 0f;
        }
        Paint paint = new Paint();
        paint.setTextSize(text.getTextSize() * text.getTextScaleX());
        float text_width = paint.measureText(text.getText().toString());
        return text_width;
    }

    public static void getScreenRect(Context ctx_, Rect outrect_ ) {
        Display screenSize = ((WindowManager) ctx_.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        outrect_.set(0, 0, screenSize.getWidth(), screenSize.getHeight());
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        statusHeight = rect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object object = localClass.newInstance();
                int height = Integer.parseInt(localClass.getField("status_bar_height").get(object).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

}