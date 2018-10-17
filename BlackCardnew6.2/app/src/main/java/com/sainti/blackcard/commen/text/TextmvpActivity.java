package com.sainti.blackcard.commen.text;

import android.support.annotation.NonNull;

import com.sainti.blackcard.R;
import com.sainti.blackcard.base.newbase.MBaseMVPActivity;

public class TextmvpActivity extends MBaseMVPActivity<TextmvpView, TexcPresenter> implements TextmvpView {


    @Override
    public int setLayout() {
        return R.layout.activity_textmvp;
    }

    @Override
    protected void initView() {
        setPageTtile("MVP测试类");
    }

    @Override
    protected void initData() {
        getPresenter().getDatas(0);
    }

    @Override
    public void showDataFromNet(int requestCode, String list) {
        switch (requestCode) {
            case 0:
                break;
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showMessage(String msg) {

    }


    @NonNull
    @Override
    public TexcPresenter createPresenter() {
        return new TexcPresenter(this, this);
    }

}
