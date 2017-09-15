package com.lenovo.service.basicpubliclibrary.schedule;
import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by iwm on 16/9/7.
 */

@SuppressLint("NewApi")
public class JobScheduleService extends JobService {

  @Override
  public boolean onStartJob(JobParameters jobParameters) {
    TimerScheduleClient client = TimerScheduleClient.getInstanceInner();
    if (client != null) {
      client.doSchedule();
    }
    return false;
  }

  @Override
  public boolean onStopJob(JobParameters jobParameters) {
    return false;
  }
}


