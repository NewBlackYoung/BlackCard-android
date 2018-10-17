package com.sainti.blackcard.chat.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/5/24.
 */

public class LinkWebActivity extends BaseTitleActivity {
    private WebView webView;
    private String code = "";
    private LoadingView loadingView;
    private String xh_url;
    @Override
    public int setLayout() {
        return R.layout.activity_linkweb;
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

        loadingView = new LoadingView(this);
        xh_url = getIntent().getStringExtra("url");
        useWebSettings();
        useWebChromeClient();
        webView.loadUrl(xh_url);
        useWebViewClient();
    }

    private void useWebSettings() {
        WebSettings settings = webView.getSettings();

        // 如果访问页面中要与JS进行交互，需要设置WebView可以支持JavaScript
        settings.setJavaScriptEnabled(true);

        // 支持插件
//        settings.setPluginState(WebSettings.PluginState.ON);

        // 设置自适应屏幕
        // 将图片调整到合适WebView的大小
        settings.setUseWideViewPort(true);
        // 缩放至屏幕大小
        settings.setLoadWithOverviewMode(true);

        // 缩放的操作
        // 支持缩放
        settings.setSupportZoom(true);
        // 设置内置的缩放控件
        settings.setBuiltInZoomControls(true);
        // 隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);

        // 其他操作
        // 设置WebView的缓存形式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // LOAD_CACHE_ONLY 不使用网络，只读取本地的缓存数据
        // LOAD_DEFAULT 默认，根据cache-control来决定是否从网络上获取
        // LOAD_NO_CACHE 不使用缓存
        // LOAD_CACHE_ELSE_NETWORK 只要本地有，无论是否过期都使用缓存中的数据

        // 设置是否可以访问文件
        settings.setAllowFileAccess(true);
        // 支持JavaScript打开新的窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
    }
    private void useWebChromeClient() {
        // 将WebChromeClient传入WebView
        // 去处理与HTML相关的操作
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(LinkWebActivity.this);
                b.setTitle("警告");
                b.setMessage(message);
                b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

            // 拦截网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            // 获取网页的加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });
    }
    private void useWebViewClient() {
        // 将WebViewClient实例对象传入WebView
        // 去处理各种通知及请求事件
        webView.setWebViewClient(new WebViewClient() {
            // 拦截WebView的加载过程
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 网页开始加载，显示进度条
                loadingView.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 网页加载完成，隐藏进度条
                loadingView.dismiss();
            }

            // 加载页面资源时调用， 每加载一个资源就会调用一次(图片)
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            // 404等错误
            // 加载页面的服务器出现错误时调用
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {


            }
        });
    }
}
