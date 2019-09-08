package com.leanderli.android.demo.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.leanderli.android.demo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private android.support.v4.app.FragmentManager fm;

    private ArrayList<String> mTitles = new ArrayList<>();
    private HashMap<String, ArrayList<String>> mItemMaps = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        mTabLayout = findViewById(R.id.sliding_tabs);
        mViewPager = findViewById(R.id.viewpager);

        mTitles.add("未分类");
        mTitles.add("工具类");
        mTitles.add("社交类");
        mTitles.add("福利类");

        ArrayList<String> data1 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data1.add("未分类" + i);
        }
        ArrayList<String> data2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data2.add("工具类" + i);
        }
        ArrayList<String> data3 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            data3.add("社交类" + i);
        }
        ArrayList<String> data4 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data4.add("福利类" + i);
        }
        mItemMaps.put("未分类", data1);
        mItemMaps.put("工具类", data2);
        mItemMaps.put("社交类", data3);
        mItemMaps.put("福利类", data4);

        fm = getSupportFragmentManager();
        mViewPager.setAdapter(new DetailPageAdapter(TabLayoutActivity.this, fm, mTitles, mItemMaps));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
