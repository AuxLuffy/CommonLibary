package com.lenovo.service.basicpubliclibrary.rxjava;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lenovo.service.basicpubliclibrary.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.*;

public class RxJavaActivity extends AppCompatActivity {
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSayHello, R.id.btnSayHelloOtherThread, R.id.ivAvatar})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btnSayHello:
                sayHello();
                break;
            case R.id.btnSayHelloOtherThread:
                sayHelloOnOtherThread();
                break;
            case R.id.ivAvatar:
                showAvatar();
                break;
        }
    }

    private void showAvatar() {
        Observable.just("help")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s+".jpg";
                    }
                })
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {

                        try {
                            InputStream open = getAssets().open(s);
                            Thread.sleep(2000);
                            Bitmap bitmap = BitmapFactory.decodeStream(open);
                            return bitmap;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Toast.makeText(RxJavaActivity.this, "模拟图片加载耗时", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Toast.makeText(RxJavaActivity.this, "图片加载完成", Toast.LENGTH_LONG).show();
                        ivAvatar.setImageBitmap(bitmap);
                    }
                });
    }

    private void sayHello() {
        Observable.just("hello world current thread name:" + Thread.currentThread().getName())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void sayHelloOnOtherThread() {
        Observable.just("")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return Thread.currentThread().getName();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJavaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(String s) {
                        String info = "hello world current thread name:" + s;
                        Toast.makeText(RxJavaActivity.this, info, Toast.LENGTH_LONG).show();
                    }
                });
    }


}
