package com.sainti.blackcard.circle.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.FansAdapter;
import com.sainti.blackcard.circle.bean.FansBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
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

public class FansActivity extends BaseTitleActivity implements  OnLoadmoreListener, OnRefreshListener, OnNetResultListener {


    private RecyclerView rv_fans;
    private LoadingView dialog;
    private FansBean fansBean;
    private FansAdapter fansAdapter;
    private SmartRefreshLayout sm_fans;
    private List<FansBean.DataBean> list;
    int page = 1;
    private String uid;

    @Override
    public int setLayout() {
        return R.layout.activity_fans2;
    }

    @Override
    protected void initView() {
        rv_fans = bindView(R.id.rv_fans);
        sm_fans = bindView(R.id.sm_fans);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getStringExtra("uid");
        String spUid = SpUtil.getString(SpCodeName.SPNAME,SpCodeName.UID,"");
        if (uid.equals(spUid)) {
            setTitle("我的粉丝");
        } else {
            setTitle("ta的粉丝");

        }
        dialog = new LoadingView(this);
        list = new ArrayList<>();
        TurnClassHttp.fansList(uid, "1",1,this, this);
        dialog.show();
        rv_fans.setLayoutManager(new LinearLayoutManager(this));
        fansAdapter = new FansAdapter(this);
        rv_fans.setAdapter(fansAdapter);
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sm_fans.setOnLoadmoreListener(this);
        sm_fans.setOnRefreshListener(this);
    }



    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        dialog.show();
        page++;
        TurnClassHttp.fansList(uid,  String.valueOf(page),2,this, this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //dialog.show();
        TurnClassHttp.fansList(uid, "1",1,this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        fansBean = gson.fromJson(stringResult, FansBean.class);
        switch (resultCode) {
            case 1:

                if (fansBean.getResult().equals("1")) {
                    list.clear();
                    list.addAll(fansBean.getData());
                    fansAdapter.setList(list);
                }
                sm_fans.finishRefresh();
                dialog.dismiss();
                break;
            case 2:
                if (fansBean != null && fansBean.getResult().equals("1")) {
                    list.addAll(fansBean.getData());
                    fansAdapter.setList(list);
                }
                dialog.dismiss();
                sm_fans.finishLoadmore();
                break;
        }
        dialog.dismiss();
        sm_fans.finishRefresh();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        dialog.dismiss();
        sm_fans.finishRefresh();
        ToastUtils.show(this, "请求服务器失败");
    }

}
