package com.leanderli.android.demo.shatter;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leanderli.android.demo.R;

import kale.ui.shatter.Shatter;

public class ContentPage extends Shatter {

    private TextView mText;

    @Override
    protected int getLayoutResId() {
        return R.layout.content_shatter;
    }

    @Override
    protected void bindViews(View rootView) {
        mText = findViewById(R.id.text);
    }

    @Override
    protected void setViews() {
        mText.setOnClickListener((v) -> {
            Toast.makeText(getActivity(), "Click content page text", Toast.LENGTH_SHORT).show();
        });
    }
}
