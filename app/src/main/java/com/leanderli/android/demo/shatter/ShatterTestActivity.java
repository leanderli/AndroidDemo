package com.leanderli.android.demo.shatter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.leanderli.android.demo.R;

import java.util.ArrayList;

import kale.ui.shatter.IShatterActivity;
import kale.ui.shatter.Shatter;
import kale.ui.shatter.ShatterManager;
import kale.ui.shatter.adapter.ShatterPagerAdapter;

public class ShatterTestActivity extends AppCompatActivity implements IShatterActivity {

    private ShatterManager mShatterManager;
//    private TestPagerAdapter mPagerAdapter;
    private ArrayList<Shatter> mShatters = new ArrayList<>();

    private ViewPager mPagerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shatter_test);
//        EventDispatchFragment.injectIfNeededIn(this);
        mShatterManager = getShatterManager();

        bindViews();
        setUpViews();
    }

    private void setUpViews() {
//        mPagerAdapter = new TestPagerAdapter(mShatterManager);
        HomePage homePage = new HomePage();
        ContentPage contentPage = new ContentPage();
        mShatters.add(contentPage);
        mShatters.add(homePage);

        mPagerContainer.setOffscreenPageLimit(2);
//        mPagerContainer.setAdapter(mPagerAdapter);
//        mPagerAdapter.notifyDataSetChanged();
    }

    private void bindViews() {
        mPagerContainer = findViewById(R.id.pager_container);
    }

    public ShatterManager getShatterManager() {
        if (mShatterManager == null) {
            mShatterManager = new ShatterManager(this);
        }
        return mShatterManager;
    }


    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

//    private class TestPagerAdapter extends ShatterPagerAdapter {
//
//        public TestPagerAdapter(ShatterManager manager) {
//            super(manager);
//        }
//
//        @NonNull
//        @Override
//        public Shatter createItem(Object type) {
//            return (Shatter) type;
//        }
//
//        @Override
//        public Object getItemType(int position) {
//            return mShatters.get(position);
//        }
//
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//    }


}
