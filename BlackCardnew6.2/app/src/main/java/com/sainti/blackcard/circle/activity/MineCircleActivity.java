package com.sainti.blackcard.circle.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.MineCircleAdapter;
import com.sainti.blackcard.circle.bean.MineBaseBean;
import com.sainti.blackcard.circle.bean.MineFindBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
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

public class MineCircleActivity extends BaseTitleActivity implements View.OnClickListener, OnLoadmoreListener, OnRefreshListener, OnNetResultListener, BaseTitleActivity.OnClickRightIcon1CallBack {
    private LoadingView P;
    private TextView tv_name, tv_fans, tv_follow, tv_publishnum, tv_jianjie, tv_huanzhu, tv_fan;
    private ImageView iv_photo, iv_kind;
    private RecyclerView rc_mineC;
    private MineFindBean mineFindBean;
    private MineCircleAdapter mineCircleAdapter;
    private SmartRefreshLayout sm_fans;
    private List<MineFindBean.DataBean.FindlistBean> list;
    int page = 1;
    private Gson gson;
    private MineBaseBean mineBaseBean;
    private ArrayList<String> listss;

    @Override
    public int setLayout() {
        return R.layout.activity_mine_circle;
    }

    @Override
    protected void initView() {
        tv_name = bindView(R.id.tv_name);
        iv_kind = bindView(R.id.iv_kind);
        tv_fans = bindView(R.id.tv_fans);
        tv_follow = bindView(R.id.tv_follow);
        tv_publishnum = bindView(R.id.tv_publishnum);
        iv_photo = bindView(R.id.iv_photo);
        tv_jianjie = bindView(R.id.tv_jianjie);
        rc_mineC = bindView(R.id.rc_mineC);
        tv_huanzhu = bindView(R.id.tv_huanzhu);
        tv_fan = bindView(R.id.tv_fan);
        sm_fans = bindView(R.id.sm_fans);
    }

    @Override
    protected void initEvent() {
        tv_huanzhu.setOnClickListener(this);
        tv_fan.setOnClickListener(this);
        tv_fans.setOnClickListener(this);
        tv_huanzhu.setOnClickListener(this);
        tv_follow.setOnClickListener(this);
        sm_fans.setOnLoadmoreListener(this);
        sm_fans.setOnRefreshListener(this);
        iv_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_huanzhu:
            case R.id.tv_follow:
                Intent intent = new Intent(this, MyFollowActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_fan:
            case R.id.tv_fans:
                Intent intents = new Intent(this, FansActivity.class);
                String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
                intents.putExtra("uid", uid);
                startActivity(intents);
                break;
            case R.id.iv_photo:
                lookPhoto();
                break;
        }
    }

    private void lookPhoto() {
        PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
        intent.setCurrentItem(0);
        intent.setPhotoPaths(listss);
        intent.setCode(1);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        setTitle("我的圈子");
        P = new LoadingView(this);
        P.show();
        rc_mineC.setLayoutManager(new LinearLayoutManager(this));
        mineCircleAdapter = new MineCircleAdapter(this);
        rc_mineC.setAdapter(mineCircleAdapter);
        TurnClassHttp.myFindlist("1", 1, this, this);
        listss = new ArrayList<>();

        setBaseRightIcon1(R.mipmap.mine_ci,this);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        P.show();
        page++;
        TurnClassHttp.myFindlist(String.valueOf(page), 2, this, this);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        //  P.show();
        TurnClassHttp.myFindlist("1", 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        mineBaseBean = gson.fromJson(stringResult, MineBaseBean.class);
        switch (resultCode) {
            case 1:

                if (mineBaseBean != null && mineBaseBean.getResult().equals("1")) {
                    listss.add(mineBaseBean.getData().getHead());
                    GlideManager.getsInstance().loadImageToUrLCircle(this, mineBaseBean.getData().getHead(), iv_photo);
                    GlideManager.getsInstance().loadImageToUrL(this, mineBaseBean.getData().getCert_pic(), iv_kind); // 等待确认
                    tv_name.setText(mineBaseBean.getData().getUser_nick());
                    tv_jianjie.setText("个人简介: " + mineBaseBean.getData().getIntroinfo());
                    tv_publishnum.setText(mineBaseBean.getData().getPublishnum());
                    tv_fans.setText(mineBaseBean.getData().getFans());
                    tv_follow.setText(mineBaseBean.getData().getFollow());
                    list.clear();
                    if (mineBaseBean.getData().getFindlist() != null && mineBaseBean.getData().getFindlist().size() > 0) {
                        mineFindBean = gson.fromJson(stringResult, MineFindBean.class);
                        list.addAll(mineFindBean.getData().getFindlist());
                        mineCircleAdapter.setList(list);
                    } else {
                        sm_fans.setEnableLoadmore(false);
                    }

                }

                break;
            case 2:
                if (mineFindBean != null && mineFindBean.getResult().equals("1")) {
                    mineFindBean = gson.fromJson(stringResult, MineFindBean.class);
                    list.addAll(mineFindBean.getData().getFindlist());
                    mineCircleAdapter.setList(list);
                }
                P.dismiss();
                sm_fans.finishLoadmore();
                break;
        }
        P.dismiss();
        sm_fans.finishRefresh();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        P.dismiss();
        sm_fans.finishRefresh();
        ToastUtils.show(this, "请求服务器失败");
        switch (resultCode) {
            case 1:
                break;
            case 2:
                sm_fans.finishLoadmore();
                break;
        }
    }

    @Override
    public void clickRightIcon1() {
        Intent msgIntent = new Intent(this, MessageActivity.class);
        msgIntent.putExtra("code","1");
        // iv_red.setVisibility(View.INVISIBLE);
        startActivity(msgIntent);
    }
}
