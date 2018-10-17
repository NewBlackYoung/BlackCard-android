package com.sainti.blackcard.base.newbase;


public interface IBasePresenter<V extends IBaseView> {

    /**绑定接口*/
    void attachView(V view);

    /**释放接口*/
    void detachView();

}
