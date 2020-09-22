package com.leanderli.android.demo.popup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.leanderli.android.demo.R;
import com.leanderli.android.demo.common.CommonRvAdapter;
import com.leanderli.android.demo.common.OnItemClickListener;
import com.leanderli.android.demo.common.RvItem;

import java.util.ArrayList;

import razerdp.basepopup.BasePopupWindow;
import razerdp.blur.PopupBlurOption;

public class AttachShortcutsViewTestActivity extends AppCompatActivity {

    private View mContentView;
    private RecyclerView mAppListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = getLayoutInflater()
                .inflate(R.layout.activity_attach_shortcuts_view_test, null, false);
        setContentView(mContentView);

        mAppListView = findViewById(R.id.rv_apps);
        mAppListView.setLayoutManager(new GridLayoutManager(this, 5));
        CommonRvAdapter commonRvAdapter = new CommonRvAdapter(this, getDemoData());
        commonRvAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                showAttachShortcutsPopupView(view);
            }
        });
        mAppListView.setAdapter(commonRvAdapter);
    }

    private void showAttachShortcutsPopupView(View view) {
        AttachShortcutsView shortcutsView = new AttachShortcutsView(this);
        shortcutsView.setOverlayStatusbar(true);
        shortcutsView.setOverlayNavigationBar(true);
        shortcutsView.setShortcuts(view);
        /*shortcutsView.setBackgroundColor(Color.TRANSPARENT);
        PopupBlurOption blurOption = new PopupBlurOption();
        blurOption.setBlurAsync(true);
        blurOption.setBlurRadius(15);
        blurOption.setBlurView(mContentView);
        shortcutsView.setBlurBackgroundEnable(true);
        shortcutsView.setBlurOption(blurOption);*/
        shortcutsView.showPopupWindow();
    }

    private ArrayList<RvItem> getDemoData() {
        ArrayList<RvItem> rvItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rvItems.add(new RvItem("应用程序" + i, getDrawable(R.mipmap.ic_launcher)));
        }
        return rvItems;
    }


}