package com.leanderli.android.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ScreenStateChangeListenerTestActivity extends AppCompatActivity {

    private ScreenBroadcastReceiver mScreenReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_state_change_listener_test);
        if (mScreenReceiver == null) {
            mScreenReceiver = new ScreenBroadcastReceiver();
            mScreenReceiver.registerScreenBroadcastReceiver(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScreenReceiver.unRegisterScreenBroadcastReceiver(this);
    }
}