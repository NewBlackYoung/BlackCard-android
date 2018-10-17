package com.sainti.blackcard.chat.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.helpdesk.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.chat.bean.WxPayBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DaShangActivity extends BaseTitleActivity implements View.OnClickListener, OnNetResultListener {


    private EditText id_money, ed_content;
    private TextView tv_money, tv_commint;
    private LoadingView loadingView;
    private String moey;
    private String neme;
    private String number;
    private IWXAPI mWxApi;

    @Override
    public int setLayout() {
        return R.layout.activity_s;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mWxApi = WXAPIFactory.createWXAPI(this, "wx343550af32846843");
        id_money = bindView(R.id.id_money);
        ed_content = bindView(R.id.ed_content);
        tv_money = bindView(R.id.tv_money);
        tv_commint = bindView(R.id.tv_commint);
        loadingView = new LoadingView(this);
        tv_commint.setClickable(false);
        id_money.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    moey = id_money.getText().toString();
                    if (moey != null && !moey.equals("")) {
                        tv_money.setText("¥" + id_money.getText().toString() + "元");
                        tv_commint.setClickable(true);

                    } else {
                        tv_money.setText("¥" + "0.00" + "元");
                        tv_commint.setClickable(false);
                    }


                }
            }
        });

    }

    @Override
    protected void initEvent() {
        tv_commint.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("打赏管家");
        neme = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_commint:
                if (moey == null || moey.equals("")) {
                    ToastUtils.show(this, "请输入打赏金额");
                    return;
                }
                String shouming = ed_content.getText().toString().trim();
                if (shouming == null || shouming.equals("")) {
                    ToastUtils.show(this, "请填写备注");
                    return;

                }

                long timeStamp = System.currentTimeMillis();
                loadingView.show();
                TurnClassHttp.reward(neme, moey, shouming, "android", timeStamp + "", "wechat", number, 1, this, this);
                //  ToastUtils.show(this, "提交");
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        WxPayBean wxPayBean = GsonSingle.getGson().fromJson(stringResult, WxPayBean.class);
        if (wxPayBean.getResult().equals("1")) {
            onSendTOWx(wxPayBean.getData());
        }
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
        loadingView.dismiss();
    }

    /**
     * 微信请求app服务器得到的回调结果
     */
    public void onSendTOWx(WxPayBean.DataBean dataBean) {

        if (mWxApi != null) {
            PayReq req = new PayReq();
            req.appId = dataBean.getAppid();// 微信开放平台审核通过的应用APPID
            req.partnerId = dataBean.getPartnerid();// 微信支付分配的商户号
            req.prepayId = dataBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
            req.nonceStr = dataBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
            req.timeStamp = dataBean.getTimestamp();// 时间戳，app服务器小哥给出
            req.packageValue = dataBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
            req.sign = dataBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            boolean sendFlag = mWxApi.sendReq(req);
            if (!sendFlag) {
                ToastUtils.show(this, "不能进行微信支付，请检查是否安装微信。");
            }
        }
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {


        // 支付成功
        if (messageEvent.getMessageCode().equals("0")) {
            ToastUtils.show(this, "打赏管家成功！");
            finish();

        }
        // 取消支付
        if (messageEvent.getMessageCode().equals("-2")) {
            Toast.makeText(DaShangActivity.this, "取消打赏", Toast.LENGTH_LONG).show();
        }
        // 支付失败
        if (messageEvent.getMessageCode().equals("1")) {
            Toast.makeText(DaShangActivity.this, "支付失败", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
