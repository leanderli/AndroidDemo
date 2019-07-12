package com.leanderli.android.demo.customizeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * @Description
 * @Author ls573
 * @Date 18.9.21
 */

public class TextImageView extends android.support.v7.widget.AppCompatImageView {

    private Bitmap bitmap;
    private int width, height;
    private String viewText;
    private int topMargin;

    private Paint textPaint;
    private Paint bmPaint;

    public TextImageView(Context context) {
        this(context, null);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bmPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (null != viewText) {
            if (topMargin == 0) {
                topMargin = 10;
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == bitmap) {
            return;
        }
        canvas.drawBitmap(bitmap, 0, 0, bmPaint);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (null != bm) {
            this.bitmap = bm;
        }
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
    }

    public void setViewText(String text) {
        this.viewText = text;
    }
}
