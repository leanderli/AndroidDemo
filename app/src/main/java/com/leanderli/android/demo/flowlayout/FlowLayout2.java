package com.leanderli.android.demo.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout2 extends ViewGroup {
    private static final String TAG = "FlowLayout2";

    private List<List<View>> mAllLineViews = new ArrayList<>();
    private List<Integer> mLineHeights = new ArrayList<>();

    private int mSpacing = 10;

    public FlowLayout2(Context context) {
        this(context, null);
    }

    public FlowLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();

        List<View> lineChildViews = new ArrayList<>();
        int lineWidthUsed = 0;
        int lineHeight = 0;

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        int parentNeedWidth = 0, parentNeedHeight = 0;

        mAllLineViews.clear();
        mLineHeights.clear();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams childLp = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLp.height);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            int childMeasureWidth = child.getMeasuredWidth();
            int childMeasureHeight = child.getMeasuredHeight();

            if (childLp instanceof MarginLayoutParams) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childLp;
                childMeasureWidth += (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                childMeasureHeight += (marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
            }
            if ((lineWidthUsed + childMeasureWidth) > selfWidth) {
                mAllLineViews.add(lineChildViews);
                mLineHeights.add(lineHeight);

                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed);
                parentNeedHeight = parentNeedHeight + lineHeight;

                lineChildViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            lineChildViews.add(child);
            lineWidthUsed += childMeasureWidth;
            lineHeight = Math.max(lineHeight, childMeasureHeight);

            if (i == getChildCount() - 1) {
                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed);
                parentNeedHeight = parentNeedHeight + lineHeight;
            }
        }

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int finalWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int finalHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;
        setMeasuredDimension(finalWidth, finalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: ");
        int lineCount = mAllLineViews.size();
        int currentLeft = getPaddingLeft();
        int currentTop = getPaddingTop();
        for (int i = 0; i < lineCount; i++) {
            List<View> views = mAllLineViews.get(i);
            int lineHeight = mLineHeights.get(i);
            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);
                int right = currentLeft + view.getMeasuredWidth();
                int bottom = currentTop + view.getMeasuredHeight();
                LayoutParams viewLp = view.getLayoutParams();
                if (viewLp instanceof MarginLayoutParams) {
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) viewLp;
                    currentLeft += marginLayoutParams.leftMargin;
                    currentTop += marginLayoutParams.topMargin;
                    right += marginLayoutParams.rightMargin;
                    bottom += marginLayoutParams.bottomMargin;
                }
                view.layout(currentLeft, currentTop, right, bottom);
                currentLeft = right;
            }
            currentLeft = getPaddingLeft();
            currentTop = currentTop + lineHeight;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
    }

}
