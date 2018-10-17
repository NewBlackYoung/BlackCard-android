package com.sainti.blackcard.goodthings.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sainti.blackcard.goodthings.bean.SelectionChoiceBean;
import com.sainti.blackcard.goodthings.fragment.CommentFragment;

import java.util.List;


public class SelectionViewPagerAdapter extends FragmentPagerAdapter {
    private List<SelectionChoiceBean.DataBean> channelsBeen;



    public void setChannelsBeen(List<SelectionChoiceBean.DataBean> channelsBeen) {
        this.channelsBeen = channelsBeen;
        notifyDataSetChanged();
    }

    public SelectionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String channelId = channelsBeen.get(position).getWt_id();
        return CommentFragment.getCommentFragment(channelId);
    }


    @Override
    public int getCount() {
        return channelsBeen != null ? channelsBeen.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channelsBeen.get(position).getWt_name();
    }

}
