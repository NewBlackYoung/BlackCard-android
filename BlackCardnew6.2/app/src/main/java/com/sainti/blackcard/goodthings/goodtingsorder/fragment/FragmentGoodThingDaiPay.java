package com.sainti.blackcard.goodthings.goodtingsorder.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialogUtil;
import com.sainti.blackcard.goodthings.activity.GoodthingsListActivity;
import com.sainti.blackcard.goodthings.activity.HaoWuZhiFuActivity;
import com.sainti.blackcard.goodthings.goodtingsorder.activity.GoodthingsOrderDetailActivity;
import com.sainti.blackcard.goodthings.goodtingsorder.adapter.AllThingsAdapter;
import com.sainti.blackcard.goodthings.goodtingsorder.bean.AllGoodthingsBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/9.
 */

public class FragmentGoodThingDaiPay extends BaseFragment implements OnRefreshListener, OnLoadmoreListener, OnNetResultListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, TimerListener, View.OnClickListener, CommenDialogUtil.CallBack {
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_gooot;
    private LoadingView loadingView;
    private AllGoodthingsBean allGoodthingsBean;
    private AllThingsAdapter allThingsAdapter;
    int page = 1;
    private List<AllGoodthingsBean.DataBean> list;
    private View v_bg;
    private TextView tv_delete_s;
    private int remov;
    private RelativeLayout lay_null;
    private ImageView iv_tolist;

    @Override
    protected int setLayout() {
        return R.layout.fragment_goodthingsdaipay;
    }

    @Override
    protected void initView(View view) {
        loadingView = new LoadingView(context);
        refresh_lay = bindView(R.id.refresh_lay);
        rv_gooot = bindView(R.id.rv_gooot);
        rv_gooot.setLayoutManager(new LinearLayoutManager(context));
        refresh_lay.setOnRefreshListener(this);
        refresh_lay.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        v_bg = bindView(R.id.v_bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
        lay_null = bindView(R.id.lay_null);
        iv_tolist = bindView(R.id.iv_tolist);

    }

    @Override
    protected void initData() {
        loadingView.show();
        allThingsAdapter = new AllThingsAdapter(R.layout.item_allthings);
        allThingsAdapter.setContext(context);
        allThingsAdapter.setOnItemClickListener(this);
        allThingsAdapter.setOnItemChildClickListener(this);
        rv_gooot.setAdapter(allThingsAdapter);
        TurnClassHttp.haowuorder("dfk", "1", 1, context, this);

    }

    @Override
    protected void initEvents() {
        iv_tolist.setOnClickListener(this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        allGoodthingsBean = gson.fromJson(stringResult, AllGoodthingsBean.class);
        switch (resultCode) {
            case 1:
                list.clear();
                list.addAll(allGoodthingsBean.getData());
                allThingsAdapter.setNewData(list);
                if (list == null || list.size() == 0) {
                    lay_null.setVisibility(View.VISIBLE);
                }
                refresh_lay.finishRefresh();
                break;
            case 2:
                list.addAll(allGoodthingsBean.getData());
                allThingsAdapter.setNewData(list);
                refresh_lay.finishLoadmore();
                break;
            case 3:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    tv_delete_s.setVisibility(View.VISIBLE);

                    CommontUtil.lateTime(1500, this);

                } else {
                    v_bg.setVisibility(View.GONE);
                    ToastUtils.show(context, baseBean.getMsg());
                }
                break;
        }

        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        //  loadingView.show();
        TurnClassHttp.haowuorder("dfk", "1", 1, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        loadingView.dismiss();
        TurnClassHttp.haowuorder("dfk", String.valueOf(page), 2, context, this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(context, GoodthingsOrderDetailActivity.class);
        intent.putExtra("id", list.get(position).getOrder_id());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isPperation = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISOPERATION, false);
        if (isPperation) {
            page = 1;
            loadingView.show();
            TurnClassHttp.haowuorder("all", "1", 1, context, this);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int state = list.get(position).getState();
        switch (view.getId()) {
            case R.id.iv_goPay:
                if (state == 0) {
                    //代付款
                    // ToastUtils.show(context, "去付款");
                    poPya(position);
                } else if (state == 1) {// 已付款

                } else if (state == 2) {
                    ToastUtils.show(context, "查看物流");

                    // 已发货iv_loo
                } else {
                    showDialog(position);

                }
                break;
        }

    }

    private void poPya(int p) {
        String ordernum = list.get(p).getOrder_sn();// 订单号
        String dingdanid = list.get(p).getOrder_id();//商品id
        double money = ConvertUtil.convertToDouble(list.get(p).getOrder_amount(), 00.0f);
        String orderName = list.get(p).getProduct_name();
        String fenlei = list.get(p).getSpec_name();
        String count = list.get(p).getBuy_number();
        String url = list.get(p).getProduct_img();
        Intent intent = new Intent(context, HaoWuZhiFuActivity.class);
        intent.putExtra("ordernum", ordernum);
        intent.putExtra("dingdanid", dingdanid);
        intent.putExtra("money", money + "");
        intent.putExtra("orderName", orderName);
        intent.putExtra("fenlei", fenlei);
        intent.putExtra("count", count);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void showDialog(final int p) {
        remov = p;
        v_bg.setVisibility(View.VISIBLE);
        CommenDialogUtil.getInstance().showDialog(context, "删除后订单记录将？", "无法找回，确定删除吗？", this, 1);
    }

    @Override
    public void onTimerListener() {
        list.remove(remov);
        allThingsAdapter.setNewData(list);
        tv_delete_s.setVisibility(View.GONE);
        v_bg.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tolist:
                startActivity(new Intent(context, GoodthingsListActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onSureClick(int tpye) {
        TurnClassHttp.delhaowuorder(list.get(remov).getOrder_id(), 3, context, FragmentGoodThingDaiPay.this);
        loadingView.show();
    }

    @Override
    public void onCansoClick(int tpye) {
        v_bg.setVisibility(View.GONE);
    }
}
