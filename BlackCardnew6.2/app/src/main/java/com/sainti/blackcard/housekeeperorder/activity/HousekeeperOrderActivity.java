package com.sainti.blackcard.housekeeperorder.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.housekeeperorder.adapter.HouseOrderAdapter;
import com.sainti.blackcard.housekeeperorder.fragment.FragmentDaiPay;
import com.sainti.blackcard.housekeeperorder.fragment.FragmentHall;
import com.sainti.blackcard.housekeeperorder.fragment.FragmentYiPay;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HousekeeperOrderActivity extends BaseTitleActivity {
    private TabLayout tab_ho;
    private ViewPager viewPager;
    private HouseOrderAdapter boardAdapter;
    private List<Fragment> fragments;
    @Override
    public int setLayout() {
        return R.layout.activity_housekeeper_order;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.vp_ho);
        tab_ho = bindView(R.id.tab_ho);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("管家订单");
        addFragment();
        boardAdapter = new HouseOrderAdapter(getSupportFragmentManager());
        boardAdapter.setFragments(fragments);
        viewPager.setOffscreenPageLimit(2);//预加载数量
        viewPager.setAdapter(boardAdapter);
        tab_ho.setupWithViewPager(viewPager);
    }
    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentHall());
        fragments.add(new FragmentDaiPay());
        fragments.add(new FragmentYiPay());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals("11111")) {
            finish();
        }
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSGUANJIA)) {
            finish();
        }
    }
}
