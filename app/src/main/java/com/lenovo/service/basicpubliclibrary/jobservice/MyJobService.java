package com.lenovo.service.basicpubliclibrary.jobservice;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * 版权所有 ,lenovo保留所有版权。<br>
 * 项目描述：JobService
 * 作者：Jiao
 * 日期：2017/9/7
 **/
@TargetApi(21)
public class MyJobService extends JobService {
    private Handler mJobHandler = new Handler( new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg ) {
            Toast.makeText( getApplicationContext(),
                    "JobService task running", Toast.LENGTH_SHORT )
                    .show();
            //让系统知道你完成了那项任务，它可以开始排队接下来的操作。如果你不这样做，你的工作将只运行一次，你的应用程序将不被允许执行额外的工作。
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }
    } );
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //onStartJob在JobService被调度到的时候会执行，我们只需要继承JobService然后重写onStartJob方法，并在里面执行我们的后台任务就可以了。
        //JobService默认在主线程中处理传入的每个操作，这意味着，你必须开一个新线程来执行你的耗时操作，如果不这样操作，将会阻塞来自JobManager的任何操作，特别是onStopJob操作
        mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, jobParameters ) );
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        mJobHandler.removeMessages( 1 );
//        系统用来取消挂起的任务
        //如果onStartJob(JobParameters params)返回 false，当取消请求被接收时，该系统假定没有目前运行的工作
        //不调用onStopJob
        return false;
    }
}
