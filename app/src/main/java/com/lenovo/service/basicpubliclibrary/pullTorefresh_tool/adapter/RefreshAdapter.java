package com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.pullTorefresh_tool.bean.RefreshData;

import java.util.List;


/**
 * Created by 李巷阳 on 2017/6/11.
 */

public class RefreshAdapter extends Lv_SimpleAdapter<RefreshData> {


    /**
     * 构造函数
     *
     * @param context 上下文
     * @param datas   数据源
     */
    public RefreshAdapter(Context context, List<RefreshData> datas) {
        super(context, R.layout.item_customer, datas);
    }

    @Override
    protected void convert(Lv_BaseViewHolder viewHoder, RefreshData item) {
        ImageView miv_portrait = viewHoder.getImageView(R.id.iv_portrait);
        TextView mtv_nickname = viewHoder.getTextView(R.id.tv_nickname);
        ImageView miv_myadd = viewHoder.getImageView(R.id.iv_myadd);
        mtv_nickname.setText(item.getNick_name());
    }


}
