package com.sainti.blackcard.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.meventbus.WxBindEventBean;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by YuZhenpeng on 2018/5/29.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mWeixinAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, SataicCode.LOGIN_APPID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {

        SendAuth.Resp re = ((SendAuth.Resp) baseResp);
        String code = re.code;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                ToastUtils.show(this, "授权成功");
                WxBindEventBean wxBindEventBean = new WxBindEventBean("1");
                wxBindEventBean.setMessage(code);
                EventBus.getDefault().post(wxBindEventBean);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.show(this, "取消授权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtils.show(this, "拒绝授权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                ToastUtils.show(this, "失败了");
                finish();
                break;
            default:
                finish();
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
