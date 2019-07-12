package com.leanderli.android.demo.timertasktest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018-06-29.
 */

public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long delayTime = 5000;
        Intent theIntent = new Intent(this, AlarmReceiver.class);
        theIntent.setAction("ACTION_TIMERTASK_TEST");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, theIntent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), delayTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
