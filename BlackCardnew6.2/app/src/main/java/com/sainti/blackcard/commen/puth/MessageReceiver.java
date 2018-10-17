package com.sainti.blackcard.commen.puth;


import android.content.Context;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * Created by YuZhenpeng on 2018/5/9.
 */

public class MessageReceiver extends XGPushBaseReceiver {

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        //MLog.d("recever", "xgPushRegisterResult" + xgPushRegisterResult);
    }

    @Override
    public void onUnregisterResult(Context context, int i) {
        //MLog.d("recever", "onUnregisterResult" + i);
    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {
       // MLog.d("recever", "onSetTagResult" + s);
    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {
       // MLog.d("recever", "onDeleteTagResult" + s);
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        //MLog.d("recever", "xgPushTextMessage" + xgPushTextMessage);
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
       // MLog.d("recever", "xgPushClickedResult" + xgPushClickedResult);
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
        //MLog.d("recever", "xgPushShowedResult" + xgPushShowedResult);

    }
}
