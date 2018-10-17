package com.sainti.blackcard.commen.mpay.wechatpay;

import android.app.Activity;
import android.content.Context;

import com.sainti.blackcard.coffee.coffeeorder.bean.WinXinPayBean;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.primary.WxBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;

/**
 * Created by YuZhenpeng on 2018/6/20.
 */

public class WachatPay {
    private static IWXAPI mWxApi;
    private static WachatPay instance;
    private static Context mContext;
    private static Activity mActivity;
    private final static ArrayList<PayResultListener> resultList = new ArrayList<>();

    private WachatPay() {
    }

    public static WachatPay getInstance(Context context) {
        if (instance == null) {
            instance = new WachatPay();
            mWxApi = WXAPIFactory.createWXAPI(context, SataicCode.WXAPP_ID);
            mWxApi.registerApp(SataicCode.WXAPP_ID);

        }

        mContext = context;
        mActivity = (Activity) mContext;
        return instance;
    }

/*
    *//**
     * 微信请求app服务器得到的回调结果
     *//*
    public void onSendTOWx(WxBean.DataBean dataBean) {
        if (mWxApi != null) {
            PayReq req = new PayReq();
            req.appId = dataBean.getAppid();// 微信开放平台审核通过的应用APPID
            req.partnerId = dataBean.getPartnerid();// 微信支付分配的商户号
            req.prepayId = dataBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
            req.nonceStr = dataBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
            req.timeStamp = dataBean.getTimestamp();
            ;// 时间戳，app服务器小哥给出
            req.packageValue = dataBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
            req.sign = dataBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            boolean sendFlag = mWxApi.sendReq(req);
            if (!sendFlag) {
                ToastUtils.show(mContext, "不能进行微信支付，请检查是否安装微信。");
            }
        }

    }*/

    public void onSendTOWx(WxBean.DataBean dataBean, PayResultListener listener) {
        if (!resultList.contains(listener)) {
            resultList.add(listener);
        }
        if (mWxApi != null) {
            PayReq req = new PayReq();
            req.appId = dataBean.getAppid();// 微信开放平台审核通过的应用APPID
            req.partnerId = dataBean.getPartnerid();// 微信支付分配的商户号
            req.prepayId = dataBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
            req.nonceStr = dataBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
            req.timeStamp = dataBean.getTimestamp();
            ;// 时间戳，app服务器小哥给出
            req.packageValue = dataBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
            req.sign = dataBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            boolean sendFlag = mWxApi.sendReq(req);
            if (!sendFlag) {
                ToastUtils.show(mContext, "不能进行微信支付，请检查是否安装微信。");
            }
        }

    }

    public void onSendTOWxofCoffee(WinXinPayBean.PayDataBean dataBean, PayResultListener listener) {
        if (!resultList.contains(listener)) {
            resultList.add(listener);
        }
        if (mWxApi != null) {
            PayReq req = new PayReq();
            req.appId = dataBean.getAppid();// 微信开放平台审核通过的应用APPID
            req.partnerId = dataBean.getPartnerid();// 微信支付分配的商户号
            req.prepayId = dataBean.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
            req.nonceStr = dataBean.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
            req.timeStamp = dataBean.getTimestamp();
            ;// 时间戳，app服务器小哥给出
            req.packageValue = dataBean.getPackageX();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
            req.sign = dataBean.getSign();// 签名，服务器小哥给出，他会根据：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=4_3指导得到这个
            boolean sendFlag = mWxApi.sendReq(req);
            if (!sendFlag) {
                ToastUtils.show(mContext, "不能进行微信支付，请检查是否安装微信。");
            }
        }

    }

    public void removeListener(PayResultListener listener) {
        if (resultList.contains(listener)) {
            resultList.remove(listener);
        }
    }

    public void addSuccess() {
        for (PayResultListener listener : resultList) {
            listener.onPaySuccess();
        }
    }

    public void addCancel() {
        for (PayResultListener listener : resultList) {
            listener.onPayCancel();
        }
    }

    public void addError() {
        for (PayResultListener listener : resultList) {
            listener.onPayError();
        }

    }
}
