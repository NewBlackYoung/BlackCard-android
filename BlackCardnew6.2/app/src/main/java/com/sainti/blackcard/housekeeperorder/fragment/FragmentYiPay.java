package com.sainti.blackcard.housekeeperorder.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.housekeeperorder.activity.HkOrderDetailActivity;
import com.sainti.blackcard.housekeeperorder.adapter.HousCommentAdapter;
import com.sainti.blackcard.housekeeperorder.bean.GuanJiaOrderBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
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

public class FragmentYiPay extends BaseFragment implements OnRefreshListener, OnLoadmoreListener, OnNetResultListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, TimerListener {
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_gooot;
    private LoadingView loadingView;
    private GuanJiaOrderBean allGoodthingsBean;
    private HousCommentAdapter allThingsAdapter;
    int page = 1;
    private RelativeLayout lay_null;
    private List<GuanJiaOrderBean.DataBean> list;
    private int remov;
    private View v_bg;
    private  TextView tv_delete_s;
    @Override
    protected int setLayout() {
        return R.layout.fragmemt_gj_yipay;
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
        lay_null= bindView(R.id.lay_null);
        v_bg = bindView(R.id.v_bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
    }

    @Override
    protected void initData() {
        loadingView.show();
        allThingsAdapter = new HousCommentAdapter(R.layout.item_guanjiacomment);
        allThingsAdapter.setContext(context);
        rv_gooot.setAdapter(allThingsAdapter);
        allThingsAdapter.setOnItemClickListener(this);
        allThingsAdapter.setOnItemChildClickListener(this);
        TurnClassHttp.guanjiaorder("ywc", "1", 1, context, this);
    }

    @Override
    protected void initEvents() {

    }
    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        String a =stringResult;
        Gson gson = GsonSingle.getGson();
        allGoodthingsBean = gson.fromJson(stringResult, GuanJiaOrderBean.class);
        switch (resultCode) {
            case 1:
                list.clear();
                list.addAll(allGoodthingsBean.getData());
                if (list==null||list.size()==0){
                    lay_null.setVisibility(View.VISIBLE);
                }
                allThingsAdapter.setNewData(list);
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
        loadingView.show();
        TurnClassHttp.guanjiaorder("ywc", "1", 1, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
      //  loadingView.dismiss();
        TurnClassHttp.guanjiaorder("ywc", String.valueOf(page), 2, context, this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(context, HkOrderDetailActivity.class);
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
            TurnClassHttp.guanjiaorder("ywc", "1", 1, context, this);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_delete:
                String state = list.get(position).getState();
                if (state.equals("0")) {
                    // 已完成
                    //showDialog(position);
                } else if (state.equals("1")) {

                } else if (state.equals("2")) {
                    return;
                    // 已付款
                } else {
                    showDialog(position);
                    // 已失效
                }

                break;
        }
    }
    private void showDialog(final int p) {
        remov = p;
        v_bg.setVisibility(View.VISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_shopcard, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_deleteSure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_delelteText);// 删除字
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_teleteCso);// 取消删除
        TextView tv_del = (TextView) view.findViewById(R.id.tv_del);// 取消删除
        tv_delelteText.setText("删除后订单记录将？");
        tv_del.setText("无法找回，确定删除吗？");
        tv_deleteSure.setText("确定");
        tv_teleteCso.setText("放弃");
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        tv_deleteSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnClassHttp.delguanjiaorder(list.get(p).getOrder_id(), 3, context, FragmentYiPay.this);
                dialog.dismiss();
                //bg.setVisibility(View.GONE);
                loadingView.show();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                v_bg.setVisibility(View.GONE);
            }
        });
        dialog.setCancelable(false);
    }

    @Override
    public void onTimerListener() {
        list.remove(remov);
        allThingsAdapter.setNewData(list);
        tv_delete_s.setVisibility(View.GONE);
        v_bg.setVisibility(View.GONE);

    }
}
