package com.sainti.blackcard.commen.mpay.alipay;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.sainti.blackcard.mtuils.MLog;

import java.lang.ref.WeakReference;
import java.util.Map;

public class Malipay {
    private WeakReference<Activity> mActivity;
    private static final int SDK_PAY_FLAG = 1;
    private OnAlipayListener mListener;

    public void setmListener(OnAlipayListener mListener) {
        this.mListener = mListener;
    }

    public Malipay(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }
    public Malipay(Activity activity,OnAlipayListener mListener) {
        mActivity = new WeakReference<Activity>(activity);
        this.mListener = mListener;
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                String resultInfo = payResult.getResult();
                String resultStatus = payResult.getResultStatus();
                MLog.d("aaaaaaaaaaaaaaaaaaaaaaaaaa",resultStatus);
                // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                if (TextUtils.equals(resultStatus, "9000")) {
                    if (mListener != null) mListener.onSuccess();
                } else {
                    // 判断resultStatus 为非“9000”则代表可能支付失败
                    // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                    // 最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "8000")) {
                        if (mListener != null) mListener.onWait();
                    } else {
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        if (mListener != null) mListener.onCancel();
                    }
                }
            }
        }
    };

    /**
     * 支付
     */
    public void pay(final String orderInfo) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Activity activity = mActivity.get();
                if (activity == null) return;
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 支付回调接口
     *
     * @author lenovo
     */
    public interface OnAlipayListener {
        /**
         * 支付成功
         */
        void onSuccess();

        /**
         * 支付取消
         */
        void onCancel();

        /**
         * 等待确认
         */
        void onWait();
    }
}
