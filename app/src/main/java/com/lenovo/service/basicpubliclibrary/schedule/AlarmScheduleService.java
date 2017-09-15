package com.lenovo.service.basicpubliclibrary.schedule;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by iwm on 16/9/7.
 */
public class AlarmScheduleService extends Service {

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    TimerScheduleClient client = TimerScheduleClient.getInstanceInner();
    if (client != null) {
      client.doSchedule();
    }
    return super.onStartCommand(intent, flags, startId);
  }
}
