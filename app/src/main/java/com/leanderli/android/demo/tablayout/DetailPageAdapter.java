package com.leanderli.android.demo.tablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author leanderli
 * @date 2019.08.27
 */
public class DetailPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<String> mTitles;
    private HashMap<String, ArrayList<String>> mItemMaps = new HashMap<>();

    public DetailPageAdapter(Context context, FragmentManager fm, ArrayList<String> titles,
                             HashMap<String, ArrayList<String>> mItemMaps) {
        super(fm);
        this.mContext = context;
        this.mTitles = titles;
        this.mItemMaps = mItemMaps;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailInfoFragment.newInstance(position, mItemMaps.get(mTitles.get(position)));
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}