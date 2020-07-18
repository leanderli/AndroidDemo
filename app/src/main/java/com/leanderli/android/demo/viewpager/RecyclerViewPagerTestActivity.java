package com.leanderli.android.demo.viewpager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.leanderli.android.demo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPagerTestActivity extends AppCompatActivity {

    RecyclerViewPager mPager;
    FragmentPagerAdapter mPagerAdapter;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_pager_test);

        mPager = findViewById(R.id.content_pager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPager.setLayoutManager(layoutManager);
        mPager.setFlingFactor(1f);
        mPager.setTriggerOffset(0.5f);

        mFragments = new ArrayList<>();
        mFragments.add(FragmentOne.newInstance());
        mFragments.add(FragmentTwo.newInstance());
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mPager.setAdapter(mPagerAdapter);
    }
}
