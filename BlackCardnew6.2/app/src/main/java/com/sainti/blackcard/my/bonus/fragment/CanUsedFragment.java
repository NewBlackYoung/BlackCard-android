package com.sainti.blackcard.my.bonus.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.my.coupon.bean.CommenCouponBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.my.bonus.adapter.CanUsedAdapter;
import com.sainti.blackcard.my.bonus.bean.CanUsedBean;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class CanUsedFragment extends BaseFragment implements OnNetResultListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    private String gids;
    private String amount;
    private LoadingView loadingView;
    private RecyclerView rv_coupon;
    private TextView noused;
    private CanUsedAdapter availableAdapter;
    private CommenCouponBean commenCouponBean;
    private String cl_id;

    @Override
    protected int setLayout() {

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
        noused.setVisibility(View.GONE);
        loadingView.show();
        TurnClassHttp.getMyBonus("wsy", "1", "10", 1, context, this);
        rv_coupon.setLayoutManager(new LinearLayoutManager(context));
        availableAdapter = new CanUsedAdapter(R.layout.item_used_coupon);
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
                CanUsedBean canUsedBean = GsonSingle.getGson().fromJson(stringResult,CanUsedBean.class);
                availableAdapter.setNewData(canUsedBean.getData());
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
