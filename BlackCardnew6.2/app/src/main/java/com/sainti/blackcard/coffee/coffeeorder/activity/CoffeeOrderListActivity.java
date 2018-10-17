package com.sainti.blackcard.coffee.coffeeorder.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.coffeeorder.adapter.CoffeeOrderListAdapter;
import com.sainti.blackcard.coffee.coffeeorder.bean.CoffeelistBean;
import com.sainti.blackcard.coffee.coffeeorder.bean.HeiSouBean;
import com.sainti.blackcard.coffee.coffeeorder.bean.WinXinPayBean;
import com.sainti.blackcard.coffee.coffeeorder.bean.ZhifuPayBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mdialog.CommenDialogUtil;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.commen.popup.CommenPayPopup;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CoffeelistToArryUtil;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeOrderListActivity extends BaseTitleActivity implements OnNetResultListener, OnLoadmoreListener, OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, Malipay.OnAlipayListener, TimerListener, CommenPayPopup.OnPopWindowClickListener, PayResultListener, CommenDialogUtil.CallBack {
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_coffee;
    private CoffeelistBean coffeelistBean;
    private CoffeeOrderListAdapter orderListAdapter;
    private int page = 1;
    private List<CoffeelistBean.DataBean> list;
    private LoadingView loadingView;
    private JSONArray jsonArray;
    private JSONObject object;
    private JSONObject object2;
    private String jsonString;
    private JSONArray jsonArray1;
    private String xf_order_sn;
    private RelativeLayout ll_coffee_l;
    private View bg;
    private Malipay malipay;
    private TextView tv_delete_s;
    private int pp;
    private String xf_id;
    private TextView tv_null;
    private BaseBean baseBean;


    @Override
    public int setLayout() {
        return R.layout.activity_coffee_order_list;
    }

    @Override
    protected void initView() {
        refresh_lay = bindView(R.id.refresh_lay);
        rv_coffee = bindView(R.id.rv_coffee);
        rv_coffee.setLayoutManager(new LinearLayoutManager(this));
        ll_coffee_l = bindView(R.id.ll_coffee_l);
        malipay = new Malipay(this);
        malipay.setmListener(this);
        bg = bindView(R.id.v_bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
        tv_null = bindView(R.id.tv_null);

    }

    @Override
    protected void initEvent() {
        refresh_lay.setOnLoadmoreListener(this);
        refresh_lay.setOnRefreshListener(this);
        loadingView = new LoadingView(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        setTitle("咖啡订单");
        loadingView.show();
        TurnClassHttp.myCoffeeOrder("1", 1, this, this);
        orderListAdapter = new CoffeeOrderListAdapter(R.layout.item_cofferoder_list);
        orderListAdapter.setOnItemChildClickListener(this);
        orderListAdapter.setContext(context);
        rv_coffee.setAdapter(orderListAdapter);
        list = new ArrayList<>();

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        Gson gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                coffeelistBean = gson.fromJson(stringResult, CoffeelistBean.class);
                list.clear();
                list.addAll(coffeelistBean.getData());
                if (list == null || list.size() == 0) {
                    tv_null.setVisibility(View.VISIBLE);
                }
                orderListAdapter.setNewData(list);
                refresh_lay.finishRefresh();
                break;
            case 2:
                coffeelistBean = gson.fromJson(stringResult, CoffeelistBean.class);
                list.addAll(coffeelistBean.getData());
                orderListAdapter.setNewData(list);
                refresh_lay.finishLoadmore();
                break;
            case 3:// 支付宝支付
                ZhifuPayBean zhifuPayBean = gson.fromJson(stringResult, ZhifuPayBean.class);
                malipay.pay(zhifuPayBean.getPay_data());
                break;
            case 4:// 微信支付
                WinXinPayBean wxPayBean = gson.fromJson(stringResult, WinXinPayBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    WachatPay.getInstance(this).onSendTOWxofCoffee(wxPayBean.getPay_data(), this);
                }
                break;
            case 5:// 余额支付
                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(CoffeeOrderListActivity.this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "3");// 1 是管家支付
                    intent.putExtra("json", CoffeelistToArryUtil.listToJosnString(list,pp));
                    startActivity(intent);
                } else {
                    ToastUtils.show(this, pinganBean.getMsg());
                }
                break;
            case 6:/*给黑手传完回调*/
                HeiSouBean heiSouBean = gson.fromJson(stringResult, HeiSouBean.class);
                if (heiSouBean.getResultmsg().equals("成功")) {
                    loadingView.show();
                    page = 1;
                    TurnClassHttp.myCoffeeOrder("1", 1, this, this);
                }
                break;

            case 7:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    tv_delete_s.setVisibility(View.VISIBLE);
                    CommontUtil.lateTime(1500, this);

                } else {
                    bg.setVisibility(View.GONE);
                    ToastUtils.show(this, baseBean.getMsg());
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
    public void onLoadmore(RefreshLayout refreshlayout) {
        loadingView.show();
        page++;
        TurnClassHttp.myCoffeeOrder(String.valueOf(page), 2, this, this);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        TurnClassHttp.myCoffeeOrder("1", 1, this, this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        pp = position;
        xf_order_sn = list.get(position).getXf_order_sn();
        xf_id = list.get(position).getXf_id();// 订单id
        switch (view.getId()) {
            case R.id.tv_actionBtn:
                int state = list.get(position).getXf_state();
                if (state == 3) {
                    CommenDialogUtil.getInstance().showDialog(context, "删除后订单记录将？", "无法找回，确定删除吗？", this, 1);
                }
                if (state == 0) {
                    new CommenPayPopup(this, this, SataicCode.PayCode.ALL_SHOW).show();
                }
                break;
        }
    }

    //  支付成功后追加订单
    private void putDate(String trades) {
        TurnClassHttp.putOrderInfo(trades, 6, this, this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onTimerListener() {
        tv_delete_s.setVisibility(View.GONE);
        bg.setVisibility(View.GONE);
        list.remove(pp);
        orderListAdapter.setNewData(list);
    }


    @Override
    public void onPopWindowClickListener(View view, int code) {
        switch (code) {
            case SataicCode.PayCode.ZERO:  // 余额支付
                TurnClassHttp.pingan_pay(xf_order_sn, 5, CoffeeOrderListActivity.this, CoffeeOrderListActivity.this);
                break;
            case SataicCode.PayCode.ONE:  // 支付宝支付
                TurnClassHttp.getPayCode("alipay", xf_order_sn, ConvertUtil.getTime(), 3, CoffeeOrderListActivity.this, CoffeeOrderListActivity.this);
                break;
            case SataicCode.PayCode.TWO: // 微信支付
                TurnClassHttp.getPayCode("wechat", xf_order_sn, ConvertUtil.getTime(), 4, CoffeeOrderListActivity.this, CoffeeOrderListActivity.this);
                break;
        }
    }

    /* 微信支付回调*/
    @Override
    public void onPaySuccess() {
        ToastUtils.show(this, "支付成功");
        putDate(CoffeelistToArryUtil.listToJosnString(list,pp));
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

    /*支付宝支付回调*/
    @Override
    public void onSuccess() {
        putDate(CoffeelistToArryUtil.listToJosnString(list,pp));
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onCancel() {
        Toast.makeText(CoffeeOrderListActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWait() {

    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSCOFFEE)) {
            finish();
        }
    }





    @Override
    public void onSureClick(int tpye) {
        loadingView.show();
        TurnClassHttp.delCoffeeOrder(xf_id, 7, CoffeeOrderListActivity.this, CoffeeOrderListActivity.this);
    }

    @Override
    public void onCansoClick(int tpye) {
        bg.setVisibility(View.GONE);
    }
}
