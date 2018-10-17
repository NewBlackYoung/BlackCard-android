package com.sainti.blackcard.commen.text;

import android.app.Activity;

import com.sainti.blackcard.base.newbase.IBasePresenter;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by guohuichen on 2018/9/27.
 */

public class TexcPresenter implements IBasePresenter<TextmvpView>, OnNetResultListener {
    private TextmvpView textmvpView;
    private Activity activity;
    private final LoadingView loadingView;

    public TexcPresenter(TextmvpView textmvpView, Activity activity) {
        attachView(textmvpView);
        loadingView = new LoadingView(activity);
        this.activity = activity;
    }

    @Override
    public void attachView(TextmvpView view) {
        this.textmvpView = view;
    }

    @Override
    public void detachView() {
        this.textmvpView = null;
    }

    public void getDatas(int requestCode) {
        // textmvpView.showLoading();
        loadingView.show();
        TurnClassHttp.getPrivilegeDate(requestCode, activity, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        textmvpView.showDataFromNet(resultCode, stringResult);
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        textmvpView.showMessage(errMsg);
        loadingView.dismiss();
    }
}
