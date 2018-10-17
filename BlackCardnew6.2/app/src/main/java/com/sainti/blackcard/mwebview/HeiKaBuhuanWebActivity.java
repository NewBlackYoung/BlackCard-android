package com.sainti.blackcard.mwebview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
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

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HeiKaBuhuanWebActivity extends BaseTitleActivity {
    private WebView flxq_WebView;
    private String wel_url;
    private WebSettings setting;

    @Override
    public int setLayout() {
        return R.layout.activity_hei_ka_buhuan_web;
    }

    @Override
    protected void initView() {
        flxq_WebView = bindView(R.id.web_ppd);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("青年黑卡黑卡补换");
        wel_url = getIntent().getStringExtra("wel_url");
        getWebView();
    }

    private void getWebView() {

        flxq_WebView.getSettings().setUserAgentString("blackyoung");
        setting = flxq_WebView.getSettings();
        flxq_WebView.setHorizontalScrollbarOverlay(false);
        flxq_WebView.setHorizontalScrollBarEnabled(false);
        flxq_WebView.requestFocus();
        flxq_WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
        /**
         * 设置js调用Android的接口
         */
        flxq_WebView.addJavascriptInterface(new HeiKaBuhuanWebActivity.JsInteration(), "control");
        /**
         * 提供网页加载各阶段的通 知
         * */
        flxq_WebView.setWebViewClient(new HeiKaBuhuanWebActivity.MyWebViewClient());
        /**
         * 提供网页加载过程中提供的数据内容
         * */
        flxq_WebView.setWebChromeClient(new HeiKaBuhuanWebActivity.MyWebChromeClient());

        flxq_WebView.loadUrl(wel_url);

    }

    /**
     * js调用和传递的数据
     * 类JsInteration里面的方法被js调用
     * 以下三个接口都是提供给js调用的接口
     * -------注意, 一定要加 @JavascriptInterface 在每个方法上面,否则无法执行
     */
    public class JsInteration {
        @JavascriptInterface
        public void levelInfoMessage(String payInfoJson) {
            //js将返回payInfoJson 信息 , 具体是什么信息 , 需要js传入 , 我这里传入的是json
            //获取payInfoJson之后 , 就可以在这里使用了 , 最好用handler
            //js将返回payInfoJson 信息 , 具体是什么信息 , 需要js传入 , 我这里传入的是json
            //获取payInfoJson之后 , 就可以在这里使用了 , 最好用handler、
            JSONObject data = null;
            try {
                data = new JSONObject(payInfoJson);
                String stringTalk = data.getString("text");
                // 我要订机票
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "heikabuhuan");
                    MobclickAgent.onEvent(HeiKaBuhuanWebActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    //已经登录，可以直接进入会话界面
                    Intent intents = new IntentBuilder(context)
                            .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                            .build()
                            .putExtra("type", "4")
                            .putExtra("talk", stringTalk);
                    startActivity(intents);
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(context, "登陆失败");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


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
                        String call = "javascript:levelInfoMessage()";
                        flxq_WebView.loadUrl(call);
                    } else if (Uri.parse(url).getQueryParameter("main_page").equals("checkout_payment")) {
                        //这里调用js里面的方法去说明是移动应用访问服务器
                        String call = "javascript:checkPaymentApp(\"" + "android" + "\")";
                        flxq_WebView.loadUrl(call);
                    } else {
                        String call = "javascript:onSumResult(\"" + "我传onSumResult给js" + "\")";
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
            return super.shouldOverrideUrlLoading(view, url);
        }

//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            return super.shouldOverrideUrlLoading(view, request);
//        }

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
