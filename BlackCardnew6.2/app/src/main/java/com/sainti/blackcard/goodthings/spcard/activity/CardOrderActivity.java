package com.sainti.blackcard.goodthings.spcard.activity;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.my.coupon.activity.CommentCouponActivity;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.bean.KeyBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.spcard.adapter.ShopOrderInfoAdapter;
import com.sainti.blackcard.goodthings.spcard.baen.AddressBean;
import com.sainti.blackcard.goodthings.spcard.baen.CouponlistBean;
import com.sainti.blackcard.goodthings.spcard.baen.OrderInfoIDean;
import com.sainti.blackcard.goodthings.spcard.baen.ZhifubaoPayBean;
import com.sainti.blackcard.housekeeperorder.activity.PaySuceesActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.PingAnPayWebView;
import com.sainti.blackcard.mwebview.primary.WxBean;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardOrderActivity extends BaseTitleActivity implements PopupWindow.OnDismissListener, Malipay.OnAlipayListener, OnNetResultListener, TimerListener, PayResultListener {


    private RecyclerView rvOrder;
    private AddressBean addressBean;
    private TextView tvZhezhiAdress, tvAdress;
    private boolean hasMoren = false;
    private String province;
    private String citv;
    private String area;
    private String adress;
    private String name;
    private String tel;
    private TextView tv_ad, tv_name, tv_tle, tv_know, tv_choiceP, tv_price_now, tv_commint, tv_price_old, tv_cankey;
    private ImageView iv_bianjia_add, iv_danba, iv_getkey;
    private RelativeLayout lay_youhui;
    private String prcie;
    private String cl_id = "";
    private String co_dizhi = "";
    private List<GoodthingsBean> orderBeanss;
    private String tvZongjias;
    private PopupWindow downPoup;
    private RelativeLayout activity_card_order, lay_getkey;
    private String orderId;
    private String lastPrice;
    private Malipay malipay;
    private Gson gson;
    private String newZongjias;
    private double keyCount;
    private double keyMonety;
    private double blacksacle;
    private double kouKeyCount;
    private double mkouMoney;
    private EditText id_beizhu;
    private double isKey = 0.00;
    private double zongjiaDou;
    private CheckBox chek_box;
    private LoadingView loadingView;
    private String w_id;

    @Override
    public int setLayout() {
        return R.layout.activity_card_order;
    }

    @Override
    protected void initView() {
        rvOrder = bindView(R.id.rv_order);
        tvZhezhiAdress = bindView(R.id.tv_zhezhiAdress);
        tvAdress = bindView(R.id.tv_adress);
        tv_ad = bindView(R.id.tv_ad);
        iv_bianjia_add = bindView(R.id.iv_bianjia_add);
        tv_name = bindView(R.id.tv_name);
        tv_tle = bindView(R.id.tv_tle);
        lay_youhui = bindView(R.id.lay_youhui);
        tv_choiceP = bindView(R.id.tv_choiceP);
        iv_danba = bindView(R.id.iv_danba);
        tv_price_now = bindView(R.id.tv_price_now);
        tv_commint = bindView(R.id.tv_commint);
        tv_price_old = bindView(R.id.tv_price_old);
        activity_card_order = bindView(R.id.activity_card_order);
        EventBus.getDefault().register(this);
        lay_getkey = bindView(R.id.lay_getkey);
        iv_getkey = bindView(R.id.iv_getkey);
        tv_know = bindView(R.id.tv_know);
        tv_cankey = bindView(R.id.tv_cankey);
        id_beizhu = bindView(R.id.id_beizhu);
        chek_box = bindView(R.id.chek_box);
        loadingView = new LoadingView(this);

    }

    @Override
    protected void initEvent() {
        tvZhezhiAdress.setOnClickListener(this);
        iv_bianjia_add.setOnClickListener(this);
        lay_youhui.setOnClickListener(this);
        tv_commint.setOnClickListener(this);
        lay_getkey.setOnClickListener(this);
        iv_getkey.setOnClickListener(this);
        tv_know.setOnClickListener(this);
        chek_box.setOnClickListener(this);
        chek_box.setClickable(false);
    }


    @Override
    protected void initData() {
        setTitle("确认订单");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("isOrder", "0");
                setResult(111, intent);
                finish();
            }
        });

        settingDate();
        TurnClassHttp.getDiZhi(1, this, this);// 获取当前用户是否添写过地址
        downPopupWindow();
        malipay = new Malipay(CardOrderActivity.this,this);



        CommontUtil.lateTime(1000, this);
    }

    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isOrder", "0");
        setResult(111, intent);
        super.onBackPressed();
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                addressBean = gson.fromJson(stringResult, AddressBean.class);
                if (addressBean.getData() == null || addressBean.getData().size() == 0) {
                    tv_ad.setVisibility(View.GONE);
                    iv_bianjia_add.setVisibility(View.GONE);
                    tvZhezhiAdress.setVisibility(View.VISIBLE);
                    tv_commint.setBackgroundResource(R.drawable.shap_jiesuan_un);
                    tv_commint.setClickable(false);

                } else {
                    for (int i = 0; i < addressBean.getData().size(); i++) {
                        String moren = addressBean.getData().get(i).getAd_moren();
                        if (moren.equals("1")) {
                            hasMoren = true;
                            province = addressBean.getData().get(i).getAd_province(); // 省
                            citv = addressBean.getData().get(i).getAd_city();   // 市
                            area = addressBean.getData().get(i).getAd_area();    // 区
                            adress = addressBean.getData().get(i).getAd_address(); // 详细地址
                            name = addressBean.getData().get(i).getAd_user(); // 姓名
                            tel = addressBean.getData().get(i).getAd_tel();// 手机号
                        }
                    }
                    if (hasMoren) {
                        tvAdress.setText(province + citv + area + adress);
                        tv_name.setText(name);
                        tv_tle.setText(tel);

                    } else {
                        tv_ad.setVisibility(View.GONE);
                        iv_bianjia_add.setVisibility(View.GONE);
                        tvZhezhiAdress.setVisibility(View.VISIBLE);
                        tv_commint.setBackgroundResource(R.drawable.shap_jiesuan_un);
                        tv_commint.setClickable(false);
                    }

                }
                break;
            case 2:
                CouponlistBean couponlistBean = gson.fromJson(stringResult, CouponlistBean.class);
                if (couponlistBean.getData().getIs_use_bonus().equals("1")) {
                    lay_youhui.setClickable(true);
                } else {
                    lay_youhui.setBackgroundColor(getResources().getColor(R.color.d_c));
                    tv_choiceP.setText("无优惠券可用");
                    tv_choiceP.setTextColor(getResources().getColor(R.color.eight_four));
                    iv_danba.setVisibility(View.INVISIBLE);
                    lay_youhui.setClickable(false);
                }

                break;
            case 3:
                OrderInfoIDean orderInfoIDean = gson.fromJson(stringResult, OrderInfoIDean.class);
                if (orderInfoIDean.getResult() == 0) {
                    ToastUtils.show(this, orderInfoIDean.getMsg());
                    return;
                }
                if (orderInfoIDean.getResult() == 1) {
                    lastPrice = tv_price_now.getText().toString().substring(1, tv_price_now.length());
                    orderId = orderInfoIDean.getData();// 订单ID
                    downPoup.showAtLocation(activity_card_order, Gravity.BOTTOM, 0, 0);
                    darkenBackground(0.2f);
                }

                break;
            case 4:
                WxBean wxPayBean = gson.fromJson(stringResult, WxBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    WachatPay.getInstance(this).onSendTOWx(wxPayBean.getData(),this);
                    //onSendTOWx();
                }
                break;
            case 5:

                ZhifubaoPayBean zhifubaoPayBean = gson.fromJson(stringResult, ZhifubaoPayBean.class);
                if (zhifubaoPayBean != null) {
                    malipay.pay(zhifubaoPayBean.getData());
                }
                break;

            case 6:
                KeyBean keyBean = gson.fromJson(stringResult, KeyBean.class);
                keyCount = keyBean.getBlackkey();
                keyMonety = keyBean.getBlackkeyTomoney();
                blacksacle = keyBean.getBlacksacle();
                zongjiaDou = ConvertUtil.convertToDouble(prcie, 0.00);
                if (keyCount < 0.01) {
                    chek_box.setClickable(false);
                    tv_cankey.setText("可使用" + 0 + "把黑钥匙抵扣" + 0 + "元");
                    loadingView.dismiss();
                    return;
                }
                chek_box.setClickable(true);
                if (zongjiaDou > keyMonety) {
                    /* 当选择的商品总价大于要是抵扣的情况*/
                    // 折扣的key个数
                    kouKeyCount = keyCount;
                    // 折扣的钱
                    mkouMoney = keyMonety;
                    tv_cankey.setText("可使用" + ConvertUtil.doubleToString(kouKeyCount) + "把黑钥匙抵扣" + mkouMoney + "元");
                } else {
                     /* 当选择的商品总价小yu要是抵扣的情况*/
                    kouKeyCount = zongjiaDou * blacksacle;// 折扣的key个数
                    mkouMoney = zongjiaDou;// 折扣的钱
                    tv_cankey.setText("可使用" + ConvertUtil.doubleToString(kouKeyCount) + "把黑钥匙抵扣" + mkouMoney + "元");
                }
                break;
            case 7:
                PinganBean pinganBean = gson.fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(CardOrderActivity.this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "2");// 2 好物支付
                    startActivity(intent);

                } else {
                    ToastUtils.show(this, pinganBean.getMsg());
                }
                break;
        }
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                ToastUtils.show(CardOrderActivity.this, "生成订单信息错误");
                break;
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_zhezhiAdress:
                Intent intent = new Intent(this, AdressListActivity.class);
                startActivityForResult(intent, 114);
                break;
            case R.id.iv_bianjia_add:
                Intent intents = new Intent(this, AdressListActivity.class);
                startActivityForResult(intents, 114);
                break;
            case R.id.lay_youhui:
                Intent intent1 = new Intent(this, CommentCouponActivity.class);
                intent1.putExtra("gids", w_id);
                intent1.putExtra("amount", prcie);
               intent1.putExtra("cl_id", cl_id);
                /* intent1.putExtra("code", "");*/
                startActivityForResult(intent1, 116);
                break;
            case R.id.tv_commint:
                commit();
                break;
            case R.id.lay_getkey:
                break;

            case R.id.iv_getkey:
                lay_getkey.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_know:
                lay_getkey.setVisibility(View.GONE);
                break;
            case R.id.chek_box:
                if (chek_box.isChecked()) {
                    isKey = kouKeyCount;
                    zongjiaDou = zongjiaDou - mkouMoney;

                } else {
                    isKey = 0.00;
                    zongjiaDou = zongjiaDou + mkouMoney;
                }
                if (zongjiaDou < 0.01) {
                    tv_price_now.setText("¥" + 0.01);
                } else {
                    tv_price_now.setText("¥" + ConvertUtil.doubleToString(zongjiaDou));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 114 && resultCode == 115) {
            tvAdress.setText(data.getStringExtra("adress"));
            tv_name.setText(data.getStringExtra("name"));
            tv_tle.setText(data.getStringExtra("tel"));
            tv_ad.setVisibility(View.VISIBLE);
            iv_bianjia_add.setVisibility(View.VISIBLE);
            tvZhezhiAdress.setVisibility(View.GONE);
            tv_commint.setBackgroundResource(R.drawable.shap_jiesuan_sp);
            tv_commint.setClickable(true);
        }
        if (requestCode == 116 && resultCode == 117) {
            cl_id = data.getStringExtra("cl_id"); // 优惠券ID
            co_dizhi = data.getStringExtra("co_dizhi");// 优惠券价钱
            String name = data.getStringExtra("name");
            if (name.equals("")) {
                tv_choiceP.setText("选择优惠券");
                refFress(0.00);
                //tv_price_now.setText(tvZongjias);
            } else {
                tv_choiceP.setText(name);
                double youhuiMoney = Double.parseDouble(co_dizhi);
                refFress(youhuiMoney);
            }
        }
    }

    private void refFress(double youhuiMoney) {
        zongjiaDou = ConvertUtil.convertToDouble(prcie, 0.00);
        zongjiaDou = zongjiaDou - youhuiMoney;// 优惠后的价格
        if (zongjiaDou < 0.01) {
            zongjiaDou = 0.01;
        }
        if (keyCount < 0.01) {
            chek_box.setClickable(false);
            tv_cankey.setText("可使用" + 0 + "把黑钥匙抵扣" + 0 + "元");
            tv_price_now.setText("¥" + zongjiaDou);
            return;
        }
        if (zongjiaDou > keyMonety) {
            /* 当选择的商品总价大于要是抵扣的情况*/
            kouKeyCount = keyCount; // 折扣的key个数
            mkouMoney = keyMonety;// 折扣的钱
            tv_cankey.setText("可使用" + ConvertUtil.doubleToString(kouKeyCount) + "把黑钥匙抵扣" + mkouMoney + "元");
        } else {
             /* 当选择的商品总价小yu要是抵扣的情况*/
            kouKeyCount = zongjiaDou * blacksacle;// 折扣的key个数
            mkouMoney = zongjiaDou;// 折扣的钱
            tv_cankey.setText("可使用" + ConvertUtil.doubleToString(kouKeyCount) + "把黑钥匙抵扣" + mkouMoney + "元");
        }
        if (chek_box.isChecked()) {/* 当被已经选上了黑钥匙*/
            zongjiaDou = zongjiaDou - mkouMoney;
            isKey = kouKeyCount;
        } else {
            isKey = 0.00;
        }
        if (zongjiaDou < 0.01) {
            tv_price_now.setText("¥" + 0.01);
        } else {
            tv_price_now.setText("¥" + ConvertUtil.doubleToString(zongjiaDou));
        }

    }

    private void settingDate() {
        // 查询订单数据
        orderBeanss = GoodThingsDao.getsInstance(this).queryForPayState("1");
        ShopOrderInfoAdapter shopOrderInfoAdapter = new ShopOrderInfoAdapter(R.layout.item_shooping_card);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        shopOrderInfoAdapter.setContext(this);
        rvOrder.setAdapter(shopOrderInfoAdapter);
        shopOrderInfoAdapter.setNewData(orderBeanss);
        tvZongjias = getIntent().getStringExtra("tvZongjias");
        newZongjias = tvZongjias;// 新的总价
        String tvYuanjia = getIntent().getStringExtra("tvYuanjia");
        tv_price_old.setText("¥" + tvYuanjia);
        prcie = tvZongjias.substring(1, tvZongjias.length());
        tv_price_now.setText(tvZongjias);
        //TurnClassHttp.getYouHuiJuan(prcie, 2, this, this);
        w_id = "";
        for (int i = 0; i < orderBeanss.size(); i++) {
            if (i == orderBeanss.size() - 1) {
                w_id = w_id + orderBeanss.get(i).getwId();
            } else {
                w_id = w_id + orderBeanss.get(i).getwId() + ",";
            }

        }
        TurnClassHttp.getUerBonus(w_id, prcie, 2, this, this);
    }

    private void commit() {
        String beizhu = id_beizhu.getText().toString();
        lastPrice = tv_price_now.getText().toString().substring(1, tv_price_now.length());
        if (beizhu == null) {
            beizhu = "";
        }
        try {
            String xw_json = changeArrayDateToJson(orderBeanss);
            String xw_name = tv_name.getText().toString();
            String xw_tel = tv_tle.getText().toString();
            String xw_address = tvAdress.getText().toString();
            String xw_youhuiquan = cl_id;
            loadingView.show();
            TurnClassHttp.getCartOrder(xw_json, xw_name, xw_tel, xw_address, xw_youhuiquan, isKey + "", beizhu, lastPrice, 3, this, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String changeArrayDateToJson(List<GoodthingsBean> allList) throws JSONException {
        JSONArray j = new JSONArray();
        for (int i = 0; i < allList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("w_id", allList.get(i).getwId());
            jsonObject.put("w_num", allList.get(i).getTingsCount());
            jsonObject.put("w_kindid", allList.get(i).getTingsKindId());
            j.put(jsonObject);
        }
        return j.toString();
    }

    /*************
     * 选择商品分类列表downPopupWindow
     ***************/
    private void downPopupWindow() {
        downPoup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        downPoup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(context).inflate(R.layout.view_paywindow, null);
        RelativeLayout lay_zhifubao = (RelativeLayout) view.findViewById(R.id.lay_zhifubao);
        RelativeLayout lay_weixin = (RelativeLayout) view.findViewById(R.id.lay_weixin);
        RelativeLayout lay_yue = (RelativeLayout) view.findViewById(R.id.lay_yue);
        lay_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnClassHttp.pingan_pay(orderId, 7, CardOrderActivity.this, CardOrderActivity.this);
            }
        });
        lay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lastPrcies = ConvertUtil.convertToFloat(lastPrice) * 100;
                int a = (int) lastPrcies;
                TurnClassHttp.getApporder(a + "", orderId, "购物车聚合支付订单", "android", ConvertUtil.getTime(), 4, CardOrderActivity.this, CardOrderActivity.this);

            }
        });
        lay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnClassHttp.getHaoWuDanHao("购物车聚合支付订单", "购物车聚合支付订单", orderId, lastPrice, 5, CardOrderActivity.this, CardOrderActivity.this);

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

        showDialog();

    }

    private void showDialog() {

        // v_bg.setVisibility(View.VISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_shopcard, null);
        TextView tv_deleteSure = (TextView) view.findViewById(R.id.tv_deleteSure); // 确定删除
        TextView tv_delelteText = (TextView) view.findViewById(R.id.tv_delelteText);// 删除字
        TextView tv_teleteCso = (TextView) view.findViewById(R.id.tv_teleteCso);// 取消删除
        TextView tv_del = (TextView) view.findViewById(R.id.tv_del);// 取消删除
        tv_delelteText.setText("订单已经生成");
        tv_del.setText("确定放弃支付吗？");
        tv_deleteSure.setText("确定");
        tv_teleteCso.setText("取消");
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        tv_deleteSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //bg.setVisibility(View.GONE);
                finish();

            }
        });
        tv_teleteCso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                darkenBackground(1f);
                downPoup.showAtLocation(activity_card_order, Gravity.BOTTOM, 0, 0);
                darkenBackground(0.2f);
                //  v_bg.setVisibility(View.GONE);
            }
        });
        dialog.setCancelable(false);
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

    /**
     * 支付宝支付回调接口
     *
     * @author lenovo
     */
    @Override
    public void onSuccess() {

        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(this, "pay_success", map_ekv0);// 友盟统计
        Intent intent1 = new Intent(this, PaySuceesActivity.class);
        intent1.putExtra("type", "haowu");
        intent1.putExtra("order_sn", orderId);
        startActivity(intent1);
        finish();
    }

    @Override
    public void onCancel() {
        ToastUtils.show(this, "支付失败!");
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onTimerListener() {
        loadingView.show();
        TurnClassHttp.getUserBlackkey(prcie,6, this, this);
    }
/*微信支付回调*/
    @Override
    public void onPaySuccess() {
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "weixin");
        MobclickAgent.onEvent(this, "pay_success", map_ekv0);// 友盟统计
        // ToastUtils.show(this, "支付成功");
        Intent intent1 = new Intent(this, PaySuceesActivity.class);
        intent1.putExtra("type", "haowu");
        intent1.putExtra("order_sn", orderId);
        startActivity(intent1);
        finish();
    }

    @Override
    public void onPayError() {
        ToastUtils.show(this, "支付失败！请稍后尝试");
    }

    @Override
    public void onPayCancel() {

    }
}
