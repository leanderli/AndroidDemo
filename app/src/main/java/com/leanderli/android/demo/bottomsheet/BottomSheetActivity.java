package com.leanderli.android.demo.bottomsheet;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.leanderli.android.demo.R;
import com.leanderli.android.demo.util.SystemUiController;

public class BottomSheetActivity extends AppCompatActivity {
    protected SystemUiController mSystemUiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        updateUi(false);

        View sheetTopBarView = findViewById(R.id.sheet_top_bar);

        View sheetContentView = findViewById(R.id.sheet_content_view);

        sheetContentView.setAlpha(0);
        View mainContentView = findViewById(R.id.main_content_view);
        mainContentView.setAlpha(1);

        View bottomSheetView = findViewById(R.id.sheet_view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        updateUi(false);
//                        sheetTopBarView.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        updateUi(true);
//                        sheetTopBarView.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                mainContentView.setAlpha(1 - slideOffset * 2);
//                sheetTopBarView.setAlpha(1 - slideOffset * 2);
                sheetContentView.setAlpha(slideOffset);
            }
        });
    }

    void updateUi(boolean isLight) {
        getSystemUiController().updateUiState(SystemUiController.UI_STATE_BASE_WINDOW, isLight);
    }

    public SystemUiController getSystemUiController() {
        if (mSystemUiController == null) {
            mSystemUiController = new SystemUiController(getWindow());
        }
        return mSystemUiController;
    }
}
