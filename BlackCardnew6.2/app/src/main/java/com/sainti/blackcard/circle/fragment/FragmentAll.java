package com.sainti.blackcard.circle.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.circle.adapter.AllAdapter;
import com.sainti.blackcard.circle.bean.NewFindBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.AnimationUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
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

public class FragmentAll extends BaseFragment implements OnNetResultListener, OnLoadmoreListener, View.OnClickListener {

    private RecyclerView rv_all;
    private SmartRefreshLayout refreshLayout;
    private List<NewFindBean.DataBean.AllfindBean> list;
    private AllAdapter allAdapter;
    private LoadingView progressDialog;
    int page = 1;
    private ImageView ib_top;

    @Override
    protected int setLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(View view) {
        final AnimationUtil animationUtil = new AnimationUtil(context);
        ib_top = bindView(R.id.ib_top);
        rv_all = (RecyclerView) view.findViewById(R.id.rv_all);
        GridLayoutManager manager = new GridLayoutManager(context, 1);
        rv_all.setLayoutManager(manager);//设置只为一列
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_lay); // 刷新控件
        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setEnableRefresh(false);

        refreshLayout.setOnLoadmoreListener(this);
        ib_top.setOnClickListener(this);
        EventBus.getDefault().register(this);
       /* refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）*/
        refreshLayout.setHeaderTriggerRate(0.8f);//触发刷新距离 与 HeaderHeight 的比率1.0.4
    }

    @Override
    protected void initData() {
        progressDialog = new LoadingView(context);
        list = new ArrayList<>();
        allAdapter = new AllAdapter(context);
        rv_all.setAdapter(allAdapter);
        progressDialog.show();
        TurnClassHttp.getNewFindV("1", 1, context, this);
    }

    @Override
    protected void initEvents() {

    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        EventBus.getDefault().post(new NormalEventBean("jieshu"));
        Gson gson = GsonSingle.getGson();
        BaseBean baseBeans = gson.fromJson(stringResult, BaseBean.class);
        if (!baseBeans.getResult().equals("1")) {
            ToastUtils.show(context, baseBeans.getMsg());
            return;
        }
        NewFindBean baseBean = gson.fromJson(stringResult, NewFindBean.class);
        switch (resultCode) {
            case 1:
                if (baseBean != null && baseBean.getResult() == 1) {
                    list.clear();
                    list.addAll(baseBean.getData().getAllfind());
                    allAdapter.setList(list);
                }
                break;
            case 2:
                if (baseBean != null && baseBean.getResult() == 1) {
                    list.addAll(baseBean.getData().getAllfind());
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
    public void onLoadmore(RefreshLayout refreshlayout) {
        progressDialog.show();
        page++;
        TurnClassHttp.getNewFindV(String.valueOf(page), 2, context, this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_top:
                rv_all.scrollToPosition(0);
                break;

        }
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
        }
        if (messageEvent.getMessageCode().equals("shuaxin")){
            //progressDialog.show();
            page = 1;
            TurnClassHttp.getNewFindV("1", 1, context, this);
        }
    }
}
