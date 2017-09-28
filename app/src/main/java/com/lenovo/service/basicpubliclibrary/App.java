package com.lenovo.service.basicpubliclibrary;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.baidu.crabsdk.CrabSDK;
import com.blankj.ALog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lenovo.service.basicpubliclibrary.bgabanner.Engine;
import com.lenovo.service.basicpubliclibrary.config.Config;
import com.lenovo.service.basicpubliclibrary.loaddata.LoadDataLayout;
import com.orm.SugarApp;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.smtt.sdk.QbSdk;

import org.litepal.LitePal;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Description : Application  <br/>
 * author : WangGanxin <br/>
 * date : 2017/3/31 <br/>
 * email : mail@wangganxin.me <br/>
 */
public class App extends SugarApp {

    private static Context mContext;
    private static App sInstance;
    private Engine mEngine;
    private RefWatcher refWatcher;
    private static final String BAIDU_CRAB = "a3b3ddfe123c3d76";

    public static int widthPixels = 720;// 屏幕宽度
    public static int heightPixels = 1280;// 屏幕高度

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        LitePal.initialize(this);
        sInstance = this;
        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/banner/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);

        Fresco.initialize(this);
        CrabSDK.init(this, BAIDU_CRAB);
        LoadDataLayout.getBuilder()
                .setLoadingText(getString(R.string.custom_loading_text))
                .setLoadingTextSize(16)
                .setLoadingTextColor(R.color.colorPrimary)
                //.setLoadingViewLayoutId(R.layout.custom_loading_view) //如果设置了自定义loading视图,上面三个方法失效
                .setEmptyImgId(R.drawable.ic_empty)
                .setErrorImgId(R.drawable.ic_error)
                .setNoNetWorkImgId(R.drawable.ic_no_network)
                .setEmptyImageVisible(true)
                .setErrorImageVisible(true)
                .setNoNetWorkImageVisible(true)
                .setEmptyText(getString(R.string.custom_empty_text))
                .setErrorText(getString(R.string.custom_error_text))
                .setNoNetWorkText(getString(R.string.custom_nonetwork_text))
                .setAllTipTextSize(16)
                .setAllTipTextColor(R.color.text_color_child)
                .setAllPageBackgroundColor(R.color.pageBackground)
                .setReloadBtnText(getString(R.string.custom_reloadBtn_text))
                .setReloadBtnTextSize(16)
                .setReloadBtnTextColor(R.color.colorPrimary)
                .setReloadBtnBackgroundResource(R.drawable.selector_btn_normal)
                .setReloadBtnVisible(true)
                .setReloadClickArea(LoadDataLayout.FULL);

/*        SwipeLoadDataLayout.getBuilder()
                .setEmptyImgId(R.drawable.ic_empty)
                .setErrorImgId(R.drawable.ic_error)
                .setNoNetWorkImgId(R.drawable.ic_no_network)
                .setEmptyImageVisible(true)
                .setErrorImageVisible(true)
                .setNoNetWorkImageVisible(true)
                .setEmptyText(getString(R.string.custom_empty_text))
                .setErrorText(getString(R.string.custom_error_text))
                .setNoNetWorkText(getString(R.string.custom_nonetwork_text))
                .setAllTipTextSize(16)
                .setAllTipTextColor(R.color.text_color_child)
                .setAllPageBackgroundColor(R.color.pageBackground)
                .setReloadBtnText(getString(R.string.custom_reloadBtn_text))
                .setReloadBtnTextSize(16)
                .setReloadBtnTextColor(R.color.colorPrimary)
                .setReloadBtnBackgroundResource(R.drawable.selector_btn_normal)
                .setReloadBtnVisible(true)
                .setReloadClickArea(SwipeLoadDataLayout.FULL);*/
        //设置输出日志
        Config.setDebug(true);

        FlowManager.init(this);

        initALog();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher=  LeakCanary.install(this);
        }
        QbSdk.initX5Environment(this,null);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    public static Context getContext() {
        return mContext;
    }

    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        try {
            SophixManager.getInstance().setContext(this)
                    .setAppVersion((getPackageManager().getPackageInfo(getPackageName(),0)).versionName)
                    .setAesKey(null)
                    .setEnableDebug(true)
                    .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                        @Override
                        public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                            // 补丁加载回调通知
                            if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                // 表明补丁加载成功
                                Toast.makeText(App.this , "补丁加载成功成功", Toast.LENGTH_SHORT).show();
                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                Toast.makeText(App.this , "补丁下载成功请重启应用", Toast.LENGTH_SHORT).show();
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            } else {
                                // 其它错误信息, 查看PatchStatus类说明
                                Toast.makeText(App.this , "热修复"+info, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MultiDex.install(this);
    }



    public void initALog() {
        ALog.Config config = ALog.init(this)
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"alog"，即写入文件为"alog-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(ALog.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(ALog.V)// log文件过滤器，和logcat过滤器同理，默认Verbose
                .setStackDeep(1);// log栈深度，默认为1
        ALog.d(config.toString());
    }
    public static RefWatcher getRefWatcher(Context context){
        App app=(App)context.getApplicationContext();
        return app.refWatcher;
    }
}
