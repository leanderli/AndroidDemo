package com.leanderli.android.demo.blur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.leanderli.android.demo.R;

public class BlurTestActivity extends AppCompatActivity {

    private ImageView image;
    private RelativeLayout blurLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_test);
        image = findViewById(R.id.image);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);


    }
}
