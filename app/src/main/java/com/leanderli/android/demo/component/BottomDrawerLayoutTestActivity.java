package com.leanderli.android.demo.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.leanderli.android.demo.R;

/**
 * @author leanderli
 * @date 2019/7/12
 */
public class BottomDrawerLayoutTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_drawer_layout);
        BottomDrawerLayout drawerLayout = new BottomDrawerLayout(this);
        drawerLayout.setOnDrawerStatusChanged(new BottomDrawerLayout.OnDrawerStatusChanged() {
            @Override
            public void onChanged(int parentHeight, int drawerTop) {

            }
        });
    }
}