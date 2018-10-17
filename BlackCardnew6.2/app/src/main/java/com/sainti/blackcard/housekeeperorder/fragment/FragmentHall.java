package com.sainti.blackcard.housekeeperorder.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.goodthings.bean.HaoWuZhIFuBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.bean.WxPayBean;
import com.sainti.blackcard.housekeeperorder.activity.HkOrderDetailActivity;
import com.sainti.blackcard.housekeeperorder.activity.PaySuceesActivity;
import com.sainti.blackcard.housekeeperorder.adapter.HousCommentAdapter;
import com.sainti.blackcard.housekeeperorder.bean.GuanJiaOrderBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuZhenpeng on 2018/5/9.
 */

public class FragmentHall extends BaseFragment implements OnRefreshListener, OnLoadmoreListener, OnNetResultListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, TimerListener, PopupWindow.OnDismissListener, Malipay.OnAlipayListener, View.OnClickListener {
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_gooot;
    private LoadingView loadingView;
    private GuanJiaOrderBean allGoodthingsBean;
    private HousCommentAdapter allThingsAdapter;
    int page = 1;
    private List<GuanJiaOrderBean.DataBean> list;
    private int remov;
    private View v_bg;
    private TextView tv_delete_s;
    private RelativeLayout lay_null, ll_lay;
    private PopupWindow downPoup;
    private String orderId;
    private String product_name;
    private String lastPrice;
    private IWXAPI mWxApi;
    private Malipay malipay;
    private String type;
    private String order_sn;


