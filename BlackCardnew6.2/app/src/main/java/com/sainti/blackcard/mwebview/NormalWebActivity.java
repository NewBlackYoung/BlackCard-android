package com.sainti.blackcard.mwebview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.sainti.blackcard.BuildConfig;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.commen.mpay.wechatpay.PayResultListener;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.my.taskcenter.activity.TaskCenterAcvitity;
import com.sainti.blackcard.goodthings.activity.GoodthingsListActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.ConvertUtil;
import com.sainti.blackcard.mtuils.FileUtils;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.bean.ShengjiBean;
import com.sainti.blackcard.mwebview.primary.WxBean;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NormalWebActivity extends BaseTitleActivity implements View.OnClickListener, View.OnLongClickListener, OnNetResultListener, PayResultListener {
    private WebView webView;
    private LoadingView loadingView;
    private String code = "";
    private View v_beiijing;
    private RelativeLayout lay_get;
    private ImageView iv_erweima, yoashishangh, yaoshito;
    private TextView tv_zhaohuan, huoquhjei;
    private String xh_url;
    private Bitmap bitmapssss;
    private String title;
    private String shareUrl;
    private WebSettings setting;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessagesAboveL;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private Uri cameraUri;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_CHOOSE = 2;
    private File mPhotoFile;
    private LinearLayout ll_heiyaoshi;
    private String uids;
    private String tokens;
    private String orderSn;

    @Override
    public int setLayout() {
        return R.layout.activity_normal_web;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.web_Detail);
        v_beiijing = bindView(R.id.v_beiijing);
        lay_get = bindView(R.id.lay_get);
        iv_erweima = bindView(R.id.iv_erweima);
        tv_zhaohuan = bindView(R.id.tv_zhaohuan);
        ll_heiyaoshi = bindView(R.id.ll_heiyaoshi);
        yoashishangh = bindView(R.id.yoashishangh);
        yaoshito = bindView(R.id.yaoshito);
        huoquhjei = bindView(R.id.huoquhjei);
       // PayListenerUtils.getInstance(this).addListener(this);
    }

    @Override
    protected void initEvent() {
        v_beiijing.setOnClickListener(this);
        lay_get.setOnClickListener(this);
        lay_get.setOnLongClickListener(this);
        tv_zhaohuan.setOnClickListener(this);
        yoashishangh.setOnClickListener(this);
        yaoshito.setOnClickListener(this);
        huoquhjei.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getNewShare();
        code = getIntent().getStringExtra("code");
        loadingView = new LoadingView(this);
        xh_url = getIntent().getStringExtra("url");
        setting = webView.getSettings();
        /**
         * 设置js调用Android的接口
         */
        webView.addJavascriptInterface(new NormalWebActivity.JsInteration(), "control");
        useWebChromeClient();
        String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        String token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
        if (xh_url.contains("?")) {
            xh_url = xh_url + "&uid=" + uid + "&token=" + token;
        } else {
            xh_url = xh_url + "?uid=" + uid + "&token=" + token;
        }
        webView.loadUrl(xh_url);
       // webView.loadUrl(xh_url);
        useWebViewClient();
        if (code.equals("1")) {
            setBaseRightIcon1(R.mipmap.ic_share, new OnClickRightIcon1CallBack() {


                @Override
                public void clickRightIcon1() {
                    if ((PackageManager.PERMISSION_GRANTED ==
                            getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "com.sainti.blackcard"))) {
                        if (shareUrl == null || shareUrl.equals("")) {
                            ToastUtils.show(NormalWebActivity.this, "请求数据中，请稍后");
                        } else {
                            bitmapssss = generateBitmap(shareUrl, 165, 165);
                            iv_erweima.setImageBitmap(bitmapssss);
                            lay_get.setVisibility(View.VISIBLE);
                            v_beiijing.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        startActivity(intent);
                        ToastUtils.show(NormalWebActivity.this, "请打开存储权限");
                    }
                    v_beiijing.setVisibility(View.VISIBLE);
                    lay_get.setVisibility(View.VISIBLE);
                }
            });
            setTitle("邀请好礼");
            tv_zhaohuan.setVisibility(View.VISIBLE);
        }
        if (code.equals("3")) {
            setTitle("更多额度");
        }
        if (code.equals("4")) {
            setTitle("青年黑卡信用卡申请");
        }
        if (code.equals("5")) {
            setTitle("青年黑卡信贷特权");
        }
        if (code.equals("6")) {
            setTitle("青年黑卡信用卡申请");
        }
        if (code.equals("7")) {
            setTitle("青年黑卡黑卡补换");
        }
        if (code.equals("8")) {
            setTitle("青年黑卡兑换商城");
        }
        if (code.equals("9")) {
            ll_heiyaoshi.setVisibility(View.VISIBLE);
            huoquhjei.setVisibility(View.VISIBLE);
            setTitle("青年黑卡黑钥匙");
        } else {
            ll_heiyaoshi.setVisibility(View.GONE);
            huoquhjei.setVisibility(View.GONE);
        }
        if (code.equals("10")) {
            setTitle("物流");
        }
        if (code.equals("11")) {
            setTitle("违章代缴");
        }
        uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
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

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.v_beiijing:
                lay_get.setVisibility(View.GONE);
                v_beiijing.setVisibility(View.GONE);
                break;
            case R.id.lay_get:
                return;
            case R.id.tv_zhaohuan:
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "tequanxaingqingye");
                    MobclickAgent.onEvent(NormalWebActivity.this, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    //已经登录，可以直接进入会话界面
                    Intent intent = new IntentBuilder(context)
                            .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                            .build();
                    startActivity(intent);
                    SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, false).commit();
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(context, "登陆失败");
                }
              /*  Intent intent = new Intent(toString(), ChatRoomActivity.class);
                startActivity(intent);*/
                break;
            case R.id.yoashishangh:
                Intent intents = new Intent(this, NormalWebActivity.class);
                intents.putExtra("code", "8");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/Key/change/?uid=" + uids + "&token=" + tokens);
                startActivity(intents);
                break;
            case R.id.yaoshito:
                startActivity(new Intent(this, GoodthingsListActivity.class));
                break;
            case R.id.huoquhjei:
                startActivity(new Intent(this, TaskCenterAcvitity.class));
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {
        saveFile(bitmapssss);
        return false;
    }

    private final static String ALBUM_PATH = Environment
            .getExternalStorageDirectory() + "/download_test/";
    private String mFileName = "test.jpg";

    public void saveFile(Bitmap bm) {
        try {
            File dirFile = new File(ALBUM_PATH);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File myCaptureFile = new File(ALBUM_PATH + mFileName);
            BufferedOutputStream bos = null;
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            Uri uri = Uri.fromFile(myCaptureFile);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            ToastUtils.show(this, "保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getNewShare() {
        TurnClassHttp.getNewShare(1, this, this);

    }


    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    private void useWebChromeClient() {
        // 将WebChromeClient传入WebView
        // 去处理与HTML相关的操作
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(NormalWebActivity.this);
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
        });


    }


    private void useWebViewClient() {
        // 将WebViewClient实例对象传入WebView
        // 去处理各种通知及请求事件
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (code.equals("2")) {
                    if (url == null) return false;
                    if (url.startsWith("http://") || url.startsWith("https://")) {
                        return super.shouldOverrideUrlLoading(view, url);
                    } else {
                        try {
                            Log.i("wufhsdbfsd", "url=" + url);

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                            return true;

                        } catch (Exception e) {
                            return false;
                        }
                    }
                } else {

                    String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
                    String token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
                    if (url.contains("?")) {
                        url = url + "&uid=" + uid + "&token=" + token;
                    } else {
                        url = url + "?uid=" + uid + "&token=" + token;
                    }
                    webView.loadUrl(url);
                    return true;
                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 网页开始加载，显示进度条
                String dir = NormalWebActivity.this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
                setting.setJavaScriptEnabled(true);//true加载JavaScript
                setting.setJavaScriptCanOpenWindowsAutomatically(true);
                setting.setCacheMode(WebSettings.LOAD_DEFAULT);//是否覆盖缓存
                setting.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                setting.setAppCacheEnabled(true);
                setting.setDomStorageEnabled(true);
                setting.setAllowFileAccess(true);
                setting.setSupportMultipleWindows(true);
                setting.setDatabaseEnabled(true);
                setting.setBlockNetworkImage(false);//true不下载图片
                setting.setCacheMode(WebSettings.LOAD_DEFAULT);
                setting.setSavePassword(false);
                setting.setGeolocationEnabled(true);
                setting.setLoadWithOverviewMode(true);

                //设置定位的数据库路径
                setting.setGeolocationDatabasePath(dir);
                loadingView.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 网页加载完成，隐藏进度条
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
            public void onReceivedError(WebView view, WebResourceRequest
                    request, WebResourceError error) {
                loadingView.dismiss();

            }
        });
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
                                        if (ContextCompat.checkSelfPermission(NormalWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(NormalWebActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

                                        } else {
                                            openCamera();
                                        }

//                                        openCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        if (ContextCompat.checkSelfPermission(NormalWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(NormalWebActivity.this,
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

    @Override
    public void onPaySuccess() {
        showToast("支付成功");
        String url = " http://www.qing-hei.com/mobile.php/NewOrder/success_pay/order_sn/" + orderSn;
        webView.loadUrl(url);

    }

    @Override
    public void onPayError() {
        showToast("支付失败");
    }

    @Override
    public void onPayCancel() {

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                // Permission Denied
                Toast.makeText(NormalWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chosePicture();
            } else {
                // Permission Denied
                Toast.makeText(NormalWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
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


    /**
     * js调用和传递的数据
     * 类JsInteration里面的方法被js调用
     * 以下三个接口都是提供给js调用的接口
     * -------注意, 一定要加 @JavascriptInterface 在每个方法上面,否则无法执行
     */
    public class JsInteration {
        @JavascriptInterface
        public void payInfoMessage(String payInfoJson) {
            ShengjiBean shengjiBean = GsonSingle.getGson().fromJson(payInfoJson, ShengjiBean.class);
            String price = shengjiBean.getOrderprice();
            orderSn = shengjiBean.getOrdersn();
            String orderName = shengjiBean.getOrdername();
            float lastPrcies = ConvertUtil.convertToFloat(price) * 100;
            int a = (int) lastPrcies;
            TurnClassHttp.getApporder(a + "", orderSn, orderName, "android", ConvertUtil.getTime(), 2, NormalWebActivity.this, NormalWebActivity.this);


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

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        switch (resultCode) {
            case 1:
                try {
                    JSONObject object = new JSONObject(stringResult);
                    shareUrl = object.getString("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                WxBean wxPayBean = GsonSingle.getGson().fromJson(stringResult, WxBean.class);
                WachatPay.getInstance(this).onSendTOWx(wxPayBean.getData(),this);
                break;
        }


    }
}
