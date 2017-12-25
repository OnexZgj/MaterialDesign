package com.it.onex.materialdesigndemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Linsa on 2017/12/25:14:40.
 * des: Fragment的adapter
 */

public class FragmentsAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    String[] titles=new String[]{"","头条","社交","直播","约吗"};

    public FragmentsAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
