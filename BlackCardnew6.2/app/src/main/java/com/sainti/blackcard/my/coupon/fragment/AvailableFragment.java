package com.sainti.blackcard.my.coupon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.my.coupon.adapter.AvailableAdapter;
import com.sainti.blackcard.my.coupon.bean.CommenCouponBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class AvailableFragment extends BaseFragment implements OnNetResultListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    private String gids;
    private String amount;
    private LoadingView loadingView;
    private RecyclerView rv_coupon;
    private TextView noused;
    private AvailableAdapter availableAdapter;
    private CommenCouponBean commenCouponBean;
    private String cl_id;

    @Override
    protected int setLayout() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gids = bundle.getString("gids");
            amount = bundle.getString("amount");
            cl_id = bundle.getString("cl_id");
        }
        return R.layout.fragment_coupon_uesd;

    }

    @Override
    protected void initView(View view) {
        loadingView = new LoadingView(context);
        rv_coupon = bindView(R.id.rv_coupon);

        noused = bindView(R.id.noused);
    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.getUerBonus(gids, amount, 1, context, this);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        availableAdapter = new AvailableAdapter(R.layout.item_used_coupon);
        availableAdapter.setCl_id(cl_id);
        availableAdapter.setOnItemClickListener(this);
        rv_coupon.setAdapter(availableAdapter);

    }

    @Override
    protected void initEvents() {
        noused.setOnClickListener(this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                commenCouponBean = GsonSingle.getGson().fromJson(stringResult, CommenCouponBean.class);
                availableAdapter.setNewData(commenCouponBean.getData().getKeyy_bonus_data());
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("name", commenCouponBean.getData().getKeyy_bonus_data().get(position).getCo_name() + commenCouponBean.getData().getBuky_bonus_data().get(position).getCo_stype());
        intent.putExtra("cl_id", commenCouponBean.getData().getKeyy_bonus_data().get(position).getCl_id());
        intent.putExtra("co_dizhi", commenCouponBean.getData().getKeyy_bonus_data().get(position).getCo_dizhi());
        getActivity().setResult(117, intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", "");
        intent.putExtra("cl_id", "");
        intent.putExtra("co_dizhi", "");
        getActivity().setResult(117, intent);
        getActivity().finish();
    }
}
