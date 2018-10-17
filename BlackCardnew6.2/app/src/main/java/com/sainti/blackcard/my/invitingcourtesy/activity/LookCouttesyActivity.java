package com.sainti.blackcard.my.invitingcourtesy.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.my.invitingcourtesy.adapter.MyInvitAdapter;
import com.sainti.blackcard.my.invitingcourtesy.bean.AdressMorenBean;
import com.sainti.blackcard.my.invitingcourtesy.bean.MyInvitBean;
import com.sainti.blackcard.goodthings.goodtingsorder.bean.WuLiuBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.WuliuWebViewActivity;
import com.sainti.blackcard.widget.CommonPopupWindow;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class LookCouttesyActivity extends BaseNoTitleActivity implements View.OnClickListener, OnNetResultListener, CommonPopupWindow.ViewInterface {


    private ImageView iv_title_leftOne, iv_change;
    private LinearLayout tvToGuanjia;
    private TextView runshu, yao, canso, commint;
    private LoadingView loadingView;
    private RecyclerView rvInvitlist;
    private MyInvitAdapter myInvitAdapter;
    private MyInvitBean myInvitBean;
    private CommonPopupWindow commonPopupWindow;
    private EditText name, phone, sure_mima;

    @Override
    public int setLayout() {
        return R.layout.activity_look_couttesy;
    }

    @Override
    protected void initView() {
        iv_title_leftOne = bindView(R.id.iv_title_leftOne);
        tvToGuanjia = bindView(R.id.tv_toGuanjia);
        runshu = bindView(R.id.runshu);
        rvInvitlist = bindView(R.id.rv_invitlist);
        iv_change = bindView(R.id.iv_change);
        yao = bindView(R.id.yao);
    }

    @Override
    protected void initEvents() {
        loadingView = new LoadingView(this);
        iv_title_leftOne.setOnClickListener(this);
        tvToGuanjia.setOnClickListener(this);
        rvInvitlist.setLayoutManager(new LinearLayoutManager(this));
        rvInvitlist.setNestedScrollingEnabled(false);
        myInvitAdapter = new MyInvitAdapter(R.layout.item_mayinvit);
        rvInvitlist.setAdapter(myInvitAdapter);
        iv_change.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.myInvite("1", "5", "1", 1, this, this);
        commonPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.view_tiajiadizhi)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT).setAnimationStyle(R.style.popwin_liebiao_style).setViewOnclickListener(this).setOutsideTouchable(true).create();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_leftOne:
                finish();
                break;
            case R.id.tv_toGuanjia:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "tequanxaingqingye");
                    MobclickAgent.onEvent(LookCouttesyActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    //已经登录，可以直接进入会话界面
                    Intent intent = new IntentBuilder(this)
                            .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                            .build();
                    startActivity(intent);
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, false).commit();
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(this, "登陆失败");
                }
                break;
            case R.id.iv_change:
                if (!myInvitBean.getData().getWuliubtn().equals("0")) {
                    //  查看物流
                    loadingView.show();
                    TurnClassHttp.queryKdyao(myInvitBean.getData().getWuliubtn(), 2, this, this);
                } else {
                    if (myInvitBean.getData().getChangebtn().equals("1")) {
                        //  可以申请

                        TurnClassHttp.getDefaultAddress(4, this, this);

                    } else {
                        return;
                    }
                }
                break;
            case R.id.commint:
                String nameContent = name.getText().toString();
                String phoneContent = phone.getText().toString();
                String sure_mimaContent = sure_mima.getText().toString();
                if (nameContent.equals("")) {
                    showToast("请填写姓名");
                    return;
                }
                if (phoneContent.equals("")) {
                    showToast("请填写11位数手机号码");
                    return;
                }
                if (phoneContent.length() != 11) {
                    showToast("请填写11位数手机号码");
                    return;
                }
                if (sure_mimaContent.equals("")) {
                    showToast("请填写收货地址");
                    return;
                }
                loadingView.show();
                TurnClassHttp.inviteChange(nameContent, phoneContent, sure_mimaContent, 3, this, this);
                commonPopupWindow.dismiss();
                break;
            case R.id.canso:
                commonPopupWindow.dismiss();
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                myInvitBean = GsonSingle.getGson().fromJson(stringResult, MyInvitBean.class);
                myInvitAdapter.setNewData(myInvitBean.getData().getInvites());
                runshu.setText(myInvitBean.getData().getCount());
                if (!myInvitBean.getData().getWuliubtn().equals("0")) {
                    iv_change.setImageResource(R.mipmap.lookwuliu);
                } else {
                    if (myInvitBean.getData().getChangebtn().equals("1")) {
                        iv_change.setImageResource(R.mipmap.sheniqngyouli);
                    } else {
                        iv_change.setImageResource(R.mipmap.yaoqing_huise);
                    }
                }
                break;
            case 2:
                BaseBean baseBean = GsonSingle.getGson().fromJson(stringResult, BaseBean.class);
                if (!baseBean.getResult().equals("1")) {
                    showToast(baseBean.getMsg());
                    return;
                }
                WuLiuBean wuLiuBean = GsonSingle.getGson().fromJson(stringResult, WuLiuBean.class);
                Intent intents = new Intent(this, WuliuWebViewActivity.class);
                intents.putExtra("wel_url", wuLiuBean.getData());
                intents.putExtra("code", "");
                startActivity(intents);
                break;
            case 3:
                showToast("兑换成功！");
                TurnClassHttp.myInvite("1", "5", "1", 1, this, this);
                break;
            case 4:
                AdressMorenBean adressMorenBean = GsonSingle.getGson().fromJson(stringResult, AdressMorenBean.class);
                if (adressMorenBean.getData() != null) {
                    if (adressMorenBean.getData().getAd_user() != null) {
                        name.setText(adressMorenBean.getData().getAd_user());
                    }

                    if (adressMorenBean.getData().getAd_tel() != null) {
                        phone.setText(adressMorenBean.getData().getAd_tel());
                    }
                    if (adressMorenBean.getData().getAd_province() != null) {
                        sure_mima.setText(adressMorenBean.getData().getAd_province() + adressMorenBean.getData().getAd_city() + adressMorenBean.getData().getAd_area() + adressMorenBean.getData().getAd_address());
                    }
                }
                commonPopupWindow.showPopAtViewDown(yao);
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        canso = bindView(view, R.id.canso);
        commint = bindView(view, R.id.commint);
        name = bindView(view, R.id.name);
        phone = bindView(view, R.id.phone);
        sure_mima = bindView(view, R.id.sure_mima);
        commint.setOnClickListener(this);
        canso.setOnClickListener(this);
    }
}
