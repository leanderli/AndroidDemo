package com.leanderli.android.demo.viewpager;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

/**
 * @author leanderli
 * @date 2019.09.08
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mFragments;

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position, Fragment.SavedState savedState) {
        return mFragments.get(position);
    }

    @Override
    public void onDestroyItem(int position, Fragment fragment) {

    }

    @Override
    public int getItemCount() {
        return null == mFragments ? 0 : mFragments.size();
    }
}
