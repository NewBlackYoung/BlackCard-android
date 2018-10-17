package com.sainti.blackcard.mwebview;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.coffee.coffeeorder.activity.CoffeeOrderListActivity;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.goodtingsorder.activity.GoodThingOsderActivity;
import com.sainti.blackcard.housekeeperorder.activity.HousekeeperOrderActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PingAnPayWebView extends BaseTitleActivity implements TimerListener, OnNetResultListener {

    private WebSettings setting;
    private WebView webView;
    private LoadingView progressDialog; // 处理WebView加载网页的loading过程
    private String xh_url;
    private String payCode;
    private String json;
    private List<GoodthingsBean> allList;

    @Override
    public int setLayout() {
        return R.layout.activity_ping_an_pay_web_view;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_Detail);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("黑卡余额支付");
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressDialog = new LoadingView(this);
        xh_url = getIntent().getStringExtra("xh_url");
        // 支付方式code 1 是管家支付 2是好物支付 3 是coffee支付
        payCode = getIntent().getStringExtra("payCode");

        //  webView.loadUrl(xh_url);
        getWebView();
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面

                } else {
                    finish();
                }
            }
        });
    }

    private void getWebView() {
        webView.getSettings().setUserAgentString("blackyoung");
        setting = webView.getSettings();
        webView.setHorizontalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.requestFocus();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
        /**
         * 设置js调用Android的接口
         */
        webView.addJavascriptInterface(new PingAnPayWebView.JsInteration(), "control");
        /**
         * 提供网页加载各阶段的通 知
         * */
        webView.setWebViewClient(new PingAnPayWebView.MyWebViewClient());
        /**
         * 提供网页加载过程中提供的数据内容
         * */
        webView.setWebChromeClient(new PingAnPayWebView.MyWebChromeClient());

        webView.loadUrl(xh_url);

    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

        switch (resultCode) {
            case 1:
                if (payCode.equals("3")) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(PingAnPayWebView.this, CoffeeOrderListActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case 2:
                BaseBean baseBean = GsonSingle.getGson().fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(PingAnPayWebView.this, GoodThingOsderActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
        String a = stringResult;


    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        String a = errMsg;
    }


    /**
     * js调用和传递的数据
     * 类JsInteration里面的方法被js调用
     * 以下三个接口都是提供给js调用的接口
     * -------注意, 一定要加 @JavascriptInterface 在每个方法上面,否则无法执行
     */
    public class JsInteration implements TimerListener {
        @JavascriptInterface
        public void jump(String payInfoJson) {
            handler.sendEmptyMessage(0);

        }


        @JavascriptInterface
        public void checkPaymentApp(String validate) {
            String v = validate;
            String a = v;
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            int a = result;
            int b = a;
        }

        @Override
        public void onTimerListener() {


        }

    }

    //消息处理者,创建一个Handler的子类对象,目的是重写Handler的处理消息的方法(handleMessage())
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressDialog.show();
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            map_ekv0.put("payment", "pingan");
            MobclickAgent.onEvent(context, "pay_success", map_ekv0);// 友盟统计
            // 支付方式code 1 是管家支付 2是好物支付 3 是coffee支付
            if (payCode.equals("1")) {
                EventBus.getDefault().post(new NormalEventBean(SataicCode.PAYSUCESSGUANJIA));
                CommontUtil.lateTime(1000, PingAnPayWebView.this);
            }
            if (payCode.equals("2")) {
                EventBus.getDefault().post(new NormalEventBean(SataicCode.PAYSUCESSHAOWU));
                CommontUtil.lateTime(1000, PingAnPayWebView.this);
            }
            if (payCode.equals("3")) {
                EventBus.getDefault().post(new NormalEventBean(SataicCode.PAYSUCESSCOFFEE));
                CommontUtil.lateTime(1000, PingAnPayWebView.this);
            }
            if (payCode.equals("4")) {
                CommontUtil.lateTime(1000, PingAnPayWebView.this);
            }
            if (payCode.equals("5")){
                CommontUtil.lateTime(1000, PingAnPayWebView.this);
            }

        }
    };

    @Override
    public void onTimerListener() {

        MobclickAgent.onEvent(context, "  pinganshiyong");// 友盟统计

        // 支付方式code 1 是管家支付 2是好物支付 3 是coffee支付
        if (payCode.equals("1")) {
            progressDialog.dismiss();
            Intent intent = new Intent(PingAnPayWebView.this, HousekeeperOrderActivity.class);
            startActivity(intent);
            finish();

        }
        if (payCode.equals("2")) {
            allList = GoodThingsDao.getsInstance(this).queryForPayState("0");
            if (allList != null && allList.size() > 0) {
                toService();
            } else {
                progressDialog.dismiss();
                Intent intent = new Intent(PingAnPayWebView.this, GoodThingOsderActivity.class);
                startActivity(intent);
                finish();
            }

        }
        if (payCode.equals("3")) {
            String json = getIntent().getStringExtra("json");
            TurnClassHttp.putOrderInfo(json, 1, PingAnPayWebView.this, PingAnPayWebView.this);
        }

        if (payCode.equals("4")) {
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISFINISH, true).commit();
            finish();
        }
        if (payCode.equals("5")){
            SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.GUANJIA, SpCodeName.REFRESH).commit();
            finish();
        }
    }

    private void toService() {
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
                        String call = "javascript：jump()";
                        webView.loadUrl(call);
                    } else if (Uri.parse(url).getQueryParameter("main_page").equals("checkout_payment")) {
                        //这里调用js里面的方法去说明是移动应用访问服务器
                        String call = "javascript：checkPaymentApp(\"" + "android" + "\")";
                        webView.loadUrl(call);
                    } else {
                        String call = "javascript：onSumResult(\"" + "我传onSumResult给js" + "\")";
                        webView.loadUrl(call);
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
            return super.shouldOverrideUrlLoading(view, url);
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

    final class MyWebChromeClient extends WebChromeClient {
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

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

    }


}
