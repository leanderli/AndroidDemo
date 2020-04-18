package com.leanderli.android.demo.bottomsheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.leanderli.android.demo.R;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        LinearLayout searchEdit = findViewById(R.id.search_edit);
        LinearLayout searchContent = findViewById(R.id.search_content);
        searchEdit.setAlpha(1);
        searchContent.setAlpha(0);
    }
}
