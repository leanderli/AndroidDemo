package com.leanderli.android.demo.timertasktest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Administrator on 2018-06-29.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("ACTION_TIMERTASK_TEST".equals(intent.getAction())) {
            Toast.makeText(context, "定时任务执行了..,date:{" + new Date() + "}", Toast.LENGTH_SHORT).show();
        }
    }
}
