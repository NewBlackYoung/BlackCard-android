package com.sainti.blackcard.mwebview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.BuildConfig;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.FileUtils;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.primary.MembershipUpgradeActivity;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LookMemberActivity extends BaseTitleActivity {


    private WebView flxq_WebView;
    private WebSettings setting;
    private String wel_url;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessagesAboveL;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private Uri cameraUri;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_CHOOSE = 2;
    private File mPhotoFile;

    private boolean isXiangji = false;
    @Override
    public int setLayout() {
        return R.layout.activity_look_member;
    }

    @Override
    protected void initView() {
        flxq_WebView = bindView(R.id.flxq_WebView);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        setTitle("青年黑卡会籍");
        String title = "";
        title = getIntent().getStringExtra("title");
        if (!title.equals("")) {
            setTitle(title);
        }
        getBaseBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        wel_url = getIntent().getStringExtra("wel_url");
        getWebView();
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) || ContextCompat.checkSelfPermission(this.getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果没有授权，则请求授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1311);
            //String数组中为申请的权限，第一个是相机，第二个为修改内存，最后的参数即为申请授权的返回值，我设置的1311
            //showToast("已经打开权限");
            isXiangji = false;
        } else {
            isXiangji = true;
        }
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
        flxq_WebView.addJavascriptInterface(new LookMemberActivity.JsInteration(), "control");
        /**
         * 提供网页加载各阶段的通 知
         * */
        flxq_WebView.setWebViewClient(new LookMemberActivity.MyWebViewClient());
        /**
         * 提供网页加载过程中提供的数据内容
         * */
        flxq_WebView.setWebChromeClient(new LookMemberActivity.MyWebChromeClient());

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
            MemberTypeBean memberTypeBean = GsonSingle.getGson().fromJson(payInfoJson, MemberTypeBean.class);

            if (memberTypeBean.getType().equals("level")) {
                startActivity(new Intent(LookMemberActivity.this, MembershipUpgradeActivity.class));
            } else {
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    String type = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TYPETHREE, "");
                    toChat(type);
                    SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPETHREE, "100").commit();
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(context, "登陆失败");
                }
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

    private void toChat(String type) {
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("rukou", "chakanhuijiye");
        MobclickAgent.onEvent(LookMemberActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
        //已经登录，可以直接进入会话界面
        Intent intents = new IntentBuilder(context)
                .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                .build()
                .putExtra("type", "");
        startActivity(intents);
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
                        String call = "javascript：levelInfoMessage()";
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

        //配置权限（同样在WebChromeClient中实现）
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean f = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISFINISH, false);
        if (f) {
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISFINISH, false).commit();
            finish();
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
                                        if (!isXiangji) {
                                            showToast("请前往设置打开手访问手机多媒体权限");
                                            return;
                                        }

                                       openCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        if (!isXiangji) {
                                            showToast("请前往设置打开手访问手机多媒体权限");
                                            return;
                                        }
                                       chosePicture();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
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

    /**
     * 本地相册选择图片
     */
    private void chosePicture() {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, REQUEST_CHOOSE);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1311:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    isXiangji = true;
                } else {
                    isXiangji = false;

                }
                break;
            default:
        }
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

}
