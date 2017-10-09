package com.lenovo.service.basicpubliclibrary.screenshot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;

import java.io.File;

public class ScreenShotActivity extends AppCompatActivity implements View.OnClickListener{
private Button mBtnShot1;
    private Button mBtnShot2;
    private ScrollView mScroll;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        mScroll = (ScrollView) findViewById(R.id.scrollRoot);
        mBtnShot1 = (Button) findViewById(R.id.btnScreenShot1);
        mBtnShot2 = (Button) findViewById(R.id.btnScreenShot2);
        mBtnShot1.setOnClickListener(this);
        mBtnShot2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnScreenShot1:
                Bitmap bitmap = ScreenUtils.takeScreenShot(this);
                if(bitmap != null){
                    ScreenUtils.savePic(bitmap,getFilePath());
                    ToastUtil.showMessage(ScreenShotActivity.this,"截图成功");
                    file = new File(getFilePath());
                    if(file.exists()){
                        sendBroadCast(file);
                    }
                }else {
                    ToastUtil.showMessage(ScreenShotActivity.this,"截图失败");
                }
                break;
            case R.id.btnScreenShot2:
                ScreenUtils.getScrollViewBitmap(mScroll,getFilePath());
                ToastUtil.showMessage(ScreenShotActivity.this,"截图成功");
                file = new File(getFilePath());
                if(file.exists()){
                    sendBroadCast(file);
                }
                break;
        }
    }
    private String getFilePath(){
        String path = null;
        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File myShot = new File(root,"shot");
        if(!myShot.exists()){
            myShot.mkdirs();
        }
        path = myShot.getPath()+"/"+System.currentTimeMillis()+".jpg";
         return path;
    }
    private void sendBroadCast(File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        sendBroadcast(intent);
    }
}
