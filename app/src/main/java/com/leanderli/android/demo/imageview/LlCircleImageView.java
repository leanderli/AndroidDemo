package com.leanderli.android.demo.imageview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description
 * @Author ls573
 * @Date 18.9.22
 */

public class LlCircleImageView extends View {

    private float width, height, radius;
    private Paint paint;
    private int color;
    private Bitmap bitmap;

    public LlCircleImageView(Context context) {
        this(context, null);
    }

    public LlCircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LlCircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (0 != this.color) {
            paint.setColor(this.color);
            //使用画笔在画布上画圆
            canvas.drawCircle(width / 2, height / 2, radius, paint);
        }
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColor(String strColor) {
        if (null != strColor) {
            try {
                this.color = Color.parseColor(strColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
