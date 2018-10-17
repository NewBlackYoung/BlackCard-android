package com.sainti.blackcard.mhttp.okgo;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.util.List;


/**
 * **********************************************************************
 * **********************************************************************
 */

public class OkGoUtils {
    private static OkGoUtils instance;
    private static final int CALLBACK_TOKEN = 9001;
    private String mUrl;
    private int mRequestCode;
    private HttpParams mHttpParams;
    private Context mContext;
    private OnNetResultListener mOnNetResultListener;

    // 私有构造方法
    private OkGoUtils() {
    }

    public static OkGoUtils getInstance() {
        if (null == instance) {
            synchronized (OkGoUtils.class) {
                if (null == instance) {
                    instance = new OkGoUtils();
                }
            }
        }
        return instance;
    }
    /**
     * post请求方法
     *
     * @param url                 请求地址
     * @param requestCode         结果码
     * @param httpParams          参数列表
     * @param context             上下文
     * @param onNetResultListener 请求回调
     */
    private void _post(String url, int requestCode, HttpParams httpParams, Context context, OnNetResultListener onNetResultListener) {

        try {
            NetResulyCallBack netResulyCallBack = new NetResulyCallBack(requestCode, onNetResultListener);
            OkGo.<String>post(url)
                    .tag(context)
                    .retryCount(3)
                    .params(httpParams)
                    .execute(netResulyCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _postfile(String url, String keyName, List<File> files, int requestCode, Context context, OnNetResultListener onNetResultListener) {
        try {
            NetResulyCallBack netResulyCallBack = new NetResulyCallBack(requestCode, onNetResultListener);
            files.size();
            OkGo.<String>post(url)//
                    .tag(context)//
                    .addFileParams(keyName, files)
                    // 这里支持一个key传多个文件
                    .execute(netResulyCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 对内取消当前页面网络请求
    private void _callOffNet(Context context) {
        OkGo.getInstance().cancelAll();
    }

    // 提供对外取消请求
    public void callOffNet(Context context) {
        _callOffNet(context);
    }

    // 提供对外请求
    public void post(String url, int requestCode, HttpParams httpParams, Context context, OnNetResultListener onNetResultListener) {
        _post(url, requestCode, httpParams, context, onNetResultListener);
    }
    // 提供对外请求
    public void postFile(String url, String keyName, List<File> files, int requestCode, Context context, OnNetResultListener onNetResultListener) {
       _postfile(url,keyName,files,requestCode,context,onNetResultListener);
    }
    private void _get(String url, int requestCode, Context context, OnNetResultListener onNetResultListener) {

        try {
            NetResulyCallBack netResulyCallBack = new NetResulyCallBack(requestCode, onNetResultListener);
            OkGo.<String>get(url)
                    .tag(context)
                    .retryCount(3)
                    .execute(netResulyCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 提供对外请求
    public void get(String url, int requestCode, Context context, OnNetResultListener onNetResultListener) {
        _get(url, requestCode, context, onNetResultListener);
    }

}
