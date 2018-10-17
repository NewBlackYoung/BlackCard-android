package com.sainti.blackcard.my.flyorder.drivinglicense.actvity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.flyorder.drivinglicense.bean.DrDetailBean;
import com.sainti.blackcard.goodthings.bean.GetKeyCountBean;
import com.sainti.blackcard.goodthings.bean.HaoWuZhIFuBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.bean.WxPayBean;
import com.sainti.blackcard.housekeeperorder.activity.PaySuceesActivity;
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
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DrivingDetailActivity extends BaseTitleActivity implements Malipay.OnAlipayListener, OnNetResultListener, TimerListener, PopupWindow.OnDismissListener {
    private TextView add_time, creater_user, tv_keycount, tv_delete_s, tv_p, product_type_name, tv_bottom, tv_product_name, tv_state, order_amount, payment_name, youhq_money, product_price, order_sn, tv_copy;
    private LoadingView loadingView;
    private RelativeLayout ll_par;

    private View bg;
    private PopupWindow downPoup;
    private String lastPrice;
    private String orderId;
    private LinearLayout ll_paysucess;
    private IWXAPI mWxApi;
    private Malipay malipay;
    private String product_name;
    private ImageView iv_back_home, iv_look_order;
    private String id;
    private  DrDetailBean  housekeeperDetailBean;

    @Override
    public int setLayout() {
        return R.layout.activity_driving_detail;
    }

    @Override
    protected void initView() {
        add_time = bindView(R.id.add_time);
        creater_user = bindView(R.id.creater_user);
        product_type_name = bindView(R.id.product_type_name);
        tv_bottom = bindView(R.id.tv_bottom);
        tv_product_name = bindView(R.id.tv_product_name);
        tv_state = bindView(R.id.tv_state);
        order_amount = bindView(R.id.order_amount);
        payment_name = bindView(R.id.payment_name);
        youhq_money = bindView(R.id.youhq_money);
        product_price = bindView(R.id.product_price);
        order_sn = bindView(R.id.order_sn);
        tv_copy = bindView(R.id.tv_copy);
        tv_p = bindView(R.id.tv_p);
        bg = bindView(R.id.bg);
        tv_delete_s = bindView(R.id.tv_delete_s);
        ll_par = bindView(R.id.ll_par);
        ll_paysucess = bindView(R.id.ll_paysucess);
        iv_back_home = bindView(R.id.iv_back_home);
        iv_look_order = bindView(R.id.iv_look_order);
        mWxApi = WXAPIFactory.createWXAPI(this, SataicCode.WXAPP_ID);
        mWxApi.registerApp(SataicCode.WXAPP_ID);
        context = this;
        malipay = new Malipay(this);
        malipay.setmListener(this);
        iv_back_home.setOnClickListener(this);
        iv_look_order.setOnClickListener(this);
        tv_keycount = bindView(R.id.tv_keycount);
    }

    @Override
    protected void initEvent() {
        loadingView = new LoadingView(this);
        tv_copy.setOnClickListener(this);
        tv_bottom.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_copy:
                CommontUtil.onClickCopy(order_sn, this);
                break;
            case R.id.tv_bottom:
                String state = housekeeperDetailBean.getData().getState();
                if (state.equals("3")) {
                    // 已完成
                    showDialog();
                } else if (state.equals("0")) {
                    // 未付款
                    downPoup.showAtLocation(ll_par, Gravity.BOTTOM, 0, 0);
                    darkenBackground(0.2f);
                } else if (state.equals("2")) {
                    return;
                    // 已付款
                } else {
                    return;
                    // 已失效
                }

                break;
            case R.id.iv_back_home:
                EventBus.getDefault().post(new NormalEventBean("11111"));
                finish();
                break;
            case R.id.iv_look_order:
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISOPERATION, true).commit();
                finish();
                break;
        }
    }

    @Override
    protected void initData() {
        setTitle("订单详情");
        id = getIntent().getStringExtra("id");
        loadingView.show();
        TurnClassHttp.myDriverOrderDetail(id, 1, this, this);
        downPopupWindow();

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        String s = stringResult;
        switch (resultCode) {
            case 1:
               housekeeperDetailBean = gson.fromJson(stringResult, DrDetailBean.class);
                if (housekeeperDetailBean.getResult().equals("1")) {
                    add_time.setText(housekeeperDetailBean.getData().getOrder_time());
                    creater_user.setText(housekeeperDetailBean.getData().getConsignee());
                    product_type_name.setText(housekeeperDetailBean.getData().getOrder_name());
                    tv_product_name.setText(housekeeperDetailBean.getData().getOrder_name());
                    order_amount.setText(housekeeperDetailBean.getData().getOrder_amount());
                    payment_name.setText(housekeeperDetailBean.getData().getPayment_name());
                    product_price.setText(housekeeperDetailBean.getData().getPay_amount());
                    order_sn.setText(housekeeperDetailBean.getData().getOrder_sn());
                  //  String youhuijuan = housekeeperDetailBean.getData().getYouhq_money();
                    product_name = housekeeperDetailBean.getData().getOrder_name();
                    lastPrice = housekeeperDetailBean.getData().getOrder_amount();
                    orderId = housekeeperDetailBean.getData().getOrder_sn();
                    youhq_money.setVisibility(View.GONE);

                    String state = housekeeperDetailBean.getData().getState();
                    if (state.equals("4")) {
                        // 已完成
                        tv_p.setBackgroundResource(R.mipmap.yiwc);
                        tv_bottom.setVisibility(View.GONE);
                        // tv_bottom.setBackgroundResource(R.mipmap.delete_b);
                        order_amount.setTextColor(getResources().getColor(R.color.black));
                        tv_state.setVisibility(View.INVISIBLE);
                    } else if (state.equals("0")) {
                        // 未付款
                        tv_p.setBackgroundResource(R.mipmap.iv_daizhifu);
                        tv_bottom.setBackgroundResource(R.mipmap.gopay_b);
                        tv_state.setTextColor(getResources().getColor(R.color.eight_f));
                        order_amount.setTextColor(getResources().getColor(R.color.eight_f));
                    } else if (state.equals("1")) {
                        tv_p.setBackgroundResource(R.mipmap.yif);
                        tv_bottom.setVisibility(View.GONE);
                        order_amount.setTextColor(getResources().getColor(R.color.black));
                        tv_state.setVisibility(View.INVISIBLE);
                        // 已付款
                    }  else if (state.equals("5") ||state.equals("6")) {
                        order_amount.setTextColor(getResources().getColor(R.color.black));
                        tv_p.setBackgroundResource(R.mipmap.yituikuanlan);
                        tv_bottom.setVisibility(View.GONE);
                        tv_state.setTextColor(getResources().getColor(R.color.eight_four));
                        // 退款
                    }else if (state.equals("2")) {
                        order_amount.setTextColor(getResources().getColor(R.color.black));
                        tv_p.setBackgroundResource(R.mipmap.yifahuo);
                        tv_bottom.setVisibility(View.GONE);
                        tv_state.setTextColor(getResources().getColor(R.color.eight_four));
                        // 已发货
                    }else {
                        order_amount.setTextColor(getResources().getColor(R.color.black));
                        tv_p.setBackgroundResource(R.mipmap.yisiao);
                        tv_bottom.setBackgroundResource(R.mipmap.delete_b);
                        tv_state.setTextColor(getResources().getColor(R.color.eight_four));
                        // 已失效
                    }
                } else {
                    ToastUtils.show(this, housekeeperDetailBean.getMsg());
                }
                break;
            case 2:
                BaseBean baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.REFRESH).commit();

                  //  SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISOPERATION, true).commit();
                    tv_delete_s.setVisibility(View.VISIBLE);
                    CommontUtil.lateTime(1500, this);

                } else {
                    bg.setVisibility(View.GONE);
                    ToastUtils.show(this, baseBean.getMsg());
                }
                break;

            case 3:// 微信支付
                WxPayBean wxPayBean = gson.fromJson(stringResult, WxPayBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    onSendTOWx(wxPayBean.getData());
                }

                break;
            case 4:// 支付宝支付
                HaoWuZhIFuBean fuBean = gson.fromJson(stringResult, HaoWuZhIFuBean.class);
                if (fuBean != null) {
                    malipay.pay(fuBean.getData());
                }

                break;
            case 5:// 余额支付
                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(DrivingDetailActivity.this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "5");// 1 是管家支付
                    startActivity(intent);

                }
                break;
            case 6:
                GetKeyCountBean countBean = gson.fromJson(stringResult, GetKeyCountBean.class);
                String count = countBean.getBlackkey();
                if (count.equals("0.00")) {
                    tv_keycount.setVisibility(View.INVISIBLE);
                } else {
                    tv_keycount.setVisibility(View.VISIBLE);
                    tv_keycount.setText("恭喜您获得黑钥匙" + count + "把");
                }
                ll_paysucess.setVisibility(View.VISIBLE);
                break;
        }

        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }


    private void showDialog() {
        bg.setVisibility(View.VISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_dialog_shopcard, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_deleteSure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_delelteText);// 第一行字
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_teleteCso);// 取消删除
        TextView tv_del = (TextView) view.findViewById(R.id.tv_del);// 第二行字
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
                TurnClassHttp.delDriverOrder(id, 2, context, DrivingDetailActivity.this);
                dialog.dismiss();
                //bg.setVisibility(View.GONE);
                loadingView.show();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.setCancelable(false);
    }

    /*************
     * 选择商品分类列表downPopupWindow
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
                yueZhifu();
            }
        });
        lay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lastPrcies = ConvertUtil.convertToFloat(lastPrice) * 100;
                int a = (int) lastPrcies;
                loadingView.show();
                TurnClassHttp.getApporder(a + "", orderId, product_name, "android", ConvertUtil.getTime(), 3, context, DrivingDetailActivity.this);


            }
        });
        lay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingView.show();
                TurnClassHttp.getHaoWuDanHao(product_name, product_name, orderId, lastPrice, 4, context, DrivingDetailActivity.this);

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

    private void yueZhifu() {
        loadingView.show();
        TurnClassHttp.pingan_pay(orderId, 5, context, this);
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
        if (messageEvent.getMessageCode().equals(SataicCode.PAYSUCESSGUANJIA)) {
            finish();
        }
        // 支付成功
        if (messageEvent.getMessageCode().equals("0")) {
              /*  // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText(HaoWuZhiFuActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                finish();*/

            paySucess();
            //ll_paysucess.setVisibility(View.VISIBLE);

        }
        // 取消支付
        if (messageEvent.getMessageCode().equals("-2")) {
            Toast.makeText(DrivingDetailActivity.this, "支付失败", Toast.LENGTH_LONG).show();
        }
        // 支付失败
        if (messageEvent.getMessageCode().equals("1")) {
            Toast.makeText(DrivingDetailActivity.this, "支付失败", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    @Override
    public void onSuccess() {
        paySucess();
       // TurnClassHttp.getBlackkey(orderId, "guanjia", 6, this, this);

    }
    private void paySucess() {
        Intent intent = new Intent(context, PaySuceesActivity.class);
        intent.putExtra("jiazhao", "jiazhao");
        intent.putExtra("order_sn", housekeeperDetailBean.getData().getOrder_sn());
        finish();
        startActivity(intent);
    }
    @Override
    public void onCancel() {
        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
        Toast.makeText(DrivingDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWait() {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onTimerListener() {
        finish();
    }
}
