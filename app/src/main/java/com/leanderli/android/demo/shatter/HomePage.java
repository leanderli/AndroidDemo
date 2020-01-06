package com.leanderli.android.demo.shatter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

import kale.ui.shatter.Shatter;

public class HomePage extends Shatter {

    private TextView mText;
    private String mData;

    @Override
    protected int getLayoutResId() {
        return R.layout.home_shatter;
    }

    @Override
    protected void bindViews(View rootView) {
        mText = findViewById(R.id.text);
    }

    @Override
    protected void setViews() {
        mText.setOnClickListener((v) -> {
            Toast.makeText(getActivity(), "Click home page text:" + mData, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mData = "NB";
    }
}
