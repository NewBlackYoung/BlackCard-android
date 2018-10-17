package com.sainti.blackcard.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mpay.wechatpay.WachatPay;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mtuils.MLog;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, SataicCode.WXAPP_ID);
        api.registerApp(SataicCode.WXAPP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        MLog.d("earrodddddd", "错误码" + resp.errCode + "");
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                //  Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new NormalEventBean("0"));
                //PayListenerUtils.getInstance(this).addSuccess();
                WachatPay.getInstance(this).addSuccess();
            } else if (resp.errCode == -2) {
                //Toast.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new NormalEventBean("-2"));
                //PayListenerUtils.getInstance(this).addCancel();
                WachatPay.getInstance(this).addCancel();

            } else {
                //Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new NormalEventBean("1"));
                //PayListenerUtils.getInstance(this).addError();
                WachatPay.getInstance(this).addError();
            }
            finish();
        }
    }

}