package com.leanderli.android.demo.textview;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.common.util.DensityUtils;

public class DrawableTextViewTestActivity extends AppCompatActivity {

    private LlDrawableTextView drawableTextView;
    private LlShadowTextView shadowTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_text_view_test);
        drawableTextView = findViewById(R.id.drawableTextView);
        drawableTextView.setIcon(getDrawable(R.drawable.musk_rec));
        drawableTextView.setText("达特测试应用牛");
        drawableTextView.setIconSize(DensityUtils.dip2px(this, 64));
        drawableTextView.setTextSize(DensityUtils.dip2px(this, 12));
        drawableTextView.setTextTopMargin(DensityUtils.dip2px(this, 0));
        drawableTextView.setTextColor(Color.WHITE);

        shadowTextView = findViewById(R.id.tv_shadow_test);
        //渐变颜色
//        shadowTextView.setStyle("#ded8cd", "#7d7a74", "#a1969d", 3, 9);
        //阴影
        shadowTextView.setShadowLayer(2, 0, 2, "#ccc");
    }
}
