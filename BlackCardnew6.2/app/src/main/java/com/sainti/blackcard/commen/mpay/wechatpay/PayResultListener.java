package com.sainti.blackcard.commen.mpay.wechatpay;

/**
 * Created by YuZhenpeng on 2018/6/20.
 */

public interface PayResultListener {
    public void onPaySuccess();

    public void onPayError();

    public void onPayCancel();
}