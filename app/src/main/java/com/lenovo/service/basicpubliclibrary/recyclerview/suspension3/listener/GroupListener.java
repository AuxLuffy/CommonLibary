package com.lenovo.service.basicpubliclibrary.recyclerview.suspension3.listener;

import android.view.View;

/**
 * @description: 城市分组监听
 * @author:袁东华
 * @time:2017/9/23 下午4:33
 */
public interface GroupListener {
    /**
     * 获取分组展示的昵称
     *
     * @param position
     * @return
     */
    String getGroupName(int position);

    /**
     * 获取分组的View
     *
     * @return
     */
    View getGroupView(int position);
}
