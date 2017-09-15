package com.lenovo.service.basicpubliclibrary.schedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by iwm on 16/9/7.
 *
 * This is a reliable timer, but is single instance.
 * A process can hold only one reliable timer.
 */
public class TimerScheduleClient {

  private long mPeriod;
  private long mAppendPeriod;
  private long mIgnoreInterval;
  private long mLastExecTime;

  private Context mContext;
  private TimerScheduleCallback mCallback;

  private Handler mHandler = new Handler(Looper.getMainLooper());

  private static TimerScheduleClient mInstance;

  public static final int PERIOD  = 5 * 60 * 1000;





  private TimerScheduleClient(Context context, TimerScheduleCallback callback) {
    this.mContext = context;
    this.mCallback = callback;
  }

  public static TimerScheduleClient getInstance(Context context, TimerScheduleCallback callback) {
/*        if (mInstance == null) {
            mInstance = new TimerScheduleClient(context, callback);
        }
        return mInstance;*/
    if (mInstance == null)
    {
      synchronized(TimerScheduleClient.class) {      //1
        TimerScheduleClient inst = mInstance;         //2
        if (inst == null)
        {
          synchronized(TimerScheduleClient.class) {  //3
            inst = new TimerScheduleClient(context,callback);        //4
          }
          mInstance = inst;                 //5
        }
      }
    }
    return mInstance;
  }

  // don't invoke this method
  protected static TimerScheduleClient getInstanceInner() {
    return mInstance;
  }

  // don't invoke this method
  protected void doSchedule() {
    long freeTime = System.currentTimeMillis() - mLastExecTime;
    if (freeTime > mIgnoreInterval) {
      mLastExecTime = System.currentTimeMillis();
      if (mCallback != null) {
        mCallback.doSchedule();
      }
    }
  }

  /**
   * suggest appendPeriod = period
   * suggest ignoreInterval = period / 2
   * or
   * suggest appendPeriod = period * 3
   * suggest ignoreInterval = period / 3
   */
  public void start(long delay, long period, long appendPeriod, long ignoreInterval) {
    mPeriod = period;
    mAppendPeriod = appendPeriod;
    mIgnoreInterval = ignoreInterval;
    startHandlerTask(delay);
    startAlarmTask(delay + appendPeriod);
    startJobTask();
  }

  public void stop() {
    stopHandlerTask();
    stopAlarmTask();
    stopJobTask();
  }

  private void startHandlerTask(long delay) {
    if (mPeriod <= 0) {
      return;
    }
    stopHandlerTask();
    mHandler.postDelayed(mRunnable, delay);
  }

  private void stopHandlerTask() {
    mHandler.removeCallbacks(mRunnable);
  }

  private void startAlarmTask(long delay) {
    if (mAppendPeriod <= 0) {
      return;
    }
    stopAlarmTask();
    Intent intent = new Intent(mContext, AlarmScheduleService.class);
    PendingIntent pendingIntent = PendingIntent.getService(mContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Service.ALARM_SERVICE);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay,
            mAppendPeriod, pendingIntent);
  }

  private void stopAlarmTask() {
    Intent intent = new Intent(mContext, AlarmScheduleService.class);
    PendingIntent pendingIntent = PendingIntent.getService(mContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Service.ALARM_SERVICE);
    alarmManager.cancel(pendingIntent);
  }

  private void startJobTask() {
    if (mAppendPeriod <= 0) {
      return;
    }
    stopJobTask();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      JobScheduler jobScheduler = (JobScheduler) mContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
      JobInfo.Builder builder = new JobInfo.Builder(1,
              new ComponentName(mContext.getPackageName(), JobScheduleService.class.getName()));
      builder.setPeriodic(mAppendPeriod);
      builder.setRequiresCharging(true);
      builder.setPersisted(false);  //设置设备重启后，是否重新执行任务
      builder.setRequiresDeviceIdle(true);
      jobScheduler.schedule(builder.build());
    }
  }

  private void stopJobTask() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      JobScheduler jobScheduler = (JobScheduler) mContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
      jobScheduler.cancelAll();
    }
  }

  private Runnable mRunnable = new Runnable() {
    @Override
    public void run() {
      doSchedule();
      mHandler.removeCallbacks(mRunnable);
      mHandler.postDelayed(mRunnable, mPeriod);
    }
  };

  public interface TimerScheduleCallback {
    public void doSchedule();
  }
}
