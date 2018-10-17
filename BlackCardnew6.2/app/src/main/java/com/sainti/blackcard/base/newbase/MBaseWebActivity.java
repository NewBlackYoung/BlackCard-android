package com.sainti.blackcard.base.newbase;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.sainti.blackcard.BuildConfig;
import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.FileUtils;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.sainti.blackcard.widget.LoadingView;

import java.io.File;

import butterknife.ButterKnife;

public abstract class MBaseWebActivity extends AppCompatActivity {
    private WebView webView;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> mUploadMessagesAboveL;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private Uri cameraUri;
    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_CHOOSE = 2;
    private File mPhotoFile;
    private WebSettings setting;
    private LoadingView loadingView;
    private String jsMethodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.statusBar));
        initData();
        webView = getWebView();
        useWebChromeClient();
        loadingView = new LoadingView(this);
        setting = webView.getSettings();
        jsMethodName = setJsMethodName();
        useWebViewClient();
        String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        String token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
        String url =  setWebUrl();
        if (!url.contains("token")){
            if (url.contains("?")) {
                url = url + "&uid=" + uid + "&token=" + token;
            } else {
                url = url + "?uid=" + uid + "&token=" + token;
            }
        }
        webView.loadUrl( url+ "?uid=" + uid + "&token=" + token);
    }

    public abstract int setLayout();
    // 数据
    protected abstract void initData();
    public abstract WebView getWebView();
    public abstract String setWebUrl();
    public abstract String setJsMethodName();

    private void useWebChromeClient() {
        // 将WebChromeClient传入WebView
        // 去处理与HTML相关的操作
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MBaseWebActivity.this);
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
                                        if (ContextCompat.checkSelfPermission(MBaseWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(MBaseWebActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

                                        } else {
                                            openCamera();
                                        }

//                                        openCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        if (ContextCompat.checkSelfPermission(MBaseWebActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(MBaseWebActivity.this,
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
                Toast.makeText(MBaseWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chosePicture();
            } else {
                // Permission Denied
                Toast.makeText(MBaseWebActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
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


    private void useWebViewClient() {
        // 将WebViewClient实例对象传入WebView
        // 去处理各种通知及请求事件
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
     /*           if (code.equals("2")) {
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
*/
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
                if (!url.contains("token")){
                    if (url.contains("?")) {
                        url = url + "&uid=" + uid + "&token=" + token;
                    } else {
                        url = url + "?uid=" + uid + "&token=" + token;
                    }
                }
                if (url.contains("Welfare")) {
                    toComment(url);
                } else {
                    webView.loadUrl(url);
                }

                return true;

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 网页开始加载，显示进度条
                String dir = MBaseWebActivity.this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
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
                            ///String call = "javascript：payInfoMessage()";
                            String call = "javascript："+jsMethodName+"()";
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

}
