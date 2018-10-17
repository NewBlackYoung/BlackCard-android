package com.sainti.blackcard.goodthings.activity;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.goodthings.bean.GetKeyCountBean;
import com.sainti.blackcard.goodthings.bean.HaoWuZhIFuBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.goodtingsorder.activity.GoodThingOsderActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.mwebview.primary.WxBean;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class HaoWuZhiFuActivity extends BaseTitleActivity implements View.OnClickListener, OnNetResultListener, Malipay.OnAlipayListener, TimerListener, PayResultListener {

    private TextView price, tv_biaoti, tv_shuliang, tv_fenlei, tv_keycount;
    private CheckBox weixin, zhifubao, yuezhifu;
    private ImageView iv_chanpin, iv_back_home, iv_look_order;
    private TextView commit;
    private String ordernum;
    private String orderName;
    private String url;
    private String count;
    private String money;
    private Context context;
    private String fenlei;;
    private RelativeLayout lay_yue;
    private RelativeLayout ll_weixin;
    private RelativeLayout lay_zhifubao;
    private String payCode;
    private String orderId;
    private Gson gson;
    private Malipay malipay;
    private LinearLayout ll_paysucess;
    private LoadingView loadingView;

    @Override
    public int setLayout() {
        return R.layout.activity_hao_wu_zhi_fu;
    }

    @Override
    protected void initView() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // deviceID = tm.getDeviceId();
        price = (TextView) findViewById(R.id.price);
        commit = (TextView) findViewById(R.id.commit);
        tv_biaoti = (TextView) findViewById(R.id.tv_biaoti);
        tv_shuliang = (TextView) findViewById(R.id.tv_shuliang);
        iv_chanpin = (ImageView) findViewById(R.id.iv_chanpin);
        tv_fenlei = (TextView) findViewById(R.id.tv_fenlei);
        // registerBoradcastReceiver();
        weixin = (CheckBox) findViewById(R.id.weixin); // 微信支付的check
        weixin.setClickable(false);
        zhifubao = (CheckBox) findViewById(R.id.zhifubao);// 支付宝支付的check
        zhifubao.setClickable(false);
        yuezhifu = (CheckBox) findViewById(R.id.yuezhifu); // 余额支付的check
        yuezhifu.setClickable(false);
        lay_yue = (RelativeLayout) findViewById(R.id.lay_yue);
        lay_yue.setOnClickListener(this);
        ll_weixin = (RelativeLayout) findViewById(R.id.ll_weixin);
        ll_weixin.setOnClickListener(this);
        lay_zhifubao = (RelativeLayout) findViewById(R.id.lay_zhifubao);
        lay_zhifubao.setOnClickListener(this);
        EventBus.getDefault().register(this);
        context = this;
        malipay = new Malipay(this,this);
        ll_paysucess = bindView(R.id.ll_paysucess);
        tv_keycount = bindView(R.id.tv_keycount);
        iv_back_home = bindView(R.id.iv_back_home);
        iv_look_order = bindView(R.id.iv_look_order);
        loadingView = new LoadingView(this);
    }

    @Override
    protected void initEvent() {
        iv_back_home.setOnClickListener(this);
        iv_look_order.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("好物订单支付");
        yuezhifu.setChecked(true);
        weixin.setChecked(false);
        zhifubao.setChecked(false);
        // 余额
        payCode = "0";
        ordernum = getIntent().getStringExtra("ordernum");// 订单号
        money = getIntent().getStringExtra("money");
        //money = 0.01;
        orderName = getIntent().getStringExtra("orderName");
        url = getIntent().getStringExtra("url");
        count = getIntent().getStringExtra("count");
        fenlei = getIntent().getStringExtra("fenlei");
        orderId = getIntent().getStringExtra("dingdanid");
        GlideManager.getsInstance().loadImageToUrL(this, url, iv_chanpin);
        tv_biaoti.setText(orderName);
        tv_shuliang.setText(count);
        tv_fenlei.setText(fenlei);
        price.setText(money);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payCode.equals("0")) {  // 余额
                    yueZhifu();
                }
                if (payCode.equals("1")) {// 微信
                    // ToastUtil.showToast(HaoWuZhiFuActivity.this, "微信支付");
                    weixinPay();
                }
                if (payCode.equals("2")) {// 支付宝
                    zhifuaopay();
                }
            }
        });

        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("haowuname", orderName);
        MobclickAgent.onEvent(context, "haowuzhifu", map_ekv0);// 友盟统计


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_yue:
                yuezhifu.setChecked(true);
                weixin.setChecked(false);
                zhifubao.setChecked(false);
                // 余额
                payCode = "0";
                break;
            case R.id.ll_weixin:
                yuezhifu.setChecked(false);
                weixin.setChecked(true);
                zhifubao.setChecked(false);
                payCode = "1";// 微信
                break;
            case R.id.lay_zhifubao:
                yuezhifu.setChecked(false);
                weixin.setChecked(false);
                zhifubao.setChecked(true);
                payCode = "2";// 支付宝
                break;
            case R.id.iv_back_home:
                EventBus.getDefault().post(new NormalEventBean("88888"));
                finish();
                break;
            case R.id.iv_look_order: /* 去查看订单界面*/
                EventBus.getDefault().post(new NormalEventBean("88888"));
                CommontUtil.lateTime(1500, this);
                break;
        }
    }


    private void yueZhifu() {
        TurnClassHttp.pingan_pay(ordernum, 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

        gson = GsonSingle.getGson();
        BaseBean baseBean = gson.fromJson(stringResult,BaseBean.class);
        if (baseBean.getResult().equals("0")){
            ToastUtils.show(this,baseBean.getMsg());
            return;
        }
        switch (resultCode) {
            case 1:

                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(HaoWuZhiFuActivity.this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "2");// 2 好物支付
                    startActivity(intent);

                }
                break;
            case 2:
                HaoWuZhIFuBean fuBean = gson.fromJson(stringResult, HaoWuZhIFuBean.class);
                if (fuBean != null) {
                    malipay.pay(fuBean.getData());
                }
                break;
            case 3:
                WxBean wxPayBean = gson.fromJson(stringResult, WxBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    WachatPay.getInstance(this).onSendTOWx(wxPayBean.getData(),this);
                    //onSendTOWx(wxPayBean.getData());
                }
                break;
            case 4:
                GetKeyCountBean countBean = gson.fromJson(stringResult, GetKeyCountBean.class);
                String count = countBean.getBlackkey();
                if (count.equals("0.00")) {
                    tv_keycount.setVisibility(View.INVISIBLE);
                } else {
                    tv_keycount.setVisibility(View.VISIBLE);
                    tv_keycount.setText("恭喜您获得黑钥匙" + count + "把");
                }
                commit.setVisibility(View.GONE);
                ll_paysucess.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        //loadingView.dismiss();
        switch (resultCode) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                ToastUtils.show(HaoWuZhiFuActivity.this, "生成订单信息错误");
                break;
        }
    }

    private void zhifuaopay() {
        if (ordernum != null) {
            TurnClassHttp.getHaoWuDanHao(orderName, orderName, ordernum, money, 2, this, this);
        }
    }

    private void weixinPay() {
        double lastPrcies = ConvertUtil.convertToDouble(money, 0.00) * 100;
        int a = (int) lastPrcies;
        TurnClassHttp.getApporder(a + "", ordernum, orderName, "android", ConvertUtil.getTime(), 3, this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /*支付宝支付回调*/
    @Override
    public void onSuccess() {
        TurnClassHttp.getBlackkey(ordernum, "haowu", 4, this, this);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onCancel() {
        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
        Toast.makeText(HaoWuZhiFuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWait() {

    }



    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSHAOWU)) {
            finish();
        }
    }

    @Override
    public void onTimerListener() {
        Intent intent = new Intent(HaoWuZhiFuActivity.this, GoodThingOsderActivity.class);
        startActivity(intent);
        EventBus.getDefault().post(new NormalEventBean("77777"));
        finish();
    }

/*微信支付回调*/
    @Override
    public void onPaySuccess() {

        TurnClassHttp.getBlackkey(ordernum, "haowu", 4, this, this);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "weixin");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onPayError() {
        Toast.makeText(HaoWuZhiFuActivity.this, "支付失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPayCancel() {
        Toast.makeText(HaoWuZhiFuActivity.this, "取消支付", Toast.LENGTH_LONG).show();
    }
}
