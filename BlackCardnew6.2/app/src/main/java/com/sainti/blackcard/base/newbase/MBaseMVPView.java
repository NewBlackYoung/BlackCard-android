package com.sainti.blackcard.base.newbase;

/**
 * Created by guohuichen on 2018/9/27.
 */

public interface MBaseMVPView extends IBaseView {
    /**显示数据*/
    void showDataFromNet(String list);

    /**无数据*/
    void showEmpty();

    /**检测数据*/
    void showMessage(String msg);
}
