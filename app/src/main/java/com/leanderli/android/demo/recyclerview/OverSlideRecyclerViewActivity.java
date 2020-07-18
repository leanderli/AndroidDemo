package com.leanderli.android.demo.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.leanderli.android.demo.R;

public class OverSlideRecyclerViewActivity extends AppCompatActivity {

    private OverSlideLayout mOverSlideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_slide_recycler_view);

        mOverSlideLayout = findViewById(R.id.osl_content);
    }
}