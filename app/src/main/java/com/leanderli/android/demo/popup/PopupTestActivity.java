package com.leanderli.android.demo.popup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_popup_test, null, false);
        setContentView(contentView);

        ShortcutItemView shortcutItemView = contentView.findViewById(R.id.shortcut_item);
        shortcutItemView.applyShortcutInfo(new ShortcutsInfo("测试", getDrawable(R.drawable.ic_keyboard_arrow_down)));

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
        shortcutsPopupView.setMaxHeight(2000);
        shortcutsPopupView.showPopupWindow(view);
    }
}
