package com.sainti.blackcard.my.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.my.coupon.adapter.UnAvailableAdapter;
import com.sainti.blackcard.my.coupon.bean.CommenCouponBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class UnAvailableFragment extends BaseFragment implements OnNetResultListener {

    private String gids;
    private String amount;
    private LoadingView loadingView;
    private RecyclerView rv_coupon;
    private UnAvailableAdapter availableAdapter;

    @Override
    protected int setLayout() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gids = bundle.getString("gids");
            amount = bundle.getString("amount");
        }
        return R.layout.fragment_coupon_unuesd;
    }

    @Override
    protected void initView(View view) {
        loadingView = new LoadingView(context);
        rv_coupon = bindView(R.id.rv_coupon);

    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.getUerBonus(gids, amount, 1, context, this);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        availableAdapter = new UnAvailableAdapter(R.layout.item_unused_coupon);
        rv_coupon.setAdapter(availableAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                CommenCouponBean commenCouponBean = GsonSingle.getGson().fromJson(stringResult, CommenCouponBean.class);
                availableAdapter.setNewData(commenCouponBean.getData().getBuky_bonus_data());
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }
}
