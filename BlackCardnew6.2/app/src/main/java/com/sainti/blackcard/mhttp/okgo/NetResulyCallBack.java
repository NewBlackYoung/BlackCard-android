package com.sainti.blackcard.mhttp.okgo;


import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.text.SimpleDateFormat;
import java.util.Date;


public  class NetResulyCallBack extends StringCallback {
    private OnNetResultListener onNetResultListener;
    private int resultCode;
    // 日期格式
    private String DATE_FORMATE = "MM月dd日HH时mm分ss秒";
    // 开始时间
    public String startTimeStamp;
    // 结束时间
    public String endTimeStamp;
    // 开始时间
    private Date dateStartTime;
    // 结束时间
    private Date dateEndTime;
    //数据大小
    public String netSize;

    public NetResulyCallBack(int resultCode, OnNetResultListener onNetResultListener) {
        setResultCode(resultCode);
        setOnNetResultListener(onNetResultListener);
        //请求开始时间
        long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE);
        // edit by syh
        dateStartTime = new Date(currentTimestamp);
        this.startTimeStamp = sdf.format(dateStartTime);
    }

    public void setOnNetResultListener(OnNetResultListener onNetResultListener) {
        this.onNetResultListener = onNetResultListener;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public OnNetResultListener getOnNetResultListener() {
        return onNetResultListener;
    }


    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onCacheSuccess(Response<String> response) {
        super.onCacheSuccess(response);
    }

    @Override
    public void onSuccess(Response<String> response) {
//        LogUtil.d("服务器返回信息：", response.body());
        onNetResultListener.onSuccessfulListener(response.body(), resultCode);
    }

    @Override
    public void onError(Response<String> response) {

        // 无网络
        if (null == response || -1 == response.code()) {
            onNetResultListener.onFailureListener(response.message(), resultCode);
        } else if (404 == response.code()) {
            onNetResultListener.onFailureListener(response.message(), resultCode);
        }else {
            onNetResultListener.onFailureListener("服务器连接错误", resultCode);
        }
        super.onError(response);
    }

}
