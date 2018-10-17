package com.sainti.blackcard.my.flyorder.drivinglicense.fragment;

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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.my.flyorder.drivinglicense.adapter.AllAdapter;
import com.sainti.blackcard.my.flyorder.drivinglicense.bean.DrivingCommentBena;
import com.sainti.blackcard.goodthings.bean.HaoWuZhIFuBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.bean.WxPayBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
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
 * Created by YuZhenpeng on 2018/7/3.
 */

public class FragmentDaiFu extends BaseFragment implements OnNetResultListener, OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemChildClickListener, PopupWindow.OnDismissListener, Malipay.OnAlipayListener {
    private LoadingView loadingView;
    private SmartRefreshLayout refresh_lay;
    private RecyclerView rv_gooot;
    int page = 1;
    private List<DrivingCommentBena.DataBean> list;
    private DrivingCommentBena commentBena;
    private AllAdapter allAdapter;
    private PopupWindow downPoup;
    private IWXAPI mWxApi;
    private Malipay malipay;
    private RelativeLayout lay_ll;
    private String type;
    private String order_sn;
    private String orderId;
    private String product_name;
    private String lastPrice;

    @Override
    protected int setLayout() {
        return R.layout.fragment_gj_daipay;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().unregister(this);
        loadingView = new LoadingView(context);
        refresh_lay = bindView(R.id.refresh_lay);
        rv_gooot = bindView(R.id.rv_gooot);
        lay_ll = bindView(R.id.lay_ll);
        rv_gooot.setLayoutManager(new LinearLayoutManager(context));
        allAdapter = new AllAdapter(R.layout.item_guanjiacomment);
        allAdapter.setContext(context);
        rv_gooot.setAdapter(allAdapter);
        refresh_lay.setOnRefreshListener(this);
        refresh_lay.setOnLoadmoreListener(this);
        allAdapter.setOnItemChildClickListener(this);
        mWxApi = WXAPIFactory.createWXAPI(context, SataicCode.WXAPP_ID);
        mWxApi.registerApp(SataicCode.WXAPP_ID);
        malipay = new Malipay(getActivity());
        malipay.setmListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        TurnClassHttp.myDriverOrder("dfk", "1", "10", 1, context, this);
        downPopupWindow();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        refresh_lay.finishLoadmore();
        refresh_lay.finishRefresh();
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                commentBena = GsonSingle.getGson().fromJson(stringResult, DrivingCommentBena.class);
                list.clear();
                list.addAll(commentBena.getData());
                allAdapter.setNewData(list);
                break;
            case 2:
                commentBena = GsonSingle.getGson().fromJson(stringResult, DrivingCommentBena.class);
                list.addAll(commentBena.getData());
                allAdapter.setNewData(list);
                break;
            case 4:// 微信支付
                WxPayBean wxPayBean =  GsonSingle.getGson().fromJson(stringResult, WxPayBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    onSendTOWx(wxPayBean.getData());
                }

                break;
            case 5:// 支付宝支付
                HaoWuZhIFuBean fuBean =  GsonSingle.getGson().fromJson(stringResult, HaoWuZhIFuBean.class);
                if (fuBean != null) {
                    malipay.pay(fuBean.getData());
                }
                break;
            case 6:
                PinganBean pinganBean =  GsonSingle.getGson().fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(context, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "5");// 1 是管家支付
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        refresh_lay.finishLoadmore();
        refresh_lay.finishRefresh();
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        // loadingView.show();
        TurnClassHttp.guanjiaorder("dfk", "1", 1, context, this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        //loadingView.dismiss();
        TurnClassHttp.guanjiaorder("dfk", String.valueOf(page), 2, context, this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String s = list.get(position).getState();
        if (s.equals("0")) {
            // 执行支付
            type = "jiazhao";
            order_sn = list.get(position).getOrder_sn();
            downPoup.showAtLocation(lay_ll, Gravity.BOTTOM, 0, 0);
            darkenBackground(0.2f);
            orderId = list.get(position).getOrder_sn();
            product_name = list.get(position).getOrder_name();
            lastPrice = list.get(position).getOrder_amount();
        }
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
                TurnClassHttp.pingan_pay(orderId, 6, context, FragmentDaiFu.this);
            }
        });
        lay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lastPrcies = ConvertUtil.convertToFloat(lastPrice) * 100;
                int a = (int) lastPrcies;
                loadingView.show();
                TurnClassHttp.getApporder(a + "", orderId, product_name, "android", ConvertUtil.getTime(), 4, context, FragmentDaiFu.this);


            }
        });
        lay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingView.show();
                TurnClassHttp.getHaoWuDanHao(product_name, product_name, orderId, lastPrice, 5, context, FragmentDaiFu.this);

            }
        });

        downPoup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        downPoup.setAnimationStyle(R.style.popwin_liebiao_style);
        downPoup.setContentView(view);
        downPoup.setOnDismissListener(this);
        downPoup.setFocusable(true);
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
            //  paySucess();
            page = 1;
            // loadingView.show();
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("payment", "weixin");
            MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
            TurnClassHttp.myDriverOrder("all", "1", "10", 1, context, this);
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
        // paySucess();
        page = 1;
        // loadingView.show();
        TurnClassHttp.myDriverOrder("all", "1", "10", 1, context, this);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onWait() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}