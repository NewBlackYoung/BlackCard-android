package com.sainti.blackcard.my.flyorder.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.flyorder.adapter.DetaiInfoAdapter;
import com.sainti.blackcard.my.flyorder.bean.FlyDetailBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;

public class FlyOrderDetailActivity extends BaseTitleActivity implements OnNetResultListener {
private TextView ao_qftime,ao_qifei,ao_date,ao_jltime,ao_jiangluo,tv_hangban,ao_lianxidianhua,ao_lianxiren,ao_personchengren,ao_orderno,ao_price,state;
private RecyclerView rv_info;
    private DetaiInfoAdapter detaiInfoAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_fly_order_detail;
    }

    @Override
    protected void initView() {
        ao_qftime= bindView(R.id.ao_qftime);
        ao_qifei= bindView(R.id.ao_qifei);
        ao_date= bindView(R.id.ao_date);
        ao_jltime= bindView(R.id.ao_jltime);
        ao_jiangluo= bindView(R.id.ao_jiangluo);
        tv_hangban= bindView(R.id.tv_hangban);
        ao_lianxidianhua= bindView(R.id.ao_lianxidianhua);
        ao_lianxiren= bindView(R.id.ao_lianxiren);
        ao_personchengren= bindView(R.id.ao_personchengren);
        ao_orderno= bindView(R.id.ao_orderno);
        ao_price= bindView(R.id.ao_price);
        state= bindView(R.id.state);
        rv_info =bindView(R.id.rv_info);
        rv_info.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("机票订单详情");
        detaiInfoAdapter = new DetaiInfoAdapter(R.layout.item_flydetail);
        rv_info.setAdapter(detaiInfoAdapter);
        String ao_id = getIntent().getStringExtra("ao_id");
        String ao_orderno = getIntent().getStringExtra("ao_orderno");
        TurnClassHttp.airorder_detail(ao_id,ao_orderno,1,this,this);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        FlyDetailBean flyDetailBean = gson.fromJson(stringResult,FlyDetailBean.class);
        ao_qftime.setText(flyDetailBean.getData().getAo_qftime());
        ao_qifei.setText(flyDetailBean.getData().getAo_qifei());
        ao_date.setText(flyDetailBean.getData().getAo_date());
        ao_jltime.setText(flyDetailBean.getData().getAo_jltime());
        ao_jiangluo.setText(flyDetailBean.getData().getAo_jiangluo());
        tv_hangban.setText(flyDetailBean.getData().getAo_company()+flyDetailBean.getData().getAo_flight()+" | "+flyDetailBean.getData().getAo_position());
        ao_lianxidianhua.setText(flyDetailBean.getData().getAo_lianxidianhua());
        ao_lianxiren.setText(flyDetailBean.getData().getAo_lianxiren());
        ao_personchengren.setText(flyDetailBean.getData().getAo_personchengren());
        ao_orderno.setText(flyDetailBean.getData().getAo_orderno());
        ao_price.setText(flyDetailBean.getData().getAo_price());
        state.setText(flyDetailBean.getData().getAo_qftime());
        detaiInfoAdapter.setNewData(flyDetailBean.getData().getAo_person());
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a= errMsg;
    }
}
