package com.sainti.blackcard.circle.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.circle.adapter.MyFollowAdapter;
import com.sainti.blackcard.circle.bean.NewFindBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/10.
 */

public class FragmentFollow extends BaseFragment implements  OnLoadmoreListener, OnNetResultListener {
    private RecyclerView rv_all;
    private SmartRefreshLayout refreshLayout;
    private List<NewFindBean.DataBean.MyflowBean> list;
    private MyFollowAdapter allAdapter;
    private LoadingView progressDialog;
    private ImageView iv_kong;
    int page = 1;

    @Override
    protected int setLayout() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void initView(View view) {
        rv_all = (RecyclerView) view.findViewById(R.id.rv_all);
        rv_all.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_lay); // 刷新控件
        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadmoreListener(this);
        iv_kong = bindView(R.id.iv_kong);
        EventBus.getDefault().register(this);
        refreshLayout.setHeaderTriggerRate(0.8f);//触发刷新距离 与 HeaderHeight 的比率1.0.4
    }

    @Override
    protected void initData() {
        progressDialog = new LoadingView(context);
        list = new ArrayList<>();
        allAdapter = new MyFollowAdapter(context);
        rv_all.setAdapter(allAdapter);
        progressDialog.show();
        TurnClassHttp.getNewFindV("1", 1, context, this);
    }

    @Override
    protected void initEvents() {

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        progressDialog.show();
        page++;
        TurnClassHttp.getNewFindV(String.valueOf(page), 2, context, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        EventBus.getDefault().post(new NormalEventBean("jieshu"));
        Gson gson = GsonSingle.getGson();
        NewFindBean baseBean = gson.fromJson(stringResult, NewFindBean.class);
        switch (resultCode) {
            case 1:
                if (baseBean != null && baseBean.getResult() == 1) {
                    list.clear();
                    list.addAll(baseBean.getData().getMyflow());
                    if (list == null || list.size() == 0) {
                        iv_kong.setVisibility(View.VISIBLE);
                    }
                    allAdapter.setList(list);
                }
                break;
            case 2:
                if (baseBean != null && baseBean.getResult() == 1) {
                    list.addAll(baseBean.getData().getMyflow());
                    allAdapter.setList(list);
                }
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
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onPause() {
        super.onPause();
        // MobclickAgent.onPageEnd("fragmentfallow");
        // MobclickAgent.onPause(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        // MobclickAgent.onPageStart("fragmentfallow");
        //MobclickAgent.onResume(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals("369")) {
            rv_all.scrollToPosition(0);
            //   ToastUtils.show(context,"收到消息");
        }
        if (messageEvent.getMessageCode().equals("shuaxin")) {
            //progressDialog.show();
            page = 1;
            TurnClassHttp.getNewFindV("1", 1, context, this);
        }
    }
}
