package com.sainti.blackcard.privilege.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.privilege.adapter.TixainAdapter;
import com.sainti.blackcard.privilege.bean.TixianBean;

public class TixianjiluActivity extends BaseTitleActivity implements OnNetResultListener {


    private RecyclerView rv_tixian;
    private TixainAdapter tixainAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_tixianjilu;
    }

    @Override
    protected void initView() {
        rv_tixian = bindView(R.id.rv_tixian);
    }

    @Override
    protected void initEvent() {
        rv_tixian.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        setTitle("交易记录");
        TurnClassHttp.topup_record(1, this, this);
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tixainAdapter = new TixainAdapter(R.layout.tx_item);
        tixainAdapter.setContext(this);
        rv_tixian.setAdapter(tixainAdapter);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        TixianBean tixianBean = gson.fromJson(stringResult, TixianBean.class);
        if (tixianBean.getResult().equals("1") && tixianBean.getData() != null && tixianBean.getData().size() > 0) {
            tixainAdapter.setNewData(tixianBean.getData());
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }
}