    @Override
    protected int setLayout() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_hoall;

    }

    @Override
    protected void initView(View view) {
        loadingView = new LoadingView(context);
        refresh_lay = bindView(R.id.refresh_lay);
        rv_gooot = bindView(R.id.rv_gooot);
        rv_gooot.setLayoutManager(new LinearLayoutManager(context));
        refresh_lay.setOnRefreshListener(this);
        refresh_lay.setOnLoadmoreListener(this);
        list = new ArrayList<>();
        v_bg = bindView(R.id.v_bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
        lay_null = bindView(R.id.lay_null);
        ll_lay = bindView(R.id.lay_ll);
        mWxApi = WXAPIFactory.createWXAPI(context, SataicCode.WXAPP_ID);
        mWxApi.registerApp(SataicCode.WXAPP_ID);
        malipay = new Malipay(getActivity());
        malipay.setmListener(this);

    }

    @Override
    protected void initData() {
        loadingView.show();
        allThingsAdapter = new HousCommentAdapter(R.layout.item_guanjiacomment);
        allThingsAdapter.setContext(context);
        rv_gooot.setAdapter(allThingsAdapter);
        allThingsAdapter.setOnItemClickListener(this);
        allThingsAdapter.setOnItemChildClickListener(this);
        TurnClassHttp.guanjiaorder("all", "1", 1, context, this);
        downPopupWindow();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void initEvents() {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        String a = stringResult;
        Gson gson = GsonSingle.getGson();

        switch (resultCode) {
            case 1:
                allGoodthingsBean = gson.fromJson(stringResult, GuanJiaOrderBean.class);
                list.clear();
                list.addAll(allGoodthingsBean.getData());
                if (list == null || list.size() == 0) {
                    lay_null.setVisibility(View.VISIBLE);
                }
                allThingsAdapter.setNewData(list);
                refresh_lay.finishRefresh();
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISOPERATION, false).commit();
                break;
            case 2:
                allGoodthingsBean = gson.fromJson(stringResult, GuanJiaOrderBean.class);
                list.addAll(allGoodthingsBean.getData());
                allThingsAdapter.setNewData(list);
                refresh_lay.finishLoadmore();
                break;
            case 3:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    tv_delete_s.setVisibility(View.VISIBLE);
                    CommontUtil.lateTime(1500, this);

                } else {
                    v_bg.setVisibility(View.GONE);
                    ToastUtils.show(context, baseBean.getMsg());
                }

                break;
            case 4:// 微信支付
                WxPayBean wxPayBean = gson.fromJson(stringResult, WxPayBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    onSendTOWx(wxPayBean.getData());
                }

                break;
            case 5:// 支付宝支付
                HaoWuZhIFuBean fuBean = gson.fromJson(stringResult, HaoWuZhIFuBean.class);
                if (fuBean != null) {
                    malipay.pay(fuBean.getData());
                }
                break;
            case 6:
                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(context, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "1");// 1 是管家支付
                    startActivity(intent);
                }
                break;
        }

        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
       // loadingView.show();
        TurnClassHttp.guanjiaorder("all", "1", 1, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        //loadingView.dismiss();
        TurnClassHttp.guanjiaorder("all", String.valueOf(page), 2, context, this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(context, HkOrderDetailActivity.class);
        intent.putExtra("id", list.get(position).getOrder_id());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        boolean isPperation = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISOPERATION, false);
        String code = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.GUANJIA, "");
        if (isPperation || code.equals(SpCodeName.REFRESH)) {
            if (downPoup.isShowing()) {
                downPoup.dismiss();
            }
            page = 1;
            loadingView.show();
            TurnClassHttp.guanjiaorder("all", "1", 1, context, this);
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, "").commit();

        }
        if (code.equals(SpCodeName.FINISH)) {
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, "").commit();
            getActivity().finish();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_delete:
                String state = list.get(position).getState();
                if (state.equals("0")) {
                    // 已完成
                    //showDialog(position);
                } else if (state.equals("1")) {
                    type = "guanjia";
                    order_sn = list.get(position).getOrder_sn();
                    downPoup.showAtLocation(ll_lay, Gravity.BOTTOM, 0, 0);
                    darkenBackground(0.2f);
                    orderId = list.get(position).getOrder_sn();
                    product_name = list.get(position).getProduct_name();
                    lastPrice = list.get(position).getOrder_amount();
                } else if (state.equals("2")) {
                    return;
                    // 已付款
                } else {
                    showDialog(position);
                    // 已失效
                }

                break;
        }
    }

    private void showDialog(final int p) {
        remov = p;
        v_bg.setVisibility(View.VISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_shopcard, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_deleteSure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_delelteText);// 删除字
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_teleteCso);// 取消删除
        TextView tv_del = (TextView) view.findViewById(R.id.tv_del);// 取消删除
        tv_delelteText.setText("删除后订单记录将？");
        tv_del.setText("无法找回，确定删除吗？");
        tv_deleteSure.setText("确定");
        tv_teleteCso.setText("放弃");
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        tv_deleteSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnClassHttp.delguanjiaorder(list.get(p).getOrder_id(), 3, context, FragmentHall.this);
                dialog.dismiss();
                //bg.setVisibility(View.GONE);
                loadingView.show();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                v_bg.setVisibility(View.GONE);
            }
        });
        dialog.setCancelable(false);
    }

    @Override
    public void onTimerListener() {
        list.remove(remov);
        allThingsAdapter.setNewData(list);
        tv_delete_s.setVisibility(View.GONE);
        v_bg.setVisibility(View.GONE);
    }



    /*************
     * 支付的window
     ***************/
    private void downPopupWindow() {
        downPoup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        downPoup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(context).inflate(R.layout.view_popu_guanjia, null);
        RelativeLayout lay_zhifubao = (RelativeLayout) view.findViewById(R.id.lay_zhifubao);
        RelativeLayout lay_weixin = (RelativeLayout) view.findViewById(R.id.lay_weixin);
        RelativeLayout lay_yue = (RelativeLayout) view.findViewById(R.id.lay_yue);
        lay_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.show();
                TurnClassHttp.pingan_pay(orderId, 6, context, FragmentHall.this);
            }
        });
        lay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lastPrcies = ConvertUtil.convertToFloat(lastPrice) * 100;
                int a = (int) lastPrcies;
                loadingView.show();
                TurnClassHttp.getApporder(a + "", orderId, product_name, "android", ConvertUtil.getTime(), 4, context, FragmentHall.this);


            }
        });
        lay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingView.show();
                TurnClassHttp.getHaoWuDanHao(product_name, product_name, orderId, lastPrice, 5, context, FragmentHall.this);

            }
        });

        downPoup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        downPoup.setAnimationStyle(R.style.popwin_liebiao_style);
        downPoup.setContentView(view);
        downPoup.setOnDismissListener(this);
        downPoup.setFocusable(true);
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgcolor;

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);

    }

    @Override
    public void onDismiss() {
        darkenBackground(1f);
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
                ToastUtils.show(context, "不能进行微信支付，请检查是否安装微信。");
            }
        }
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        // 支付成功
        if (messageEvent.getMessageCode().equals("0")) {
            Intent intent = new Intent(context, PaySuceesActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("order_sn", order_sn);
            startActivity(intent);
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("payment", "weixin");
            MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
        }
        // 取消支付
        if (messageEvent.getMessageCode().equals("-2")) {
            Toast.makeText(context, "取消支付", Toast.LENGTH_LONG).show();
        }
        // 支付失败
        if (messageEvent.getMessageCode().equals("1")) {
            Toast.makeText(context, "支付失败", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSuccess() {
        paySucess();
    }

    private void paySucess() {
        Intent intent = new Intent(context, PaySuceesActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("order_sn", order_sn);
        startActivity(intent);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }
    @Override
    public void onCancel() {
        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
        Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWait() {

    }

    @Override
    public void onClick(View v) {

    }

}
