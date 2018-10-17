package com.sainti.blackcard.my.flyorder.drivinglicense.actvity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.flyorder.drivinglicense.fragment.FragmentAl;
import com.sainti.blackcard.my.flyorder.drivinglicense.fragment.FragmentDaiFu;
import com.sainti.blackcard.my.flyorder.drivinglicense.fragment.FragmentFaHuo;
import com.sainti.blackcard.housekeeperorder.adapter.HouseOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class DrivingLicenseActivity extends BaseTitleActivity {
    private TabLayout tab_ho;
    private ViewPager viewPager;
    private HouseOrderAdapter boardAdapter;
    private List<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.activity_driving_license;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.vp_ho);
        tab_ho = bindView(R.id.tab_ho);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("驾照订单");
        addFragment();
        boardAdapter = new HouseOrderAdapter(getSupportFragmentManager());
        boardAdapter.setFragments(fragments);
        viewPager.setOffscreenPageLimit(2);//预加载数量
        viewPager.setAdapter(boardAdapter);
        tab_ho.setupWithViewPager(viewPager);
    }

    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentAl());
        fragments.add(new FragmentDaiFu());
        fragments.add(new FragmentFaHuo());
    }
}
