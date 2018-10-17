package com.sainti.blackcard.goodthings.goodtingsorder.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.goodthings.goodtingsorder.adapter.GoodThingsOrderAdapter;
import com.sainti.blackcard.goodthings.goodtingsorder.fragment.FragmentGoodThingAll;
import com.sainti.blackcard.goodthings.goodtingsorder.fragment.FragmentGoodThingDaiPay;
import com.sainti.blackcard.goodthings.goodtingsorder.fragment.FragmentGoodThingFaHuo;
import com.sainti.blackcard.goodthings.goodtingsorder.fragment.FragmentGoodThingYiPay;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class GoodThingOsderActivity extends BaseTitleActivity {
    private TabLayout tab_ho;
    private ViewPager viewPager;
    private GoodThingsOrderAdapter boardAdapter;
    private List<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.activity_good_thing_osder;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.vp_go);
        tab_ho = bindView(R.id.tab_go);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("好物订单");
        addFragment();
        boardAdapter = new GoodThingsOrderAdapter(getSupportFragmentManager());
        boardAdapter.setFragments(fragments);
        viewPager.setOffscreenPageLimit(3);//预加载数量
        viewPager.setAdapter(boardAdapter);
        tab_ho.setupWithViewPager(viewPager);
    }

    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentGoodThingAll());
        fragments.add(new FragmentGoodThingDaiPay());
        fragments.add(new FragmentGoodThingYiPay());
        fragments.add(new FragmentGoodThingFaHuo());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals("88888")) {
            finish();
        }
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSHAOWU)) {
            finish();
        }

    }
}
