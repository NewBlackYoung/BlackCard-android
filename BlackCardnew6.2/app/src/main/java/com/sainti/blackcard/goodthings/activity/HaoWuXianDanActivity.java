package com.sainti.blackcard.goodthings.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.goodthings.bean.KeyBean;
import com.sainti.blackcard.goodthings.spcard.baen.CouponlistBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.my.coupon.activity.CommentCouponActivity;
import com.sainti.blackcard.widget.LoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HaoWuXianDanActivity extends BaseTitleActivity implements OnNetResultListener, View.OnClickListener, View.OnLayoutChangeListener {
    private TextView tv_xingming, tv_dianhua, tv_location, tv_biaoti, tv_fenlei, tv_money, tv_count, tv_peisong, tv_youhuijuan, tv_zongjia, tv_xiadan;

    private RelativeLayout rela_youhuijuan, rela_dizhi,rel_shipping;
    private String title, count, danjia, url, fenlei, fenleiid;
    private ImageView iv_chanpin;
    private double zongjia;
    private Context context;
    private String dizhi, xingming, dianhua;
    private View view_yuan, view_yinying;
    private String kuaidi = "";
    private String kuaidijia = "";
    private JSONArray youhuijuan = new JSONArray();
    private int juanPosition = -1;
    private String w_id = "";
    private double yuanja;
    private boolean chooseDiZhi = false;
    private String youhuijuanid = "";
    private String smallTitle = "";
    private RelativeLayout root_layout;
    private LinearLayout lin_xiafang;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private CheckBox chek_box;
    private EditText id_beizhu;
    private double isKey = 0.00;
    private KeyBean keyBean;
    private double keyCount;
    private double keyMonety;
    private TextView tv_cankey;
    private ImageView iv_getkey;
    private double blacksacle;
    private double kouKeyCount;
    private double mkouMoney;
    private LoadingView loadingView;
    private String lastPrice;
    private String cl_id = "";
    private String co_dizhi;
    private String name;

    @Override
    public int setLayout() {
        return R.layout.activity_hao_wu_xian_dan;
    }

    @Override
    protected void initView() {
        view_yuan = findViewById(R.id.view_yuan);
        view_yinying = findViewById(R.id.view_yinying);
        view_yinying.setVisibility(View.VISIBLE);
        view_yuan.setVisibility(View.VISIBLE);
        tv_xingming = (TextView) findViewById(R.id.tv_xingming);
        tv_dianhua = (TextView) findViewById(R.id.tv_dianhua);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_biaoti = (TextView) findViewById(R.id.tv_biaoti);
        tv_fenlei = (TextView) findViewById(R.id.tv_fenlei);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_peisong = (TextView) findViewById(R.id.tv_peisong);
        tv_youhuijuan = (TextView) findViewById(R.id.tv_youhuijuan);
        tv_zongjia = (TextView) findViewById(R.id.tv_zongjia);
        tv_xiadan = (TextView) findViewById(R.id.tv_xiadan);
        rela_dizhi = (RelativeLayout) findViewById(R.id.rela_dizhi);
        rela_youhuijuan = (RelativeLayout) findViewById(R.id.rela_youhuijuan);
        iv_chanpin = (ImageView) findViewById(R.id.iv_chanpin);
        context = this;
        root_layout = bindView(R.id.root_layout);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        lin_xiafang = bindView(R.id.lin_xiafang);
        chek_box = bindView(R.id.chek_box);
        id_beizhu = bindView(R.id.id_beizhu);
        tv_cankey = bindView(R.id.tv_cankey);
        iv_getkey = bindView(R.id.iv_getkey);
        rel_shipping = bindView(R.id.rel_shipping);
        loadingView = new LoadingView(this);
    }

    @Override
    protected void initEvent() {
        rela_dizhi.setOnClickListener(this);
        rela_youhuijuan.setOnClickListener(this);
        tv_xiadan.setOnClickListener(this);
        root_layout.addOnLayoutChangeListener(this);//软键盘消失和出现的监听
        chek_box.setOnClickListener(this);
        iv_getkey.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        getNewData();// 页面初始化数据
        loadingView.show();
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TurnClassHttp.getUserBlackkey(zongjia + "", 4, this, this);
    }

    public void getNewData() {
        setTitle("订单信息");
        smallTitle = getIntent().getStringExtra("smallTitles");
        title = getIntent().getStringExtra("title");
        zongjia = Double.parseDouble(getIntent().getStringExtra("zongjia"));
        count = getIntent().getStringExtra("count");
        danjia = getIntent().getStringExtra("danjia");
        url = getIntent().getStringExtra("url");
        fenlei = getIntent().getStringExtra("fenlei");
        fenleiid = getIntent().getStringExtra("fenleiid");
        kuaidi = getIntent().getStringExtra("kuaidi");
        w_id = getIntent().getStringExtra("w_id");
        kuaidijia = getIntent().getStringExtra("kuaidijia");
        if (!kuaidi.equals("")) {
            if (kuaidijia.equals("0.00") ||kuaidijia.equals("")) {
                tv_peisong.setText(kuaidi);
            } else {
                tv_peisong.setText(kuaidi + ":¥" + kuaidijia);
                zongjia += Double.parseDouble(kuaidijia);
            }
        }else {
            rel_shipping.setVisibility(View.GONE);
        }

        yuanja = zongjia;
        GlideManager.getsInstance().loadImageToUrL(this, url, iv_chanpin);
        tv_biaoti.setText(smallTitle);
        if (fenlei != null && fenlei.length() > 0) {
            tv_fenlei.setText(fenlei);
        } else {
            fenlei = "";
        }
        tv_money.setText("¥" + danjia);
        tv_zongjia.setText("    ¥" + zongjia);
        tv_count.setText(count);
        tv_fenlei.setText(fenlei);
        TurnClassHttp.getDiZhi(1, this, this);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        String a = stringResult;
        switch (resultCode) {
            case 1:
                try {
                    getYouHuiJuan();
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        final JSONArray array = object.getJSONArray("data");
                        for (int i = 0; i < array.length(); i++) {
                            if (array.getJSONObject(i).getString("ad_moren").equals("1")) {
                                tv_xingming.setText(array.getJSONObject(i).getString("ad_user"));
                                tv_dianhua.setText(array.getJSONObject(i).getString("ad_tel"));
                                tv_location.setText(array.getJSONObject(i).getString("ad_province") + array.getJSONObject(i).getString("ad_city") +
                                        array.getJSONObject(i).getString("ad_area") + array.getJSONObject(i).getString("ad_address"));
                                chooseDiZhi = true;
                            }
                        }
                    } else {
                        ToastUtils.show(context, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    ToastUtils.show(context, "订单数据有误");
                    finish();
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);
                    e.printStackTrace();
                }
                break;

            case 2:
                CouponlistBean couponlistBean = GsonSingle.getGson().fromJson(stringResult, CouponlistBean.class);
                if (couponlistBean.getData().getIs_use_bonus().equals("1")) {
                    rela_youhuijuan.setClickable(true);
                    tv_youhuijuan.setText("请选择优惠卷");
                } else {
                    //lay_youhui.setBackgroundColor(getResources().getColor(R.color.d_c));
                    tv_youhuijuan.setText("无优惠券可用");
                    //tv_choiceP.setTextColor(getResources().getColor(R.color.eight_four));
                    // iv_danba.setVisibility(View.INVISIBLE);
                    rela_youhuijuan.setClickable(false);
                }
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
               /* try {
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        youhuijuan = object.getJSONArray("data");
                        if (youhuijuan != null && youhuijuan.length() > 0) {
                            if (youhuijuan.getJSONObject(0).getString("cl_keyong").equals("0")) {
                                tv_youhuijuan.setText("选择优惠券");
                            } else {
                                tv_youhuijuan.setText("无可用优惠券");
                                rela_youhuijuan.setClickable(false);
                            }
                        }
                    }


                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);
                    e.printStackTrace();
                }*/
                break;

            case 3:
                try {
                    Log.i("fdfdfsdf", "result=" + stringResult);
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        Intent intent = new Intent(context, HaoWuZhiFuActivity.class);
                        intent.putExtra("ordernum", object.getString("dingdanhao"));
                        intent.putExtra("dingdanid", object.getString("dingdanid"));
                        intent.putExtra("money", lastPrice);
                        intent.putExtra("orderName", smallTitle);
                        intent.putExtra("fenlei", fenlei);
                        intent.putExtra("count", count);
                        intent.putExtra("url", url);
                        Log.i("fdfdfsdf", "11fenlei=" + fenlei);
                        Log.i("fdfdfsdf", "11dingdanid=" + object.getString("dingdanid"));
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtils.show(context, object.getString("msg"));
                    }
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);

                } catch (JSONException e) {
                    view_yinying.setVisibility(View.GONE);
                    view_yuan.setVisibility(View.GONE);

                    e.printStackTrace();
                }
                break;

            case 4:
                Gson gson = GsonSingle.getGson();
                keyBean = gson.fromJson(stringResult, KeyBean.class);
                keyCount = keyBean.getBlackkey();
                keyMonety = keyBean.getBlackkeyTomoney();
                blacksacle = keyBean.getBlacksacle();
                if (keyCount < 0.01) {
                    chek_box.setClickable(false);
                    tv_cankey.setText("可使用" + 0 + "把黑钥匙抵扣" + 0 + "元");
                    loadingView.dismiss();
                    return;
                }
                if (zongjia > keyMonety) {
                    /* 当选择的商品总价大于要是抵扣的情况*/
                    kouKeyCount = keyCount; // 折扣的key个数
                    mkouMoney = keyMonety;// 折扣的钱
                    tv_cankey.setText("可使用" + kouKeyCount + "黑钥匙抵扣" + mkouMoney + "元");
                } else {
                     /* 当选择的商品总价小yu要是抵扣的情况*/
                    kouKeyCount = zongjia * blacksacle;// 折扣的key个数
                    mkouMoney = zongjia;// 折扣的钱
                    tv_cankey.setText("可使用" + ConvertUtil.doubleToSt(kouKeyCount) + "黑钥匙抵扣" + mkouMoney + "元");
                }
                break;


        }
        loadingView.dismiss();
    }

    public void getYouHuiJuan() {
        TurnClassHttp.getUerBonus(w_id, zongjia + "", 2, this, this);
        // TurnClassHttp.getYouHuiJuan(zongjia + "", 2, this, this);
    }

    public void xiadan() {
        String beizhu = id_beizhu.getText().toString();
        lastPrice = tv_zongjia.getText().toString().trim();
        lastPrice = lastPrice.substring(1, lastPrice.length());
        if (beizhu == null) {
            beizhu = "";
        }
        loadingView.show();
        TurnClassHttp.haowuxiadan(smallTitle + "-" + fenlei, lastPrice, w_id, kuaidi, count, fenleiid, youhuijuanid, danjia, kuaidijia, tv_xingming.getText().toString(),
                tv_dianhua.getText().toString(), tv_location.getText().toString(), isKey + "", beizhu, 3, this, this);
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                ToastUtils.show(context, "订单数据有误");
                finish();
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
                break;
            case 2:
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
                break;
            case 3:
                view_yinying.setVisibility(View.GONE);
                view_yuan.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderback:
                finish();
                break;
            case R.id.rela_dizhi:
                Intent intent = new Intent(context, DiZhiListActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.rela_youhuijuan:
              /*  Intent intent1 = new Intent(context, YouHuiJuanActivity.class);
                intent1.putExtra("youhuijuan", youhuijuan.toString());
                startActivityForResult(intent1, 200);*/
                Intent intent1 = new Intent(this, CommentCouponActivity.class);

                intent1.putExtra("gids", w_id);
                intent1.putExtra("amount", zongjia + "");
                intent1.putExtra("cl_id", cl_id);
                startActivityForResult(intent1, 200);
                break;
            case R.id.tv_xiadan:
                if (chooseDiZhi) {
                    view_yinying.setVisibility(View.VISIBLE);
                    view_yuan.setVisibility(View.VISIBLE);

                    xiadan();
                } else {
                    ToastUtils.show(context, "请选择收货地址");
                }
                break;
            case R.id.chek_box:
                if (chek_box.isChecked()) {
                    isKey = kouKeyCount;
                    zongjia = zongjia - mkouMoney;
                } else {
                    isKey = 0.00;
                    zongjia = zongjia + mkouMoney;
                }
                if (zongjia < 0.01) {
                    tv_zongjia.setText("¥" + 0.01);
                } else {
                    tv_zongjia.setText("¥" + ConvertUtil.doubleToSt(zongjia));
                }
                break;
            case R.id.iv_getkey:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            dizhi = data.getStringExtra("sheng") + data.getStringExtra("shi") + data.getStringExtra("qu") + data.getStringExtra("xiangxi");
            dianhua = data.getStringExtra("dianhua");
            xingming = data.getStringExtra("xingming");
            tv_xingming.setText(xingming);
            tv_dianhua.setText(dianhua);
            tv_location.setText(dizhi);
            chooseDiZhi = true;
        } else if (requestCode == 200 && resultCode == 117) {
            //juanPosition = data.getIntExtra("position", -1);
            cl_id = data.getStringExtra("cl_id"); // 优惠券ID
            co_dizhi = data.getStringExtra("co_dizhi");  // 优惠券价钱
            // 优惠卷名称
            name = data.getStringExtra("name");
            if (co_dizhi.equals("")) {
                co_dizhi = "0";
            }
            double youhuiMoney = Double.parseDouble(co_dizhi);
            zongjia = yuanja - youhuiMoney;// 优惠后的价格
            if (zongjia < 0.01) {
                zongjia = 0.01;
            }
            if (keyCount < 0.01) {
                chek_box.setClickable(false);
                tv_cankey.setText("可使用" + 0 + "把黑钥匙抵扣" + 0 + "元");
                tv_zongjia.setText("¥" + zongjia);
                return;
            }
            if (zongjia > keyMonety) {
                    /* 当选择的商品总价大于要是抵扣的情况*/
                kouKeyCount = keyCount; // 折扣的key个数
                mkouMoney = keyMonety;// 折扣的钱
                tv_cankey.setText("可使用" + kouKeyCount + "黑钥匙抵扣" + mkouMoney + "元");
            } else {
                     /* 当选择的商品总价小yu要是抵扣的情况*/
                kouKeyCount = zongjia * blacksacle;// 折扣的key个数

                mkouMoney = zongjia;// 折扣的钱
                tv_cankey.setText("可使用" + ConvertUtil.doubleToSt(kouKeyCount) + "黑钥匙抵扣" + mkouMoney + "元");
            }
            if (chek_box.isChecked()) {/* 当被已经选上了黑钥匙*/
                isKey = kouKeyCount;
                zongjia = zongjia - mkouMoney;
            } else {
                isKey = 0.00;
            }
            if (zongjia < 0.01) {
                tv_zongjia.setText("¥" + 0.01);
            } else {
                tv_zongjia.setText("¥" + ConvertUtil.doubleToSt(zongjia));
            }
            if (co_dizhi.equals("0")) {
                tv_youhuijuan.setText("请选择优惠券");
            } else {
                tv_youhuijuan.setText("使用优惠券: " + name);
            }

            youhuijuanid = data.getStringExtra("cl_id");
            /************************************************************/


        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

            //  Toast.makeText(HaoWuXianDanActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
            lin_xiafang.setVisibility(View.GONE);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            //  Toast.makeText(HaoWuXianDanActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            lin_xiafang.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //  MobclickAgent.onPageStart("hoawuxiadan");
        // MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //  MobclickAgent.onPageEnd("hoawuxiadan");
        // MobclickAgent.onPause(this);
    }
}
