package com.lenovo.service.basicpubliclibrary.ProgressBaranimation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.utils.Tool;
import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.widget.RxProgressBar;
import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.widget.RxRoundProgress;
import com.lenovo.service.basicpubliclibrary.ProgressBaranimation.widget.RxRoundProgressBar;
import com.lenovo.service.basicpubliclibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 进度条的艺术
 * @Author 李巷阳
 * Created at 2017/8/1 17:54
 */
public class ProgressActivity extends Activity{

    @BindView(R.id.flikerbar)
    RxProgressBar mFlikerbar;// 进度条一
    @BindView(R.id.round_flikerbar)
    RxProgressBar mRoundFlikerbar;// 进度条二
    @BindView(R.id.rx_round_pd1)
    RxRoundProgressBar mRxRoundPd1;// 进度条三
    @BindView(R.id.pb_line_of_credit)
    ProgressBar mPbLineOfCredit;

    
    @BindView(R.id.bt_download)
    Button mBt_download;
    @BindView(R.id.roundProgressBar1)
    RxRoundProgress mRxRoundProgress1;

    Thread downLoadThread;
    Thread downLoadThread1;
    Thread downLoadThread2;
    Thread downLoadRxRoundPdThread;


    private int mRxRoundPdMax = 100;
    private int mRxRoundProgress;
    private int progress1;
    double money = 1000;
    private int money1 = 10000;
    private double progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.bind(this);
        downLoad();
        initRxRoundPd();
        initLineProgress();
        initRoundProgress();
    }


    // 进度条一和进度条二
    private void downLoad() {
        downLoadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!downLoadThread.isInterrupted()) {
                        float progress = mFlikerbar.getProgress();
                        progress += 2;
                        Thread.sleep(200);
                        Message message = handler.obtainMessage();
                        message.arg1 = (int) progress;
                        handler.sendMessage(message);
                        if (progress == 100) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        downLoadThread.start();
    }



    // 进度条三
    private void initRxRoundPd() {
        mRxRoundProgress = 0;// 进度初始化
        mRxRoundPd1.setMax(mRxRoundPdMax);
        // 开启子线程,模拟下载进度。
        downLoadRxRoundPdThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 判断是否正在开启线程下载。
                    while (!downLoadRxRoundPdThread.isInterrupted()) {
                        while (mRxRoundProgress < mRxRoundPd1.getMax()) {
                            mRxRoundProgress += mRxRoundPd1.getMax() * 0.01;
                            if (mRxRoundProgress < mRxRoundPd1.getMax()) {
                                // 每次进度发现变化,通过handler更新进度条。
                                Message message = new Message();
                                message.what = 101;
                                mRxRoundPdHandler.sendMessage(message);
                            }
                            Thread.sleep(8);
                        }
                        // 如果进度是满的,就反过来,进度回退。
                        while (mRxRoundProgress > 0) {
                            mRxRoundProgress -= mRxRoundPd1.getMax() * 0.01;
                            if (mRxRoundProgress > 0) {
                                Message message = new Message();
                                message.what = 101;
                                mRxRoundPdHandler.sendMessage(message);
                            }
                            Thread.sleep(8);
                        }
                        // 和第一步是一样的。
                        while (mRxRoundProgress < mRxRoundPdMax) {
                            mRxRoundProgress += mRxRoundPdMax * 0.01;
                            Message message = new Message();
                            message.what = 101;
                            mRxRoundPdHandler.sendMessage(message);
                            Thread.sleep(10);
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadRxRoundPdThread.start();// 开启子线程。
    }
    // 进度条四
    private void initLineProgress() {

        // TODO Auto-generated method stub
        progress1 = 0;// 进度初始化
        // 设置当前进度
        mPbLineOfCredit.setProgress(progress1);
        // 最大进度
        mPbLineOfCredit.setMax(getMax(money1));

        downLoadThread1 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    while (!downLoadThread1.isInterrupted()) {
                        while (progress1 < mPbLineOfCredit.getMax()) {
                            progress1 += mPbLineOfCredit.getMax() * 0.01;
                            if (progress1 < mPbLineOfCredit.getMax()) {
                                mPbLineOfCredit.setProgress(progress1);
                                //tv_current.setText(progress1+"");
                            }
                            Thread.sleep(8);
                        }
                        while (progress1 > 0) {
                            progress1 -= mPbLineOfCredit.getMax() * 0.01;
                            if (progress1 > 0) {
                                mPbLineOfCredit.setProgress(progress1);
                                //tv_current.setText(progress1+"");
                            }
                            Thread.sleep(8);
                        }

                        while (progress1 < money1) {
                            progress1 += money1 * 0.01;
                            mPbLineOfCredit.setProgress(progress1);
                            //tv_current.setText(progress1+"");
                            Thread.sleep(10);
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadThread1.start();
    }
    // 进度条五
    private void initRoundProgress() {
        // TODO Auto-generated method stub
        progress = 0;// 进度初始化

        mRxRoundProgress1.setProgress(progress);
        mRxRoundProgress1.setMax(getMax(money));

        downLoadThread2 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    while (!downLoadThread2.isInterrupted()) {
                        while (progress < mRxRoundProgress1.getMax()) {
                            progress += mRxRoundProgress1.getMax() * 0.01;
                            if (progress < mRxRoundProgress1.getMax()) {
                                mRxRoundProgress1.setProgress(progress);
                            }
                            Thread.sleep(8);
                        }
                        while (progress > 0) {
                            progress -= mRxRoundProgress1.getMax() * 0.01;
                            if (progress > 0) {
                                mRxRoundProgress1.setProgress(progress);
                            }
                            Thread.sleep(8);
                        }

                        if (money != 0) {
                            while (progress < money) {
                                progress += money * 0.01;
                                mRxRoundProgress1.setProgress(progress);
                                Thread.sleep(10);
                            }
                        }

                        mRxRoundProgress1.setProgress(money);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadThread2.start();
    }


    private int getMax(double currentProgress) {
        if (currentProgress < 100 && currentProgress > 0) {
            return 100;
        } else if (currentProgress >= 100 && currentProgress < 1000) {
            return 1000;
        } else if (currentProgress >= 1000 && currentProgress < 5000) {
            return 5000;
        } else if (currentProgress >= 5000 && currentProgress < 20000) {
            return 20000;
        } else if (currentProgress >= 20000 && currentProgress < 100000) {
            return 100000;
        } else if (currentProgress >= 100000) {
            return Tool.stringToInt(currentProgress * 1.1 + "");
        } else {
            return Tool.stringToInt(currentProgress + "");
        }
    }
    @OnClick({R.id.flikerbar, R.id.round_flikerbar,R.id.bt_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flikerbar:
                initFlikerProgressBar();
                break;
            case R.id.round_flikerbar:
                initFlikerProgressBar();
                break;
            // 重新下载
            case R.id.bt_download:
                downLoadThread.interrupt();
                // 重新加载
                mFlikerbar.reset();
                mRoundFlikerbar.reset();
                downLoad();
                break;
        }
    }

    private void initFlikerProgressBar() {
        if (!mFlikerbar.isFinish()) {
            mFlikerbar.toggle();
            mRoundFlikerbar.toggle();

            if (mFlikerbar.isStop()) {
                downLoadThread.interrupt();
            } else {
                downLoad();
            }

        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mFlikerbar.setProgress(msg.arg1);
            mRoundFlikerbar.setProgress(msg.arg1);
            if (msg.arg1 == 100) {
                mFlikerbar.finishLoad();
                mRoundFlikerbar.finishLoad();
            }
        }
    };
    Handler mRxRoundPdHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRxRoundPd1.setProgress(mRxRoundProgress);
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        downLoadThread.interrupt();
        downLoadThread1.interrupt();
        downLoadThread2.interrupt();
        downLoadRxRoundPdThread.interrupt();
    }
}
