package com.sainti.blackcard.mhttp.okgo;

/**
 * Created by $ Chenguohui on 2017/6/6.
 */

public  interface OnNetResultListener {

    void  onSuccessfulListener(String stringResult, int resultCode);
    void onFailureListener(String errMsg, int resultCode);

}
