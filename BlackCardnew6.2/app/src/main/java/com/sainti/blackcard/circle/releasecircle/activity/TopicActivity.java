package com.sainti.blackcard.circle.releasecircle.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.circle.releasecircle.adapter.TopicAdapter;
import com.sainti.blackcard.circle.releasecircle.bean.TopicBean;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;



public class TopicActivity extends BaseTitleActivity implements OnRefreshListener, OnLoadmoreListener, MyItemClickListener, OnNetResultListener {
    private RecyclerView rv_all;
    private SmartRefreshLayout refreshLayout;
    private List<TopicBean.DataBean> list;
    private LoadingView progressDialog;
    int page = 1;
    private TopicAdapter topicAdapter;

    @Override
    public int setLayout() {

        return R.layout.activity_topic;
    }

    @Override
    protected void initView() {
        rv_all = (RecyclerView) findViewById(R.id.rv_all);
        rv_all.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_lay); // 刷新控件
        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
       /* refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);*/
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("选择话题");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressDialog = new LoadingView(this);
        list = new ArrayList<>();
        progressDialog.show();
        topicAdapter = new TopicAdapter(this);
        rv_all.setAdapter(topicAdapter);
        topicAdapter.setListener(this);
        TurnClassHttp.getHuaTiList("1", 1, this, this);

    }



    /*下拉刷新*/
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        progressDialog.show();
        page = 1;
        TurnClassHttp.getHuaTiList("1", 1, this, this);
    }

    /*上拉加载*/
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        progressDialog.show();
        page++;
        TurnClassHttp.getHuaTiList(String.valueOf(page), 2, this, this);
    }

    @Override
    public void onItemClick(int position, int code) {
        Intent intent = new Intent();
        intent.putExtra("to_name", list.get(position).getTo_name());
        intent.putExtra("to_id", list.get(position).getTo_id());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        TopicBean baseBean = gson.fromJson(stringResult, TopicBean.class);
        switch (resultCode) {
            case 1:
                if (baseBean != null && baseBean.getResult().equals("1")) {
                    list.clear();
                    list.addAll(baseBean.getData());
                    topicAdapter.setList(list);
                }

                break;
            case 2:
                if (baseBean != null && baseBean.getResult().equals("1")) {
                    list.addAll(baseBean.getData());
                    topicAdapter.setList(list);
                }
                progressDialog.dismiss();
                refreshLayout.finishLoadmore();
                break;
        }
        progressDialog.dismiss();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        progressDialog.dismiss();
        refreshLayout.finishRefresh();

    }
}
