package com.leanderli.android.demo.drawerlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.drawerlayout.VerticalDrawerLayout;

public class VerticalDrawerLayoutActivity extends AppCompatActivity implements VerticalDrawerLayout.DrawerListener, View.OnClickListener {

    VerticalDrawerLayout mBottomDrawerLayout;
    ImageView mBottomArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_drawer_layout);
        mBottomDrawerLayout = findViewById(R.id.bottom_drawer_layout);
        mBottomArrow = findViewById(R.id.arrow);
        mBottomArrow.setOnClickListener(this);
        mBottomDrawerLayout.setDrawerListener(this);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mBottomArrow.setRotation(slideOffset * 180);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow:
                if (mBottomDrawerLayout.isDrawerOpen()) {
                    mBottomDrawerLayout.closeDrawer();
                } else {
                    mBottomDrawerLayout.openDrawerView();
                }
                break;
            default:
                break;
        }
    }
}
