package com.sainti.blackcard.circle.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.FollowAdapter;
import com.sainti.blackcard.circle.bean.FollowBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MyFollowActivity extends BaseTitleActivity implements OnLoadmoreListener, OnRefreshListener, OnNetResultListener {

    private RecyclerView rv_fans;
    private LoadingView dialog;
    private FollowBean followbean;
    private FollowAdapter fansAdapter;
    private SmartRefreshLayout sm_fans;
    private List<FollowBean.DataBean> list;
    int page = 1;

    @Override
    public int setLayout() {
        return R.layout.activity_my_follow;
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
        setTitle("我的关注");
        dialog = new LoadingView(this);
        list = new ArrayList<>();
        TurnClassHttp.follow("1", 1, this, this);
        dialog.show();
        rv_fans.setLayoutManager(new LinearLayoutManager(this));
        fansAdapter = new FollowAdapter(this);
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
        TurnClassHttp.follow(String.valueOf(page), 2, this, this);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
      //  dialog.show();
        page = 1;
        TurnClassHttp.follow("1", 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        followbean = gson.fromJson(stringResult, FollowBean.class);
        switch (resultCode) {
            case 1:
                if (followbean.getResult().equals("1")) {
                    list.clear();
                    list.addAll(followbean.getData());
                    fansAdapter.setList(list);
                }

                break;
            case 2:
                if (followbean != null && followbean.getResult().equals("1")) {
                    list.addAll(followbean.getData());
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
        sm_fans.finishLoadmore();
        ToastUtils.show(this, "请求服务器失败");
    }
}
