package com.leanderli.android.demo.linearGradient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.leanderli.android.demo.R;

public class LinearGradientTestActivity extends AppCompatActivity {

    private ImageView wallpaper;
//    private ImageView frontColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_gradient_test);

        wallpaper = findViewById(R.id.iv_wallpaper);
        wallpaper.setScaleType(ImageView.ScaleType.CENTER_CROP);
        wallpaper.setImageDrawable(getResources().getDrawable(R.drawable.demo_bg));

//        frontColor = findViewById(R.id.v_front_color);
//        frontColor.setBackgroundColor(Color.BLUE);
//        frontColor.setAlpha(0.5f);
//
//
//        frontColor.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT >= 16) {
//                    frontColor.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }else {
//                    frontColor.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//                int width = frontColor.getWidth();
//                int height = frontColor.getHeight();
//                System.out.println(width + " " + height);
//            }
//        });
    }

}
