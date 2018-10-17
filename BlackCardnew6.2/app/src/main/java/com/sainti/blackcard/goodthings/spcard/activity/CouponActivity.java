package com.sainti.blackcard.goodthings.spcard.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.goodthings.spcard.adapter.CouponAdapter;
import com.sainti.blackcard.goodthings.spcard.baen.CouponlistBean;
import com.sainti.blackcard.widget.LoadingView;


public class CouponActivity extends BaseTitleActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener, OnNetResultListener {


    private RecyclerView rv_coupon;
    private CouponAdapter couponAdapter;
    private TextView tv_sure;
    private CouponlistBean couponlistBean;
    private CheckBox checkBox;
    boolean choice = false;
    int ps = 99999;
    private String code;
    private LoadingView loadingView;
    private TextView tv_null;

    @Override
    public int setLayout() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initView() {
        rv_coupon = bindView(R.id.rv_coupon);
        tv_sure = bindView(R.id.tv_sure);
        tv_null = bindView(R.id.tv_null);
    }

    @Override
    protected void initEvent() {
        tv_sure.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        loadingView = new LoadingView(this);
        setTitle("优惠券");
        String prcie = getIntent().getStringExtra("price");
        String cl_id = getIntent().getStringExtra("cl_id");
        String w_id = getIntent().getStringExtra("w_id");
        code = getIntent().getStringExtra("code");
        if (code.equals("1")) {
            tv_sure.setVisibility(View.GONE);
        }
        rv_coupon.setLayoutManager(new LinearLayoutManager(this));
        couponAdapter = new CouponAdapter(R.layout.item_coupon);
        rv_coupon.setAdapter(couponAdapter);
        couponAdapter.setChoiceCode(cl_id, code);
        couponAdapter.setOnItemClickListener(this);
        loadingView.show();
        TurnClassHttp.getUerBonus(w_id, prcie, 1, this, this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_sure:
                Intent intent = new Intent();
                if (choice) {
                    intent.putExtra("name", couponlistBean.getData().getBonus_data().get(ps).getCo_name() + couponlistBean.getData().getBonus_data().get(ps).getCo_stype());
                    intent.putExtra("cl_id", couponlistBean.getData().getBonus_data().get(ps).getCl_id());
                    intent.putExtra("co_dizhi", couponlistBean.getData().getBonus_data().get(ps).getCo_dizhi());
                    setResult(117, intent);
                } else {
                    intent.putExtra("name", "");
                    intent.putExtra("cl_id", "");
                    intent.putExtra("co_dizhi", "");
                    setResult(117, intent);
                }
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
      /*  String cl_id = couponlistBean.getData().getBonus_data().get(position).getCl_id();
        String cl_keyong = couponlistBean.getData().getBonus_data().get(position).getCl_keyong();
        if (cl_keyong.equals("1")) {
            ToastUtils.show(this, " 当前优惠券不符合使用要求");
            return;
        }
        couponAdapter.setChoiceCode(cl_id, code);
        checkBox = (CheckBox) couponAdapter.getViewByPosition(rv_coupon, position, R.id.checkbox);
        if (!checkBox.isChecked()) {
            choice = true;
            ps = position;
        } else {
            choice = false;
            ps = 999999;
        }*/

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        couponlistBean = gson.fromJson(stringResult, CouponlistBean.class);
        couponAdapter.setNewData(couponlistBean.getData().getBonus_data());
        loadingView.dismiss();
        if (couponlistBean.getData().getBonus_data() == null || couponlistBean.getData().getBonus_data().size() == 0) {
            tv_null.setVisibility(View.VISIBLE);
            tv_sure.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }
}
