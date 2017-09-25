package com.lenovo.service.basicpubliclibrary.Exception;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.lenovo.service.basicpubliclibrary.App;
import com.lenovo.service.basicpubliclibrary.request.ui.BaseActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cx on 2016/9/1.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private static CrashHandler crashHandler;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private Context context;
    private static final String PATH = Environment.getExternalStorageDirectory().getPath()+"/shandiancrash/log/";
    private static final String FILE_NAME = "crashinfo";
    private static final String FILE_NAME_SUFFIX = ".txt";//trace
    private Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    private Map<Integer, Runnable> disallowablePermissionRunnables = new HashMap<>();
    public CrashHandler(){
    }
    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    public void init(Context context){
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context.getApplicationContext();
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            saveUncaughtException(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public void saveUncaughtException(final Throwable e){
        e.printStackTrace();
        if (Build.VERSION.SDK_INT >= 23) {
            getPermission(10132, Manifest.permission.READ_PHONE_STATE, new Runnable() {
                @Override
                public void run() {
                    PrintWriter pw = null;
                    try{

                        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            Log.e("crashinfo","sdcard不存在或者不可读写！");
                            return;
                        }
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = format.format(new Date());
                        File fileDir = new File(PATH);
                        if(!fileDir.exists()){
                            fileDir.mkdirs();
                        }
                        File file = new File(PATH+FILE_NAME+time+FILE_NAME_SUFFIX);

                        pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                        pw.println(time);
                        writePhoneInfo(pw);
                        pw.println();
                        e.printStackTrace(pw);

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }finally {
                        pw.close();
                    }
                }
            }, new Runnable() {
                @Override
                public void run() {
//                    ToastUtil.showToast("请您授权！");
                }
            });
        } else {
            PrintWriter pw = null;
            try{

                if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    Log.e("crashinfo","sdcard不存在或者不可读写！");
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(new Date());
                File fileDir = new File(PATH);
                if(!fileDir.exists()){
                    fileDir.mkdirs();
                }
                File file = new File(PATH+FILE_NAME+time+FILE_NAME_SUFFIX);

                pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                pw.println(time);
                writePhoneInfo(pw);
                pw.println();
                e.printStackTrace(pw);

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                pw.close();
            }
        }


    }

    private void writePhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException{
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App version:");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);

        pw.print("OS version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        pw.print("Vendor:");
        pw.println(Build.MANUFACTURER);

        pw.print("Model:");
        pw.println(Build.MODEL);

        pw.print("CPU ABI:");
        pw.println(Build.CPU_ABI);

    }
    protected void getPermission(int id, String permission, Runnable allowableRunnable, Runnable disallowableRunnable) {

        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        allowablePermissionRunnables.put(id, allowableRunnable);
        if (disallowableRunnable != null) {
            disallowablePermissionRunnables.put(id, disallowableRunnable);
        }
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(App.getContext(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                ActivityCompat.requestPermissions((Activity) App.getContext(), new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }


    }


//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Runnable allowRun = allowablePermissionRunnables.get(requestCode);
//            allowRun.run();
//        } else {
//            Runnable disallowRun = disallowablePermissionRunnables.get(requestCode);
//            disallowRun.run();
//        }
//
//    }
}
