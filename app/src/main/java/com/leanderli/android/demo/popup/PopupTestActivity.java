package com.leanderli.android.demo.popup;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

import razerdp.basepopup.BasePopupWindow;
import razerdp.blur.PopupBlurOption;

public class PopupTestActivity extends AppCompatActivity {

    private ArrayList<ShortcutsInfo> mShortcutsInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_test);

        mShortcutsInfo.clear();
        initShortcutsInfos();
    }

    private void initShortcutsInfos() {
        for (int i = 0; i < 10; i++) {
            mShortcutsInfo.add(new ShortcutsInfo("捷径" + i, null));
        }
    }

    public void showPopup(View view) {
        ShortcutsPopupView shortcutsPopupView = new ShortcutsPopupView(this);
        shortcutsPopupView.setShortcuts(mShortcutsInfo);
        shortcutsPopupView.setPopupGravity(BasePopupWindow.GravityMode.RELATIVE_TO_ANCHOR, Gravity.CENTER);
        PopupBlurOption popupBlurOption = new PopupBlurOption();
        popupBlurOption.setBlurView(getWindow().getDecorView());
        popupBlurOption.setBlurAsync(true);
        popupBlurOption.setBlurRadius(10);
        shortcutsPopupView.setBlurOption(popupBlurOption);
        shortcutsPopupView.setBlurBackgroundEnable(true);
        shortcutsPopupView.showPopupWindow(view);
    }
}
