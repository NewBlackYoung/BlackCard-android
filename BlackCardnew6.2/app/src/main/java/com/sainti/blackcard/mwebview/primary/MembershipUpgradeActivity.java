package com.sainti.blackcard.mwebview.primary;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.PayListenerUtils;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.widget.CommonPopupWindow;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class MembershipUpgradeActivity extends BaseTitleActivity implements OnNetResultListener, CommonPopupWindow.ViewInterface, View.OnClickListener, Malipay.OnAlipayListener, PayResultListener {


    private TextView shenmfen, nextshenfen, feiyong;
    private CommonPopupWindow commonPopupWindow;
    private TextView tv_gopay, card_sn_split, user_piny;
    private RelativeLayout lay_lay;
    private LoadingView loadingView;
    private String level_id;
    private String price;
    private String order_sn;
    private ZhifubaoPayBean zhifubaoPayBean;
    private Malipay malipay;

    @Override
    public int setLayout() {
        return R.layout.activity_membership_upgrade;
    }

    @Override
    protected void initView() {
        loadingView = new LoadingView(this);
        shenmfen = bindView(R.id.shenmfen);
        nextshenfen = bindView(R.id.nextshenfen);
        feiyong = bindView(R.id.feiyong);
        tv_gopay = bindView(R.id.tv_gopay);
        lay_lay = bindView(R.id.lay_lay);
        malipay = new Malipay(this);
        malipay.setmListener(this);
        //PayListenerUtils.getInstance(this).addListener(this);
        //WachatPay.getInstance(this).addListener(this);
        card_sn_split = bindView(R.id.card_sn_split);
        user_piny = bindView(R.id.user_piny);
    }

    @Override
    protected void
    initEvent() {
        tv_gopay.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        setTitle("会籍介绍");
        loadingView.show();
        TurnClassHttp.levelUp(1, this, this);
        commonPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.view_popu_guanjia)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT).setAnimationStyle(R.style.popwin_liebiao_style).setViewOnclickListener(this).setOutsideTouchable(true).create();
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                LeveUpBean leveUpBean = GsonSingle.getGson().fromJson(stringResult, LeveUpBean.class);
                if (leveUpBean != null && leveUpBean.getData() != null && leveUpBean.getResult().equals("1")) {
                    shenmfen.setText("· 您当前的身份为 : " + leveUpBean.getData().getUser_type_name());
                    nextshenfen.setText("· 您要升级的会籍 : " + leveUpBean.getData().getLevelup_data().get(0).getType_name());
                    feiyong.setText("· 升级所需费用:  ¥" + leveUpBean.getData().getLevelup_data().get(0).getType_price());
                    level_id = leveUpBean.getData().getLevelup_data().get(0).getType_id();
                    price = leveUpBean.getData().getLevelup_data().get(0).getType_price();
                    card_sn_split.setText(leveUpBean.getData().getUser_tel());
                    user_piny.setText(leveUpBean.getData().getUser_piny());

                } else {
                    ToastUtils.show(this, leveUpBean.getMsg());
                }
                break;
            case 2:
                PayBean payBean = GsonSingle.getGson().fromJson(stringResult, PayBean.class);
                if (payBean != null && payBean.getResult().equals("1")) {
                    order_sn = payBean.getData();
                    commonPopupWindow.showPopAtPatentDown(lay_lay);
                } else {
                    ToastUtils.show(this, payBean.getMsg());
                }
                break;
            case 3:
                zhifubaoPayBean = GsonSingle.getGson().fromJson(stringResult, ZhifubaoPayBean.class);
                malipay.pay(zhifubaoPayBean.getData());
                break;
            case 4:
                PinganBean pinganBean = GsonSingle.getGson().fromJson(stringResult, PinganBean.class);
                Intent intent = new Intent(MembershipUpgradeActivity.this, PingAnPayWebView.class);
                intent.putExtra("xh_url", pinganBean.getPay_data());
                intent.putExtra("payCode", "4");// 升级支付
                startActivity(intent);
                break;
            case 5:
                WxBean wxBean = GsonSingle.getGson().fromJson(stringResult, WxBean.class);
                WachatPay.getInstance(this).onSendTOWx(wxBean.getData(),this);
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(this, errMsg);

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        RelativeLayout relativeLayout = bindView(view, R.id.lay_yue);
        RelativeLayout lay_weixin = bindView(view, R.id.lay_weixin);
        RelativeLayout lay_zhifubao = bindView(view, R.id.lay_zhifubao);
        lay_weixin.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        lay_zhifubao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_gopay:
                TurnClassHttp.levelUpOrder(level_id, price, 2, MembershipUpgradeActivity.this, this);
                loadingView.show();
                break;
            case R.id.lay_zhifubao:
                commonPopupWindow.dismiss();
                TurnClassHttp.getPayData(order_sn, "alipay", 3, MembershipUpgradeActivity.this, this);

                break;
            case R.id.lay_yue:
                TurnClassHttp.getPayData(order_sn, "blackcard", 4, MembershipUpgradeActivity.this, this);
                commonPopupWindow.dismiss();
                // ToastUtils.show(this, "余额");
                break;
            case R.id.lay_weixin:
                TurnClassHttp.getPayData(order_sn, "wechat", 5, MembershipUpgradeActivity.this, this);
                commonPopupWindow.dismiss();
                break;
        }
    }

    /*支付宝回调*/
    @Override
    public void onSuccess() {
        paySuc();
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    private void paySuc() {
        SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISFINISH, true).commit();
        ToastUtils.show(this, "升级成功");
        finish();
    }

    @Override
    public void onCancel() {
        ToastUtils.show(this, "支付失败");
    }

    @Override
    public void onWait() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PayListenerUtils.getInstance(this).removeListener(this);
    }

    /* 用于接收微信支付结果回调*/
    @Override
    public void onPaySuccess() {
        paySuc();
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "weixin");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onPayError() {
        ToastUtils.show(this, "支付失败");
    }

    @Override
    public void onPayCancel() {
        ToastUtils.show(this, "取消支付");
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean f = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISFINISH, false);
        if (f) {
            finish();
        }
    }
}
