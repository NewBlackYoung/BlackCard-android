package com.sainti.blackcard.my.coupon.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.coupon.adapter.CouponViewpageAdapter;
import com.sainti.blackcard.my.coupon.fragment.AvailableFragment;
import com.sainti.blackcard.my.coupon.fragment.UnAvailableFragment;

import java.util.ArrayList;
import java.util.List;
/*优惠券*/
public class CommentCouponActivity extends BaseTitleActivity {

    private List<Fragment> fragments;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String gids;
    private String amount;
    private String cl_id;

    @Override
    public int setLayout() {
        return R.layout.activity_comment_coupon;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tablayout);
        viewPager = bindView(R.id.view_p);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        gids = getIntent().getStringExtra("gids");
        amount = getIntent().getStringExtra("amount");
        cl_id = getIntent().getStringExtra("cl_id");
        addFragment();
        CouponViewpageAdapter circleFragmentAdapter = new CouponViewpageAdapter(getSupportFragmentManager());
        circleFragmentAdapter.setFragments(fragments);
        viewPager.setAdapter(circleFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void addFragment() {
        //创建Fragment对象，并通过Bundle对象传递值
        AvailableFragment availableFragment = new AvailableFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gids",gids);
        bundle.putString("amount", amount);
        bundle.putString("cl_id", cl_id);
        availableFragment.setArguments(bundle);
        UnAvailableFragment unAvailableFragment = new UnAvailableFragment();
        Bundle bundles = new Bundle();
        bundle.putString("gids",gids);
        bundle.putString("amount", amount);
        bundle.putString("cl_id", cl_id);
        unAvailableFragment.setArguments(bundle);
        setTitle("优惠卷");


        fragments = new ArrayList<>();
        fragments.add(availableFragment);
        fragments.add(unAvailableFragment);

    }
}
