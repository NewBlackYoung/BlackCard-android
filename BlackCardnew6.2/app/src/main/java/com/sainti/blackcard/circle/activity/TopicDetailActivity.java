package com.sainti.blackcard.circle.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.circle.adapter.ToppicDetailAdapter;
import com.sainti.blackcard.circle.bean.TopDetailBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.circle.releasecircle.activity.ReleaseCircleActivity;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicDetailActivity extends BaseNoTitleActivity implements View.OnClickListener, OnRefreshListener, OnLoadmoreListener, OnNetResultListener {

    private LoadingView progressDialog;
    int page = 1;
    private ImageView to_pic, back;
    private TextView to_name;
    private RecyclerView rv_all;
    private SmartRefreshLayout refresh_lay;
    private ToppicDetailAdapter toppicDetailAdapter;
    private List<TopDetailBean.DataBean.FindlistBean> list;
    private ImageView iv_tofa;

    @Override
    public int setLayout() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void initView() {
        to_pic = bindView(R.id.to_pic);
        back = bindView(R.id.back);
        to_name = bindView(R.id.to_name);
        refresh_lay = bindView(R.id.refresh_lay);
        rv_all = bindView(R.id.rv_all);
        iv_tofa = bindView(R.id.iv_tofa);
    }

    @Override
    protected void initEvents() {
        back.setOnClickListener(this);
        iv_tofa.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        list = new ArrayList<>();
        progressDialog = new LoadingView(this);
        progressDialog.show();
        toppicDetailAdapter = new ToppicDetailAdapter(this);
        rv_all.setLayoutManager(new LinearLayoutManager(this));
        refresh_lay.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refresh_lay.setOnRefreshListener(this);
        refresh_lay.setOnLoadmoreListener(this);
        rv_all.setAdapter(toppicDetailAdapter);
        TurnClassHttp.topicDetail(getIntent().getStringExtra("to_id"), "1", 1, this, this);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("huatiname", getIntent().getStringExtra("to_id"));
        MobclickAgent.onEvent(this, "huatiliulan", map_ekv0);// 友盟统计


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_tofa:
                Intent intent1 = new Intent(this, ReleaseCircleActivity.class);
                intent1.putExtra("to_name", to_name.getText().toString());
                intent1.putExtra("to_id", getIntent().getStringExtra("to_id"));
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        progressDialog.show();
        page = 1;
        TurnClassHttp.topicDetail(getIntent().getStringExtra("to_id"), "1", 1, this, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
     //   progressDialog.show();
        page++;
        TurnClassHttp.topicDetail(getIntent().getStringExtra("to_id"), String.valueOf(page), 2, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        TopDetailBean topDetailBean = gson.fromJson(stringResult, TopDetailBean.class);
        switch (resultCode) {
            case 1:
                if (topDetailBean != null && topDetailBean.getResult() == 1) {
                    GlideManager.getsInstance().loadImageToUrL(this, topDetailBean.getData().getTopicinfo().get(0).getTo_pic(), to_pic);
                    to_name.setText(topDetailBean.getData().getTopicinfo().get(0).getTo_name());
                    list.clear();
                    list.addAll(topDetailBean.getData().getFindlist());
                    toppicDetailAdapter.setList(list);
                }
                break;
            case 2:
                if (topDetailBean != null && topDetailBean.getResult() == 1) {
                    list.addAll(topDetailBean.getData().getFindlist());
                    toppicDetailAdapter.setList(list);
                }
                refresh_lay.finishLoadmore();
                break;
        }

        progressDialog.dismiss();
        refresh_lay.finishRefresh();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        progressDialog.dismiss();
        refresh_lay.finishRefresh();
        refresh_lay.finishLoadmore();
        ToastUtils.show(this, "请求服务器失败");
    }
    @Override
    public void onPause() {
        super.onPause();

    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}
