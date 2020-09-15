package com.leanderli.android.demo.popup;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.leanderli.android.demo.R;

import razerdp.basepopup.BasePopupWindow;

public class AttachShortcutsView extends BasePopupWindow {
    private ImageView mIconView;

    public AttachShortcutsView(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createView();
    }

    private View createView() {
        View view = createPopupById(R.layout.attach_shortcuts_view);
        mIconView = view.findViewById(R.id.iv_icon);
        return view;
    }

    public void setIcon(View iconView) {
        if (iconView instanceof ImageView && null != mIconView) {
            ImageView icon = (ImageView) iconView;
            mIconView.setImageDrawable(icon.getDrawable());
            int[] iconViewLocations = new int[2];
            iconView.getLocationOnScreen(iconViewLocations);
            int x = iconViewLocations[0];
            int y = iconViewLocations[1];
            mIconView.setTranslationX(x);
            mIconView.setTranslationY(y);

            showSourceIconViewAnimation(iconView, true);
            showSourceIconViewAnimation(iconView, false);
            showTargetIconViewAnimation();
        }
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
}
