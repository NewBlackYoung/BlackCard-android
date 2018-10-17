package com.sainti.blackcard.my.flyorder.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.flyorder.adapter.FlyListAdapter;
import com.sainti.blackcard.my.flyorder.bean.FlyListBean;
import com.sainti.blackcard.my.flyorder.drivinglicense.actvity.DrivingLicenseActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FlyOrderActivity extends BaseTitleActivity implements OnNetResultListener, BaseQuickAdapter.OnItemClickListener, BaseTitleActivity.OnClickRightTextCallBack {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView rv_gooot;
    private FlyListAdapter flyListAdapter;
    private FlyListBean flyListBean;
    private TextView id_null;

    @Override
    public int setLayout() {
        return R.layout.activity_fly_order;
    }

    @Override
    protected void initView() {
        smartRefreshLayout = bindView(R.id.refresh_lay);
        rv_gooot = bindView(R.id.rv_gooot);
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setEnableRefresh(false);
        id_null = bindView(R.id.id_null);
    }

    @Override
    protected void initEvent() {
        flyListAdapter = new FlyListAdapter(R.layout.item_flylist);
        rv_gooot.setLayoutManager(new LinearLayoutManager(this));
        rv_gooot.setAdapter(flyListAdapter);
        flyListAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        setTitle("出行订单");
        TurnClassHttp.airorder(1, this, this);

        setBaseRightText("",this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        flyListBean = gson.fromJson(stringResult, FlyListBean.class);
        if (flyListBean != null && flyListBean.getData() != null && flyListBean.getData().size() > 0) {
            flyListAdapter.setNewData(flyListBean.getData());
        }else {
            id_null.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,FlyOrderDetailActivity.class);
        intent.putExtra("ao_id",flyListBean.getData().get(position).getAo_id());
        intent.putExtra("ao_orderno",flyListBean.getData().get(position).getAo_orderno());
        startActivity(intent);

    }

    @Override
    public void clickRightText() {
        Intent i = new Intent(this, DrivingLicenseActivity.class);
        startActivity(i);
    }
}
