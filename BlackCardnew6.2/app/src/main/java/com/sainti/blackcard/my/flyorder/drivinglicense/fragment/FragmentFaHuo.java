package com.sainti.blackcard.my.flyorder.drivinglicense.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.my.flyorder.drivinglicense.adapter.AllAdapter;
import com.sainti.blackcard.my.flyorder.drivinglicense.bean.DrivingCommentBena;
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

/**
 * Created by YuZhenpeng on 2018/7/3.
 */

public class FragmentFaHuo extends BaseFragment implements OnNetResultListener, OnRefreshListener, OnLoadmoreListener {

    private LoadingView loadingView;
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_gooot;
    int page = 1;
    private List<DrivingCommentBena.DataBean> list;
    private DrivingCommentBena commentBena;
    private AllAdapter allAdapter;

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
        allAdapter = new AllAdapter(R.layout.item_guanjiacomment);
        allAdapter.setContext(context);
        rv_gooot.setAdapter(allAdapter);
        refresh_lay.setOnRefreshListener(this);
        refresh_lay.setOnLoadmoreListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        TurnClassHttp.myDriverOrder("yfh", "1", "10", 1, context, this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                commentBena = GsonSingle.getGson().fromJson(stringResult, DrivingCommentBena.class);
                list.clear();
                list.addAll(commentBena.getData());
                allAdapter.setNewData(list);
                break;
            case 2:
                commentBena = GsonSingle.getGson().fromJson(stringResult, DrivingCommentBena.class);
                list.addAll(commentBena.getData());
                allAdapter.setNewData(list);
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        // loadingView.show();
        TurnClassHttp.guanjiaorder("yfh", "1", 1, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        //loadingView.dismiss();
        TurnClassHttp.guanjiaorder("yfh", String.valueOf(page), 2, context, this);
    }
}