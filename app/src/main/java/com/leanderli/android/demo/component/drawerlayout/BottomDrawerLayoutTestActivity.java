package com.leanderli.android.demo.component.drawerlayout;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
