package com.leanderli.android.demo.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.customizeviews.RoundImageView;
import com.ruffian.library.RTextView;

public class CircleImageViewTestActivity extends AppCompatActivity {

    private RTextView rTextView;
    private RoundImageView roundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view_test);
//        rTextView = findViewById(R.id.rtextview);
//        rTextView.setIconHeight(64)
//                .setIconWidth(64);

    }
}
