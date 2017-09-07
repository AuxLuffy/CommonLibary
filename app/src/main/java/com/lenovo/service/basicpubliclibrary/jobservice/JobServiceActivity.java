package com.lenovo.service.basicpubliclibrary.jobservice;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.concurrent.TimeUnit;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：启动JobService的Activity  jobService本身是个service并不一定需要界面
 * 作者：焦禹铭
 * 日期：2017/9/7
 **/
@TargetApi(21)
public class JobServiceActivity extends Activity {
    JobScheduler jobScheduler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobservice);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(this, "当前版本过低", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.start_service:
                doService();
                TextView t=(TextView)view;
                t.setText("插入充电器触发任务");
                break;
            case R.id.stop_service:
                jobScheduler.cancel(1);
//                jobScheduler.cancelAll();
                break;
        }
    }
    private void doService() {
        //使用jobService需要用到用到jobScheduler（任务调度器）
         jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, MyJobService.class));  //指定哪个JobService执行操作
        //触发任务条件
//        一共有五个条件，
//        最短延迟时间 setMinimumLatency
//        最长延迟时间 setOverrideDeadline
//        执行时网络状态 setRequiredNetworkType 共有（NETWORK_TYPE_NONE默认）、（NETWORK_TYPE_ANY任何网络状态）、（NETWORK_TYPE_UNMETERED不需要计量的网络状态）、（NETWORK_TYPE_NOT_ROAMING非漫游状态）
//        重试方案 setBackoffCriteria 第一个参数为时间，第二个是重试方案有JobInfo.BACKOFF_POLICY_LINEAR限行方案、BACKOFF_POLICY_EXPONENTIAL指数方案。
//        是否需要在充电状态执行 setRequiresCharging 默认是false
//        除此之外，还有
//        重复每周期间隔时间 setPeriodic
//        设备重启后是否继续后台操作 setPersisted，前提是具有权限
//        setTriggerContentMaxDelay设置从第一个*时间检测到内容更改被允许的最大总延迟（以毫秒为单位），直到作业被调度为止
        builder.setPeriodic( 3000 );//重复任务间隔时间 不能与下面两个方法同时使用
//        builder.setMinimumLatency(TimeUnit.MILLISECONDS.toMillis(10)); //执行的最小延迟时间
//        builder.setOverrideDeadline(TimeUnit.MILLISECONDS.toMillis(15));  //执行的最长延时时间
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);  //任何网络状态
        builder.setBackoffCriteria(TimeUnit.MINUTES.toMillis(10), JobInfo.BACKOFF_POLICY_LINEAR);  //线性重试方案
        builder.setRequiresCharging(true); // 未充电状态 是否在充电状态执行任务
        jobScheduler.schedule(builder.build());//jobInfo对象
    }
}
