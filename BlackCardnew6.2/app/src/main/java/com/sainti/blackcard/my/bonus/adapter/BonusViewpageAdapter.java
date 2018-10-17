package com.sainti.blackcard.my.bonus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zouguohua on 2017/2/22.
 * 看板页面适配器
 */

public class BonusViewpageAdapter extends FragmentPagerAdapter {
    private String[] titles = {"未使用", "已失效"};
    private List<Fragment> fragments;

    public BonusViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
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
