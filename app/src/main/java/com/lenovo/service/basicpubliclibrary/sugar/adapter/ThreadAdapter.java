package com.lenovo.service.basicpubliclibrary.sugar.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by xuxiaowei on 2017/9/22.
 */

public class ThreadAdapter extends Thread {
    private ArrayList<File> list = new ArrayList<>();
    private File startFile;
    private OnDataListener mOnDataListener;

    public ThreadAdapter(File file){
        this.startFile =file;
    }

    @Override
    public void run() {
        readFile(startFile);
        mOnDataListener.data(list);
    }

    private void readFile(File mfile){
        File[] files = mfile.listFiles();
        for(File file:files){
            if(file.isDirectory())
                readFile(file);
            else if(file.getName().endsWith(".mp3")){
                list.add(file);
            }
            Log.e("ThreadAdapter",file.getPath());
        }
    }


    public interface OnDataListener{
        void data(ArrayList<File> list);
    }

    public void setOnDataListener(OnDataListener dataListener){
        this.mOnDataListener = dataListener;
    }
}
