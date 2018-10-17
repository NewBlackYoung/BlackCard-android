package com.sainti.blackcard.my.bonus.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.my.bonus.adapter.NotUsedAdapter;
import com.sainti.blackcard.my.bonus.bean.CanUsedBean;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class NotUsedFragment extends BaseFragment implements OnNetResultListener {

;
    private LoadingView loadingView;
    private RecyclerView rv_coupon;
    private NotUsedAdapter availableAdapter;

    @Override
    protected int setLayout() {

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
        TurnClassHttp.getMyBonus("ysx", "1", "10", 1, context, this);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        availableAdapter = new NotUsedAdapter(R.layout.item_unused_coupon);
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
                CanUsedBean canUsedBean = GsonSingle.getGson().fromJson(stringResult,CanUsedBean.class);
                availableAdapter.setNewData(canUsedBean.getData());
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }
}
