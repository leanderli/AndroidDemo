package com.leanderli.android.demo.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class ShortcutItemView extends TextView {

    public ShortcutItemView(Context context) {
        this(context, null);
    }

    public ShortcutItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShortcutItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ShortcutItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void applyShortcutInfo(ShortcutsInfo shortcutsInfo) {
        if (!TextUtils.isEmpty(shortcutsInfo.label)) {
            setText(shortcutsInfo.label);
        }
        Drawable mIcon = shortcutsInfo.icon;
        if (null != mIcon) {
            mIcon.setBounds(0, 0, 56, 56);
            setCompoundDrawablesRelative(null, null, mIcon, null);
        }
    }

}
