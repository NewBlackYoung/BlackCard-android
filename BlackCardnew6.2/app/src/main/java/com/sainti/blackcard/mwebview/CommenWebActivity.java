
package com.sainti.blackcard.mwebview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.ppdai.loan.PPDLoanAgent;
import com.sainti.blackcard.BuildConfig;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.activity.HaoWuXianDanActivity;
import com.sainti.blackcard.goodthings.bean.HaoWuZhIFuBean;
import com.sainti.blackcard.goodthings.bean.PinganBean;
import com.sainti.blackcard.goodthings.bean.ShoppingCardBean;
import com.sainti.blackcard.goodthings.bean.WxPayBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.commen.mpay.alipay.Malipay;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.FileUtils;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.privilege.bean.GoodthingsDetailBean;
import com.sainti.blackcard.goodthings.spcard.baen.ShoppingCardBaseBean;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.SaintiDialogTwo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class CommenWebActivity extends BaseNoTitleActivity implements View.OnClickListener, OnNetResultListener, Malipay.OnAlipayListener, PopupWindow.OnDismissListener {
    private WebView flxq_WebView;
    private WebSettings setting;
    private LoadingView pd;
    private String w_id, wel_url, title, img_url, name1;
    private LinearLayout lin_xiafang, lin_fenlei1, lin_fenlei2, lin_fenlei3, lin_fenlei4, lin_fenlei5;
    private boolean isSecond = false;
    private boolean isBai = false;
    private ImageView orderback, img_share, iv_jia, iv_jian, iv_tupian, iv_guanbi;
    private String kuaidi = "";
    private String kuaidijia = "";
    private JSONArray fenleiArray = new JSONArray();
    private View view_yinying, view_heiyinying;
    private TextView tv_fenlei1, tv_fenlei2, tv_fenlei3, tv_fenlei4, tv_fenlei5, tv_fenlei6, tv_fenlei7, tvwebtwo, tv_yuanjia, tv_fenlei8, tv_fenlei9, tv_fenlei10, tv_guanjia, tv_danjia, tv_jieshao, tv_zongjia, tv_xiadan, tv_shuliang, tv_kuanshi, tv_queding;
    private ArrayList<TextView> textViewArrayList = new ArrayList<>();
    private Animation animationIn, animationOut;
    private RelativeLayout rela_xiadan;
    private String yuanjia;
    private int count = 1;
    private double zongjia = 0;
    private double danjia = 0;
    private String fenlei = "";
    private String fenleiid = "";
    private int choose = 0;
    private PopupWindow downPoup;
    private SaintiDialogTwo saintiDialog = null;
    private String smallTitle;
    private int counts;
    private String json;
    private TextView tv_addCard;
    private String codeAdd = "";
    private Gson gson;
    private Malipay malipay;
    private LinearLayout ll_l;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessagesAboveL;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private Uri cameraUri;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_CHOOSE = 2;
    private File mPhotoFile;
    private String orderSn;
    private IWXAPI mWxApi;
    private RelativeLayout lay_ll;
    private String orderName;
    private String price;
    private ImageView pi;
    private String open;

    @Override
    public int setLayout() {
        return R.layout.activity_commen_web;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        lin_xiafang = bindView(R.id.lin_xiafang);
        orderback = bindView(R.id.orderback);
        img_share = bindView(R.id.img_share);
        iv_jia = (ImageView) findViewById(R.id.iv_jia);
        iv_jian = (ImageView) findViewById(R.id.iv_jian);
        view_heiyinying = findViewById(R.id.view_heiyinying);
        iv_tupian = (ImageView) findViewById(R.id.iv_tupian);
        iv_guanbi = (ImageView) findViewById(R.id.iv_guanbi);
        lin_fenlei1 = (LinearLayout) findViewById(R.id.lin_fenlei1);
        lin_fenlei2 = (LinearLayout) findViewById(R.id.lin_fenlei2);
        lin_fenlei3 = (LinearLayout) findViewById(R.id.lin_fenlei3);
        lin_fenlei4 = (LinearLayout) findViewById(R.id.lin_fenlei4);
        lin_fenlei5 = (LinearLayout) findViewById(R.id.lin_fenlei5);
        tv_fenlei1 = (TextView) findViewById(R.id.tv_fenlei1);
        tv_fenlei2 = (TextView) findViewById(R.id.tv_fenlei2);
        tv_fenlei3 = (TextView) findViewById(R.id.tv_fenlei3);
        tv_fenlei4 = (TextView) findViewById(R.id.tv_fenlei4);
        tv_fenlei5 = (TextView) findViewById(R.id.tv_fenlei5);
        tv_fenlei6 = (TextView) findViewById(R.id.tv_fenlei6);
        tv_fenlei7 = (TextView) findViewById(R.id.tv_fenlei7);
        tv_fenlei8 = (TextView) findViewById(R.id.tv_fenlei8);
        tv_fenlei9 = (TextView) findViewById(R.id.tv_fenlei9);
        tv_fenlei10 = (TextView) findViewById(R.id.tv_fenlei10);
        tv_guanjia = (TextView) findViewById(R.id.tv_guanjia);
        tv_guanjia.setOnClickListener(this);
        textViewArrayList.add(tv_fenlei1);
        textViewArrayList.add(tv_fenlei2);
        textViewArrayList.add(tv_fenlei3);
        textViewArrayList.add(tv_fenlei4);
        textViewArrayList.add(tv_fenlei5);
        textViewArrayList.add(tv_fenlei6);
        textViewArrayList.add(tv_fenlei7);
        textViewArrayList.add(tv_fenlei8);
        textViewArrayList.add(tv_fenlei9);
        textViewArrayList.add(tv_fenlei10);
        tv_danjia = (TextView) findViewById(R.id.tv_danjia);
        tv_yuanjia = (TextView) findViewById(R.id.tv_yuanjia);
        tv_jieshao = (TextView) findViewById(R.id.tv_jieshao);
        tv_zongjia = (TextView) findViewById(R.id.tv_zongjia);
        tv_xiadan = (TextView) findViewById(R.id.tv_xiadan);
        tv_shuliang = (TextView) findViewById(R.id.tv_shuliang);
        tv_kuanshi = (TextView) findViewById(R.id.tv_kuanshi);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
        rela_xiadan = (RelativeLayout) findViewById(R.id.rela_xiadan);
        tv_queding = (TextView) findViewById(R.id.tv_queding);
        tv_addCard = (TextView) findViewById(R.id.tv_addCard);
        tvwebtwo = (TextView) findViewById(R.id.tvwebtwo);
        ll_l = bindView(R.id.ll_l);
        mWxApi = WXAPIFactory.createWXAPI(this, SataicCode.WXAPP_ID);
        mWxApi.registerApp(SataicCode.WXAPP_ID);
        lay_ll = bindView(R.id.lay_ll);
        pi = bindView(R.id.pi);

    }

    @Override
    protected void initEvents() {
        orderback.setOnClickListener(this);
        iv_jia.setOnClickListener(this);
        iv_jian.setOnClickListener(this);
        img_share.setOnClickListener(this);
        tv_xiadan.setOnClickListener(this);
        view_heiyinying.setOnClickListener(this);
        tv_queding.setOnClickListener(this);
        iv_guanbi.setOnClickListener(this);
        tv_addCard.setOnClickListener(this);
        malipay = new Malipay(this);
        malipay.setmListener(this);
        pi.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        pd = new LoadingView(this);

        String isguanjia = getIntent().getStringExtra("isguanjia");
        if (isguanjia == null) {
            isguanjia = "";
        }
        if (isguanjia.equals("1")) {
            pi.setVisibility(View.VISIBLE);
            lin_xiafang.setVisibility(View.VISIBLE);
        } else {
            pi.setVisibility(View.INVISIBLE);
        }
        // 好物id
        w_id = getIntent().getStringExtra("w_id");
        wel_url = getIntent().getStringExtra("wel_url");
        title = getIntent().getStringExtra("title");
        img_url = getIntent().getStringExtra("img_url");
        name1 = getIntent().getStringExtra("name1");
        smallTitle = getIntent().getStringExtra("small_title");
        //  MLog.d("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPP",smallTitle);
        if (title.equals("") && img_url.equals("") && name1.equals("") && smallTitle.equals("")) {
            ll_l.setVisibility(View.INVISIBLE);
        } else {
            ll_l.setVisibility(View.VISIBLE);
        }
        if (w_id.equals("")) {
            lin_xiafang.setVisibility(View.GONE);
            getWebView();
        } else {
            // 获取好物购物车数据
            TurnClassHttp.getWelcartDate(6, this, this); // 获取好物购物车数据
            getWebView();
            getDate();
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
            map_ekv0.put("haowuname", smallTitle);
            MobclickAgent.onEvent(this, "haowulianjie", map_ekv0);// 友盟统计
        }
        downPopupWindow();
        List<GoodthingsBean> allLists = GoodThingsDao.getsInstance(this).queryForPayState("0"); // 查询所有数据传给后台
        MLog.d("数据", "原有的数据allLists One====================" + allLists.size() + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_queding:
                if (codeAdd.equals("1")) {
                    pd.show();
                    if (fenleiid.equals("")) {
                        fenleiid = "0";
                    }
                    addShopCard();

                } else {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("hoawuname", title);
                    MobclickAgent.onEvent(this, "haowulijigoumai", map_ekv0);// 友盟统计
                    zongjia = danjia * count;
                    Intent intent1 = new Intent(this, HaoWuXianDanActivity.class);
                    intent1.putExtra("smallTitles", smallTitle);
                    intent1.putExtra("title", title);
                    intent1.putExtra("count", count + "");
                    intent1.putExtra("zongjia", zongjia + "");
                    intent1.putExtra("danjia", danjia + "");
                    intent1.putExtra("url", img_url);
                    intent1.putExtra("fenlei", fenlei);
                    intent1.putExtra("fenleiid", fenleiid);
                    intent1.putExtra("w_id", w_id);
                    intent1.putExtra("yuanjia", yuanjia);
                    intent1.putExtra("kuaidi", kuaidi);
                    intent1.putExtra("kuaidijia", kuaidijia);
                    startActivity(intent1);
                    finish();
                }

                break;
            case R.id.iv_guanbi:
                animationOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
                view_heiyinying.setVisibility(View.GONE);
                rela_xiadan.setAnimation(animationOut);
                rela_xiadan.setVisibility(View.GONE);
                codeAdd = "";
                break;
            case R.id.iv_jia:
                count++;
                tv_shuliang.setText(count + "");
                break;
            case R.id.iv_jian:
                if (count > 1) {
                    count--;
                    tv_shuliang.setText(count + "");
                }
                break;
            case R.id.tv_xiadan:
                animationIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
                view_heiyinying.setVisibility(View.VISIBLE);
                rela_xiadan.setAnimation(animationIn);
                rela_xiadan.setVisibility(View.VISIBLE);

                break;
            case R.id.view_heiyinying:
                animationOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
                view_heiyinying.setVisibility(View.GONE);
                rela_xiadan.setAnimation(animationOut);
                rela_xiadan.setVisibility(View.GONE);
                codeAdd = "";
                break;
            case R.id.orderback:
                if (flxq_WebView.canGoBack()) {
                    isSecond = false;
                    flxq_WebView.goBack();// 返回前一个页面
                    img_share.setVisibility(View.VISIBLE);
                } else {
                    setResult(100);
                    finish();
                }
                break;
            case R.id.img_share:
                showShare();
                break;

            case R.id.tv_guanjia:
                toHuanxin();
                break;
            case R.id.tv_addCard:
                animationIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
                view_heiyinying.setVisibility(View.VISIBLE);
                rela_xiadan.setAnimation(animationIn);
                rela_xiadan.setVisibility(View.VISIBLE);
                codeAdd = "1";
                break;
            case R.id.pi:
                toHuanxin();
                break;
        }

    }

    private void toHuanxin() {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
            map_ekv0.put("rukou", "haowuxiangqingye");
            MobclickAgent.onEvent(CommenWebActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
            //已经登录，可以直接进入会话界面
            Intent intent = new IntentBuilder(CommenWebActivity.this)
                    .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                    .build()
                    .putExtra("content", wel_url)
                    .putExtra("title", title);
            startActivity(intent);


        } else {
            //未登录，需要登录后，再进入会话界面
            ToastUtils.show(CommenWebActivity.this, "登陆失败");
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(smallTitle);
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(wel_url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(title);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(img_url);
        // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(wel_url);
        // comment是我对这条分享的评论，仅在人人网使用
        //   oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(this);
    }

    private void getDate() {
        pd.show();
        TurnClassHttp.getHaoWuXiangQing(w_id, 1, this, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                GoodthingsDetailBean goodthingsDetailBean = gson.fromJson(stringResult, GoodthingsDetailBean.class);
                try {
                    Log.i("character", "result=" + stringResult);
                    JSONObject object = new JSONObject(stringResult);
                    if (object.getString("result").equals("1")) {
                        JSONObject data = object.getJSONObject("data");
                        if (!data.getString("kuaidiname").toString().equals("")) {
                            kuaidi = data.getString("kuaidiname") + "包邮";
                            kuaidijia = data.getString("kuaidi");
                        } else {
                            kuaidi = data.getString("kuaidi2");
                            kuaidijia = data.getString("kuaidi");
                        }
                        if (data.getJSONArray("w_type") != null && data.getJSONArray("w_type").length() > 0) {
                            fenleiArray = data.getJSONArray("w_type");
                        }
                        yuanjia = data.getString("w_yuanjia");
                        danjia = Double.parseDouble(data.getString("w_jiage"));
                        tv_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                        tv_yuanjia.setText(yuanjia);
                        tv_zongjia.setText("¥ " + danjia + " /" + data.getString("danwei"));
                        tv_danjia.setText("价格:  ¥" + danjia);
                        tv_yuanjia.setText(yuanjia + "");
                        tvwebtwo.setText("详情");
                        title = data.getString("w_title");
                        img_url = data.getString("w_pic");
                        name1 = data.getString("w_title2");
                        smallTitle = data.getString("w_title3");
                        open = data.getString("open");
                        if (open.equals("0")) {
                            lin_xiafang.setVisibility(View.VISIBLE);
                        } else {
                            lin_xiafang.setVisibility(View.GONE);
                        }
                        GlideManager.getsInstance().loadImageToUrL(this, img_url, iv_tupian);
                        tv_jieshao.setText(smallTitle);
                        if (fenleiArray != null && fenleiArray.length() > 0) {
                            tv_kuanshi.setVisibility(View.VISIBLE);
                            for (int i = 0; i < fenleiArray.length(); i++) {
                                fenlei = fenleiArray.getJSONObject(0).getString("wtype_name");
                                fenleiid = fenleiArray.getJSONObject(0).getString("w_typeid");
                                if (fenleiid.equals("")) {
                                    fenleiid = "0";
                                }
                                try {
                                    textViewArrayList.get(i).setVisibility(View.VISIBLE);
                                    textViewArrayList.get(i).setText(fenleiArray.getJSONObject(i).getString("wtype_name"));
                                    final int finalI = i;
                                    tv_danjia.setText("价格:  ¥" + fenleiArray.getJSONObject(0).getString("wtype_price"));
                                    textViewArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (choose != finalI) {
                                                try {
                                                    textViewArrayList.get(choose).setBackgroundResource(R.drawable.bg_corner_shenhuixianer);
                                                    textViewArrayList.get(choose).setTextColor(getResources().getColor(R.color.shenhui));
                                                    textViewArrayList.get(finalI).setBackgroundResource(R.drawable.bg_corner_huangquaner);
                                                    textViewArrayList.get(finalI).setTextColor(getResources().getColor(R.color.white));
                                                    choose = finalI;
                                                    if (!fenleiArray.getJSONObject(finalI).getString("wtype_img").equals("")) {
                                                        GlideManager.getsInstance().loadImageToUrL(CommenWebActivity.this, fenleiArray.getJSONObject(finalI).getString("wtype_img"), iv_tupian);
                                                        img_url = fenleiArray.getJSONObject(finalI).getString("wtype_img");
                                                    }
                                                    fenlei = fenleiArray.getJSONObject(finalI).getString("wtype_name");
                                                    fenleiid = fenleiArray.getJSONObject(finalI).getString("w_typeid");
                                                    tv_danjia.setText("价格:  ¥" + fenleiArray.getJSONObject(finalI).getString("wtype_price"));
                                                    danjia = Double.parseDouble(fenleiArray.getJSONObject(finalI).getString("wtype_price"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        getWebView();
                    } else {
                        ToastUtils.show(this, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    finish();
                    view_yinying.setVisibility(View.GONE);

                    e.printStackTrace();
                }
                break;
            case 2:
                pd.dismiss();
                ShoppingCardBaseBean baseBean = gson.fromJson(stringResult, ShoppingCardBaseBean.class);
                if (baseBean.getResult().equals("1")) {

                    ToastUtils.show(this, "添加成功");
          /*  Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();*/
                }
                break;
            case 3:// 支付宝支付
                HaoWuZhIFuBean fuBean = GsonSingle.getGson().fromJson(stringResult, HaoWuZhIFuBean.class);
                if (fuBean != null) {
                    malipay.pay(fuBean.getData());
                }
                break;
            case 4:// 微信支付
                WxPayBean wxPayBean = GsonSingle.getGson().fromJson(stringResult, WxPayBean.class);
                if (wxPayBean.getResult().equals("1")) {
                    onSendTOWx(wxPayBean.getData());
                }

                break;
            case 5:// 余额支付
                PinganBean pinganBean = GsonSingle.getGson().fromJson(stringResult, PinganBean.class);
                if (pinganBean != null && pinganBean.getResult().equals("1")) {
                    Intent intent = new Intent(this, PingAnPayWebView.class);
                    intent.putExtra("xh_url", pinganBean.getPay_url());
                    intent.putExtra("payCode", "5");// 1 是管家支付
                    startActivity(intent);
                }
                break;
            case 6:
                ShoppingCardBaseBean shoppingCardBaseBean = gson.fromJson(stringResult, ShoppingCardBaseBean.class);
                if (shoppingCardBaseBean.getData() == null || shoppingCardBaseBean.getData().equals("")) {
                    return;
                } else {

                    ShoppingCardBean shoppingCardBean = gson.fromJson(stringResult, ShoppingCardBean.class);
                    addCardDate(shoppingCardBean);// 添加商品到数据库
                }

                break;
        }
        pd.dismiss();

    }

    // 添加好物商品
    private void addCardDate(ShoppingCardBean shoppingCardBean) {
        GoodThingsDao.getsInstance(this).deleteAll();
        for (int i = 0; i < shoppingCardBean.getData().size(); i++) {
            GoodthingsBean s = new GoodthingsBean();
            s.setwId(shoppingCardBean.getData().get(i).getW_id()); // 添加好物ID
            s.setTingsCount(shoppingCardBean.getData().get(i).getW_num());
            s.setTingsId(shoppingCardBean.getData().get(i).getW_id() + shoppingCardBean.getData().get(i).getW_kindid()); // 将好物id和分类id拼接在一起
            s.setTingsType(shoppingCardBean.getData().get(i).getW_sale());
            s.setTingsPrice(shoppingCardBean.getData().get(i).getW_price());
            s.setTingsYuanjia(shoppingCardBean.getData().get(i).getW_o_price()); // 添加原价
            s.setTingsName(shoppingCardBean.getData().get(i).getW_name());// 添加添加姓名
            s.setImageurl(shoppingCardBean.getData().get(i).getW_img());
            s.setIsChoice("0");// 0 未被选择状态
            s.setIsPay("0");// 0 未被选择状态
            s.setTingsKind(shoppingCardBean.getData().get(i).getW_kind());
            s.setTingsKindId(shoppingCardBean.getData().get(i).getW_kindid());
            GoodThingsDao.getsInstance(this).add(s);
        }
        List<GoodthingsBean> allLists = GoodThingsDao.getsInstance(this).queryForPayState("0"); // 查询所有数据传给后台
        MLog.d("数据", "拉取后购物车的数据 One====================" + allLists.size() + "");
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

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        pd.dismiss();

        switch (resultCode) {
            case 1:
                break;
            case 2:
                ToastUtils.show(this, "添加购物车失败");
                pd.dismiss();
                break;
        }
        String a = errMsg;
    }

    private void addShopCard() {

        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("hoawuname", smallTitle);
        MobclickAgent.onEvent(this, "jiarugouwuche", map_ekv0);// 友盟统计
        List<GoodthingsBean> allLists = GoodThingsDao.getsInstance(this).queryForPayState("0"); // 查询所有数据传给后台
        MLog.d("数据", "原有的数据allLists====================" + allLists.size() + "");
        List<GoodthingsBean> goodthingsBeanList = GoodThingsDao.getsInstance(this).queryForContent(w_id + fenleiid); // 通过商品ID查数据库商品
        if (goodthingsBeanList != null && goodthingsBeanList.size() > 0) {
            String countStr = goodthingsBeanList.get(0).getTingsCount();// 获取商品数量count
            GoodThingsDao.getsInstance(this).deleteOne(w_id + fenleiid); // 删除当前好物商品
            counts = Integer.parseInt(countStr) + count; // 增加商品数量
        } else {
            counts = count;
        }
        GoodthingsBean s = new GoodthingsBean();
        s.setTingsCount(String.valueOf(counts));// 添加count
        s.setwId(w_id); // 添加好物ID
        s.setTingsId(w_id + fenleiid);// 添加好物ID和分类id 拼接在一起
        s.setTingsPrice(danjia + "");// 添加好物单价
        s.setTingsType("0");// 添加好物是否已经下架code 0未下架  1已经下架
        s.setTingsYuanjia(yuanjia); // 添加原价
        s.setTingsName(smallTitle);// 添加添加姓名
        s.setImageurl(img_url);
        s.setIsChoice("0");// 0 未被选择状态
        s.setIsPay("0");// 0 未被选择状态
        s.setTingsKind(fenlei);
        s.setTingsKindId(fenleiid);
        GoodThingsDao.getsInstance(this).add(s); // 添加对应商品到数据库
        List<GoodthingsBean> allList = GoodThingsDao.getsInstance(this).queryForPayState("0"); // 查询所有数据传给后台
        MLog.d("数据", "allLists====================" + allList.size() + "");
        try {
            json = changeArrayDateToJson(allList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TurnClassHttp.addWelcart(json, 2, this, this);
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

    private void getWebView() {
        flxq_WebView = (WebView) findViewById(R.id.flxq_WebView);
        flxq_WebView.getSettings().setUserAgentString("blackyoung");
        setting = flxq_WebView.getSettings();
        flxq_WebView.setHorizontalScrollbarOverlay(false);
        flxq_WebView.setHorizontalScrollBarEnabled(false);
        flxq_WebView.requestFocus();
        flxq_WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
        /**
         * 设置js调用Android的接口
         */
        flxq_WebView.addJavascriptInterface(new JsInteration(), "control");
        /**
         * 提供网页加载各阶段的通 知
         * */
        flxq_WebView.setWebViewClient(new MyWebViewClient());
        /**
         * 提供网页加载过程中提供的数据内容
         * */
        flxq_WebView.setWebChromeClient(new MyWebChromeClient());
        String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        String token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");

        if (!wel_url.contains("token")) {
            if (wel_url.contains("?")) {
                wel_url = wel_url + "&uid=" + uid + "&token=" + token;
            } else {
                wel_url = wel_url + "?uid=" + uid + "&token=" + token;
            }
        }
        flxq_WebView.loadUrl(wel_url);
        Log.i("wufhsdbfsd", "newurl=" + wel_url);
        //flxq_WebView.loadUrl(wel_url + "?uid=" + uid + "&token=" + token);

    }

    @Override
    public void onSuccess() {
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("payment", "zhifubao");
        MobclickAgent.onEvent(this, "pay_success", map_ekv0);// 友盟统计
        ToastUtils.show(this, "支付成功");

    }

    @Override
    public void onCancel() {
        ToastUtils.show(this, "支付失败");
    }

    @Override
    public void onWait() {

    }

    @Override
    public void onDismiss() {

    }


    class MyWebViewClient extends WebViewClient {
        /**
         * 内核加载完当前页面时会通知我们的应用程序
         * 在页面加载完毕后调用网页的js,确保js完全加载,Android调用时能调用到
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            setting.setBlockNetworkImage(false);//false下载图片
            //try是处理getBooleanQueryParameter返回数据越界时的异常
            try {
                //这里是判断 , 当url的参数main_page 不为空 , 且等于checkout_confirmation或者checkout_payment
                //时 , 将调用JavaScript的响应方法 , 如果URL没有main_page参数 , 将调用JavaScript的onSumResult,
                //并且传入 "我传onSumResult给js" 这个值,注意书写格式
                if (Uri.parse(url).getBooleanQueryParameter("main_page", false)) {
                    //根据URL参数判断获取预支付订单
                    if (Uri.parse(url).getQueryParameter("main_page").equals("checkout_confirmation")) {
                        //这里调用js里面的方法去请求预支付订单
                        String call = "javascript：payInfoMessage()";
                        flxq_WebView.loadUrl(call);
                    } else if (Uri.parse(url).getQueryParameter("main_page").equals("checkout_payment")) {
                        //这里调用js里面的方法去说明是移动应用访问服务器
                        String call = "javascript：checkPaymentApp(\"" + "android" + "\")";
                        flxq_WebView.loadUrl(call);
                    } else {
                        String call = "javascript：onSumResult(\"" + "我传onSumResult给js" + "\")";
                        flxq_WebView.loadUrl(call);
                    }
                }
            } catch (Exception e) {
            }
        }

        /**
         * 内核加载当前主框架开始时调用
         * 在网页还没被加载之前设置setting , 确保网页以最快速度加载 , 增加用户体验
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i("yyyyyzzzzzppp", "url=" + url);
            setting.setDefaultTextEncodingName("UTF-8");//设置字符编码
            setting.setJavaScriptEnabled(true);//true加载JavaScript
            setting.setJavaScriptCanOpenWindowsAutomatically(true);
            setting.setCacheMode(WebSettings.LOAD_DEFAULT);//是否覆盖缓存
            setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
            setting.setAppCacheEnabled(true);
            setting.setDomStorageEnabled(true);
            setting.setAllowFileAccess(true);
            setting.setSupportMultipleWindows(true);
            setting.setDatabaseEnabled(true);
            setting.setBlockNetworkImage(true);//true不下载图片
            setting.setCacheMode(WebSettings.LOAD_DEFAULT);
            setting.setSavePassword(false);
            setting.setUseWideViewPort(true);
            setting.setLoadWithOverviewMode(true);
            setting.setGeolocationEnabled(true);
        }

        /**
         * 网页是否由webView显示,返回true是由浏览器显示,返回false由webView显示
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) return false;
            try {
                Log.i("wufhsdbfsd", "url=" + url);
                if (url.startsWith("weixin://") || url.startsWith("alipays://") ||
                        url.startsWith("mailto://") || url.startsWith("tel://")
                    //其他自定义的scheme
                        ) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
            String token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
            if (!url.contains("token")) {
                if (url.contains("?")) {
                    url = url + "&uid=" + uid + "&token=" + token;
                } else {
                    url = url + "?uid=" + uid + "&token=" + token;
                }
            }
            if (url.contains("Welfare")) {
                toComment(url);
            } else {
                flxq_WebView.loadUrl(url);
            }
            Log.i("wufhsdbfsd", "newurl=" + url);
            return true;
        }


        /**
         * 如果网页加载失败(超时 , 没有网络等) , 在这里处理
         *
         * @param view
         * @param errorCode
         * @param description
         * @param failingUrl
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.i("yyyyyzzzzzppp", "errorCode=" + errorCode);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed(); // 信任所有的证书   默认是handler.cancle(),即不做处理
        }

        /**
         * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);

        }
    }

    /**
     * js调用和传递的数据
     * 类JsInteration里面的方法被js调用
     * 以下三个接口都是提供给js调用的接口
     * -------注意, 一定要加 @JavascriptInterface 在每个方法上面,否则无法执行
     */
    public class JsInteration {
        @JavascriptInterface
        public void payInfoMessage(String payInfoJson) {

            //js将返回payInfoJson 信息 , 具体是什么信息 , 需要js传入 , 我这里传入的是json
            //获取payInfoJson之后 , 就可以在这里使用了 , 最好用handler
            Log.i("fsdfsdfds", payInfoJson);
            try {
                JSONObject data = new JSONObject(payInfoJson);
                orderSn = data.getString("Ordersn");
                orderName = data.getString("Ordername");
                price = data.getString("Orderprice");
                downPoup.showAtLocation(lay_ll, Gravity.BOTTOM, 0, 0);
                //  pay(data.getString("Orderprice"), data.getString("Ordersn"), data.getString("Ordername"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @JavascriptInterface
        public void checkPaymentApp(String validate) {

        }

        @JavascriptInterface
        public void onSumResult(int result) {

        }
    }

    final class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if (newProgress != 100) {
                isBai = false;
            }
            if (newProgress == 100) {

                if (!isBai) {
                    if (isSecond) {
                        // img_share.setVisibility(View.GONE);
                        //  img_talk.setVisibility(View.GONE);
                    } else {
                        isSecond = true;
                    }
                    isBai = true;
                }
            }
        }

        /**
         * 处理js返回的Alter他框
         *
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return super.onJsAlert(view, url, message, result);
        }

        /**
         * 处理js返回的Confirm框
         *
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 处理js返回的Prompt框
         *
         * @param view
         * @param url
         * @param message
         * @param defaultValue
         * @param result
         * @return
         */
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            result.confirm();
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }


        /**
         * 获取网页标题
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

            if (mUploadMessage != null) return;
            mUploadMessage = uploadMsg;
            selectImage();
        }

        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        // For Android  > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }

        // For Android 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

            if (mUploadMessagesAboveL != null) {
                mUploadMessagesAboveL.onReceiveValue(null);
            } else {
                mUploadMessagesAboveL = filePathCallback;
                selectImage();
            }
            return true;
        }
    }

    private void selectImage() {
        if (!FileUtils.checkSDcard(this)) {
            return;
        }
        String[] selectPicTypeStr = {"拍照", "图库"};
        new AlertDialog.Builder(this)
                .setOnCancelListener(new ReOnCancelListener())
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    // 相机拍摄
                                    case 0:
                                        if (ContextCompat.checkSelfPermission(CommenWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(CommenWebActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

                                        } else {
                                            openCamera();
                                        }

//                                        openCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        if (ContextCompat.checkSelfPermission(CommenWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(CommenWebActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                                        } else {
                                            chosePicture();
                                        }
//                                        chosePicture();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
    }

    /**
     * 本地相册选择图片
     */
    private void chosePicture() {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, REQUEST_CHOOSE);
    }

    /**
     * dialog监听类
     */
    private class ReOnCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }

            if (mUploadMessagesAboveL != null) {
                mUploadMessagesAboveL.onReceiveValue(null);
                mUploadMessagesAboveL = null;
            }
        }
    }

    /**
     * 打开照相机
     */
    private void openCamera() {
        Intent intent = new Intent();
        mPhotoFile = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        String imagePaths = Environment.getExternalStorageDirectory() + "/temp/" + System.currentTimeMillis() + ".jpg";
        File vFile = new File(imagePaths);
        if (!vFile.getParentFile().exists()) vFile.getParentFile().mkdirs();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            cameraUri = FileProvider.getUriForFile(this, "com.sainti.blackcard.fileprovider", mPhotoFile);
            this.grantUriPermission(BuildConfig.APPLICATION_ID, cameraUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        } else {
            cameraUri = Uri.fromFile(vFile);
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    @Override
    protected void onResume() {
        super.onResume();
        PPDLoanAgent.getInstance().onLaunchResume(this);

    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                // Permission Denied
                Toast.makeText(CommenWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chosePicture();
            } else {
                // Permission Denied
                Toast.makeText(CommenWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 返回文件选择
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (mUploadMessagesAboveL != null) {
            onActivityResultAboveL(requestCode, resultCode, intent);
        }

        if (mUploadMessage == null) return;

        Uri uri = null;

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            uri = cameraUri;
        }

        if (requestCode == REQUEST_CHOOSE && resultCode == RESULT_OK) {
            uri = afterChosePic(intent);
        }

        if (mPhotoFile != null && Build.VERSION.SDK_INT >= 24) {
            //适配Android7.0拍照返回图片处理
            uri = Uri.fromFile(mPhotoFile);
            mUploadMessagesAboveL.onReceiveValue(new Uri[]{uri});
        }

        mUploadMessage.onReceiveValue(uri);
        mUploadMessage = null;
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 5.0以后机型 返回文件选择
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {

        Uri[] results = null;

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            results = new Uri[]{cameraUri};
        }

        if (requestCode == REQUEST_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                String dataString = data.getDataString();
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }

        mUploadMessagesAboveL.onReceiveValue(results);
        mUploadMessagesAboveL = null;
        return;
    }

    /**
     * 选择照片后结束
     *
     * @param data
     */
    private Uri afterChosePic(Intent data) {
        if (data != null) {
            final String path = data.getData().getPath();
            if (path != null && (path.endsWith(".png") || path.endsWith(".PNG") || path.endsWith(".jpg") || path.endsWith(".JPG"))) {
                return data.getData();
            } else {
                Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }


    private void toComment(String url) {
        Intent intent = new Intent(this, CommenWebActivity.class);
        intent.putExtra("wel_url", url);
        intent.putExtra("title", "标题");
        intent.putExtra("img_url", "标题");
        intent.putExtra("name1", "标题");
        intent.putExtra("small_title", "标题");
        if (url.contains("Welfare")) {
            String newS = url.substring(url.indexOf("id/"));
            intent.putExtra("w_id", newS.substring(3, newS.length()));
        } else {
            intent.putExtra("w_id", "");
        }
        startActivity(intent);
    }

    /*************
     * 选择商品分类列表downPopupWindow
     ***************/
    private void downPopupWindow() {
        downPoup = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        downPoup.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        View view = LayoutInflater.from(this).inflate(R.layout.view_popu_guanjia, null);
        RelativeLayout lay_zhifubao = (RelativeLayout) view.findViewById(R.id.lay_zhifubao);
        RelativeLayout lay_weixin = (RelativeLayout) view.findViewById(R.id.lay_weixin);
        RelativeLayout lay_yue = (RelativeLayout) view.findViewById(R.id.lay_yue);
        lay_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingView.show();
                TurnClassHttp.pingan_pay(orderSn, 5, CommenWebActivity.this, CommenWebActivity.this);
            }
        });
        lay_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float lastPrcies = ConvertUtil.convertToFloat(price) * 100;
                int a = (int) lastPrcies;
                TurnClassHttp.getApporder(a + "", orderSn, orderName, "android", ConvertUtil.getTime(), 4, CommenWebActivity.this, CommenWebActivity.this);

            }
        });
        lay_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loadingView.show();
                TurnClassHttp.getHaoWuDanHao(orderName, orderName, orderSn, price, 3, CommenWebActivity.this, CommenWebActivity.this);

            }
        });

        downPoup.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        downPoup.setAnimationStyle(R.style.popwin_liebiao_style);
        downPoup.setContentView(view);
        downPoup.setOnDismissListener(this);
        downPoup.setFocusable(true);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        // 支付成功
        if (messageEvent.getMessageCode().equals("0")) {
            Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("payment", "weixin");
            MobclickAgent.onEvent(this, "pay_success", map_ekv0);// 友盟统计
        }
        // 取消支付
        if (messageEvent.getMessageCode().equals("-2")) {
            Toast.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
        }
        // 支付失败
        if (messageEvent.getMessageCode().equals("1")) {
            Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
