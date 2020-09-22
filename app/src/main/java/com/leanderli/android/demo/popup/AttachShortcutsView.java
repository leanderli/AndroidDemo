package com.leanderli.android.demo.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.leanderli.android.demo.R;

import razerdp.basepopup.BasePopupWindow;

public class AttachShortcutsView extends BasePopupWindow {
    private static final String TAG = "AttachShortcutsView";
    private AnimatorSet mShowAnimatorSet, mDismissAnimatorSet;

    private ImageView mIconView;
    private CardView mShortcutsContainer;

    private int mScreenWidth, mScreenHeight;

    public AttachShortcutsView(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.attach_shortcuts_view);
    }

    @Override
    public void onViewCreated(@NonNull View contentView) {
        super.onViewCreated(contentView);
        initAnimator();
        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();

        mIconView = contentView.findViewById(R.id.iv_icon);
        mShortcutsContainer = contentView.findViewById(R.id.cv_shortcuts_container);
    }

    @Override
    protected Animator onCreateShowAnimator() {
        return mShowAnimatorSet;
    }

    @Override
    protected Animator onCreateDismissAnimator() {
        return mDismissAnimatorSet;
    }

    public void setShortcuts(View iconView) {
        if (!(iconView instanceof ImageView)) {
            throw new IllegalArgumentException("iconView must be a image and has a drawable!");
        }

        int[] iconViewLocations = new int[2];
        iconView.getLocationOnScreen(iconViewLocations);
        int iconViewX = iconViewLocations[0];
        int iconViewY = iconViewLocations[1];
        int iconViewHeight = (int) (iconView.getHeight() * 1.2);
        int iconViewWidth = (int) (iconView.getWidth() * 1.2);

        ImageView icon = (ImageView) iconView;
        mIconView.setImageDrawable(icon.getDrawable());
        mIconView.setTranslationX(iconViewX);
        mIconView.setTranslationY(iconViewY);
        Log.d(TAG, "setShortcuts: iconView:x(" + iconViewX + "),y(" + iconViewY + ")," +
                "height(" + iconViewHeight + "),width(" + iconViewWidth + ")");

        int[] shortcutsViewTranslateLocations = getShortcutsViewTranslateLocations(iconViewX, iconViewY, iconViewHeight, iconViewWidth);
        mShortcutsContainer.setTranslationX(shortcutsViewTranslateLocations[0]);
        mShortcutsContainer.setTranslationY(shortcutsViewTranslateLocations[1]);

        showSourceIconViewAnimation(iconView, true);
        showSourceIconViewAnimation(iconView, false);
        showTargetIconViewAnimation();
    }

    private int[] getShortcutsViewTranslateLocations(int iconViewX, int iconViewY, int iconViewHeight, int iconViewWidth) {
        int[] locations = new int[2];
        int iconShortcutsDividerHeight = ConvertUtils.dp2px(6);
        int bothSideMargin = ConvertUtils.dp2px(16);
        int shortcutsViewHeight = ConvertUtils.dp2px(200) + iconShortcutsDividerHeight;
        int shortcutsViewWidth = ConvertUtils.dp2px(250);
        Log.d(TAG, "setShortcuts: shortcutsView:height(" + shortcutsViewHeight + "),width(" + shortcutsViewWidth + ")");

        int shortcutsViewX = 0, shortcutsViewY = 0;
        int iconRightSideMargin = mScreenWidth - iconViewX - bothSideMargin;
        if (iconRightSideMargin > shortcutsViewWidth) {
            shortcutsViewX = iconViewX;
        } else {
            if (iconViewX > shortcutsViewWidth) {
                shortcutsViewX = (iconViewX + iconViewWidth) - shortcutsViewWidth - bothSideMargin;
            } else {
                int margin = shortcutsViewWidth - iconViewX;
                shortcutsViewX = mScreenWidth - iconViewX - margin - bothSideMargin;
            }
        }
        locations[0] = shortcutsViewX;

        if (iconViewY > shortcutsViewHeight) {
            // 显示在icon上方
            shortcutsViewY = iconViewY - shortcutsViewHeight - iconShortcutsDividerHeight;
        } else {
            // 显示在icon下方
            shortcutsViewY = iconViewY + iconViewHeight + iconShortcutsDividerHeight;
        }
        locations[1] = shortcutsViewY;

        return locations;
    }

    private void showSourceIconViewAnimation(View iconView, boolean zoomOut) {
        ObjectAnimator scaleXAnimator = null;
        ObjectAnimator scaleYAnimator = null;
        if (zoomOut) {
            scaleXAnimator = ObjectAnimator.ofFloat(iconView, View.SCALE_X, 1f, 0.75f);
            scaleYAnimator = ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 1f, 0.75f);
        } else {
            scaleXAnimator = ObjectAnimator.ofFloat(iconView, View.SCALE_X, 0.75f, 1f);
            scaleYAnimator = ObjectAnimator.ofFloat(iconView, View.SCALE_Y, 0.75f, 1f);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }

    private void showTargetIconViewAnimation() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mIconView, View.SCALE_X, 0.75f, 1f, 1.2f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mIconView, View.SCALE_Y, 0.75f, 1f, 1.2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }

    private void initAnimator() {
        ObjectAnimator showAlphaAnimator = ObjectAnimator.ofFloat(getContentView(), View.ALPHA, 0f, 1f);
        ObjectAnimator showScaleXAnimator = ObjectAnimator.ofFloat(getContentView(), View.SCALE_X, 0.9f, 1f);
        ObjectAnimator showScaleYAnimator = ObjectAnimator.ofFloat(getContentView(), View.SCALE_Y, 0.9f, 1f);
        mShowAnimatorSet = new AnimatorSet();
        mShowAnimatorSet.setInterpolator(new FastOutSlowInInterpolator());
        mShowAnimatorSet.playTogether(showAlphaAnimator, showScaleXAnimator, showScaleYAnimator);

        ObjectAnimator dismissAlphaAnimator = ObjectAnimator.ofFloat(getContentView(), View.ALPHA, 1f, 0f);
        ObjectAnimator dismissScaleXAnimator = ObjectAnimator.ofFloat(getContentView(), View.SCALE_X, 1f, 0.9f);
        ObjectAnimator dismissScaleYAnimator = ObjectAnimator.ofFloat(getContentView(), View.SCALE_Y, 1f, 0.9f);
        mDismissAnimatorSet = new AnimatorSet();
        mDismissAnimatorSet.setInterpolator(new FastOutSlowInInterpolator());
        mDismissAnimatorSet.playTogether(dismissAlphaAnimator, dismissScaleXAnimator, dismissScaleYAnimator);
    }

}
