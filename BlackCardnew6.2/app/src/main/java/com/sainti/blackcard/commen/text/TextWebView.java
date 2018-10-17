package com.sainti.blackcard.commen.text;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.newbase.MBaseWebActivity;
import com.sainti.blackcard.mtuils.ToastUtils;

import butterknife.BindView;

public class TextWebView extends MBaseWebActivity {


    @BindView(R.id.webView)
    WebView webView;
    private String url;

    @Override
    public int setLayout() {
        return R.layout.activity_text_web_view;
    }

    @Override
    protected void initData() {

        url = getIntent().getStringExtra("wel_url");
        //设置js调用Android的接口
        webView.addJavascriptInterface(new TextWebView.JsInteration(), "control");
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public String setWebUrl() {
        return url;
    }

    /* 这个方法名字一定要和后台配置的一致，下面的自己设置的回调方法名也要一制*/
    @Override
    public String setJsMethodName() {
        return "payInfoMessage";
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
            ToastUtils.show(TextWebView.this, "dddddd");
        }


        @JavascriptInterface
        public void checkPaymentApp(String validate) {
        }

        @JavascriptInterface
        public void onSumResult(int result) {
        }
    }
}
