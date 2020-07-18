package com.leanderli.android.demo.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.leanderli.android.demo.R;

public class OverSlideLayout extends LinearLayout {
    private ImageView mOverSlideTopView;
    private RecyclerView mMainListView;

    private boolean mIsScrolledToTop = true;
    private float mStartY;

    public OverSlideLayout(Context context) {
        this(context, null);
    }

    public OverSlideLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverSlideLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public OverSlideLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        bindView();
        setView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setView() {
        mMainListView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (0 == mStartY) {
                        mStartY = event.getY();
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (0 == mStartY) {
                        mStartY = event.getY();
                    }
                    if (mIsScrolledToTop && event.getY() - mStartY > 0) {
                        updateOverSlideView(event.getY());
                    }

                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                default:


                    break;
            }
            return false;
        });
        mMainListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mIsScrolledToTop = !canScrollVertically(-1);
            }
        });
    }

    private void updateOverSlideView(float movedY) {
        LayoutParams layoutParams = (LayoutParams) mOverSlideTopView.getLayoutParams();
        layoutParams.height = (int) (mOverSlideTopView.getHeight() + (movedY - mStartY));
        mOverSlideTopView.setLayoutParams(layoutParams);
    }

    private void bindView() {
        mOverSlideTopView = findViewById(R.id.iv_over_slide_top_view);
        mMainListView = findViewById(R.id.rv_main);
    }

}
