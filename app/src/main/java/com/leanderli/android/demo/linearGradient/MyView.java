package com.leanderli.android.demo.linearGradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.leanderli.android.demo.R;

/**
 * @Description
 * @Author ls573
 * @Date 18.8.28
 */

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();

        float startX = getX();
        float startY = getY();


        int colorStart = getResources().getColor(R.color.color_4);
        int colorEnd = getResources().getColor(R.color.color_3);

        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(0, 0, 0,
                height, new int[]{colorStart, colorEnd}, new float[]{0.5f, 1f}, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        paint.setAlpha(150);

        canvas.drawRect(0, 0, width, height, paint);
    }
}
