package com.leanderli.android.demo.customizeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.leanderli.android.demo.common.util.DensityUtils;

/**
 * @Description
 * @Author ls573
 * @Date 18.9.23
 */

public class LlDrawableTextView extends View {

    private final static String TAG = "LlDrawableTextView";

    private Drawable icon;
    private String text;
    private int iconSize, textSize, textColor, textTopMargin;

    private Paint textPaint;
    private int mViewWidth, mViewHeight;
    private Rect srcRect, destRect;

    private static final int DEFAULT_TEXT_SIZE_DP = 14;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_TOP_ICON_MARGIN_DP = 5;

    public LlDrawableTextView(Context context) {
        this(context, null);
    }

    public LlDrawableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LlDrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LlDrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setDither(true);
        textPaint.setFilterBitmap(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null != this.icon) {
            Bitmap bitmap = ((BitmapDrawable) icon).getBitmap();
            int bmWidth = bitmap.getWidth();
            int bmHeight = bitmap.getHeight();
            if ((0 != this.iconSize && this.iconSize > this.mViewWidth) || 0 == this.iconSize) {
                this.iconSize = this.mViewWidth;
            }
            Bitmap scaledBm = createScaleBitmap(bitmap, bmWidth, bmHeight);
            int scaledBmWidth = scaledBm.getWidth();
            int scaledBmHeight = scaledBm.getHeight();
            int left = (this.mViewWidth - this.iconSize) / 2;
            srcRect = new Rect(0, 0, scaledBmWidth, scaledBmHeight);
            destRect = new Rect(left, 0, scaledBmWidth + left, scaledBmHeight);
            canvas.drawBitmap(scaledBm, srcRect, destRect, null);
            this.text = bmWidth + "-" + bmHeight;
        }
        if (null != this.text && 0 < this.text.length()) {
            if (0 == this.textSize) {
                this.textSize = DensityUtils.dip2px(getContext(), DEFAULT_TEXT_SIZE_DP);
            }
            if (0 == this.textColor) {
                this.textColor = DEFAULT_TEXT_COLOR;
            }
            if (0 == this.textTopMargin) {
                this.textTopMargin = DensityUtils.dip2px(getContext(), DEFAULT_TOP_ICON_MARGIN_DP);
            }
            textPaint.setTextSize(this.textSize);
            textPaint.setColor(this.textColor);
            textPaint.setTextAlign(Paint.Align.CENTER);
            Rect bounds = new Rect();
            String strText = text.trim();
            textPaint.getTextBounds(strText, 0, text.length(), bounds);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float textHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;
            int textYPosition = (int) (this.iconSize + textHeight + this.textTopMargin);
            float textWidth = textPaint.measureText(strText);
            if (textWidth > this.mViewWidth) {
                int subStrIndex = textPaint.breakText(strText, 0, strText.length(),
                        true, mViewWidth, null);
                strText = strText.substring(0, subStrIndex - 1) + "...";
            }
            if (this.textColor != Color.BLACK) {
                int shadowColor = this.textColor;
                if (this.textColor == Color.WHITE) {
                    shadowColor = Color.BLACK;
                }
                textPaint.setShadowLayer(2, 2, 1, shadowColor);
            }
            canvas.drawText(strText, mViewWidth / 2, textYPosition, textPaint);
        }
    }

    private Bitmap createScaleBitmap(Bitmap sourceBm, int sourceWidth, int sourceHeight) {
        Matrix matrix = new Matrix();
        float scaleValue = (float) this.iconSize / sourceWidth;
        matrix.postScale(scaleValue, scaleValue);
        Bitmap scaledBm = Bitmap.createBitmap(sourceBm, 0, 0, sourceWidth, sourceHeight, matrix, true);
        return scaledBm;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextTopMargin(int textTopMargin) {
        this.textTopMargin = textTopMargin;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public int getIconSize() {
        return iconSize;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextTopMargin() {
        return textTopMargin;
    }
}
