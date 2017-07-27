package com.lenovo.service.basicpubliclibrary.headerfloatanimation.control;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;


import com.lenovo.service.basicpubliclibrary.headerfloatanimation.adapter.GridLayoutRecyclerViewAdapter;

import java.lang.ref.WeakReference;

/**
 * 控制播放逻辑的hander类
 */

public class ImageHandler extends Handler {
    /**
     * 请求更新显示的View。
     */
    public static final int MSG_UPDATE_IMAGE = 1;
    /**
     * 请求暂停轮播。
     */
    public static final int MSG_KEEP_SILENT = 2;
    /**
     * 请求恢复轮播。
     */
    public static final int MSG_BREAK_SILENT = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    public static final int MSG_PAGE_CHANGED = 4;
    //轮播间隔时间,毫秒
    public static final long MSG_DELAY = 3000;
    //使用弱引用避免Handler泄露.这里的泛型参数可以不是container，也可以是Fragment等
    private WeakReference<GridLayoutRecyclerViewAdapter> weakReference;
    private int currentItem = 0;

    public ImageHandler(WeakReference<GridLayoutRecyclerViewAdapter> wk) {
        weakReference = wk;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        GridLayoutRecyclerViewAdapter container = weakReference.get();
        if (container == null) {
            //container，无需再处理UI了
            return;
        }
        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
        /*if (container.handler.hasMessages(MSG_UPDATE_IMAGE)){
            container.handler.removeMessages(MSG_UPDATE_IMAGE);
        }*/
        switch (msg.what) {
            //setCurrentItem，并设置三秒后设置下一个
            case MSG_UPDATE_IMAGE:
                if (container.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                    container.handler.removeMessages(MSG_UPDATE_IMAGE);
                }
                currentItem++;
                container.gridBannerViewHolder.viewPager.setCurrentItem(currentItem);
                //准备下次播放
                container.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            //移除message，暂停播放
            case MSG_KEEP_SILENT:
                if (container.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                    container.handler.removeMessages(MSG_UPDATE_IMAGE);
                }
                break;
            //触发MSG_UPDATE_IMAGE(此message会定时时间间隔执行setCurrentItem）
            case MSG_BREAK_SILENT:
                container.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            //暂停播放，并记录当前item
            case MSG_PAGE_CHANGED:
                //记录当前的页号，避免播放的时候页面显示不正确。
                currentItem = msg.arg1;
                break;
            default:
                break;
        }
    }
}
