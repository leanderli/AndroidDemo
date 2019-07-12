package com.leanderli.android.demo.timertasktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.leanderli.android.demo.R;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AlarmService.class);
        startService(intent);
        Toast.makeText(this, "定时任务服务已经注册，每6秒执行", Toast.LENGTH_SHORT).show();

    }
}
