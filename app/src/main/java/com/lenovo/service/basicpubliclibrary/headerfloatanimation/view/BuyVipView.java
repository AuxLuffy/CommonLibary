package com.lenovo.service.basicpubliclibrary.headerfloatanimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lenovo.service.basicpubliclibrary.R;


/**
 * TODO: document your custom view class.
 */
public class BuyVipView extends RelativeLayout {

    public BuyVipView(Context context) {
        super(context);
        init(context);
    }

    public BuyVipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BuyVipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.vip_buy_widget_view, this);

    }

}
