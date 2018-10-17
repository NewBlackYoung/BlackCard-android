package com.sainti.blackcard.circle.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.MessageAdapter;
import com.sainti.blackcard.circle.bean.MessageBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseTitleActivity implements OnLoadmoreListener, OnNetResultListener {
    private RecyclerView rv_fans;
    private LoadingView dialog;
    private SmartRefreshLayout sm_fans;
    private List<MessageBean.DataBean> list;
    int page = 1;
    private TextView tv_count, tv_k;
    private MessageBean messageBean;
    private MessageAdapter messageAdapter;
    private String code;

    @Override
    public int setLayout() {
        return R.layout.activity_message2;
    }

    @Override
    protected void initView() {
        rv_fans = bindView(R.id.rv_fans);
        sm_fans = bindView(R.id.sm_fans);
        tv_count = bindView(R.id.tv_count);
        tv_k = bindView(R.id.tv_k);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("圈子消息");
        dialog = new LoadingView(this);
        sm_fans.setEnableRefresh(false);
        list = new ArrayList<>();
        code = getIntent().getStringExtra("code");
        TurnClassHttp.getHuDong(code,"1", "0", 1, this, this);
        dialog.show();
        messageAdapter = new MessageAdapter(this);
        rv_fans.setLayoutManager(new LinearLayoutManager(this));
        rv_fans.setAdapter(messageAdapter);
        sm_fans.setOnLoadmoreListener(this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        dialog.show();
        page++;
        TurnClassHttp.getHuDong(code, String.valueOf(page), "0", 2, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        messageBean = gson.fromJson(stringResult, MessageBean.class);
        switch (resultCode) {
            case 1:
                if (messageBean != null && messageBean.getResult().equals("1") && !messageBean.getUnreadcount().equals("0")) {
                    list.clear();
                    list.addAll(messageBean.getData());
                    if (code.equals("0")){
                        tv_count.setVisibility(View.VISIBLE);
                        tv_count.setText(messageBean.getUnreadcount() + "条未读消息");

                    }
                    messageAdapter.setList(list);
                } else {
                    if (code.equals("0")){
                        tv_k.setVisibility(View.VISIBLE);
                    }

                }
                dialog.dismiss();
                break;
            case 2:
                list.addAll(messageBean.getData());
                messageAdapter.setList(list);
                sm_fans.finishLoadmore();
                break;
        }
        dialog.dismiss();
        sm_fans.finishLoadmore();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }
}
