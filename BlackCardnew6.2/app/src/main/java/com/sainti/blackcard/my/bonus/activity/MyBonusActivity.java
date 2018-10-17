package com.sainti.blackcard.my.bonus.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.bonus.adapter.BonusViewpageAdapter;
import com.sainti.blackcard.my.bonus.fragment.CanUsedFragment;
import com.sainti.blackcard.my.bonus.fragment.NotUsedFragment;

import java.util.ArrayList;
import java.util.List;

public class MyBonusActivity extends BaseTitleActivity {
    private List<Fragment> fragments;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String gids;
    private String amount;
    private String cl_id;

    @Override
    public int setLayout() {
        return R.layout.activity_my_bonus;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tablayout);
        viewPager = bindView(R.id.view_p);
    }

    @Override
    protected void initEvent() {
        addFragment();
        BonusViewpageAdapter circleFragmentAdapter = new BonusViewpageAdapter(getSupportFragmentManager());
        circleFragmentAdapter.setFragments(fragments);
        viewPager.setAdapter(circleFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }
    private void addFragment() {
        //创建Fragment对象，并通过Bundle对象传递值
        CanUsedFragment availableFragment = new CanUsedFragment();
        NotUsedFragment unAvailableFragment = new NotUsedFragment();
        setTitle("优惠卷");

        fragments = new ArrayList<>();
        fragments.add(availableFragment);
        fragments.add(unAvailableFragment);

    }
}
