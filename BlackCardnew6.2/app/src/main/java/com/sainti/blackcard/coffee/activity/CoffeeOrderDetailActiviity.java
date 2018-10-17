package com.sainti.blackcard.coffee.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.adapter.OrderDetailAdapter;
import com.sainti.blackcard.coffee.adapter.TimeListAdapter;
import com.sainti.blackcard.coffee.bean.AdressBean;
import com.sainti.blackcard.coffee.bean.OrderDetailBean;
import com.sainti.blackcard.coffee.bean.ZhifubaoPayBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.commen.popup.CommenPayPopup;
import com.sainti.blackcard.db.bean.CoffeeLookBean;
import com.sainti.blackcard.db.dao.CoffeeLookDao;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.spcard.baen.BaseBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CoffeelistToArryUtil;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.TimeUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.mwebview.primary.WxBean;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeOrderDetailActiviity extends BaseTitleActivity implements View.OnClickListener, PopupWindow.OnDismissListener, OnNetResultListener, BaseQuickAdapter.OnItemClickListener, TimerListener, View.OnLayoutChangeListener, Malipay.OnAlipayListener, PayResultListener, CommenPayPopup.OnPopWindowClickListener {
    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private RecyclerView xrvOrderDetail;
    private OrderDetailAdapter orderDetailAdapter;
    private TextView tvNameUser, tvTel, tvAdressUser, tvZhezhiAdress, tvNotPeisong, tv_choice_time, tvCount, tvZongjia, tvTvPay, tv_more;
    private OrderDetailBean orderBodyBean;
    private RelativeLayout layDibu, head, activityCoffeeOrderDetailActiviity, bbody;
    private ImageView ivBianjiAdress;
    private Float zongPrice = 00.00f;
    private List<CoffeeLookBean> lookBeen;
    private boolean isDianJi = false;// 判断是否已经选地址
    private PopupWindow downPoup, timePopu;
    private Animation animationIn, animationOut;
    private LoadingView loadingView;
    private Malipay malipay;
    private String xoName, xoTel, xoProvince, xoCity, xoArea, xoAddress, count = "", jsonString, orderId, orderInfo, fi_start_deliverytime;
    private Gson gson;
    private boolean isTime = false;
    private View bejing;
    private TimeListAdapter timeListAdapter;
    private List<String> dateList;
    private Intent intent;
    private EditText ed_beizhu;
    private boolean zipei;

    @Override
    public int setLayout() {
        return R.layout.activity_detaile;
    }

    @Override
    protected void initView() {
        xrvOrderDetail = bindView(R.id.xrv_orderDetail);
        tvNameUser = bindView(R.id.tv_nameUser);
        tvTel = bindView(R.id.tv_tel);
        tvAdressUser = bindView(R.id.tv_adressUser);
        tvZhezhiAdress = bindView(R.id.tv_zhezhiAdress);
        tvNotPeisong = bindView(R.id.tv_not_peisong);
        layDibu = bindView(R.id.lay_dibu);
        ivBianjiAdress = bindView(R.id.iv_bianjiAdress);
        tvZongjia = bindView(R.id.tv_zongjia);// 底部总价
        tvCount = bindView(R.id.tv_count);
        tvTvPay = bindView(R.id.tv_tv_pay);
        activityCoffeeOrderDetailActiviity = bindView(R.id.acticit_de);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
        tv_choice_time = bindView(R.id.tv_choice_time);
        bejing = bindView(R.id.bejing);
        loadingView = new LoadingView(this);
        head = bindView(R.id.head);
        ed_beizhu = bindView(R.id.ed_beizhu);
        activityRootView = bindView(R.id.acticit_de);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        tv_more = bindView(R.id.tv_more);
        bbody = bindView(R.id.bbody);

    }

    @Override
    protected void initEvent() {
        ivBianjiAdress.setOnClickListener(this);
        tvZhezhiAdress.setOnClickListener(this);
        tvTvPay.setOnClickListener(this);
        EventBus.getDefault().register(this);
        tv_choice_time.setOnClickListener(this);
        tv_more.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        lookBeen = CoffeeLookDao.getsInstance(this).quaryAll();
        setTitle("确认订单");
        getDate();
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        orderDetailAdapter = new OrderDetailAdapter(this);
        xrvOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        xrvOrderDetail.setAdapter(orderDetailAdapter);
        orderDetailAdapter.setLookBeen(lookBeen);
        if (lookBeen.size() > 3) {
            tv_more.setVisibility(View.VISIBLE);
        }
        timePopu();
        loadingView.show();
        TurnClassHttp.getAdreeDate(1, this, this);
        tv_choice_time.setText(TimeUtil.getNowTime(45) + "  ——  " + TimeUtil.getNowTime(50));
        fi_start_deliverytime = TimeUtil.getNowDate() + " " + TimeUtil.getNowTime(45) + ":00";
        /*设置时间*/
        malipay = new Malipay(this, this);
    }

    private void getDate() {
        String zongjia = getIntent().getStringExtra("zongPrice");
        zongPrice = ConvertUtil.convertToFloat(zongjia.substring(1));
        zongPrice = zongPrice + 4.99f;
        count = getIntent().getStringExtra("zongCount");
        tvZongjia.setText("¥" + ConvertUtil.convertToString(zongPrice)); // 设置底部总价
        tvCount.setText(count + "份");
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_zhezhiAdress:
                Intent intents = new Intent(this, AddressSettingActivity.class);
                intents.putExtra("code", "2");
                startActivityForResult(intents, 100);
                break;
            case R.id.iv_bianjiAdress:
                Intent intent = new Intent(this, AddressSettingActivity.class);
                intent.putExtra("code", "1");
                intent.putExtra("xoName", xoName);
                intent.putExtra("xoTel", xoTel);
                intent.putExtra("xoProvince", xoProvince);
                intent.putExtra("xoCity", xoCity);
                intent.putExtra("xoArea", xoArea);
                intent.putExtra("xoAddress", xoAddress);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_tv_pay:
                if (isDianJi) {
                    getOrder();
                }
                break;
            case R.id.tv_choice_time:
                if (timePopu.isShowing()) {
                    timePopu.dismiss();
                } else {
                    isTime = true;
                    bejing.setVisibility(View.VISIBLE);
                    timePopu.showAtLocation(activityCoffeeOrderDetailActiviity, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.tv_more:
                String te = tv_more.getText().toString();
                if (te.equals("展开更多")) {
                    orderDetailAdapter.setCode(1);
                    tv_more.setText("收起更多");
                } else {
                    tv_more.setText("展开更多");
                    orderDetailAdapter.setCode(0);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            loadingView.show();
            CommontUtil.lateTime(1000, this);

        }
    }

    // 刷新结算UI
    private void isHasThing() {
        if (isDianJi) {
            tvTvPay.setBackgroundResource(R.drawable.shap_jiesuan_un);
            tvTvPay.setTextColor(getResources().getColor(R.color.d_b));
        }
        if (isDianJi) {
            tvTvPay.setBackgroundResource(R.drawable.shap_jiesuan);
            tvTvPay.setTextColor(getResources().getColor(R.color.e_eight));
        }
    }

    // 设置界面数据
    private void sheZhiDate(OrderDetailBean orderBodyBean) {
        tvNameUser.setText(orderBodyBean.getData().getXo_name());
        if (xoProvince.equals("")) {
            tvAdressUser.setText(xoCity + "市" + xoArea + "区" + xoAddress);
        } else {
            tvAdressUser.setText(xoProvince + "省" + xoCity + "市" + xoArea + "区" + xoAddress);
        }

        tvTel.setText(orderBodyBean.getData().getXo_tel());
    }

    /*************
     * 选择商品分类列表downPopupWindow
     ***************/
    private void timePopu() {
        timeListAdapter = new TimeListAdapter(R.layout.item_timelist);
        timePopu = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        timePopu.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(context).inflate(R.layout.view_timepopu, null);
        RecyclerView rv_tiem_list = view.findViewById(R.id.rv_tiem_list);
        TextView tv_canso = view.findViewById(R.id.tv_canso);
        tv_canso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePopu.dismiss();
            }
        });
        rv_tiem_list.setLayoutManager(new LinearLayoutManager(this));
        rv_tiem_list.setAdapter(timeListAdapter);
        dateList = new ArrayList<>();
        dateList = TimeUtil.getTIme();
        timeListAdapter.setNewData(dateList);
        timePopu.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        timePopu.setAnimationStyle(R.style.popwin_liebiao_style);
        timePopu.setContentView(view);
        timePopu.setOnDismissListener(this);
        timePopu.setFocusable(true);
        timeListAdapter.setOnItemClickListener(this);
    }

    // pipu消失监听
    @Override
    public void onDismiss() {
        if (isTime) {
            isTime = false;
            bejing.setVisibility(View.GONE);
            return;
        }
        layDibu.setAnimation(animationIn);
        layDibu.setVisibility(View.VISIBLE);
    }

    // 获取订单号吗
    private void getOrder() {
        loadingView.show();
        TurnClassHttp.getOrderInfo(ConvertUtil.convertToString(zongPrice), changeArrayDateToJson(), fi_start_deliverytime, 3, this, this);
    }

    private void weixinPay() {
        float lastPrcies = zongPrice * 100;
        TurnClassHttp.getApporder(ConvertUtil.convertToStrings(lastPrcies), orderId, "购物车聚合支付订单", "android", ConvertUtil.getTime(), 4, this, this);


    }

    private String changeArrayDateToJson() {  //把一个集合转换成json格式的字符串
        return CoffeelistToArryUtil.detailToJosnString(lookBeen, fi_start_deliverytime, zongPrice, xoName, xoCity, xoArea, xoAddress, ed_beizhu, orderId, xoTel);
    }


    //  支付成功后追加订单
    private void putDate(String trades) {
        TurnClassHttp.putOrderInfo(trades, 5, this, this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSCOFFEE)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                int results = baseBean.getResult();
                if (results == 0) {
                    isDianJi = false;
                    head.setVisibility(View.INVISIBLE);
                    // ivLocation.setVisibility(View.GONE);
                    tvZhezhiAdress.setVisibility(View.VISIBLE);
                } else {
                    tvZhezhiAdress.setVisibility(View.GONE);
                    head.setVisibility(View.VISIBLE);
                    isDianJi = true;
                    orderBodyBean = gson.fromJson(stringResult, OrderDetailBean.class);
                    xoName = orderBodyBean.getData().getXo_name();
                    xoTel = orderBodyBean.getData().getXo_tel();
                    xoProvince = orderBodyBean.getData().getXo_province();
                    xoCity = orderBodyBean.getData().getXo_city();
                    xoArea = orderBodyBean.getData().getXo_area();
                    xoAddress = orderBodyBean.getData().getXo_address();
                    sheZhiDate(orderBodyBean);
                    TurnClassHttp.getAdreeFanWei(xoCity + "市", xoArea + "区" + xoAddress, 2, this, this);
                }
                isHasThing();
                break;
            case 2:
                AdressBean adressBean = gson.fromJson(stringResult, AdressBean.class);
                if (adressBean.getResultcode().equals("0000")) {
                    // 在配送范围
                    layDibu.setVisibility(View.VISIBLE);
                    zipei = true;
                    tvNotPeisong.setVisibility(View.GONE);
                } else {
                    zipei = false;
                    tvNotPeisong.setVisibility(View.VISIBLE);
                    layDibu.setVisibility(View.GONE);
                }
                break;
            case 3:
                ZhifubaoPayBean zhifubaoPayBean = gson.fromJson(stringResult, ZhifubaoPayBean.class);
                orderId = zhifubaoPayBean.getData();// 订单ID
                orderInfo = zhifubaoPayBean.getAlipay_data(); //  支付宝的支付信息
                new CommenPayPopup(this, this, SataicCode.PayCode.ALL_SHOW).show();
                break;
            case 4:
                WxBean wxPayBean = gson.fromJson(stringResult, WxBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    WachatPay.getInstance(this).onSendTOWx(wxPayBean.getData(), this);
                }
                break;
            case 5:
                intent = new Intent();
                setResult(102, intent);
                finish();
                break;
            case 6:
                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    intent = new Intent(CoffeeOrderDetailActiviity.this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "3");// 1 是管家支付
                    intent.putExtra("json", changeArrayDateToJson());
                    startActivity(intent);
                } else {
                    ToastUtils.show(this, pinganBean.getMsg());
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        showToast(errMsg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (dateList.get(position).equals("立即送出")) {
            tv_choice_time.setText(TimeUtil.getNowTime(45) + "  —  " + TimeUtil.getNowTime(50));
            fi_start_deliverytime = TimeUtil.getNowDate() + " " + TimeUtil.getNowTime(45) + ":00";
            MLog.d("timeaaaaaaaaaaaaaaaaaaaaaaa", fi_start_deliverytime);

        } else {
            tv_choice_time.setText("指定时间   (" + dateList.get(position) + "）");
            fi_start_deliverytime = TimeUtil.getNowDate() + " " + dateList.get(position) + ":00";
            MLog.d("timeaaaaaaaaaaaaaaaaaaaaaaa", fi_start_deliverytime);
        }
        timePopu.dismiss();
    }

    @Override
    public void onTimerListener() {
        TurnClassHttp.getAdreeDate(1, this, this);
    }

    @Override
    public void onResume() {
        super.onResume();

        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            layDibu.setVisibility(View.GONE);
            //  Toast.makeText(CoffeeOrderDetailActiviity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            if (zipei) {
                layDibu.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSuccess() {
        paySuccess();
    }

    @Override
    public void onCancel() {
        payErro();
    }

    @Override
    public void onWait() {

    }

    private void paySuccess() {
        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
        Toast.makeText(CoffeeOrderDetailActiviity.this, "支付成功", Toast.LENGTH_LONG).show();
        putDate(changeArrayDateToJson());
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    private void payErro() {
        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
        Toast.makeText(CoffeeOrderDetailActiviity.this, "支付失败", Toast.LENGTH_SHORT).show();
        popState();
    }

    @Override
    public void onPaySuccess() {
        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
        putDate(changeArrayDateToJson());
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "weixin");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onPayError() {
        popState();
        showToast("支付失败");
    }

    @Override
    public void onPayCancel() {
        popState();
        showToast("取消支付");
    }

    private void popState() {
        layDibu.setAnimation(animationIn);
        layDibu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPopWindowClickListener(View view, int code) {
        switch (code) {
            case SataicCode.PayCode.ZERO:  // 余额支付
                Map<String, String> map_ekv0 = new HashMap<String, String>();
                map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                map_ekv0.put("orderkind", "coffee");
                MobclickAgent.onEvent(context, "dingdanquerentijiao", map_ekv0);// 友盟统计
                TurnClassHttp.pingan_pay(orderId, 6, CoffeeOrderDetailActiviity.this, CoffeeOrderDetailActiviity.this);
                break;
            case SataicCode.PayCode.ONE:  // 支付宝支付
                malipay.pay(orderInfo);
                break;
            case SataicCode.PayCode.TWO: // 微信支付
                weixinPay();// 微信支付
                break;
        }
    }

}
