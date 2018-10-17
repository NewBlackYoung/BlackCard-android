package com.sainti.blackcard.mblcakcard.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mblcakcard.adapter.FriendListAdapter;
import com.sainti.blackcard.mblcakcard.bean.FriendBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;

public class FriendListActivity extends BaseTitleActivity implements View.OnClickListener, OnNetResultListener {
    private ImageView id_addf;
    private LoadingView loadingView;
    private FriendBean friendBean;
    private RecyclerView rv_friendlist;
    private FriendListAdapter friendListAdapter;
    private int count = 0;


    @Override
    public int setLayout() {
        return R.layout.activity_friend_list;
    }

    @Override
    protected void initView() {
        id_addf = bindView(R.id.id_addf);
        loadingView = new LoadingView(this);
        rv_friendlist = bindView(R.id.rv_friendlist);
        rv_friendlist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initEvent() {
        id_addf.setOnClickListener(this);
        friendListAdapter = new FriendListAdapter(R.layout.item_friend);
        friendListAdapter.setContext(this);
        rv_friendlist.setAdapter(friendListAdapter);

    }

    @Override
    protected void initData() {
        setTitle("亲友绑定");
        loadingView.show();
        TurnClassHttp.getbangding(1, this, this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.id_addf:
                Intent intent = new Intent(this, AddFriendActivity.class);
                intent.putExtra("count", count + "");
                startActivityForResult(intent, 888);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 888 && resultCode == 999) {
            loadingView.show();
            TurnClassHttp.getbangding(1, this, this);

        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        friendBean = gson.fromJson(stringResult, FriendBean.class);
        friendListAdapter.setNewData(friendBean.getData());
        if (friendBean.getData() == null || friendBean.getData().size() == 0) {
            count = 1;
        } else {
            count = friendBean.getData().size() + 1;
        }
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
    }
}
