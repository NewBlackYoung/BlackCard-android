package com.sainti.blackcard.commen.text;

import com.sainti.blackcard.base.newbase.IBaseView;

/**
 * Created by guohuichen on 2018/9/27.
 */

public interface TextmvpView extends IBaseView {
    /**显示数据*/
    void showDataFromNet(int requestCode,String list);

    /**无数据*/
    void showEmpty();

    /**检测数据*/
    void showMessage(String msg);
}
