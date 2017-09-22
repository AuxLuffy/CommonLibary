package com.lenovo.service.basicpubliclibrary.sugar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.android.phone.mrpc.core.ThreadUtil;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.sugar.adapter.Mp3Adapter;
import com.lenovo.service.basicpubliclibrary.sugar.adapter.ThreadAdapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.TimerTask;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStorageState;

public class ScanSDActivity extends AppCompatActivity{

    private ArrayList<File> mlist = new ArrayList<>();
    private Mp3Adapter mp3Adapter;
    private RecyclerView recycler_mp3;
    private TextView tv_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_sd);

        recycler_mp3 = (RecyclerView) findViewById(R.id.recycler_mp3);
        tv_scan = (TextView) findViewById(R.id.tv_scan);
        recycler_mp3.setLayoutManager(new LinearLayoutManager(this));
        mp3Adapter = new Mp3Adapter(mlist, this);
        recycler_mp3.setAdapter(mp3Adapter);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        } else {
            if(getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File startFile = getExternalStorageDirectory();
                ThreadAdapter mThread = new ThreadAdapter(startFile);
                mThread.setOnDataListener(new ThreadAdapter.OnDataListener() {
                    @Override
                    public void data(final ArrayList<File> list) {
                        Log.e("ScanSDActivity",Thread.currentThread()+"");
                        recycler_mp3.post(new TimerTask() {
                            @Override
                            public void run() {
                                if(list.size() < 0){
                                    tv_scan.setText("没有.mp3文件");
                                    return;
                                }
                                tv_scan.setVisibility(View.GONE);
                                Log.e("ScanSDActivity",Thread.currentThread()+"");
                                mlist.addAll(list);
                                mp3Adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                mThread.start();
            } else {
                Toast.makeText(this,"手机不支持扫描",Toast.LENGTH_LONG).show();
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(0 == requestCode){
            if(getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File startFile = getExternalStorageDirectory();
                ThreadAdapter mThread = new ThreadAdapter(startFile);
                mThread.setOnDataListener(new ThreadAdapter.OnDataListener() {
                    @Override
                    public void data(final ArrayList<File> list) {
                        Log.e("ScanSDActivity",Thread.currentThread()+"");
                        recycler_mp3.post(new TimerTask() {
                            @Override
                            public void run() {
                                if(list.size() < 0){
                                    tv_scan.setText("没有.mp3文件");
                                    return;
                                }
                                tv_scan.setVisibility(View.GONE);
                                Log.e("ScanSDActivity",Thread.currentThread()+"");
                                mlist.addAll(list);
                                mp3Adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                mThread.start();
            } else {
                Toast.makeText(this,"手机不支持扫描",Toast.LENGTH_LONG).show();
            }
        }
    }



}
