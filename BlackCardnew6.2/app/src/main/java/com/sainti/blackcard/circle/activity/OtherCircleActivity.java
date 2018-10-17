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
import com.sainti.blackcard.circle.adapter.OtherCircleAdapter;
import com.sainti.blackcard.circle.bean.BaseFollowBean;
import com.sainti.blackcard.circle.bean.OtherFindBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class OtherCircleActivity extends BaseTitleActivity implements View.OnClickListener, OnLoadmoreListener, OnRefreshListener, OnNetResultListener {
    private LoadingView P;
    private ImageView iv_photo, iv_kind;
    private TextView tv_guanzh, tv_isF, tv_name, tv_jianjie, tv_publishnum, tv_fans, tv_fan;
    private RecyclerView rc_mineC;
    private OtherFindBean otherFindBean;
    private OtherCircleAdapter otherCircleAdapter;
    private SmartRefreshLayout sm_fans;
    private List<OtherFindBean.DataBean.FindlistBean> list;
    int page = 1;
    private Gson gson;
    private BaseFollowBean baseFollowBean;
    private ArrayList<String> lists;

    @Override
    public int setLayout() {
        return R.layout.activity_other_circle;
    }

    @Override
    protected void initView() {
        iv_photo = bindView(R.id.iv_photo);
        iv_kind = bindView(R.id.iv_kind);
        tv_name = bindView(R.id.tv_name);
        tv_jianjie = bindView(R.id.tv_jianjie);
        tv_publishnum = bindView(R.id.tv_publishnum);
        rc_mineC = bindView(R.id.rc_mineC);
        tv_fans = bindView(R.id.tv_fans);
        rc_mineC = bindView(R.id.rc_mineC);
        tv_isF = bindView(R.id.tv_isF);
        tv_guanzh = bindView(R.id.tv_guanzh);
        tv_fan = bindView(R.id.tv_fan);
        sm_fans = bindView(R.id.sm_fans);

    }

    @Override
    protected void initEvent() {
        tv_guanzh.setOnClickListener(this);
        tv_fan.setOnClickListener(this);
        tv_fans.setOnClickListener(this);
        sm_fans.setOnLoadmoreListener(this);
        sm_fans.setOnRefreshListener(this);
        iv_photo.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        setTitle("TA的圈子");
        P = new LoadingView(this);
        P.show();
        rc_mineC.setLayoutManager(new LinearLayoutManager(this));
        otherCircleAdapter = new OtherCircleAdapter(this);
        rc_mineC.setAdapter(otherCircleAdapter);
        TurnClassHttp.userFindlist(getIntent().getStringExtra("uid"), "1", 1, this, this);
        lists = new ArrayList<>();

    }

    private void lookPhoto() {
        PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
        intent.setCurrentItem(0);
        intent.setPhotoPaths(lists);
        intent.setCode(1);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_guanzh:
                isFollow();
                break;
            case R.id.tv_fan:
            case R.id.tv_fans:
                Intent intents = new Intent(this, FansActivity.class);
                intents.putExtra("uid", getIntent().getStringExtra("uid"));
                startActivity(intents);
                break;
            case R.id.iv_photo:
                lookPhoto();
                break;
        }
    }

    private void setDate() {

    }

    /* 关注取消关注的逻辑*/
    private void isFollow() {
        String id;
        String uid2 = getIntent().getStringExtra("uid");
        String idF = tv_isF.getText().toString();
        id = "";
        if (idF.equals("未")) {
            id = "1";
            tv_isF.setText("已");
            tv_guanzh.setBackgroundResource(R.mipmap.follow);

        }
        if (idF.equals("已")) {
            id = "2";
            tv_isF.setText("未");
            tv_guanzh.setBackgroundResource(R.mipmap.jiaguanzhu);
        }
        TurnClassHttp.isFollow(uid2, id, 3, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        P.show();
        page++;
        TurnClassHttp.userFindlist(getIntent().getStringExtra("uid"), String.valueOf(page), 2, this, this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        //   P.show();
        TurnClassHttp.userFindlist(getIntent().getStringExtra("uid"), "1", 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        baseFollowBean = gson.fromJson(stringResult, BaseFollowBean.class);
        switch (resultCode) {
            case 1:

                if (baseFollowBean.getResult().equals("1") && baseFollowBean != null && baseFollowBean.getData().getFindlist().size() == 0) {
                    lists.add(baseFollowBean.getData().getHead());
                    GlideManager.getsInstance().loadImageToUrLCircle(this, baseFollowBean.getData().getHead(), iv_photo);
                    GlideManager.getsInstance().loadImageToUrL(this, baseFollowBean.getData().getCert_pic(), iv_kind);
                    tv_name.setText(baseFollowBean.getData().getUser_nick());
                    tv_jianjie.setText("个人简介: " + baseFollowBean.getData().getIntroinfo());
                    tv_publishnum.setText(baseFollowBean.getData().getPublishnum());
                    tv_fans.setText(baseFollowBean.getData().getFans());
                    String follow = baseFollowBean.getData().getIsfllow();
                    if (follow.equals("0")) {
                        tv_isF.setText("未");
                        tv_guanzh.setBackgroundResource(R.mipmap.jiaguanzhu);

                    }
                    if (follow.equals("1")) {
                        tv_isF.setText("已");
                        tv_guanzh.setBackgroundResource(R.mipmap.follow);

                    }
                }
                if (baseFollowBean.getResult().equals("1") && baseFollowBean.getData().getFindlist() != null && baseFollowBean.getData().getFindlist().size() > 0) {
                    otherFindBean = gson.fromJson(stringResult, OtherFindBean.class);
                    lists.add(baseFollowBean.getData().getHead());
                    list.clear();
                    list.addAll(otherFindBean.getData().getFindlist());
                    otherCircleAdapter.setList(list);
                    GlideManager.getsInstance().loadImageToUrLCircle(this, otherFindBean.getData().getHead(), iv_photo);
                    GlideManager.getsInstance().loadImageToUrL(this, otherFindBean.getData().getCert_pic(), iv_kind);
                    tv_name.setText(otherFindBean.getData().getUser_nick());
                    tv_jianjie.setText("个人简介: " + otherFindBean.getData().getIntroinfo());
                    tv_publishnum.setText(otherFindBean.getData().getPublishnum());
                    tv_fans.setText(otherFindBean.getData().getFans());
                    String follow = otherFindBean.getData().getIsfllow();
                    if (follow.equals("0")) {
                        tv_isF.setText("未");
                        tv_guanzh.setBackgroundResource(R.mipmap.jiaguanzhu);

                    }
                    if (follow.equals("1")) {
                        tv_isF.setText("已");
                        tv_guanzh.setBackgroundResource(R.mipmap.follow);

                    }
                }

                break;
            case 2:
                if (otherFindBean != null && otherFindBean.getResult().equals("1")) {
                    otherFindBean = gson.fromJson(stringResult, OtherFindBean.class);
                    list.addAll(otherFindBean.getData().getFindlist());
                    otherCircleAdapter.setList(list);
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
        sm_fans.finishLoadmore();
    }
}
